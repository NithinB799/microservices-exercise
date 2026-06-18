import java.util.Scanner;

public class noOfWordsInStr {
    static void wordCt(String s){
        String[] words=s.split(" ");//use string.split().
        System.out.println(words.length);

    }
    static void main(String[] args) {
        System.out.println("Enter a String: ");
        Scanner s=new Scanner(System.in);
        String t=s.nextLine();

        wordCt(t);
    }
}
