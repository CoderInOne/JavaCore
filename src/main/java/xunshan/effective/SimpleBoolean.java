package xunshan.effective;

/**
 * Created by xunshan on 17-1-14.
 */
public class SimpleBoolean {
    static boolean getBoonean(String s) {
        s = s.toLowerCase();
        if (s.equals("yes") || s.equals("y") || s.equals("t")) {
            s = "true";
        }

        // WRONG! see doc -> result = parseBoolean(System.getProperty(name));
        // RIGHT: parseBoolean
        return Boolean.getBoolean(s);
    }

    public static void main(String[] args) {
        System.setProperty("true", "true");
        System.out.println(getBoonean("true") + " " + getBoonean("YeS"));
    }
}
