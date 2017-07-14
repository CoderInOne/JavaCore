package xunshan.features.proxy.jdk;

public class LogImpl implements Log {
    @Override
    public void d(String msg) {
        System.out.println(msg);
    }

    @Override
    public void i(String msg) {
        System.out.println(msg);
    }

    @Override
    public void w(String msg) {
        System.out.println(msg);
    }

    @Override
    public void e(String msg) {
        System.out.println(msg);
    }
}
