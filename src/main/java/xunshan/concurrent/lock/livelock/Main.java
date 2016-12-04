package xunshan.concurrent.lock.livelock;

/**
 * LiveLock
 * See: http://www.logicbig.com/tutorials/core-java-tutorial/java-multi-threading/thread-livelock/
 * Something wrong for this paper, read text just enough not codes
 */
public class Main {
    public static void main(String[] args) {
        final Worker w1 = new Worker(true);
        final Worker w2 = new Worker(true);
        final CommonResource res = new CommonResource(w1);

        new Thread(new Runnable(){
            public void run() {
                w1.processResource(res, w2);
            }
        }).start();

        new Thread(new Runnable(){
            public void run() {
                w2.processResource(res, w1);
            }
        }).start();
    }
}
