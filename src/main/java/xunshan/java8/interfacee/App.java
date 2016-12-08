package xunshan.java8.interfacee;

/**
 * Created by eldorado on 17-1-12.
 * <p>
 * java8 interface-based application
 *
 * Not recommend for clear
 */
public interface App {
    // interface variable String ->
    //    public static final java.lang.String name;
    //    descriptor: Ljava/lang/String;
    //    flags: ACC_PUBLIC, ACC_STATIC, ACC_FINAL
    //    ConstantValue: String hello
    public String name = "hello";
    public static int age = 1;

    public void log();

    public static void main(String[] args) {
        System.out.println("hello");

        ((App) () -> System.out.println(name + age)).log();
    }
}
