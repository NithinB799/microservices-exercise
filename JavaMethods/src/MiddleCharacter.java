import java.util.Scanner;

public class MiddleCharacter {
    static char MidChar(String a){
        int b=a.length();
        int c=b/2;
        return a.charAt(c);
    }
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        String a=s.nextLine();

        System.out.println(MidChar(a));
    }
}
