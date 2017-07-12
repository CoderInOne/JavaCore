package xunshan.di;

import java.util.HashMap;

public class ClassInjectList {
    private HashMap<String, Object> classObjectHashMap = new HashMap<>();

    public void addClass(Class<?> clazz) {
        try {
            // find proper constructor to instantiate
            Object obj = clazz.newInstance();

            // register instantiate object
            this.classObjectHashMap.put(clazz.getCanonicalName(), obj);
            // considering interface too
            Class<?>[] interfaces = clazz.getInterfaces();
            for (Class<?> cls : interfaces) {
                this.classObjectHashMap.put(cls.getCanonicalName(), obj);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Object findObject(String className) {
        return classObjectHashMap.get(className);
    }
}
