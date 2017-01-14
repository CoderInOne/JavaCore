package xunshan.concurrent.lock;

import xunshan.util.Log;
import xunshan.util.ThreadUtils;

/**
 * a situation where two or more threads are blocked forever, waiting for each other.
 *
 * See: http://stackoverflow.com/a/1385868 this is a great example
 * trick: build step by step
 */
public class DeadLock {
    private static final String TAG = DeadLock.class.getSimpleName();

    public static void main(String[] args) {
        final String res1 = "res1";
        final String res2 = "res2";

        new Thread(new Runnable(){
            public void run() {
                synchronized (res1) {
                    Log.d(TAG, res1);
                    ThreadUtils.sleep(10000);

                    synchronized (res2) {
                        Log.d(TAG, res2);
                    }
                }
            }
        }).start();

        new Thread(new Runnable(){
            public void run() {
                synchronized (res2) {
                    Log.d(TAG, res2);
                    ThreadUtils.sleep(2000);

                    synchronized (res1) {
                        Log.d(TAG, res1);
                    }
                }
            }
        }).start();
    }
}
