package xunshan.pattern.factory;

/**
 * Created by eldorado on 17-1-18.
 */
public class CircleFactory extends ShapeFactory {
    @Override
    protected Shape factoryMethod(String aName) {
        return new Circle(aName);
    }
}
