/* Copyright (c) 2011 Danish Maritime Authority.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.maritimecloud.internal.mms.client;

import static java.util.Objects.requireNonNull;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import net.maritimecloud.internal.net.util.DefaultEndpointInvocationFuture;
import net.maritimecloud.internal.util.ConcurrentWeakHashSet;
import net.maritimecloud.net.mms.MmsClientClosedException;
import net.maritimecloud.util.Binary;

import org.cakeframework.container.RunOnStart;
import org.cakeframework.container.RunOnStop;

/**
 *
 * @author Kasper Nielsen
 */
public class MmsThreadManager {

    /** The prefix of each thread created by the client. */
    static final String THREAD_PREFIX = "MMSClient";

    /** An {@link ExecutorService} for running various tasks. */
    final ThreadPoolExecutor es = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>(), new DefaultThreadFactory("GeneralPool", Executors.defaultThreadFactory()));

    /** A list of all outstanding futures. Is used to cancel each future in case of shutdown. */
    final ConcurrentWeakHashSet<DefaultEndpointInvocationFuture<?>> futures = new ConcurrentWeakHashSet<>();

    /** A {@link ScheduledExecutorService} for scheduling various tasks. */
    final ScheduledThreadPoolExecutor ses = new ScheduledThreadPoolExecutor(2, new DefaultThreadFactory("Scheduler",
            Executors.defaultThreadFactory()));

    public <T> DefaultEndpointInvocationFuture<T> create(Binary messageId) {
        DefaultEndpointInvocationFuture<T> t = new DefaultEndpointInvocationFuture<>(getScheduler(),
                new CompletableFuture<T>(), messageId);
        futures.add(t);
        return t;
    }


    public void execute(Runnable r) {
        es.execute(r);
    }


    /**
     * Returns the scheduled executor.
     *
     * @return the scheduled executor
     */
    public ScheduledThreadPoolExecutor getScheduler() {
        return ses;
    }

    /**
     * @param command
     * @param initialDelay
     * @param period
     * @param unit
     * @return
     * @see java.util.concurrent.ScheduledThreadPoolExecutor#scheduleAtFixedRate(java.lang.Runnable, long, long,
     *      java.util.concurrent.TimeUnit)
     */
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        return ses.scheduleAtFixedRate(command, initialDelay, period, unit);
    }

    /**
     * @param command
     * @param initialDelay
     * @param delay
     * @param unit
     * @return
     * @see java.util.concurrent.ScheduledThreadPoolExecutor#scheduleWithFixedDelay(java.lang.Runnable, long, long,
     *      java.util.concurrent.TimeUnit)
     */
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
        return ses.scheduleWithFixedDelay(command, initialDelay, delay, unit);
    }

    @RunOnStart
    public void start() {
        // Clean up weak references
        ses.schedule(new Runnable() {
            public void run() {
                futures.cleanup();
            }
        }, 1, TimeUnit.MINUTES);
    }

    /**
     * @param runnable
     */
    public void startCloseThread(Runnable runnable) {
        Thread t = new Thread(runnable);
        t.setName(THREAD_PREFIX + "-ClosingThread");
        t.setDaemon(true);
        t.start();
    }

    public void startConnectingManager(Runnable runnable) {
        Thread t = new Thread(runnable);
        t.setName(THREAD_PREFIX + "-ConnectionManager");
        t.setDaemon(true);
        t.start();
    }

    public void startConnectingThread(Runnable runnable) {
        Thread t = new Thread(runnable);
        t.setName(THREAD_PREFIX + "-ConnectingThread");
        t.setDaemon(true);
        t.start();
    }

    public void startDisconnectingThread(Runnable runnable) {
        Thread t = new Thread(runnable);
        t.setName(THREAD_PREFIX + "-DisconnectingThread");
        t.setDaemon(true);
        t.start();
    }

    public void startWorkerThread(Runnable runnable) {
        Thread t = new Thread(runnable);
        t.setName(THREAD_PREFIX + "-MessageProcessor");
        t.setDaemon(true);
        t.start();
    }

    @RunOnStop
    public void stop() {
        es.shutdown();
        ses.shutdown();
        for (DefaultEndpointInvocationFuture<?> f : futures) {
            if (!f.isDone()) {
                f.completeExceptionally(new MmsClientClosedException("OOps"));
            }
        }

        for (Runnable r : ses.getQueue()) {
            ScheduledFuture<?> sf = (ScheduledFuture<?>) r;
            sf.cancel(false);
        }
        ses.purge(); // remove all the tasks we just cancelled
        try {
            ses.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        try {
            es.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * @param task
     * @return
     * @see java.util.concurrent.AbstractExecutorService#submit(java.util.concurrent.Callable)
     */
    public <T> Future<T> submit(Callable<T> task) {
        return es.submit(task);
    }

    static class DefaultThreadFactory implements ThreadFactory {
        private final ThreadFactory delegate;

        private final String prefix;

        private final AtomicInteger threadNumber = new AtomicInteger(1);

        DefaultThreadFactory(String prefix, ThreadFactory delegate) {
            this.delegate = requireNonNull(delegate);
            this.prefix = prefix;
        }

        public Thread newThread(Runnable r) {
            Thread t = delegate.newThread(r);
            t.setDaemon(true);
            t.setName(THREAD_PREFIX + "-" + prefix + "-" + threadNumber.getAndIncrement());
            return t;
        }
    }
}
