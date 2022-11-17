import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class Main {
    public static void main(String[] args) throws IOException {
        //input
        Scanner scanner = new Scanner(System.in);
        int aX = scanner.nextInt();
        int aY = scanner.nextInt();
        int bX = scanner.nextInt();
        int bY = scanner.nextInt();
        int cX = scanner.nextInt();
        int cY = scanner.nextInt();

        //logic
        int dX = 0;
        int dY = 0;
        List<Integer> xList = new ArrayList<>();
        List<Integer> yList = new ArrayList<>();
        xList.add(aX);
        xList.add(bX);
        xList.add(cX);
        yList.add(aY);
        yList.add(bY);
        yList.add(cY);
        for (int i = 0; i < 3; i++) {
            if (Collections.frequency(xList, xList.get(i)) == 1) {
                dX = xList.get(i);
            }
        }
        for (int i = 0; i < 3; i++) {
            if (Collections.frequency(yList, yList.get(i))==1) {
                dY = yList.get(i);
            }
        }
        //output
        System.out.printf("%d %d", dX, dY);
    }
}