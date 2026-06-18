import java.util.Scanner;

public class Str2Problem {
    public static void main(String[] args) {
        String String1;
        String String2;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter String 1:");
        String1=sc.nextLine();
        System.out.println("Enter String 2:");
        String2=sc.nextLine();
        if(String1.compareToIgnoreCase(String2)==0){//remember logic
            System.out.println(String1+" is equal to "+String2);
        }else{
            System.out.println("Not equal");
        }

    }
}
