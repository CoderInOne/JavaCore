package xunshan.concurrent.api;

import xunshan.util.Log;
import xunshan.util.ThreadUtils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * re-implement MyBlockingQueue using Lock api
 */
public class LockApiBlockingQueue {
    private static final String TAG = LockApiBlockingQueue.class.getSimpleName();
    private Queue<String> queue = new LinkedList<String>();
    private int capacity;
    private int c = 0;
    private ReentrantLock getlock = new ReentrantLock();
    private ReentrantLock putlock = new ReentrantLock();
    private Condition notEmpty = getlock.newCondition();
    private Condition notFull = putlock.newCondition();

    public LockApiBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void put(String value) throws InterruptedException {
        putlock.lock();
        try {
            while(queue.size() >= capacity) {
                notFull.await();
            }

            queue.add(value);
            c++;
            Log.d(TAG, ThreadUtils.getThreadName(), " put " + value);
            if (c + 1 < capacity)
                notFull.signal();

        } finally {
            putlock.unlock();
        }

        if(c == 0) {
            signalNotEmpty();
        }
    }

    public String take() throws InterruptedException {
        getlock.lock();
        try {
            while(queue.isEmpty()) {
                notEmpty.await();
            }

            String ele = queue.remove();
            c--;
            if(c > 1) {
                notEmpty.signal();
            }
            return ele;
        } finally {
            getlock.unlock();
        }

    }

    private void signalNotEmpty() {
        final ReentrantLock takeLock = this.getlock;
        takeLock.lock();
        try {
            notEmpty.signal();
        } finally {
            takeLock.unlock();
        }
    }

    public static void main(String[] args) {
        final LockApiBlockingQueue queue = new LockApiBlockingQueue(10);
        new Thread(new Runnable(){
            public void run() {
                while(true) {
                    try {
                        queue.put("hello");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ThreadUtils.sleep(1000);
                }
            }
        }).start();

        new Thread(new Runnable(){
            public void run() {
                while(true) {
                    String take = null;
                    try {
                        take = queue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, ThreadUtils.getThreadName(), " get " + take);
                }
            }
        }).start();
    }
}
