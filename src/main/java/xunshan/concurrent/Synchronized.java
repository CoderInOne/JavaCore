package xunshan.concurrent;

import xunshan.util.Log;

/**
 * key word synchronized
 * 1. only one thread can execute a block of code
 * 2. inside protected code is guarded by same lock
 */
public class Synchronized {
    private static final String TAG = Synchronized.class.getSimpleName();
    private static Object lock = new Object();

    public static void main(String[] args) {
        syncBlock();
    }

    /** synchronized method test */
    public static void syncMethod() {
        new Thread(new Runnable(){
            public void run() {
                log("thread-1");
            }
        }).start();

        new Thread(new Runnable(){
            public void run() {
                log("thread-2");
            }
        }).start();
    }

    /** synchronized block & lock */
    public static void syncBlock() {
        new Thread(new Runnable(){
            public void run() {
                logBlock("thread-3");
            }
        }).start();

        new Thread(new Runnable(){
            public void run() {
                logBlock("thread-4");
            }
        }).start();
    }

    public synchronized static void log(String threadName) {
        int i = 10;
        while (i-- > 0) {
            Log.d(TAG, threadName, "" + i);
        }
    }

    public static void logBlock(String threadName) {
        int i = 10;
//        while (i-- > 0) {
//            Log.d(TAG, threadName, "" + i);
//        }
        synchronized (lock) {
            while (i-- > 0) {
                Log.d(TAG, threadName, "" + i);
            }
        }
    }
}
