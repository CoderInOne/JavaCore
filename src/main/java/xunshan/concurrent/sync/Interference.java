package xunshan.concurrent.sync;

/**
 * Interference happens when two operations, running in different threads,
 * but acting on the same data, interleave. This means that the two operations
 * consist of multiple steps, and the sequences of steps overlap.
 *
 * How it happen:
 * 1. same resource shared by multiple thread
 * 2. res are access by no blocked, can be accessed same time
 * 3. no happen before relationship
 *
 * example from:http://javaconceptoftheday.com/thread-interference-in-java/
 * execute this example multiple times, it will show like this:
             10
             10
             20
             30 // this is interference happens
             30
             30

        or
             10
             20 // this is interference happens
             20
             20
             30
             30
 */
class Shared
{
    int i;

    /*synchronized*/ void SharedMethod() throws InterruptedException {
        i = 10;
        Thread.sleep(100);
        System.out.println(i);
        i = 20;
        Thread.sleep(100);
        System.out.println(i);
        i = 30;
        Thread.sleep(100);
        System.out.println(i);
    }
}

public class Interference
{
    public static void main(String[] args)
    {
        final Shared s1 = new Shared();

        Thread t1 = new Thread()
        {
            @Override
            public void run()
            {
                try {
                    s1.SharedMethod();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t2 = new Thread()
        {
            @Override
            public void run()
            {
                try {
                    s1.SharedMethod();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        t1.start();

        t2.start();
    }
}
