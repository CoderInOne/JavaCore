package xunshan.effective;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Created by xunshan on 17-1-14.
 *
 * Mnenomic for WildCard from Joshua Bloch's lecture
 * Effective Java - Still Effective After All These Years
 *
 * don't use wild card for return type
 */
public class WildCardMnenomic {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");

        // simple conversion
        // compile error -> Inconvertible type
        // simple(strings);

        // upperBounded conversion
        upperBounded(strings);

        // lowerBounded conversion
        ArrayList<Object> objects = new ArrayList<>();
        lowerBounded(objects);

        // i want to contain something
        // not merely for my type
        // i contains subtype of my type
        objects.add(strings);

        Iterator<String> iterator = strings.iterator();
        iterator.forEachRemaining(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                // 1. expect lower bounded
                // when we accept o, we consume o, we change o
                // but we hope that we can change everything about o
                // we want to know everything about o
                // so o should be <? super my_type>
                System.out.println(o);
            }
        });
        // 2. another about lower bound
        // Stack<Number> popAll into Collection<Object>
        // Stack<Number> pushAll from Collection<Long>
        // 1 and 2 is the same in fact
    }

    public static void simple(List<Object> objects) {
        // do nothing
    }

    public static void upperBounded(List<? extends Object> objects) {
        // do nothing
    }

    public static void lowerBounded(List<? super String> strings) {
        // do nothing
    }
}
