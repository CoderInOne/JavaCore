package xunshan.concurrent.semaphore;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * 例子概述：
 *    有一个生产着，三个消费者，只有当生产者往容器里面添加资源之后，Semaphore会释放锁
 *    让等待中的消费者进来取资源
 */
public class SemaphoreMutexExample {
    static Object lock = new Object();
    static LinkedList<String> list = new LinkedList<String>();

    // Semaphore维护着一组权限，如果没有权限，那么acquire会阻塞直到有权限了
    // 每个Semaphore release会增加一个权限名额，同事也可能释放了对一个acquire的锁，如果有的话
    static Semaphore semaphore = new Semaphore(1);
    static Semaphore mutex = new Semaphore(2);

    static class Producer extends Thread {
        @Override
        public void run() {
            int count = 1;
            try {
                while(true) {
                    String threadName = this.currentThread().getName() + "-" +  count++;

                    mutex.acquire();
                    list.add(threadName);
                    System.out.println("new Value:"+threadName);
                    mutex.release();

                    semaphore.release();
                    Thread.sleep(500);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class Consumer extends Thread {
        String consumerName;

        public Consumer(String name) {
            this.consumerName = name;
        }

        @Override
        public void run() {
            try {
                while(true) {

                    // 获取锁，阻塞直到生产者释放这个锁
                    semaphore.acquire();

                    // 从semaphore中提权，如果没有就阻塞
                    mutex.acquire();
                    String result = "";
                    for (String value :
                            list) {
                        result = value + ", ";
                    }
                    System.out.println(consumerName + ":" + result + "list size:" + list.size());
                    mutex.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Producer().start();
        new Consumer("one").start();
        new Consumer("two").start();
        new Consumer("thr").start();
    }
}
