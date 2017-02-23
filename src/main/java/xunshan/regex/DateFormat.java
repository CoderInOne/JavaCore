package xunshan.regex;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by eldorado on 17-2-22.
 */
public class DateFormat {
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        System.out.println(l);
        SimpleDateFormat format = new SimpleDateFormat("MM:dd");
        String time = format.format(new Date(l));
        System.out.println(time);

//        String[] splits = time.split("-");
//        System.out.println(splits[0] + ":" + splits[1]);
    }
}
