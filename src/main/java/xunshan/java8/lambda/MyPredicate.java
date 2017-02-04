package xunshan.java8.lambda;

import java.util.Objects;

/**
 * Created by eldorado on 17-2-4.
 * 模仿实现{@link java.util.function.Predicate}
 */
@FunctionalInterface
public interface MyPredicate<T> {

    /**
     * 输入数值t，返回true或false
     * @param t true或false
     */
    boolean test(T t);

    /**
     * 两个Predicate做与运算
     * @param other 另一个MyPredicate
     * @return
     */
    default MyPredicate<T> and(MyPredicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) && other.test(t);
    }

    /**
     * 取反操作
     * @return
     */
    default MyPredicate<T> negate() {
        return t -> !test(t);
    }

    /**
     * 是否等价的比较器
     * @param targetRef 比较目标
     * @param <T> 类型
     * @return
     */
    static <T> MyPredicate<T> isEqual(Object targetRef) {
        return (targetRef == null) ?
                object -> Objects.isNull(object) : // -> Objects::isNull静态方法常用
                object -> object.equals(targetRef);
    }
}
