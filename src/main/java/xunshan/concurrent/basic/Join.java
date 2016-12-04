package xunshan.concurrent.basic;

import xunshan.util.Log;

/**
 * Join: join another thread to current thread
 * we should read code like this:
 *     for thread main:
 *         t.join();
 * thread t join in main thread, when t run out main continue to run
 */
public class Join
{
    private static final String TAG = "Join";
    private static String shareObj = "100$";
    public static void main(String[] args) {
        final Thread t1 = new Thread(new Runnable(){
            public void run() {
                int i = 10000;
                while(i-- > 0) {
                    Log.d(TAG, Thread.currentThread().getName(), shareObj);
                }
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable(){
            public void run() {
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, Thread.currentThread().getName(), shareObj);
            }
        });
        t2.start();
    }
}
