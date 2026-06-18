import java.util.Scanner;

public class ThirteenthProblem {
    static void area(int a,int b,int c){
        double s=(a+b+c)/2.0;//semi paramter
        double area=Math.sqrt(s*(s-a)*(s-b)*(s-c));//herons formula
        System.out.println("Area of triangle: "+area);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter side A: ");
        int a=sc.nextInt();
        System.out.println("Enter side B: ");
        int b=sc.nextInt();
        System.out.println("Enter side C: ");
        int c=sc.nextInt();

        area(a,b,c);
    }
}
