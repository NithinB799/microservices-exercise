import java.util.Scanner;
public class SmallestNum {
    static void smallest(int a,int b,int c){
        if(a<b&&a<c){
            System.out.println("a is smaller");
        }
        else if(b<a&&b<c){
            System.out.println("b is smaller");
        }
        else if(c<a&&c<b){
            System.out.println("c is smaller");
        }
    }
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);

        System.out.println("Enter a:");
        int a=s.nextInt();

        System.out.println("Enter b:");
        int b=s.nextInt();

        System.out.println("Enter a:");
        int c=s.nextInt();

        smallest(a,b,c);

    }
}
