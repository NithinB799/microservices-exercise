import java.util.Calendar;
import java.util.TimeZone;

public class Date5Problem {
    public static void main(String[] args) {
        TimeZone tz= TimeZone.getTimeZone("America/New_York");

        Calendar cal= Calendar.getInstance(tz);

        System.out.println(cal.getTime());
    }
}
