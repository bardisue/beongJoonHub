import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//음.....
//대충?
//방정식에.... x1, x2를 넣어서 y2개를 넣는다.
//나오는 y2개를 min max로 나눈다.
//y1, y2가 y1<y2라고 한다.
//max가 y1보다 작고, min이 y2보다 크다면?
//안지나간다고 할 수 있겠습니다.
//한 점만 지나는건 check하자. edge case가 아닐까?
//
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		boolean result = true;

		String[] abc = br.readLine().split(" ");
		int a = Integer.parseInt(abc[0]);
		int b = Integer.parseInt(abc[1]);
		int c = Integer.parseInt(abc[2]);

		String[] _input = br.readLine().split(" ");

		int x1 = Integer.parseInt(_input[0]);
		int x2 = Integer.parseInt(_input[1]);
		int y1 = Integer.parseInt(_input[2]);
		int y2 = Integer.parseInt(_input[3]);

		int min_x = Math.min(x1, x2);
		int max_x = Math.max(x1, x2);
		int min_y = Math.min(y1, y2);
		int max_y = Math.max(y1, y2);

		if (b == 0) {
			double x = -c / a;

			if (x > min_x && x < max_x) {
				result = false;
			}
		}

		double mod_y1 = -(double) (a * x1 + c) / (double) b;
		double mod_y2 = -(double) (a * x2 + c) / (double) b;

		double min_modY = Math.min(mod_y1, mod_y2);
		double max_modY = Math.max(mod_y1, mod_y2);

		if (max_modY > min_y && min_modY < max_y) {
//			System.out.println(max_modY);
//			System.out.println(min_y);
//			System.out.println(min_modY);
//			System.out.println(max_y);
			result = false;
		}

		if (result) {
			System.out.println("Lucky");
		} else {
			System.out.println("Poor");
		}
	}
}