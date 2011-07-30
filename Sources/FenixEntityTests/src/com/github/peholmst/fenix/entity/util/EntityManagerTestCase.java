package com.github.peholmst.fenix.entity.util;

import static org.eclipse.persistence.config.PersistenceUnitProperties.DDL_DATABASE_GENERATION;
import static org.eclipse.persistence.config.PersistenceUnitProperties.DDL_GENERATION_MODE;
import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_DRIVER;
import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_PASSWORD;
import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_URL;
import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_USER;
import static org.eclipse.persistence.config.PersistenceUnitProperties.LOGGING_LEVEL;
import static org.eclipse.persistence.config.PersistenceUnitProperties.TARGET_DATABASE;
import static org.eclipse.persistence.config.PersistenceUnitProperties.TARGET_SERVER;
import static org.eclipse.persistence.config.PersistenceUnitProperties.TRANSACTION_TYPE;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.sql.DataSource;

import org.eclipse.persistence.config.TargetDatabase;
import org.eclipse.persistence.config.TargetServer;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public abstract class EntityManagerTestCase {

    public static final String H2_JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";

    public static final String SEQUENCE_NAME = "mySequence";

    protected static EntityManagerFactory entityManagerFactory;

    protected static EntityManager entityManager;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @BeforeClass
    public static void setupEntityManager() throws SQLException {
        prepareDataSource();

        Map properties = new HashMap();
        properties.put(TRANSACTION_TYPE,
                PersistenceUnitTransactionType.RESOURCE_LOCAL.name());
        properties.put(JDBC_DRIVER, "org.h2.Driver");
        properties.put(JDBC_URL, H2_JDBC_URL);
        properties.put(JDBC_USER, "");
        properties.put(JDBC_PASSWORD, "");
        properties.put(LOGGING_LEVEL, "FINE");
        properties.put(TARGET_SERVER, TargetServer.None);
        properties.put(TARGET_DATABASE, TargetDatabase.Auto);
        properties.put(DDL_GENERATION_MODE, DDL_DATABASE_GENERATION);

        entityManagerFactory = Persistence.createEntityManagerFactory(
                "FenixEntityPU", properties);
        entityManager = entityManagerFactory.createEntityManager();
    }

    protected static void prepareDataSource() throws SQLException {
        final JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(H2_JDBC_URL);
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.execute("DROP SEQUENCE IF EXISTS " + SEQUENCE_NAME);
        statement.execute("CREATE SEQUENCE " + SEQUENCE_NAME
                + " START WITH 1 INCREMENT BY 10");
        statement.close();
        connection.close();
        final DataSourceSequence sequence = new DataSourceSequence(
                SEQUENCE_NAME) {

            @Override
            protected DataSource lookupDataSource() {
                return dataSource;
            }
        };
        IdGenerator.setSequence(sequence);
    }

    @AfterClass
    public static void closeEntityManager() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
