import java.util.Scanner;

public class Str1Problem {
    public static void main() {
        System.out.println("Enter a string: ");
        System.out.println("Enter index: ");
        Scanner sc=new Scanner(System.in);
        String Str=sc.nextLine();
        int i=sc.nextInt();
        System.out.println(Str.charAt(i));
    }
}
