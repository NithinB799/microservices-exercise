import java.util.Scanner;

public class Twentysecondquery {
    static boolean dgtEven(int a){
        while(a>0){
            int digit=a%10;//extracting each digit.

            if(digit%2!=0){
                return false;
            }
            a=a/10;//remove digit.
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter integer: ");
        int a=sc.nextInt();

        System.out.println(dgtEven(a));
    }
}
