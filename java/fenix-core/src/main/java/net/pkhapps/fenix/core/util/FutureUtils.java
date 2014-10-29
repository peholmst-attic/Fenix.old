package net.pkhapps.fenix.core.util;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Utilities for working with {@link java.util.concurrent.Future}s.
 */
public final class FutureUtils {

    private final static ScheduledExecutorService futureObserverThreadPool = Executors.newScheduledThreadPool(10);

    private static final long FUTURE_OBSERVER_POLL_DELAY = 50;

    private FutureUtils() {
    }

    /**
     * TODO Document me!
     *
     * @param future
     * @param observer
     * @param <V>
     */
    public static <V> void observe(Future<V> future, FutureObserver<V> observer) {
        final Runnable task = new Runnable() {
            @Override
            public void run() {
                if (future.isCancelled()) {
                    observer.onCancelled();
                } else if (future.isDone()) {
                    try {
                        observer.onDone(future.get());
                    } catch (InterruptedException ex) {
                        observer.onError(ex);
                    } catch (ExecutionException ex) {
                        observer.onError(ex.getCause());
                    }
                } else {
                    futureObserverThreadPool.schedule(this, FUTURE_OBSERVER_POLL_DELAY, TimeUnit.MILLISECONDS);
                }
            }
        };
        task.run();
    }

    /**
     * TODO Document me!
     *
     * @param <V>
     */
    public interface FutureObserver<V> {

        void onDone(V value);

        default void onCancelled() {
        }

        default void onError(Throwable throwable) {
            throw new RuntimeException("An exception occurred while waiting for the future value", throwable);
        }
    }

}
