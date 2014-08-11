package net.pkhapps.fenix.core.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Base class for entities. Entities are immutable, use {@link net.pkhapps.fenix.core.entity.AbstractEntity.Builder}s to modify them (or actually, make copies of them).
 */
@MappedSuperclass
public abstract class AbstractEntity extends AbstractPersistable<Long> {

    @Column(name = "rev", nullable = false)
    private Long optLockVersion;

    protected AbstractEntity() {
    }

    protected Long getOptLockVersion() {
        return optLockVersion;
    }

    protected void setOptLockVersion(Long optLockVersion) {
        this.optLockVersion = optLockVersion;
    }

    /**
     * Clears the persistence ID information from the entity.
     */
    protected void setNew() {
        setId(null);
        setOptLockVersion(null);
    }

    // TODO Add auditing support

    public static abstract class AbstractCopyFunction<ENTITY extends AbstractEntity, BUILDER extends Builder<ENTITY, BUILDER>> {

        private final Class<BUILDER> builderClass;
        private final boolean asNew;

        protected AbstractCopyFunction(Class<BUILDER> builderClass, boolean asNew) {
            this.builderClass = Objects.requireNonNull(builderClass);
            this.asNew = asNew;
        }

        /**
         * Creates a new builder instance initialized with the specified entity.
         */
        protected BUILDER createBuilder(ENTITY entity) {
            try {
                return builderClass.getConstructor(entity.getClass()).newInstance(entity);
            } catch (Exception ex) {
                throw new UnsupportedOperationException("Could not create new Builder instance");
            }
        }

        /**
         * Copies the specified entity.
         */
        protected ENTITY copy(ENTITY entity) {
            final BUILDER builder = createBuilder(entity);
            if (asNew) {
                builder.asNew();
            }
            return builder.build();
        }
    }

    /**
     * A consumer that copies all accepted entities to a {@link Collection}. The entities can either be copied
     * as-is or as new entities (with stripped ID information).
     */
    public static class CopyConsumer<ENTITY extends AbstractEntity, BUILDER extends Builder<ENTITY, BUILDER>> extends AbstractCopyFunction<ENTITY, BUILDER> implements Consumer<ENTITY> {

        private final Collection<ENTITY> destination;

        public CopyConsumer(Class<BUILDER> builderClass, Collection<ENTITY> destination, boolean asNew) {
            super(builderClass, asNew);
            this.destination = Objects.requireNonNull(destination);
        }

        public static <ENTITY extends AbstractEntity, BUILDER extends Builder<ENTITY, BUILDER>> CopyConsumer<ENTITY, BUILDER> copyAsIs(Class<BUILDER> builderClass, Collection<ENTITY> destination) {
            return new CopyConsumer<>(builderClass, destination, false);
        }

        public static <ENTITY extends AbstractEntity, BUILDER extends Builder<ENTITY, BUILDER>> CopyConsumer<ENTITY, BUILDER> copyAsNew(Class<BUILDER> builderClass, Collection<ENTITY> destination) {
            return new CopyConsumer<>(builderClass, destination, true);
        }

        @Override
        public void accept(ENTITY entity) {
            destination.add(copy(entity));
        }
    }

    /**
     * A function that copies the entity and returns it. An entity can either be copied as-is or as a new entity (with stripped ID information).
     */
    public static class CopyFunction<ENTITY extends AbstractEntity, BUILDER extends Builder<ENTITY, BUILDER>> extends AbstractCopyFunction<ENTITY, BUILDER> implements Function<ENTITY, ENTITY> {

        public CopyFunction(Class<BUILDER> builderClass, boolean asNew) {
            super(builderClass, asNew);
        }

        public static <ENTITY extends AbstractEntity, BUILDER extends Builder<ENTITY, BUILDER>> CopyFunction<ENTITY, BUILDER> copyAsIs(Class<BUILDER> builderClass) {
            return new CopyFunction<>(builderClass, false);
        }

        public static <ENTITY extends AbstractEntity, BUILDER extends Builder<ENTITY, BUILDER>> CopyFunction<ENTITY, BUILDER> copyAsNew(Class<BUILDER> builderClass) {
            return new CopyFunction<>(builderClass, true);
        }

        @Override
        public ENTITY apply(ENTITY entity) {
            return copy(entity);
        }
    }

    /**
     * Base class for builders that are used to modify entities.
     */
    public static abstract class Builder<ENTITY extends AbstractEntity, BUILDER extends Builder<ENTITY, BUILDER>> {

        private ENTITY instance;

        /**
         * Creates a builder for an empty entity instance.
         */
        public Builder() {
            instance = newInstance();
        }

        /**
         * Creates a builder for an entity instance that is a copy of the specified original.
         */
        public Builder(ENTITY original) {
            this();
            Objects.requireNonNull(original);
            instance.setId(original.getId());
            instance.setOptLockVersion(original.getOptLockVersion());
        }

        /**
         * Removes the persistence information from the new entity, so that its {@link AbstractEntity#isNew()}
         * method will return true.
         */
        public BUILDER asNew() {
            setNew(instance);
            return myself();
        }

        /**
         * Makes is possible for a builder to invoke {@link net.pkhapps.fenix.core.entity.AbstractEntity#setNew()} on any
         * subclass of {@link AbstractEntity}.
         */
        protected void setNew(AbstractEntity entity) {
            entity.setNew();
        }

        /**
         * Returns the actual entity instance.
         */
        protected ENTITY getInstance() {
            Assert.notNull(instance, "the builder cannot be used after the entity has been built");
            return instance;
        }

        /**
         * Creates a new entity instance.
         */
        protected abstract ENTITY newInstance();

        /**
         * Wrapper method for {@code this}.
         */
        @SuppressWarnings("unchecked")
        protected BUILDER myself() {
            return (BUILDER) this;
        }

        /**
         * Builds and returns the entity, and invalidates the builder.
         */
        public ENTITY build() {
            Assert.notNull(instance, "the build() method can only be called once");
            validateBeforeBuild();
            final ENTITY newInstance = getInstance();
            this.instance = null;
            return newInstance;
        }

        /**
         * Called by {@link #build()} to perform any last minute validations before the
         * entity is officially built and returned. Validation is performed on the instance
         * returned by {@link #getInstance()}. The default implementation does nothing, subclasses may override.
         */
        protected void validateBeforeBuild() {
        }
    }
}
