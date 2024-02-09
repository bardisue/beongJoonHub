import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
	static int[][] _map;
	static int N;
	static int M;
	static List<Node> _list;
	static int result = 0;

	static int bfs() {
		Queue<Node> _queue = new ArrayDeque<>();
		int[][] copy_map = new int[N + 2][M + 2];
		for (int i = 0; i < N + 2; i++) {
			for (int j = 0; j < M + 2; j++) {
				copy_map[i][j] = _map[i][j];
				if (copy_map[i][j] == 2) {
					_queue.add(new Node(i, j));
				}
			}
		}
		int[][] _next = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };

		while (!_queue.isEmpty()) {
			Node tmp = _queue.poll();
			int tmp_i = tmp.i;
			int tmp_j = tmp.j;
			for (int ahead = 0; ahead < 4; ahead++) {
				int next_i = tmp_i + _next[ahead][0];
				int next_j = tmp_j + _next[ahead][1];
				if (copy_map[next_i][next_j] == 0) {
					copy_map[next_i][next_j] = 2;
					_queue.add(new Node(next_i, next_j));
				}
			}
		}
		int result = 0;
		for (int i = 0; i < N + 2; i++) {
			for (int j = 0; j < M + 2; j++) {
				if (copy_map[i][j] == 0) {
					result++;
				}
			}
		}
		return result;
	}

	static void comb(int idx, int target) {
		if (target == 3) {
			int safe = bfs();
			result = Math.max(result, safe);
			return;
		}
		if (idx == _list.size()) {
			return;
		}
		Node tmp = _list.get(idx);
		_map[tmp.i][tmp.j] = 1;
		comb(idx + 1, target + 1);
		_map[tmp.i][tmp.j] = 0;
		comb(idx + 1, target);
	}

	static class Node {
		int i;
		int j;

		public Node(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);
		_map = new int[N + 2][M + 2];
		_list = new ArrayList<>();

		for (int i = 0; i < N + 2; i++) {
			for (int j = 0; j < M + 2; j++) {
				_map[i][j] = 1;
			}
		}

		for (int i = 1; i <= N; i++) {
			String[] _input = br.readLine().split(" ");
			for (int j = 1; j <= M; j++) {
				_map[i][j] = Integer.parseInt(_input[j - 1]);
				if (_map[i][j] == 0) {
					_list.add(new Node(i, j));
				}
			}
		}

		comb(0, 0);
		System.out.println(result);
	}
}