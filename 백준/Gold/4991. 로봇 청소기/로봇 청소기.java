import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// 방문하는 순서에 대한 순열을 저장한다.
// 단, 예를 들어 427이나, 247이나 그 뒤에 할 수 있는 행동은 같다. 즉 둘 중에시간이 더 걸린값이 나온다면 백트래킹을 사용해야한다. 
// 0101001000에 대한 값을 저장해야한다.
// 400 * 10!은 1억이 넘기 때문에 터질 수 있다. 백트래킹이 필수.
// 10* 2**10승만큼의 정수를 저장할 저장공간이 필요
// 각 먼지에 대한 map이 필요하다.

public class Main {

	static List<Dust> dusts;
	static char[][] _map;
	static int[][] memo_len;
	static int DustN;
	static int H;
	static int W;

	static int _min;

	static int start_i;
	static int start_j;

	static boolean isSelected[];
	static int[] select_arr;

	static class Point {
		int _i;
		int _j;

		public Point(int _i, int _j) {
			super();
			this._i = _i;
			this._j = _j;
		}
	}

	static class Dust {
		int _i;
		int _j;
		Map<Integer, Integer> _memo;

		public Dust(int _i, int _j) {
			super();
			this._i = _i;
			this._j = _j;
			this._memo = new HashMap<Integer, Integer>();
		}
	}

	static int bfs(int _i, int _j, int dust_idx) {
		Queue<Point> _queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[H][W];
		int[][] _next = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
		visited[_i][_j] = true;
		_queue.add(new Point(_i, _j));
		int target_i = dusts.get(dust_idx)._i;
		int target_j = dusts.get(dust_idx)._j;
		int depth = -1;
		while (!_queue.isEmpty()) {
			depth += 1;
			int _size = _queue.size();
			for (int rep = 0; rep < _size; rep++) {
				Point now = _queue.poll();
				for (int ahead = 0; ahead < 4; ahead++) {
					int next_i = now._i + _next[ahead][0];
					int next_j = now._j + _next[ahead][1];
					if (next_i == target_i && next_j == target_j) {
						return depth+1;
					}
					if (next_i < 0 || next_i >= H || next_j < 0 || next_j >= W) {
						continue;
					}
					if (_map[next_i][next_j] == 'x') {
						continue;
					}
					if (visited[next_i][next_j]) {
						continue;
					}
					_queue.add(new Point(next_i, next_j));
					visited[next_i][next_j] = true;
				}
			}
		}
		_min = -1;
		return -1;
	}

	static void perm(int cnt) {
		if(_min == - 1) {
			return;
		}
		
		if (cnt == DustN) {
			int _i = start_i;
			int _j = start_j;
			int bit_mask = 0;
			int _len = 0;
			int before_dust = DustN;
//			System.out.println(Arrays.toString(select_arr));
			for (int idx = 0; idx < DustN; idx++) {
				int now_dust = select_arr[idx];
				bit_mask = bit_mask | 1 << (now_dust);
				int now_len = 0;
				if(memo_len[now_dust][before_dust] == 0) {
					now_len = bfs(_i, _j, now_dust);
					memo_len[now_dust][before_dust] = now_len;
					memo_len[before_dust][now_dust] = now_len;
				}
				else {
					now_len = memo_len[now_dust][before_dust];
				}
				
				if (now_len == -1) {
					return;
				}
				_len += now_len;
				if (!dusts.get(now_dust)._memo.containsKey(bit_mask)) {
					dusts.get(now_dust)._memo.put(bit_mask, _len);
				} else {
					if (dusts.get(now_dust)._memo.get(bit_mask) >= _len) {
						dusts.get(now_dust)._memo.put(bit_mask, _len);
					} else {
						return;
					}
				}
				_i = dusts.get(now_dust)._i;
				_j = dusts.get(now_dust)._j;
				before_dust = now_dust;
//				System.out.println(bit_mask + " | " + now_len);
			}
			_min = Math.min(_min, _len);
			return;
		}
		for (int i = 0; i < DustN; i++) {
			if (isSelected[i]) {
				continue;
			}
			isSelected[i] = true;
			select_arr[cnt] = i;
			perm(cnt + 1);
			isSelected[i] = false;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String[] wh = br.readLine().split(" ");
			H = Integer.parseInt(wh[1]);
			W = Integer.parseInt(wh[0]);

			if (H == 0 && W == 0) {
				break;
			}
			DustN = 0;
			start_i = 0;
			start_j = 0;
			_map = new char[H][W];
			dusts = new ArrayList<>();
			_min = Integer.MAX_VALUE;
			for (int i = 0; i < H; i++) {
				String _input = br.readLine();
				for (int j = 0; j < W; j++) {
					_map[i][j] = _input.charAt(j);
					if (_map[i][j] == '*') {
						DustN++;
						dusts.add(new Dust(i, j));
					} else if (_map[i][j] == 'o') {
						start_i = i;
						start_j = j;
					}
				}
			}
//			System.out.println("# " + DustN);
			memo_len = new int[DustN+1][DustN+1];
			isSelected = new boolean[DustN];
			select_arr = new int[DustN];
			perm(0);
			System.out.println(_min);
		}
	}
}