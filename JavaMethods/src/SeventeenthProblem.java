import java.util.Scanner;

public class SeventeenthProblem {
    static void ctDigit(int number){
        int count=0;
        while(number>0){
            int digit=number%10;//extracts last digit
            if(digit==2){
                count++;
            }
            number=number/10;//removes last digit
        }
        System.out.println("Count: "+count);
    }
    static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a number: ");
        int number=sc.nextInt();

        ctDigit(number);
    }

}
