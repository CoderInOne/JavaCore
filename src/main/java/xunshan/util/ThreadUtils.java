package xunshan.util;

/**
 * Created by eldorado on 16-12-4.
 */
public class ThreadUtils {
    public static String getThreadName() {
        return Thread.currentThread().getName();
    }

    public static void sleep(long t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void wait(Object obj) {
        try {
            obj.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void wait(Object obj, long t) {
        try {
            obj.wait(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
