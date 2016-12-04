package xunshan.concurrent.lock.impl;

import xunshan.util.Log;
import xunshan.util.ThreadUtils;

/**
 * this program is not thread safe enough, just for demo
 * note that code between return and lock block is not sync
 * so it maybe cause some like:
 *    Counter: Thread-0->1000
 *    Counter: Thread-1->1001 [(c = counter.inc()) < 1000, and think about
 *            what happen when c = 999]
 */
public class Counter {
    private static final String TAG = Counter.class.getSimpleName();
    final Lock lock = new Lock();
    private int c = 0;

    public int inc() {
        try {
            lock.lock();
            c++;
            Log.d(TAG, ThreadUtils.getThreadName(), "" + c);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return c;
    }

    public static void main(String[] args) {
        final Counter counter = new Counter();
        new Thread(new Runnable() {
            public void run() {
                int c;
                while((c = counter.inc()) < 1000) {
                    Log.d(TAG, ThreadUtils.getThreadName(), " get " + c);
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                int c = 0;
                while((c = counter.inc()) < 1000) {
                    Log.d(TAG, ThreadUtils.getThreadName(), " get " + c);
                }
            }
        }).start();
    }
}
