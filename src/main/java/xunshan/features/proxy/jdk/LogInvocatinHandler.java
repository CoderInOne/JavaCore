package xunshan.features.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogInvocatinHandler implements InvocationHandler {
    private Log proxied;

    public LogInvocatinHandler(Log proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("e")) {
            System.out.print("E: ");
        } else if (method.getName().equals("w")) {
            System.out.print("W: ");
        } else if (method.getName().equals("i")) {
            System.out.print("I: ");
        } else if (method.getName().equals("d")) {
            System.out.print("D: ");
        } else {
            System.out.println("?: ");
        }

        return method.invoke(proxied, args);
    }
}
