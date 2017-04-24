package xunshan.anno.bind;

/**
 * Created by eldorado on 17-4-24.
 *
 * 通过注解实例化字段
 */
public class BindMain {
    @BindObjectById(Ids.STRING_A)
    String strA;

    @BindObjectById(Ids.STRING_B)
    String strB;

    @NonNull
    @BindObjectById(Ids.STRING_E)
    String strE;

    BindMain() {
        Binder.bind(this);
    }

    public static void main(String[] args) {
        BindMain main = new BindMain();
        System.out.println(main.strA);
        System.out.println(main.strB);
        System.out.println(main.strE);
    }
}
