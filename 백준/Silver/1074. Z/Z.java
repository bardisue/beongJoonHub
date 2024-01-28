import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int r;
	static int c;

	static void drawRec(int n, int _x, int _y, int _num) {
		if (n == 1) {
			if (r == _x && c == _y) {
				System.out.println(_num);
			} else if (r == _x + 1 && c == _y) {
				System.out.println(_num + 1);
			} else if (r == _x && c == _y + 1) {
				System.out.println(_num + 2);
			} else if (r == _x + 1 && c == _y + 1) {
				System.out.println(_num + 3);
			}
		} else {
			int _mod = (int) Math.pow(4, n - 1);
			int _plus = (int) Math.pow(2, n - 1);
			if (r < _x + _plus && c < _y + _plus){
				drawRec(n - 1, _x, _y, _num + _mod * 0);
			}
			else if (r < _x + _plus*2 && c < _y + _plus){
				drawRec(n - 1, _x + _plus, _y, _num + _mod * 1);
			}
			else if(r < _x + _plus && c < _y + _plus*2) {
				drawRec(n - 1, _x, _y + _plus, _num + _mod * 2);
			}else {
				drawRec(n - 1, _x + _plus, _y + _plus, _num + _mod * 3);
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] _input = br.readLine().split(" ");
		int n = Integer.parseInt(_input[0]);
		c = Integer.parseInt(_input[1]);
		r = Integer.parseInt(_input[2]);

		drawRec(n, 0, 0, 0);
	}

}