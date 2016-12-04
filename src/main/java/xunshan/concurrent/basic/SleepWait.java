package xunshan.concurrent.basic;

import xunshan.util.Log;
import xunshan.util.ThreadUtils;

/**
 * diff between Object.wait() and Thread.sleep
 * See:
 *    1.http://stackoverflow.com/a/1036763
 *    2.http://stackoverflow.com/a/1036808
 *
 * 1. wait (and notify) must happen in a block synchronized on the monitor object whereas sleep does not
 * 2. sleep hold lock, while wait not
 */
public class SleepWait {
    private static final String TAG = SleepWait.class.getSimpleName();

    public static void main(String[] args) {
        final SleepWait lock = new SleepWait();

        new Thread(new Runnable(){
            public void run() {
                synchronized (lock) {
//                    ThreadUtils.sleep(1000);
                    ThreadUtils.wait(lock, 1000);
                    lock.setMessage("hello");
                }
                Log.d(TAG, "sleep finished");
            }
        }).start();

        new Thread(new Runnable(){
            public void run() {
                synchronized (lock) {
                    lock.printMessage();
                }
                Log.d(TAG, "wait finished");
            }
        }).start();
    }

    private String message;
    public String printMessage() {
        if(this.message == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Log.d(TAG, ThreadUtils.getThreadName(), message);
        return this.message;
    }
    public void setMessage(String message) {
        this.message = message;
        Log.d(TAG, ThreadUtils.getThreadName(), "set " + message);
        notify();
    }
}
