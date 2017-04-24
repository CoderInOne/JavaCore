package xunshan.anno.bind;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by eldorado on 17-4-24.
 *
 * 对象和int标记值的匹配器
 */
public class StringFinder {
    private static Map<Integer, String> sStringMap;

    static {
        sStringMap = new HashMap<>();
        sStringMap.put(Ids.STRING_A, "A");
        sStringMap.put(Ids.STRING_B, "B");
        sStringMap.put(Ids.STRING_C, "C");
        sStringMap.put(Ids.STRING_D, "D");
    }

    public static String findStringById(int id) {
        return sStringMap.get(id);
    }
}
