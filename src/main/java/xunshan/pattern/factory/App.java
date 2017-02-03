package xunshan.pattern.factory;

/**
 * Created by eldorado on 17-1-18.
 *
 * https://www.ibm.com/developerworks/cn/java/designpattern/factory/
 *
 * 练习：
 *     模仿Android的数据库访问模型
 */
public class App {
    public static void main(String[] args) {
        ShapeFactory f1 = new CircleFactory();
        ShapeFactory f2 = new SqaureFactory();

        f1.anOperation("circle action");
        f2.anOperation("square action");
    }
}
