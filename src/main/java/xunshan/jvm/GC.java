package xunshan.jvm;

/**
 * JVM
 */
public class GC {
    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();
        runtime(rt);
        rt.runFinalization();
        rt.gc();
        runtime(rt);

        allocBitObject();
        runtime(rt);
        rt.gc();
        runtime(rt);
        // 用了2097152，但是只回收了1465664
    }

    public static void allocBitObject() {
        byte[] buf = new byte[2 * 1024 * 1024]; // 2097152
        buf = null;
    }

    public static void runtime(Runtime runtime) {
        System.out.println(runtime.availableProcessors());
        System.out.println(runtime.maxMemory());
        System.out.println(runtime.freeMemory());
        System.out.println(runtime.totalMemory());
    }
}
