package xunshan.concurrent.block;

import xunshan.util.Log;
import xunshan.util.ThreadUtils;

/**
 GuardBlock: Thread-0->waste waiting!
 GuardBlock: Thread-1->efficient waiting!
 GuardBlock: Thread-2->other job
 *GuardBlock: Thread-0->waste waiting!
 *GuardBlock: Thread-0->waste waiting!
 *GuardBlock: Thread-0->waste waiting!
 *GuardBlock: Thread-0->waste waiting!
 *GuardBlock: Thread-0->waste waiting!
 *GuardBlock: Thread-0->waste waiting!
 *GuardBlock: Thread-0->waste waiting!
 *GuardBlock: Thread-0->waste waiting!
 *GuardBlock: Thread-0->waste waiting!
 GuardBlock: Thread-1->efficient guard going!
 GuardBlock: Thread-0->waste guard going!

 *GuardBlock.. is wasting job, we should let other thread use cpu if current work is not available
 * to maximize cpu usage
 */
public class GuardBlock {
    private static final String TAG = GuardBlock.class.getSimpleName();
    private boolean canGo = false;

    // guard block with lock always
    public void wasteGuard() {
        while(! canGo) {
            Log.d(TAG, ThreadUtils.getThreadName(), "waste waiting!");
            ThreadUtils.sleep(10);
        }
        Log.d(TAG, ThreadUtils.getThreadName(), "waste guard going!");
    }

    public synchronized void efficientGuard() {
        while(! canGo) {
            Log.d(TAG, ThreadUtils.getThreadName(), "efficient waiting!");
            ThreadUtils.wait(this);
        }
        Log.d(TAG, ThreadUtils.getThreadName(), "efficient guard going!");
    }

    public synchronized void otherJob() {
        Log.d(TAG, ThreadUtils.getThreadName(), "other job");
        ThreadUtils.sleep(100);
        canGo = true;
        notify();
    }

    public static void main(String[] args) {
        final GuardBlock bg = new GuardBlock();

        new Thread(new Runnable(){
            public void run() {
                bg.wasteGuard();
            }
        }).start();

        new Thread(new Runnable(){
            public void run() {
                bg.efficientGuard();
            }
        }).start();

        new Thread(new Runnable(){
            public void run() {
                bg.otherJob();
            }
        }).start();
    }
}
