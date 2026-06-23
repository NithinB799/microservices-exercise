import java.util.Calendar;

public class Date3Problem {
    public static void main(String[] args) {

        Calendar cal=Calendar.getInstance();
        //cal.clear();no need of this
        System.out.println(cal.getActualMaximum(Calendar.DATE));
        System.out.println(cal.getActualMaximum(Calendar.MONTH));
        System.out.println(cal.getActualMaximum(Calendar.WEEK_OF_YEAR));
        System.out.println(cal.getActualMaximum(Calendar.YEAR));
    }
}
