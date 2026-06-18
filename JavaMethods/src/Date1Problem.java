import java.util.*;
public class Date1Problem {
    public static void main(String[] args) {
        int year=1999;
        int month=10;//Means January(intial month)
        int date=10;

        Calendar cal=Calendar.getInstance();
        System.out.println();
        cal.clear();//remove all existing dates and time from calendar object.
        cal.set (Calendar. YEAR, year);//cal.set is used for setting year,month,date.
        cal.set (Calendar.MONTH, month);
        cal.set (Calendar.DATE, date);
        System.out.println (cal.getTime ());//convert calendar to readable dates
        System. out.println ();
    }
}
