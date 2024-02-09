import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] _map = new int[100][100];

		int N = Integer.parseInt(br.readLine());

		for (int cnt = 0; cnt < N; cnt++) {
			String[] _input = br.readLine().split(" ");
			int start_i = Integer.parseInt(_input[1]);
			int start_j = Integer.parseInt(_input[0]);

			for (int i = start_i; i < start_i + 10; i++) {
				for (int j = start_j; j < start_j + 10; j++) {
					_map[i][j] = 1;
				}
			}
		}
		int result = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (_map[i][j] == 1) {
					result++;
				}
			}
		}
		System.out.println(result);
	}
}