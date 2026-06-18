import java.util.Scanner;

public class NinthProblem {
    static void printchars(char start,char end){
        for(char ch=start;ch<=end;ch++){
            System.out.print(ch+" ");
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter a starting character: ");
        char start=sc.next().charAt(0);

        System.out.println("Enter a ending character: ");
        char end=sc.next().charAt(0);

        printchars(start,end);
    }
}
