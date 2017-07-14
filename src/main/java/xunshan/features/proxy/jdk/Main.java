package xunshan.features.proxy.jdk;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Log log = new LogImpl();

        Log proxy = (Log) Proxy.newProxyInstance(LogImpl.class.getClassLoader(),
                new Class[] {Log.class},
                new LogInvocatinHandler(log));

        proxy.d("debug");
        proxy.i("info");
        proxy.w("warning");
        proxy.e("error");
    }

    private static void noOpAddTest() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        List proxy = (List) Proxy.newProxyInstance(
                NoOpAddInvocationHandler.class.getClassLoader(),
                new Class[]{List.class},
                new NoOpAddInvocationHandler(list));
        proxy.add(4);
        proxy.add(5);
        System.out.println(list);

        proxy.remove(new Integer(1));
        proxy.remove(new Integer(2));
        list.add(1);
        list.add(2);
//        proxy.remove(2);
        System.out.println(list);
    }
}
