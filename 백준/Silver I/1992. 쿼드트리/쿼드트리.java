import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int N;
	static int[][] _map;
	static BufferedWriter bw;

	static void quardTree(int n, int _i, int _j) throws IOException {
		int inital = _map[_i][_j];

		for (int i = _i; i < _i + n; i++) {
			for (int j = _j; j < _j + n; j++) {
				if (_map[i][j] != inital) {
					bw.write("(");
					quardTree(n / 2, _i, _j);
					quardTree(n / 2, _i, _j + n / 2);
					quardTree(n / 2, _i + n / 2, _j);
					quardTree(n / 2, _i + n / 2, _j + n / 2);
					bw.write(")");
					return;
				}
			}
		}
		bw.write(inital+"");
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		_map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String _input = br.readLine();
			for (int j = 0; j < N; j++) {
				_map[i][j] = _input.charAt(j) - '0';
			}
		}

		quardTree(N, 0, 0);
		bw.flush();
	}
}