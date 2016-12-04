package xunshan.concurrent;

import xunshan.util.Log;

/**
 * Process and Thread
 */
public class ProcessThread {
    private static final String TAG = ProcessThread.class.getSimpleName();

    public static void main(String[] args) {
        ProcessThread processThread = new ProcessThread();
        processThread.thread();
    }

    public void process() {
        // 1. java application run by default in one process
    }

    // thread is lightweight process
    public void thread() {
        new Thread(new Runnable() {
            public void run() {
                Log.d(TAG, "hello");
            }
        }).start();
    }
}
