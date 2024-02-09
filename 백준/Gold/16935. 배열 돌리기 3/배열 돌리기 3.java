import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class Main {
	static int N, M, R;
	static int[][] _map;
	static int[][] copy_map;

	static void mod2() {
		copy_map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy_map[i][M - 1 - j] = _map[i][j];
			}
		}
		_map = copy_map;
	}

	static void mod1() {
		copy_map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy_map[N - 1 - i][j] = _map[i][j];
			}
		}
		_map = copy_map;
	}

	static void mod3() {
		copy_map = new int[M][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int depth = Math.min(Math.min((N - i - 1), i), Math.min(j, M - j - 1));
				int _len = N - 1;
				copy_map[j][_len - i] = _map[i][j];
			}
		}
		_map = copy_map;
	}

	static void mod4() {
		copy_map = new int[M][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int depth = Math.min(Math.min((N - i - 1), i), Math.min(j, M - j - 1));
				int _len = M - 1;
				copy_map[_len - j][i] = _map[i][j];
			}
		}
		_map = copy_map;
	}

	static void mod5() {
		copy_map = new int[N][M];
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				copy_map[i][j + (M / 2)] = _map[i][j];
			}
		}
		for (int i = 0; i < N / 2; i++) {
			for (int j = M / 2; j < M; j++) {
				copy_map[i + (N / 2)][j] = _map[i][j];
			}
		}
		for (int i = N / 2; i < N; i++) {
			for (int j = M / 2; j < M; j++) {
				copy_map[i][j - (M / 2)] = _map[i][j];
			}
		}
		for (int i = N / 2; i < N; i++) {
			for (int j = 0; j < M / 2; j++) {
				copy_map[i - (N / 2)][j] = _map[i][j];
			}
		}
		_map = copy_map;
	}

	static void mod6() {
		copy_map = new int[N][M];
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				copy_map[i + (N / 2)][j] = _map[i][j];
			}
		}
		for (int i = 0; i < N / 2; i++) {
			for (int j = M / 2; j < M; j++) {
				copy_map[i][j - (M / 2)] = _map[i][j];
			}
		}
		for (int i = N / 2; i < N; i++) {
			for (int j = M / 2; j < M; j++) {
				copy_map[i - (N / 2)][j] = _map[i][j];
			}
		}
		for (int i = N / 2; i < N; i++) {
			for (int j = 0; j < M / 2; j++) {
				copy_map[i][j + (M / 2)] = _map[i][j];
			}
		}
		_map = copy_map;
	}

	static void _print() {
		int N = _map.length;
		int M = _map[0].length;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(_map[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NMR = br.readLine().split(" ");
		N = Integer.parseInt(NMR[0]);
		M = Integer.parseInt(NMR[1]);
		R = Integer.parseInt(NMR[2]);
		_map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String[] _input = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				_map[i][j] = Integer.parseInt(_input[j]);
			}
		}
		String[] _input = br.readLine().split(" ");
		for (String str : _input) {
			if (str.equals("1"))
				mod1();
			if (str.equals("2"))
				mod2();
			if (str.equals("3"))
				mod3();
			if (str.equals("4"))
				mod4();
			if (str.equals("5"))
				mod5();
			if (str.equals("6"))
				mod6();
			N = _map.length;
			M = _map[0].length;
		}
		_print();
	}
}