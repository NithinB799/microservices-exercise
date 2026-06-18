import java.util.Scanner;
public class Average {
    static void avg(int a,int b,int c){
        int avgr=(a+b+c)/3;
        System.out.println("Average "+avgr);
    }
    static void main(String[] args) {
        Scanner s=new Scanner(System.in);

        System.out.println("Enter a:");
        int a=s.nextInt();

        System.out.println("Enter b:");
        int b=s.nextInt();

        System.out.println("Enter c:");
        int c=s.nextInt();

        avg(a,b,c);

    }
}
