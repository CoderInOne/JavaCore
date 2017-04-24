package xunshan.anno;

/**
 * Created by eldorado on 17-4-24.
 *
 * 运行中可见的注解后在字节码中体现
 */
public class RuntimeInfo {
    /*
    public void test();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
    ...
    Deprecated: true
    RuntimeVisibleAnnotations: // <-
            0: #17()
            */

    /**
     * 警告被注解的对象已经过时了，运行中可见
     */
    /**
     * @deprecated
     * explanation of why it was deprecated
     */
    @Deprecated
    private static void test() {
        System.out.println("ok");
    }

    public static void main(String[] args) {
        test();
    }
}
