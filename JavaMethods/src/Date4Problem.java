import java.util.Calendar;

public class Date4Problem {
    public static void main(String[] args) {
        Calendar cal= Calendar.getInstance();
        System.out.println(cal.getActualMinimum(Calendar.YEAR));
        System.out.println(cal.getActualMinimum(Calendar.MONTH));
        System.out.println(cal.getActualMinimum(Calendar.WEEK_OF_MONTH));
        System.out.println(cal.getActualMinimum(Calendar.DATE));
    }
}
