import javax.swing.text.DateFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FifteenthProblem {
    static void main(String[] args) {
        LocalDateTime now=LocalDateTime.now();//2

        DateTimeFormatter format=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.println(now.format(format));

    }
}
