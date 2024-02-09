import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static int N, M, H;
	static int[][][] _map;
	static int _target;
	static int _sum;
	static int _result = -1;

	static class Tomato {
		int h;
		int i;
		int j;
		int day;

		public Tomato(int h, int i, int j, int day) {
			super();
			this.i = i;
			this.j = j;
			this.h = h;
			this.day = day;
		}
	}

	static Queue<Tomato> _queue;

	static void bfs() {
		int[][] _next = { { 1, 0, 0 }, { -1, 0, 0 }, { 0, 1, 0 }, { 0, -1, 0 }, { 0, 0, 1 }, { 0, 0, -1 } };
		while (!_queue.isEmpty()) {
			Tomato tmp = _queue.poll();
			int tmp_h = tmp.h;
			int tmp_i = tmp.i;
			int tmp_j = tmp.j;
			int tmp_day = tmp.day;
//			System.out.println(tmp_h + " " +tmp_i+ " " + tmp_j);
			_sum++;
			if(_sum == _target) {
				_result = tmp_day;
				return;
			}
			for (int ahead = 0; ahead < 6; ahead++) {
				int next_h = tmp_h + _next[ahead][0];
				int next_i = tmp_i + _next[ahead][1];
				int next_j = tmp_j + _next[ahead][2];
				if(_map[next_h][next_i][next_j] == 0) {
					_map[next_h][next_i][next_j] = 1;
					_queue.add(new Tomato(next_h, next_i, next_j, tmp_day+1));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] mnh = br.readLine().split(" ");
		N = Integer.parseInt(mnh[1]);
		M = Integer.parseInt(mnh[0]);
		H = Integer.parseInt(mnh[2]);
		_queue = new ArrayDeque<>();
		_map = new int[H + 2][N + 2][M + 2];
		for (int k = 0; k < H + 2; k++) {
			for (int i = 0; i < N + 2; i++) {
				for (int j = 0; j < M + 2; j++) {
					_map[k][i][j] = -1;
				}
			}
		}

		for (int k = 1; k <= H; k++) {
			for (int i = 1; i <= N; i++) {
				String[] _input = br.readLine().split(" ");
				for (int j = 1; j <= M; j++) {
					_map[k][i][j] = Integer.parseInt(_input[j - 1]);
					if (_map[k][i][j] != -1) {
						_target++;
					}
					if (_map[k][i][j] == 1) {
						_queue.add(new Tomato(k, i, j, 0));
					}
				}
			}
		}
		bfs();
		System.out.println(_result);
	}
}