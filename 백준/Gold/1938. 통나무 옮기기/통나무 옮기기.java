import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Main {

	static int[][] _next = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static Character[][] _map;
	static List<String> woods;
	static Wood end_wood;
	static int result = Integer.MAX_VALUE;

	static class Part {
		int i;
		int j;

		public Part(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

		public Part() {
			super();
		}

		public Part(Part original) {
			this.i = original.i;
			this.j = original.j;
		}

		public boolean equals(Part obj) {
			// TODO Auto-generated method stub
			if (i == obj.i && j == obj.j) {
				return true;
			} else {
				return false;
			}
		}
	}

	static class Wood {
		Part middle = new Part();
		Part top = new Part();
		Part right = new Part();
		Part bottom = new Part();
		Part left = new Part();;
		Part[] body = { top, right, bottom, left, middle };
		int depth = 0;
		boolean[] isActive;

		public Wood(Wood original) {
			this.middle = new Part(original.middle);
			this.top = new Part(original.top);
			this.right = new Part(original.right);
			this.bottom = new Part(original.bottom);
			this.left = new Part(original.left);
			this.body = new Part[] { this.top, this.right, this.bottom, this.left, this.middle };
			this.depth = original.depth;
			this.isActive = new boolean[] { original.isActive[0], original.isActive[1], original.isActive[2],
					original.isActive[3], original.isActive[4] };
		}

		public Wood() {
			isActive = new boolean[5];
			isActive[1] = false;
			isActive[2] = false;
			isActive[3] = false;
			isActive[0] = false;
			isActive[4] = true;
		}

		public boolean moveTo(int ahead) {
			for (int idx = 0; idx < 5; idx++) {
				Part _part = body[idx];
				if (isActive[idx] == false) {
					continue;
				}
				int next_i = _part.i + _next[ahead][0];
				int next_j = _part.j + _next[ahead][1];
				if (_map[next_i][next_j] == '1') {
					return false;
				}
			}
			for (int idx = 0; idx < 5; idx++) {
				Part _part = body[idx];
				int next_i = _part.i + _next[ahead][0];
				int next_j = _part.j + _next[ahead][1];
				_part.i = next_i;
				_part.j = next_j;
			}
			return true;
		}

		public boolean rotate() {
			if (isActive[0] == false) {
				for (int idx = 0; idx < 5; idx++) {
					Part _part = body[idx];
					if (isActive[idx] == false) {
						continue;
					}
					int top_i = _part.i + _next[0][0];
					int top_j = _part.j + _next[0][1];
					int bottom_i = _part.i + _next[2][0];
					int bottom_j = _part.j + _next[2][1];
					if (_map[top_i][top_j] == '1' || _map[bottom_i][bottom_j] == '1') {
						return false;
					}
				}

				isActive[0] = true;
				isActive[1] = false;
				isActive[2] = true;
				isActive[3] = false;

				return true;
			} else if (isActive[1] == false) {
				for (int idx = 0; idx < 5; idx++) {
					Part _part = body[idx];
					if (isActive[idx] == false) {
						continue;
					}
					int right_i = _part.i + _next[1][0];
					int right_j = _part.j + _next[1][1];
					int left_i = _part.i + _next[3][0];
					int left_j = _part.j + _next[3][1];
					if (_map[right_i][right_j] == '1' || _map[left_i][left_j] == '1') {
						return false;
					}
				}
				isActive[0] = false;
				isActive[1] = true;
				isActive[2] = false;
				isActive[3] = true;

				return true;
			}
			return false;

		}

		@Override
		public String toString() {
//			return middle.i + " " + middle.j + (isActive[0] ? 1 : 0);
			return middle.i + " " + middle.j + isActive[0] + " " + isActive[1] + " " + isActive[2] + " " + isActive[3] + " ";
		}

		public boolean equals(Wood obj) {
			// TODO Auto-generated method stub
			for (int i = 0; i < 4; i++) {
				if (body[i] != null && obj.body[i] != null && !body[i].equals(obj.body[i])) {
					return false;
				}
			}
			return true;
		}
	}

//	static void dfs(Wood wood, int cnt) {
////		System.out.println(wood.toString() + "[" + cnt + "]");
//		if (wood.toString().equals(end_wood.toString())) {
////			System.out.println("?");
//			result = Math.min(result, cnt);
//			return;
//		}
//		if (cnt > result) {
//			return;
//		}
//
//		if (woods.containsKey(wood.toString())) {
//			if (woods.get(wood.toString()) > cnt) {
//				woods.put(wood.toString(), cnt);
//			} else {
//				return;
//			}
//		} else {
//			woods.put(wood.toString(), cnt);
//		}
//
//		for (int ahead = 0; ahead < 4; ahead++) {
//			if (wood.moveTo(ahead)) {
//				dfs(wood, cnt + 1);
//				wood.moveTo((ahead + 2) % 4);
//			}
//		}
//		if (wood.rotate()) {
//			dfs(wood, cnt + 1);
//			wood.rotate();
//		}
//
//	}

	static void bfs(Wood wood) {
		Queue<Wood> _queue = new ArrayDeque<>();
		_queue.add(wood);
		woods.add( wood.toString());

		while (!_queue.isEmpty()) {
			Wood tmp = _queue.poll();
//			System.out.println(tmp.toString() + "[" + tmp.depth + "]");
			if(tmp.toString().equals(end_wood.toString())) {
				result = tmp.depth;
				break;
			}
			for (int ahead = 0; ahead < 4; ahead++) {
				if (tmp.moveTo(ahead)) {
					Wood wood_next = new Wood(tmp);
					if (!woods.contains(wood_next.toString())) {
						wood_next.depth += 1;
						woods.add(wood_next.toString());
						_queue.add(wood_next);
					}
					tmp.moveTo((ahead + 2) % 4);
				}
			}
//			System.out.println("??");
			if (tmp.rotate()) {
//				System.out.println("ro");
				Wood wood_next = new Wood(tmp);
				if (!woods.contains(wood_next.toString())) {
					wood_next.depth += 1;
					woods.add(wood_next.toString());
					_queue.add(wood_next);
				}
				tmp.rotate();
			}

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Wood wood = new Wood();
		Wood wood_1 = new Wood();
		end_wood = new Wood();

		int N = Integer.parseInt(br.readLine());

		_map = new Character[N + 2][N + 2];
		woods = new ArrayList<>();

		for (int i = 0; i < N + 2; i++) {
			for (int j = 0; j < N + 2; j++) {
				_map[i][j] = '1';
			}
		}

		int count = 0;
		int count_end = 0;
		for (int i = 1; i <= N; i++) {
			String _input = br.readLine();
			for (int j = 1; j <= N; j++) {
				_map[i][j] = _input.charAt(j - 1);
				if (_map[i][j] == 'B') {
					count++;
					if (count == 2) {
						wood.middle.i = i;
						wood.middle.j = j;
					}
				}
				if (_map[i][j] == 'E') {
					count_end++;
					if (count_end == 2) {
						end_wood.middle.i = i;
						end_wood.middle.j = j;
					}
				}
			}
		}
//		System.out.println(wood.toString());
		for (int ahead = 0; ahead < 4; ahead++) {
			int next_i = wood.middle.i + _next[ahead][0];
			int next_j = wood.middle.j + _next[ahead][1];
			wood.body[ahead].i = next_i;
			wood.body[ahead].j = next_j;
			if (_map[next_i][next_j] != null && _map[next_i][next_j] == 'B') {
				wood.isActive[ahead] = true;
			}
		}

		for (int ahead = 0; ahead < 4; ahead++) {
			int next_i = end_wood.middle.i + _next[ahead][0];
			int next_j = end_wood.middle.j + _next[ahead][1];
			end_wood.body[ahead].i = next_i;
			end_wood.body[ahead].j = next_j;
			if (_map[next_i][next_j] != null && _map[next_i][next_j] == 'E') {
				end_wood.isActive[ahead] = true;
			}
		}
//		System.out.println(wood.toString());
//		System.out.println(end_wood.toString());
		bfs(wood);

		System.out.println(result == Integer.MAX_VALUE ? 0 : result);
	}
}