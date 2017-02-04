package xunshan.java8.lambda;

/**
 * Created by eldorado on 17-2-4.
 */
public class ListenerTest {
    public interface Callback {
        void doAction(String cmd);
    }

    public static void main(String[] args) {
        call(new Callback() {
            @Override
            public void doAction(String cmd) {
                System.out.println(cmd);
            }
        });

        // lambda隐藏了类型和参数
        // 实现最本质的输入和输出
        call(cmd -> System.out.println("lambda: " + cmd));
    }

    public static void call(Callback callback) {
        callback.doAction("print");
    }
}
