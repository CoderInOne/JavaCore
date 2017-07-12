package xunshan.di;

import java.lang.reflect.Field;

public class Binder {
    public static void bind(Object app, ClassInjectList list) {
        Field[] fields = app.getClass().getDeclaredFields();
        for (Field f : fields) {
            Inject injectAnno = f.getAnnotation(Inject.class);
            if (injectAnno != null) {
                try {
                    Object object = list.findObject(f.getType().getCanonicalName());
                    f.set(app, object);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // do nothing
            }
        }
    }
}
