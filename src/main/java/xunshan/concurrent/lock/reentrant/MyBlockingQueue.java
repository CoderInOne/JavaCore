package xunshan.concurrent.lock.reentrant;

import xunshan.util.Log;
import xunshan.util.ThreadUtils;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
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
    private ReentrantLock putlock = new ReentrantLock();
    private Condition notFull = putlock.newCondition();
    private ReentrantLock takelock = new ReentrantLock();
    private Condition notEmpty = takelock.newCondition();
    private int capacity = 100;

    public void put(String value) {
        putlock.lock();
        try {
            if(counter == capacity) {
                try {
                    notFull.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.add(value);
            Log.d(TAG, ThreadUtils.getThreadName(), " + " + value);
            counter++;
        } finally {
            putlock.unlock();
        }

        if(queue.size() > 0) {
            signalNotEmpty();
        }
    }

    private void signalNotEmpty() {
        final ReentrantLock takeLock = this.takelock;
        takeLock.lock();
        try {
            notEmpty.signal();
        } finally {
            takeLock.unlock();
        }
    }

    public String get() throws InterruptedException {
        takelock.lock();
        try {
            if(queue.size() == 0) {
                notEmpty.await();
            }
            counter--;
            return queue.remove();
        } finally {
            takelock.unlock();
            if(queue.size() <= capacity) {
                signalNotFull();
            }
        }
    }

    private void signalNotFull() {
        final ReentrantLock putLock = this.putlock;
        putLock.lock();
        try {
            notFull.signal();
        } finally {
            putLock.unlock();
        }
    }

    public static void main(String[] args) {

        final MyBlockingQueue queue = new MyBlockingQueue();

        new Thread(new Runnable() {
            public void run() {
                int i = 0;
                while(i++ < 1000) {
                    queue.put("hello-" + i);
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                while(true) {
                    String s = null;
                    try {
                        s = queue.get();
                        Log.d(TAG, ThreadUtils.getThreadName(), " - " + s);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
