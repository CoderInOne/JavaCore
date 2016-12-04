package xunshan.concurrent.basic;

import xunshan.util.Log;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Learn wait and notify from BlockingQueue source code
 * 1. wait [pause to execution in current thread] for resource to available or action to happen
 * 2. notify that res is available or action happens
 * @see: http://stackoverflow.com/a/2537117
 */
public class MyBlockingQueue<T> {
    private static final String TAG = MyBlockingQueue.class.getSimpleName();
    private Queue<T> queue = new LinkedList<T>();
    private int capacity;
    private int id = 0;

    public MyBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(T value) throws InterruptedException {
        while(queue.size() == capacity) {
            wait();
        }

        Log.d(TAG, value + "-" + id);
        id++;
        queue.add(value);
        notify();
    }

    public synchronized T get() throws InterruptedException {
        while(queue.isEmpty()) {
            wait();
        }

        T ele = queue.remove();
        Log.d(TAG, ele + "");
        notify();

        return ele;
    }

    public static void main(String[] args) {
        final MyBlockingQueue<String> queue = new MyBlockingQueue<String>(10);
        new Thread(new Runnable(){
            public void run() {
                int i = 10000;
                while(i-- > 0) {
                    try {
                        queue.put("hello");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable(){
            public void run() {
                while(true) {
                    try {
                        String s = queue.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
