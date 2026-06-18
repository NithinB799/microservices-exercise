import java.util.Calendar;

public class Date2Problem {
    public static void main(String[] args) {
        int year=2000;
        int month=0;
        int day=6;//0->Sat,1->Sun......6->Fri.
        int hour=10;
        int minute=10;

        Calendar cal=Calendar.getInstance();
        System.out.println();
        cal.clear();//remove all existing dates and time from calendar object.
        cal.set (Calendar. YEAR, year);//cal.set is used for setting year,month,date.
        cal.set (Calendar.MONTH, month);
        cal.set (Calendar.DAY_OF_WEEK, day);
        cal.set(Calendar.HOUR_OF_DAY,hour);
        cal.set(Calendar.MINUTE,minute);

        System.out.println (cal.getTime ());//convert calendar to readable dates
        System. out.println ();


    }
}
