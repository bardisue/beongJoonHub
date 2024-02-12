import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int test_case = 0; test_case < 4; test_case++) {
			String[] _input = br.readLine().split(" ");
			
			int x1 = Integer.parseInt(_input[0]);
			int x2 = Integer.parseInt(_input[2]);
			int x3 = Integer.parseInt(_input[4]);
			int x4 = Integer.parseInt(_input[6]);
			
			int y1 = Integer.parseInt(_input[1]);
			int y2 = Integer.parseInt(_input[3]);
			int y3 = Integer.parseInt(_input[5]);
			int y4 = Integer.parseInt(_input[7]);
			
			if(y3 > y2 || y4 < y1 || x3 > x2 || x4 < x1) {
				System.out.println("d");
			}
			else if((x1 == x4 && y1 == y4) || (x1 == x4 && y2 == y3)
					|| (x2 == x3 && y1 == y4) ||(x2 == x3 &&y2 ==y3)) {
				System.out.println("c");
			}
			else if(x1 == x4 || x2 == x3 || y2 == y3 || y4 == y1) {
				System.out.println("b");
			}
			else {
				System.out.println("a");
			}
		}
	}
}