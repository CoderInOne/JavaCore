package xunshan.anno.bind;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by eldorado on 17-4-24.
 *
 * A simple bind annotation like android @BindViewById
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface BindObjectById {
    int value() default 0;
}
