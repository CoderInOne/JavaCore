package xunshan.concurrent.basic;

import xunshan.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Lock Object
 * 1. main lock: 线程共用的做
 * 2. lock: every thread having a lock
 */
public class Lock {

    private static final String TAG = Lock.class.getSimpleName();
    private static Object mainLock = new Object();

    public static void main(String[] args) {
        LockRunnable l1 = new LockRunnable(1);
        LockRunnable l2 = new LockRunnable(2);

        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(l1);
        executor.execute(l2);
    }

    public static class LockRunnable implements Runnable {
        private Object lock = new Object();
        private int id;

        public LockRunnable(int id) {
            this.id = id;
        }
        public void run() {
            print();
        }
        public void print() {
            synchronized (mainLock) {
                int i = 10;
                while(i-- > 0) {
                    Log.d(TAG, "" + id);
                }
            }
        }
    }
}
