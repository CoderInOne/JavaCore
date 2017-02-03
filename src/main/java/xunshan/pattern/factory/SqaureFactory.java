package xunshan.pattern.factory;

/**
 * Created by eldorado on 17-1-18.
 */
public class SqaureFactory extends ShapeFactory {
    @Override
    protected Shape factoryMethod(String aName) {
        return new Square(aName);
    }
}
