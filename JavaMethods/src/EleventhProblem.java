import java.util.Scanner;

public class EleventhProblem {
    static boolean isValidPassword(String password){
        int digitCount=0;
        //Atleast 8 characters
        if(password.length()<8){
            return false;
        }
        for(int i=0;i<password.length();i++){
            char ch=password.charAt(i);
        //only letters and digits
            if(!Character.isLetterOrDigit(ch)){
                return false;
            }
        //count digits
        if(Character.isDigit(ch)){
            digitCount++;
        }
        }
        return digitCount>=2;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Input a password: ");
        String password=sc.nextLine();

        if(isValidPassword(password)){
            System.out.println("Password is valid");
        }else{
            System.out.println("Password is not valid");
        }
    }
}
