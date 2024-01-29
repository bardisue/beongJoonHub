import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int _map[][];
	static int n;
	static int m;

	static void draw(int i, int j, int paint) {
		_map[i][j] = paint;

		int[][] _next = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };

		for (int k = 0; k < 4; k++) {
			int next_i = i + _next[k][0];
			int next_j = j + _next[k][1];

			if (next_i < 0 || next_i >= n || next_j < 0 || next_j >= m) {
				continue;
			}
			if(_map[next_i][next_j] == 1) {
				draw(next_i,next_j, paint);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] _nm = br.readLine().split(" ");
		n = Integer.parseInt(_nm[0]);
		m = Integer.parseInt(_nm[1]);
		_map = new int[n][m];

		for (int i = 0; i < n; i++) {
			String[] _input = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				_map[i][j] = Integer.parseInt(_input[j]);
			}
		}
		int paint = 2;
		

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (_map[i][j] == 1) {
					draw(i, j, paint);
					paint += 1;
				}
			}
		}
		
		int[] arr_sum = new int[paint];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(_map[i][j] >=2) {
					arr_sum[_map[i][j]] += 1;
				}
			}
		}
		int result = 0;
		
		for(int i = 0; i < paint; i++) {
			if (arr_sum[i] > result) {
				result = arr_sum[i];
			}
		}
		
		System.out.println(paint-2);
		System.out.println(result);
	}
}