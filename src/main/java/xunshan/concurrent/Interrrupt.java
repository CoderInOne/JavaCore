package xunshan.concurrent;

import xunshan.util.Log;

import java.lang.management.ManagementFactory;

/**
 * Interrupt
 */
public class Interrrupt {
    private static final String TAG = Interrrupt.class.getSimpleName();
    private static Interrrupt interrrupt;

    public static void main(String[] args) {
        String pname = ManagementFactory.getRuntimeMXBean().getName();
        Log.d(TAG, pname);
        interrrupt = new Interrrupt();
        interrrupt.loop();
    }

    public void loop() {
        // 1. start a new long running thread
        Thread t = new Thread(new Runnable(){
            public void run() {
                while(true) {
                    Log.d(TAG, "aliving");
                    if(Thread.interrupted()) {
                        Log.d(TAG, "Interrupt");
                        return;
                    }
                }
            }
        });
        t.start();

        // 2. try to stop long running thread after 2 secs
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // interrupt thread
        t.interrupt();
    }
}
