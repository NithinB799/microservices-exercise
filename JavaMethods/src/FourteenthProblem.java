import java.util.Scanner;

public class FourteenthProblem {
    static void Pentarea(int side) {
    double area = (5 * side * side) /
            (4 * Math.tan(Math.PI / 5));
        System.out.println("Area: "+area);
}
    static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Side: ");
        int side=sc.nextInt();

        Pentarea(side);

    }
}
