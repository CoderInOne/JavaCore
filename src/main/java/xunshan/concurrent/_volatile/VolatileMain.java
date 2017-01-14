package xunshan.concurrent._volatile;

/**
 * Created by xunshan on 17-1-4.
 */
public class VolatileMain {
    private static volatile int counter = 0;
    private long _id = 0;

    public static void main(String[] args) {
        int c = counter;

        VolatileMain v = new VolatileMain();
        long id = v._id;

        System.out.println(id);
    }
}
