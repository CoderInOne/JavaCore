package xunshan.concurrent.executor;

import xunshan.util.Log;
import xunshan.util.ThreadUtils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Executor
 *    Launching new tasks
 * Thread Pool:
 *    Thread objects use a significant amount of memory
 * if want to know how thread pool / executor work, just read ThreadPoolExecutor source code
 */
public class Main {
    private static final String TAG = Main.class.getSimpleName();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Future<?> future = executor.submit(new Runnable() {
            public void run() {
                int i = 10;
                while (i-- > 0) {
                    Log.d(TAG, ThreadUtils.getThreadName(), "" + i);
                }
            }
        });

        Object o = future.get();
        Log.d(TAG, "" + o);
    }
}
