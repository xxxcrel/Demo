package beer.cheese.codec;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class PatternTest {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 10);
        System.out.println("current date plus 10 days is" + calendar.getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        System.out.println(dateFormat.format(date));
        System.out.println(date.getTime());
        System.out.println(date);
        System.out.println("----");
        Date date1 = new Date();
        System.out.println(date1.getTime());
        System.out.println(Pattern.matches("java [^;&|]*", "java -cp xxx.jar"));
        System.out.println(Pattern.matches("java [^;&|]*", "java -cp xxx.jar ; xxx"));
        System.out.println(Pattern.matches("java [^;&|]*", "java -cp xxx.jar & xxx"));
        System.out.println(Pattern.matches("java [^;&|]*", "java -cp xxx.jar | xxx"));
    }
}
