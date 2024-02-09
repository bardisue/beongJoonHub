import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static Character[][] _map;
	static Character[][] rg_map;

	static int[][] _next = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	static void dfs(int i, int j, char color, Character[][] p) {
//		System.out.println(i + " " + j + " " + color);
		p[i][j] = '0';
		for (int ahead = 0; ahead < 4; ahead++) {
			int next_i = i + _next[ahead][0];
			int next_j = j + _next[ahead][1];
			if (p[next_i][next_j] != null) {
				if (p[next_i][next_j] == color) {
					dfs(next_i, next_j, color, p);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		_map = new Character[N + 2][N + 2];
		rg_map = new Character[N + 2][N + 2];

		for (int i = 1; i <= N; i++) {
			String _input = br.readLine();
			for (int j = 1; j <= N; j++) {
				_map[i][j] = _input.charAt(j - 1);
				rg_map[i][j] = _input.charAt(j - 1);
				if (rg_map[i][j] == 'R') {
					rg_map[i][j] = 'G';
				}
			}
		}

		int result = 0;
		int rg_result = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (!(_map[i][j] == '0')) {
					dfs(i, j, _map[i][j], _map);
					result++;
				}
				if (!(rg_map[i][j] == '0')) {
					dfs(i, j, rg_map[i][j], rg_map);
					rg_result++;
				}
			}
		}
		System.out.println(result + " " + rg_result);
	}
}