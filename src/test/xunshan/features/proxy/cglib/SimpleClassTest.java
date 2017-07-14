package xunshan.features.proxy.cglib;

import net.sf.cglib.proxy.*;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class SimpleClassTest {
    @Test
    public void testModifyMethodBody() throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SimpleClass.class);
        enhancer.setCallback((FixedValue) () -> "Hello Cglib!");
        SimpleClass proxy = (SimpleClass) enhancer.create();
        assertEquals("Hello Cglib!", proxy.test(null));
    }

    @Test
    public void testInvocationHandler() throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SimpleClass.class);
        enhancer.setCallback((InvocationHandler) (proxy, method, args) -> {
            if (method.getDeclaringClass() != Object.class
                    && method.getReturnType() == String.class) {
                return "Hello Cglib!";
            } else {
                throw new RuntimeException("Don't know what to do!");
                //return method.invoke(proxy, args);
            }
        });
        SimpleClass simpleClass = (SimpleClass) enhancer.create();
        assertEquals("Hello Cglib!", simpleClass.test(null));
        //assertNotEquals("Hello Cglib!", simpleClass.toString());
    }

    @Test
    public void testMethodInterceptor() throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SimpleClass.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
            if (method.getDeclaringClass() != Object.class
                    && method.getReturnType() == String.class) {
                return "Hello Cglib!";
            } else {
                return proxy.invokeSuper(obj, args);
            }
        });
        SimpleClass simpleClass = (SimpleClass) enhancer.create();
        assertEquals("Hello Cglib!", simpleClass.test(null));
        assertNotEquals("Hello Cglib!", simpleClass.toString());
    }

    @Test
    public void testCallbackFilter() throws Exception {
        Enhancer enhancer = new Enhancer();
        CallbackHelper callbackHelper = new CallbackHelper(SimpleClass.class, new Class[0]) {
            @Override
            protected Object getCallback(Method method) {
                if (method.getDeclaringClass() != Object.class
                        && method.getReturnType() == String.class) {
                    return (FixedValue) () -> "Hello cglib!";
                } else {
                    return NoOp.INSTANCE;
                }
            }
        };
        enhancer.setSuperclass(SimpleClass.class);
        enhancer.setCallbackFilter(callbackHelper);
        enhancer.setCallbacks(callbackHelper.getCallbacks());
        SimpleClass simpleClass = (SimpleClass) enhancer.create();

        assertEquals("Hello cglib!", simpleClass.test(null));
        assertNotEquals("Hello cglib!", simpleClass.toString());
        simpleClass.hashCode();
    }
}