package xunshan.concurrent.lock.livelock;

import xunshan.util.Log;
import xunshan.util.ThreadUtils;

/**
 * Worker to process CommonResource
 */
public class Worker {
    private static final String TAG = Worker.class.getSimpleName();
    private boolean active;

    public Worker(boolean active) {
        this.active = active;
    }

    public void processResource(CommonResource res, Worker otherWorker) {
        Log.d(TAG, ThreadUtils.getThreadName(), "begin");
        while(active) {
            // not own res, just wait
            if(res.getOwner() != this) {
                Log.d(TAG, ThreadUtils.getThreadName(), "waiting res");
                ThreadUtils.sleep(10);
                continue;
            }
            Log.d(TAG, ThreadUtils.getThreadName(), "owns res");
            // now own res, but if other worker need this res, give up this res
            if(otherWorker.active) {
                Log.d(TAG, ThreadUtils.getThreadName(), "handover the res to worker:" + otherWorker.toString());
                res.setOwner(otherWorker);
                continue;
            }

            // now own this res and no other want is
            // consume res and set active false
            Log.d(TAG, ThreadUtils.getThreadName(), "consume out res");
            active = false;
            res.setOwner(otherWorker);
        }
    }
}
