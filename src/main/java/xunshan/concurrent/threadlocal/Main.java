package xunshan.concurrent.threadlocal;

import xunshan.util.ThreadUtils;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by eldorado on 17-2-23.
 *
 * 对ThreadLocal的最佳的例子。剩余问题是： android主线程的prepare在哪里:在ActivityThread中
 */
public class Main {

    static class Looper {
        private static ThreadLocal<Looper> sThreadLocal = new ThreadLocal<>();
        private String name;
        public LinkedBlockingQueue<Message> mQueue;

        Looper(String name) {
            this.name = name;
            this.mQueue = new LinkedBlockingQueue<>();
        }

        static void prepare() {
            if (sThreadLocal.get() != null) {
                throw new RuntimeException("only one looper per thread");
            }

            sThreadLocal.set(new Looper(ThreadUtils.getThreadName()));
        }

        void loop() {
            System.out.println(this.name);
            for (;;) {
                Message msg = mQueue.poll();
                if (msg == null) {
                    ThreadUtils.sleep(500);
                    continue;
                }

                msg.target.handleMessage(msg);
            }
        }

        static Looper myLooper() {
            return sThreadLocal.get();
        }
    }

    static class Handler {
        private Looper mLooper;
        private LinkedBlockingQueue<Message> mQueue;

        public Handler() {
            // 这里在写代码的时候发现，handler如何跟Looper关联
            // 这里显示出ThreadLocal的另一个用处，全局提供变量的能力
            // 如果返回的Looper为空，说明没有调用prepare，那么也就没有
            // 轮旬器处理消息，handler没有用处
            this.mLooper = Looper.myLooper();
            this.mQueue = mLooper.mQueue;
        }

        public void sendMessage(Message msg) {
            mQueue.add(msg);
            msg.target = this;
        }

        public void handleMessage(Message msg) {
            // 一般是空实现，由客户端决定
            System.out.println("message{tName: " + msg.tName + ", what: " + msg.when + ", when: " + msg.when + "}");
        }
    }

    static class Message {
        int what;
        long when;
        Handler target;
        String tName; // source thread name
    }

    public static void main(String[] args) {
        // 1. 不同的Thread都有自己的Looper，以避免使用synchronize
        // thread-0
//        new Thread(() -> {
//            Looper.prepare();
//            Looper.myLooper().loop();
//        }).start();

        // thread-1
//        new Thread(() -> {
//            Looper.prepare();
//            Looper.myLooper().loop();
//        }).start();

        // 2. message如何发送到对应的thread呢
        new Thread(() -> {
            Looper.prepare();

            final Handler h = new Handler();

            new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    Message msg = new Message();
                    msg.what = i;
                    msg.tName = ThreadUtils.getThreadName();
                    msg.when = System.currentTimeMillis();
                    h.sendMessage(msg);

                    ThreadUtils.sleep(500);
                }
            }).start();

            new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    Message msg = new Message();
                    msg.what = i;
                    msg.tName = ThreadUtils.getThreadName();
                    msg.when = System.currentTimeMillis();
                    h.sendMessage(msg);

                    ThreadUtils.sleep(300);
                }
            }).start();

            Looper.myLooper().loop();
        }).start();
    }
}
