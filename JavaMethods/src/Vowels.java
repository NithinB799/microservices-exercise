import java.util.Scanner;
public class Vowels {
    static void vowelCount(String s) {
        int count=0;
        for (int i = 0; i < s.length(); i++) {
            char ch=s.charAt(i);

            if(ch=='a'||ch=='e'||ch=='i'||
                    ch=='o'||ch=='u'){
                count++;
            }
        }
        System.out.println("Number of vowels in the string: "+count);
    }
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        String t=s.nextLine();

        vowelCount(t);
    }
}
