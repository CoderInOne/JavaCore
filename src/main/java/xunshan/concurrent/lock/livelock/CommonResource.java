package xunshan.concurrent.lock.livelock;

/**
 * Common resource to be shared
 */
public class CommonResource {
    private Worker owner;

    public CommonResource(Worker owner) {
        this.owner = owner;
    }

    public synchronized void setOwner(Worker owner) {
        this.owner = owner;
    }

    public Worker getOwner() {
        return this.owner;
    }
}
