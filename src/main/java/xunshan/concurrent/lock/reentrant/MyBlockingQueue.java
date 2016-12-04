package xunshan.concurrent.lock.reentrant;

import xunshan.util.Log;
import xunshan.util.ThreadUtils;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Simple BlockingQueue implementation
 * this v can be think as bad one: can not test, no one knowing right or wrong
 */
public class MyBlockingQueue {

    private static final String TAG = MyBlockingQueue.class.getSimpleName();
    private int counter = 0;
    private LinkedList<String> queue = new LinkedList<String>();
    private Lock lock = new ReentrantLock();
    private int capacity = 10000;

    public void put(String value) {
        lock.lock();
        try {
            if(counter == capacity) {
                return;
            }
            queue.add(value);
            counter++;
        } finally {
            lock.unlock();
        }
    }

    public String get() {
        lock.lock();
        try {
            if(queue.size() == 0) {
                return null;
            }
            counter--;
            return queue.remove();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        final MyBlockingQueue queue = new MyBlockingQueue();

        new Thread(new Runnable() {
            public void run() {
                int i = 0;
                while(i-- < 1000) {
                    queue.put("hello-" + i);
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                while(true) {
                    String s = queue.get();
                    Log.d(TAG, ThreadUtils.getThreadName(), s);
                }
            }
        }).start();
    }
}
