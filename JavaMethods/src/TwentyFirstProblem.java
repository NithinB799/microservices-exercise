import java.util.Scanner;

public class TwentyFirstProblem {
    static void Fctrs(int a){
        while(a%3==0){
            System.out.print("3*");
            a=a/3;
        }
    }
    static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a value: ");
        int a=sc.nextInt();

        Fctrs(a);
    }
}
