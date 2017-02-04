package xunshan.java8.lambda;

/**
 * Created by eldorado on 17-2-3.
 */
public class RunnableTest {
    public static void main(String[] args) {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("r1...");
            }
        };

        // 无返回值
        // () -> statement;
        // () -> { statement; ...; }
        Runnable r2 = () -> System.out.println("r2...");

        r1.run();
        r2.run();
    }
}
