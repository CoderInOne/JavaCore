package xunshan.jvm.reference;

import java.lang.ref.WeakReference;

/**
 * Created by eldorado on 17-1-17.
 *
 * https://community.oracle.com/blogs/enicholas/2006/05/04/understanding-weak-references
 * 以图片为例，图片优先存在内存中，当图片不再显示或不在显示页面中的时候，但是保存图片的数据结构对象仍然在内存中，
 * 如果引用是强引用的话，图片将不会被释放
 * Android例子
 *    http://stackoverflow.com/a/29590774
 *    facebook的例子
 *    https://github.com/facebook/facebook-android-sdk/blob/b384c0655fe96db71229bfdcb981a522f3f1e675/facebook/src/com/facebook/login/widget/ToolTipPopup.java
 */
public class Main {
    public static void main(String[] args) {
        // Strong reference
        byte[] buffer = new byte[4 * 1024 * 1024];

        WeakReference<byte[]> weakBuffer = new WeakReference<>(buffer);
        byte[] sb = weakBuffer.get();

        System.gc();

        System.out.println(weakBuffer.get());
    }
}
