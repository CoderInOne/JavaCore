package xunshan.jvm;

/**
 * shut down hook
 */
public class ShutDownJVM {
    public static void main(String[] args) {
        String opt = "halt";
        Runtime rt = Runtime.getRuntime();
        rt.addShutdownHook(new MyShutDownHook());
        if ("exit".equals(opt)) {
            // ShutDown.sequence
            System.out.println("shutting down vm...");
            rt.exit(0);
        } else if ("halt".equals(opt)) {
            System.out.println("halt");
            rt.halt(0);
        } else {
            
        }
    }

    static class MyShutDownHook extends Thread {
        @Override
        public void run() {
            System.out.println("running shut down hook...");
        }
    }
}
