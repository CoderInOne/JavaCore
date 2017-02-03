package xunshan.pattern.factory;

/**
 * Created by eldorado on 17-1-18.
 */
public abstract class ShapeFactory {
    protected abstract Shape factoryMethod(String aName);

    public void anOperation(String aName) {
        Shape s = factoryMethod(aName);
        System.out.println("operation");

        s.draw();
        s.erase();
    }
}
