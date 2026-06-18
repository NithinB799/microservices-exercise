import java.util.Scanner;


public class TwentyThirdProblem {
    static boolean areVowels(String a){
        for(int i=0;i<a.length();i++) {
            char ch = a.charAt(i);
            if (ch != 'a' && ch != 'e' && ch != 'i' && ch != 'o' && ch != 'u') {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a String: ");
        String a=sc.nextLine();

        System.out.println(areVowels(a));
    }
}
