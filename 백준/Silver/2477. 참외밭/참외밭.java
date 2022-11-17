import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int tmpX = 0;
        int tmpY = 0;
        int saveX = 0;
        int countY = 0;
        List<Integer> xList = new ArrayList<>();
        List<Integer> yList = new ArrayList<>();
        int per = scanner.nextInt();
        int[][] point;
        point = new int[6][2];
        for(int i = 0; i < 6; i++){
            //input
            int direction = scanner.nextInt();
            int distance = scanner.nextInt();
            //logic
            if(direction == 1){
                tmpX = tmpX + distance;
                xList.add(tmpX);
            }
            if(direction == 2){
                tmpX = tmpX - distance;
                xList.add(tmpX);
            }
            if(direction == 3){
                tmpY = tmpY - distance;
                yList.add(tmpY);
            }
            if(direction == 4){
                tmpY = tmpY + distance;
                yList.add(tmpY);
            }
            point[i][0] = tmpX;
            point[i][1] = tmpY;
        }
        int index = 0;
        Collections.sort(yList);
        Collections.sort(xList);
        for(int i = 0; i < 6; i++){
            if(point[i][0] ==xList.get(1) && point[i][1] == yList.get(1)){
                index = i;
            }
        }
        int short1 = Math.abs(point[(index+1)%6][0]-point[index][0]) + Math.abs(point[(index+1)%6][1]-point[index][1]);
        int short2 = Math.abs(point[(index+5)%6][0]-point[index][0]) + Math.abs(point[(index+5)%6][1]-point[index][1]);
        int longW = Math.abs(point[(index+2)%6][0]-point[(index+4)%6][0]);
        int longH = Math.abs(point[(index+2)%6][1]-point[(index+4)%6][1]);
        int answer = (Math.abs(longH*longW) - Math.abs(short1*short2))*per;

        //output
        System.out.println(answer);
    }
}