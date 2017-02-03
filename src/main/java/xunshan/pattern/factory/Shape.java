package xunshan.pattern.factory;

/**
 * Created by eldorado on 17-1-18.
 */
public abstract class Shape {
     private String name;

    public Shape(String name) {
        System.out.println("A New Shape:" + name);
        this.name = name;
    }

    public abstract void draw();
    public abstract void erase();
}
