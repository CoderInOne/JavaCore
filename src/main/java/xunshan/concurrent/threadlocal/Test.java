package xunshan.concurrent.threadlocal;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lihuangdong on 2017/5/9.
 *
 * ThreadLocal是每个线程的全局变量
 */
public class Test {
    static class Session {
        int id;

        String name;

        Session (int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Session{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    private static AtomicInteger count = new AtomicInteger();

    // 每个线程有自己的session
    static ThreadLocal<Session> threadSession = ThreadLocal.withInitial(() -> {
        String name = Thread.currentThread().getName();
        return new Session(count.incrementAndGet(), name);
    });

    // Session{id=2, name='Thread-1'}
    // Session{id=2, name='Thread-0'}
    // 注意count的并发情况
    public static void main(String args[]) {

        ThreadFactory.execute(() -> {
            Session session = threadSession.get();
            System.out.println(session);
        });

        ThreadFactory.execute(() -> {
            Session session = threadSession.get();
            System.out.println(session);
        });
    }

    static class ThreadFactory {
        public static Thread createThread(Runnable runnable) {
            return new Thread(runnable);
        }

        public static void execute(Runnable r) {
            new Thread(r).start();
        }
    }
}
