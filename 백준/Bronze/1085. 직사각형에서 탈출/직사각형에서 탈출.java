import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class Main {
    public static void main(String[] args) throws IOException {
        //input
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int w = scanner.nextInt();
        int h = scanner.nextInt();
        List<Integer> length = new ArrayList<>();
        length.add(Math.abs(y));
        length.add(Math.abs(x));
        length.add(Math.abs(y-h));
        length.add(Math.abs(x-w));
        //logic

        int answer = Collections.min(length);

        //output
        System.out.println(answer);
    }
}