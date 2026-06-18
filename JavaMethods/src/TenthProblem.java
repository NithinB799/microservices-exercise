import java.util.Scanner;

public class TenthProblem {
    static void leapyear(int year){
        if(year%400==0){
            System.out.println(year+" is leap year");
        }
        else if(year%4==0) {
            System.out.println(year + " is leap year");
        }
        else if(year%100==0){
            System.out.println(year+" leap year");
        }
        else{
            System.out.println("Not leap year");
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter year");
        int year=sc.nextInt();

        leapyear(year);
    }
}
