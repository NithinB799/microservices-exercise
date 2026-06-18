import java.util.Scanner;

public class EighteenthProblem {
    static void Consec(int a,int b,int c){
        if(b==a+1&&c==b+1){
            System.out.println("True");
        }else{
            System.out.println("False");
        }

    }
    static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter 1st num: ");
        int a=sc.nextInt();
        System.out.println("Enter 2nd num: ");
        int b=sc.nextInt();
        System.out.println("Enter 3rd num: ");
        int c=sc.nextInt();

        Consec(a,b,c);


    }
}
