package cn.qisee.codec;

import java.util.regex.Pattern;

public class PatternTest {
    public static void main(String[] args) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        calendar.add(Calendar.DATE, 10);
//        System.out.println("current date plus 10 days is" + calendar.getTime());
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date();
//        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//        System.out.println(dateFormat.format(date));
//        System.out.println(date.getTime());
//        System.out.println(date);
//        System.out.println("----");
//        Date date1 = new Date();
//        System.out.println(date1.getTime());
//        System.out.println(Pattern.matches("java [^;&|]*", "java -cp xxx.jar"));
//        System.out.println(Pattern.matches("java [^;&|]*", "java -cp xxx.jar ; xxx"));
//        System.out.println(Pattern.matches("java [^;&|]*", "java -cp xxx.jar & xxx"));
//        System.out.println(Pattern.matches("java [^;&|]*", "java -cp xxx.jar | xxx"));
        System.out.println(Pattern.matches("/bin/bash -c ip neigh show \\| awk '\\{print \\$1, \\$5}'[^;$|]*", "/bin/bash -c ip neigh show | awk '{print $1, $5}'"));
    }
}
