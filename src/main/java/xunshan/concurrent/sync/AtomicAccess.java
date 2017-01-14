package xunshan.concurrent.sync;

import xunshan.util.Log;
import xunshan.util.ThreadUtils;

/**
 * an atomic action is one that effectively happens all at once
 * 1. Reads and writes are atomic for reference variables and for most primitive variables (all types except long and double).
 * 2. Reads and writes are atomic for all variables declared volatile (including long and double variables).
 *
 * ** volatile is atomic access
 *
 * NOT atomic
     AtomicAccess: Thread-0->10
     AtomicAccess: Thread-0->20
     AtomicAccess: Thread-0->30
     AtomicAccess: Thread-1->30
     AtomicAccess: Thread-1->20
     AtomicAccess: Thread-1->30
 *
 * atomic
     AtomicAccess: Thread-1->10
     AtomicAccess: Thread-0->10
     AtomicAccess: Thread-1->20
     AtomicAccess: Thread-0->20
     AtomicAccess: Thread-1->30
     AtomicAccess: Thread-0->30
 */
public class AtomicAccess {

    private static final String TAG = AtomicAccess.class.getSimpleName();
    private Object lock = new Object();

    public static void main(String[] args) {
        final Shared s = new Shared();
        new AtomicAccess().execute(true, s);
        new AtomicAccess().execute(false, s);
    }

    public void execute(final boolean isAtomic, final Shared s) {
        new Thread(new Runnable(){
            public void run() {
                if(isAtomic) {
                    synchronized (lock) {
                        s.print();
                    }
                    return;
                }

                // NOT atomic
                s.print();
            }
        }).start();
    }

    static class Shared {
        int i;

        public void print() {
            i = 10;
            Log.d(TAG, ThreadUtils.getThreadName(), "" + i);
            i = 20;
            Log.d(TAG, ThreadUtils.getThreadName(), "" + i);
            i = 30;
            Log.d(TAG, ThreadUtils.getThreadName(), "" + i);
        }
    }
}
