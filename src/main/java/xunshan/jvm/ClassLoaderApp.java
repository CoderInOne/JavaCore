package xunshan.jvm;

import sun.misc.Launcher;

import java.net.URL;

/**
 * Created by xunshan on 16-12-31.
 */
public class ClassLoaderApp {
    public static void main(String[] args) {
        bootStrap();
        objectHash();
    }

    private static void bootStrap() {
        // 1. 启动类加载
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urls) {
            System.out.println(url.getPath());

        }
        System.out.println(System.getProperty("sun.boot.class.path"));
        // ->
//        /usr/lib/jvm/java-8-oracle/jre/lib/resources.jar
//        /usr/lib/jvm/java-8-oracle/jre/lib/rt.jar
//        /usr/lib/jvm/java-8-oracle/jre/lib/sunrsasign.jar
//        /usr/lib/jvm/java-8-oracle/jre/lib/jsse.jar
//        /usr/lib/jvm/java-8-oracle/jre/lib/jce.jar
//        /usr/lib/jvm/java-8-oracle/jre/lib/charsets.jar
//        /usr/lib/jvm/java-8-oracle/jre/lib/jfr.jar
//        /usr/lib/jvm/java-8-oracle/jre/classes
    }

    public static void objectHash() {
        Object obj1= new Object();
        Object obj2= new Object();
        System.out.println(obj1);
        System.out.println(obj2);
    }
}
