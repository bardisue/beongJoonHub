import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            //input
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            if (a == 0 && b == 0 && c == 0) {
                break;
            }
            //logic
            List<Integer> lengths = new ArrayList<>();
            lengths.add(a);
            lengths.add(b);
            lengths.add(c);
            int max = Collections.max(lengths);
            lengths.remove(Integer.valueOf(max));
            if (max*max == lengths.get(0)*lengths.get(0)+lengths.get(1)*lengths.get(1)){
                //output
                System.out.println("right");
            }
            else{
                //output
                System.out.println("wrong");
            }
        }
    }
}