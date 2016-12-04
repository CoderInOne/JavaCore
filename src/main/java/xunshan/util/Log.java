package xunshan.util;

import java.io.PrintStream;

/**
 * Log for java
 */
public class Log {
    private static final PrintStream OUT = System.out;
    public static void d(String tag, String msg) {
        OUT.println(tag + ": " + msg);
    }

    public static void d(String tag, String subtag, String msg) {
        OUT.println(tag + ": " + subtag + "->" + msg);
    }
}
