import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {

	static int N;
	static int L;
	static int R;

	static int[][] _map;
	static int[][] next_map;

	static class Node {
		int i;
		int j;

		public Node(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}

	static void _print(int[][] p) {
		System.out.println("-------------------------------------------------------------------------------");
		for (int[] p1 : p) {
			for (int p2 : p1) {
				System.out.print(p2 + " ");
			}
			System.out.println();
		}
	}

	static int[][] _next = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	static void bfs(int i, int j) {
		Node node = new Node(i, j);
		Queue<Node> _queue = new ArrayDeque<>();
		Queue<Node> paint_queue = new ArrayDeque<>();
		_queue.add(node);
		next_map[i][j] = -1;
		int _sum = 0;
		while (!_queue.isEmpty()) {
			Node tmp = _queue.poll();
			//System.out.println(tmp.toString());
			int tmp_value = _map[tmp.i][tmp.j];
			_sum += tmp_value;
			for (int ahead = 0; ahead < 4; ahead++) {
				int next_i = tmp.i + _next[ahead][0];
				int next_j = tmp.j + _next[ahead][1];
				int _sub = Math.abs(tmp_value - _map[next_i][next_j]);
				if (L <= _sub && _sub <= R) {
					if (next_map[next_i][next_j] != -1) {
						next_map[next_i][next_j] = -1;
						_queue.add(new Node(next_i, next_j));
					}
				}
			}
			paint_queue.add(tmp);
		}
		int mod_val = _sum / paint_queue.size();
		while (!paint_queue.isEmpty()) {
			Node tmp = paint_queue.poll();
			next_map[tmp.i][tmp.j] = mod_val;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] _input = br.readLine().split(" ");

		N = Integer.parseInt(_input[0]);
		L = Integer.parseInt(_input[1]);
		R = Integer.parseInt(_input[2]);

		_map = new int[N + 2][N + 2];
		next_map = new int[N + 2][N + 2];

		for (int i = 0; i < N + 2; i++) {
			for (int j = 0; j < N + 2; j++) {
				_map[i][j] = Integer.MAX_VALUE;
				next_map[i][j] = Integer.MAX_VALUE;
			}
		}

		for (int i = 1; i <= N; i++) {
			_input = br.readLine().split(" ");
			for (int j = 1; j <= N; j++) {
				_map[i][j] = Integer.parseInt(_input[j-1]);
			}
		}
		
		
		int result = 0;
		while (true) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					bfs(i, j);
				}
			}
//			_print(_map);
//			_print(next_map);
			if (Arrays.deepEquals(next_map, _map)) {
				break;
			}
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					_map[i][j] = next_map[i][j];
				}
			}
			result++;
		}
		System.out.println(result);
	}
}