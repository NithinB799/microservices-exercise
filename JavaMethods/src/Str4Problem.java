import java.util.Scanner;

public class Str4Problem {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter String1: ");
        System.out.println("Enter String2: ");
        String String1=sc.nextLine();
        String String2=sc.nextLine();
        System.out.println(String1.endsWith(String2));//method to use,returns true or false.
    }
}
