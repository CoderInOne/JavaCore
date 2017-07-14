package xunshan.features.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class NoOpAddInvocationHandler implements InvocationHandler {
    private List proxied;

    public NoOpAddInvocationHandler(List proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 导致无穷递归
        // System.out.println(proxy);
        if (method.getName().startsWith("add")) {
            return false;
        } else {
            return method.invoke(this.proxied, args);
        }
    }
}
