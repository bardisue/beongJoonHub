import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static void _print(int[][] p) {
		for(int[] row : p) {
			for(int col : row) {
				System.out.print(col+" ");
			}
			System.out.println();
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] _map = new int[104][104];

		int N = Integer.parseInt(br.readLine());

		for (int cnt = 0; cnt < N; cnt++) {
			String[] _input = br.readLine().split(" ");
			int start_i = Integer.parseInt(_input[1]);
			int start_j = Integer.parseInt(_input[0]);

			for (int i = start_i; i < start_i + 10; i++) {
				for (int j = start_j; j < start_j + 10; j++) {
					_map[i + 2][j + 2] = 1;
				}
			}
		}

		int[][] _next = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

		int result = 0;
		for (int i = 1; i <= 102; i++) {
			for (int j = 1; j <= 102; j++) {
				if (_map[i][j] == 0) {
					int _sum = 0;
					for(int ahead = 0; ahead < 4; ahead++) {
						int next_i = i + _next[ahead][0];
						int next_j = j + _next[ahead][1];
						if(_map[next_i][next_j] == 1) {
							_sum++;
						}
					}
					if(_sum >= 1) {
						result+=_sum;
					}
				}
			}
		}
//		_print(_map);
		System.out.println(result);
	}
}