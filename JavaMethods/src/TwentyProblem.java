import java.util.Scanner;

public class TwentyProblem {
    static void FirstDgt(int a){
        a=Math.abs(a);
        while(a>=10){
            a=a/10;
        }
        System.out.println(a);
    }
    static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a value: ");
        int a=sc.nextInt();

        FirstDgt(a);
    }
}
