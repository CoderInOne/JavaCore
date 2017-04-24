package xunshan.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by eldorado on 17-4-24.
 *
 * Runtime会在运行中使用，而Class不会在运行中使用
 */
public class ClassInfo {
    @Retention(RetentionPolicy.CLASS)
    @interface RetentionClass {}

    @Retention(RetentionPolicy.RUNTIME)
    @interface  RetentionRuntime {}

    @RetentionClass
    class C {}

    @RetentionRuntime
    class R {}

    public static void main(String[] args) {
        System.out.println(C.class.getAnnotations().length); // -> 0, bytecode: RuntimeInvisibleAnnotations: RetentionClass
        System.out.println(R.class.getAnnotations().length); // -> 1, bytecode: RuntimeInvisibleAnnotations: RetentionClass
    }
}
