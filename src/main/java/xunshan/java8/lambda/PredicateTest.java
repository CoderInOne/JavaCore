package xunshan.java8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by eldorado on 17-2-4.
 *
 * Predicate是加强版比较器。注意阅读其源码，使用的就是lambda实现
 */
public class PredicateTest {

    public static class Person {
        private int age;


    }

    public static void moreThan20(List<Person> pl, Predicate<Person> pred) {
        for (Person p : pl) {
            if (pred.test(p)) {
                System.out.println(p.age);
            }
        }
    }

    public static void main(String[] args) {
        List<Person> pl = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Person p = new Person();
            p.age = i + 15;
            pl.add(p);
        }
        Predicate<Person> moreThen20Pred = p -> p.age >= 20;
        moreThan20(pl, moreThen20Pred);
    }
}
