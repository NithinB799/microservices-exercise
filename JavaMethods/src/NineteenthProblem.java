import java.util.Scanner;

public class NineteenthProblem {
    static void MidPt(int a,int b,int c){
        if(b-a==c-b){
            System.out.println("True");
        }else{
            System.out.println("False");
        }

    }
    static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter First Number: ");
        int a=sc.nextInt();
        System.out.println("Enter Second Number: ");
        int b=sc.nextInt();
        System.out.println("Enter Third Number: ");
        int c=sc.nextInt();

        MidPt(a,b,c);
    }
}
