package xunshan.pattern.factory;

/**
 * Created by eldorado on 17-1-18.
 */
public class Circle extends Shape {
    public Circle(String name) {
        super(name);
    }

    @Override
    public void draw() {
        System.out.println("draw a circle");
    }

    @Override
    public void erase() {
        System.out.println("erase a circle");
    }
}
