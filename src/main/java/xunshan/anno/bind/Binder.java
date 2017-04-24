package xunshan.anno.bind;

import java.lang.reflect.Field;

/**
 * Created by eldorado on 17-4-24.
 *
 * Initial field by annotation process
 */
public class Binder {

    public static void bind(BindMain target) {
        final Field[] fields = target.getClass().getDeclaredFields();

        for (Field f : fields) {
            Class<?> type = f.getType();

            if (!String.class.isAssignableFrom(type)) {
                System.out.println("string can not assigned from " + type);
                continue;
            }

            try {
                BindObjectById anno = f.getAnnotation(BindObjectById.class);
                if (anno == null) {
                    continue;
                }

                f.setAccessible(true);

                if (anno.value() != 0) {
                    String stringById = StringFinder.findStringById(anno.value());
                    checkNonNull(f, stringById);
                    f.set(target, stringById);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 如果字段有非空注解，且值为空则报错
     * @param f 目标字段
     * @param stringById 值
     */
    private static void checkNonNull(Field f, String stringById) {
        NonNull nonNullAnno = f.getAnnotation(NonNull.class);
        if (nonNullAnno != null && stringById == null) {
            throw new RuntimeException("field can not be null!");
        }
    }
}
