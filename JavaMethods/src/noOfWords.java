import java.util.Scanner;
public class noOfWords {
    static void sumDigit(int t){
        int sum=0;
        while(t>0){
            int digit=t%10;
            sum=sum+digit;
            t=t/10;
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int t=s.nextInt();

        sumDigit(t);

    }
}
