import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {
	static int N, M, D;
	static int[][] _map;
	static boolean[] selected;
	static boolean[][] whereToShot;
	static int[][] _next = { { 0, -1 }, { -1, 0 }, { 0, 1 } };
	static int shootCount;
	static int _max = 0;

	static class Point {
		int i;
		int j;

		public Point(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}

	static int[][] copy() {
		int[][] _copy = new int[N + 2][M + 2];
		for (int i = 0; i < N + 2; i++) {
			for (int j = 0; j < M + 2; j++) {
				_copy[i][j] = _map[i][j];
			}
		}
		return _copy;
	}

	static void print() {
		for (int i = 0; i < N + 2; i++) {
			for (int j = 0; j < M + 2; j++) {
				System.out.print(_map[i][j] + " ");
			}
			System.out.println();
		}
	}

	static void bfs(int _j) {
		Queue<Point> _queue = new ArrayDeque<>();
		_queue.add(new Point(N + 1, _j));
		int depth = 0;
		while (!_queue.isEmpty()) {
			int _size = _queue.size();
			depth += 1;
			if(depth > D) {
				return;
			}
			for (int num = 0; num < _size; num++) {
				Point _now = _queue.poll();
//				System.out.println("i:" + _now.i + " j: " +_now.j );
				for (int ahead = 0; ahead < 3; ahead++) {
					int next_i = _now.i + _next[ahead][0];
					int next_j = _now.j + _next[ahead][1];
					if (_map[next_i][next_j] == 2) {
						continue;
					}
					if (_map[next_i][next_j] == 1) {
						whereToShot[next_i][next_j] = true;
						shootCount++;
						return;
					} else {
						_queue.add(new Point(next_i, next_j));
					}
				}
			}
		}
	}

	static void comb(int target, int idx) {
		if (target == 3) {
			int[][] _copy = copy();
			int kill_sum = 0;
			// shootCount = 0;
			for (int day = 0; day < N; day++) {
				whereToShot = new boolean[N + 2][M + 2];
				for (int selected_j = 1; selected_j <= M; selected_j++) {
//					System.out.print(selected_j + " ");
					if (selected[selected_j]) {
						bfs(selected_j);
					}
				}
//				System.out.println();
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= M; j++) {
						if (whereToShot[i][j]) {
							_map[i][j] = 0;
							kill_sum++;
						}
					}
				}

				for (int i = N; i >= 2; i--) {
					_map[i] = _map[i - 1];
				}
				_map[1] = new int[M + 2];
				_map[1][0] = 2;
				_map[1][M + 1] = 2;
			}
			_max = Math.max(_max, kill_sum);
			_map = _copy;
            return;
		}
		if (idx == M + 1) {
			return;
		}
		selected[idx] = true;
		comb(target + 1, idx + 1);
		selected[idx] = false;
		comb(target, idx + 1);
	}

	public static void main(String[] args) throws IOException {
		// ???
		// 왜이렇게 어렵나요?
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		_map = new int[N + 2][M + 2];
		selected = new boolean[M + 1];
		for (int i = 0; i < N + 2; i++) {
			for (int j = 0; j < M + 2; j++) {
				_map[i][j] = 2;

			}
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				_map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		print();
		comb(0, 1);
		System.out.println(_max);
	}
}