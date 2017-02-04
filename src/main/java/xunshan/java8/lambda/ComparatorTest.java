package xunshan.java8.lambda;

import java.util.*;

/**
 * Created by eldorado on 17-2-3.
 */
public class ComparatorTest {

    public static class Person {
        private int age;
        private String name;

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Person p = new Person();
            p.age = (int) (Math.random() * 10 + 100);
            p.name = "p" + p.age;
            persons.add(p);
        }

        System.out.println(persons);

        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.age - o2.age;
            }
        });
        System.out.println(persons);

        // type func(args1, args2)
        // (args1, args2) -> { statement1; statement2; return ... }
        // 隐藏了接口的类
        Collections.sort(persons, (o1, o2) -> {
            System.out.println("lambda");
            return o1.age - o2.age;
        });
    }
}
