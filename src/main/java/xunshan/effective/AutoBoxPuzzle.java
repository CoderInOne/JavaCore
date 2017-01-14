package xunshan.effective;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by xunshan on 17-1-14.
 */
public class AutoBoxPuzzle {
    public static void main(String[] args) {
        String[] in = new String[]{"0", "1", "2", "3", "4", "5"};

        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < in.length; i++) {
            integers.add(Integer.valueOf(in[i]));
        }

        int res = Collections.binarySearch(integers, 1, cmp);
        System.out.println(res);
    }

    // Integer是一个对象，如[o1, o2] -> [Integer@607, Integer@608]
    // 解决方案1:不要使用‘==’， 使用‘<’或‘>’，让Integer实现unboxing
    // 解决方案2:先'int i = o1'，再比较
    static Comparator<Integer> cmp =
            (o1, o2) -> o1 < o2 ? -1 : (o1 == o2 ? 0 : 1);
}

/*
  JVM出了autobox之后，衍生了== ！=不支持autobox，java8已经支持了。
  a new tech lease, it will generate another problem.
  but it is jvm! jvm should not do a lot of work for devs
  but jvm may be not clear

 */