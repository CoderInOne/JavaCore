package xunshan.pattern.factory;

/**
 * Created by eldorado on 17-1-18.
 */
public class Square extends Shape {

    public Square(String name) {
        super(name);
    }

    @Override
    public void draw() {
        System.out.println("draw a square");
    }

    @Override
    public void erase() {
        System.out.println("erase a square");
    }
}
