import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 조금은 그리디하게 접근?
// 1초 제한인걸 고려하자
// 어차피 한 행의 길이는 길어야 15
// 즉 15c3만큼을 하면 된다...?
// 약 500번....
// 한 계산에 대해서 500번을 진행하고
// 공격범위도 10...
// 좌,상,우만 보면 되니까
// 하나의 그래봐야 100번...
// 3개... 300번
// 맨위에있는 몬스터가 오는데 걸리는 턴이 15턴
// 15 * 500 * 300 = 2250000;
//계산에 어느정도 착오가 있었다고 쳐도 300만정도면 넉넉하지 않나?
// 가장 왼쪽을 우선해야 하니까.... 
//그런데 잠깐만... 가장 가까운적을 우선한다는 것은 dfs가 아니라 bfs 왼쪽, 위쪽, 오른쪽 탐색
// 왜냐면...?
// 아니다 왼 오 위 순서가 맞나?
// 오 위 순서가 맞음. 오른쪽을 먼저 봐야 성에서 가까운 적을 먼저 처치 가능하니까
// 시뮬레이션 찍어보면서 검증가자...
// 단... 중요한것은 한 수행에 1명만 kill할수 있다는 점이다... 즉 하나를 찾았다면 바로 넘어가야함.
// 이전에 갔던 곳을 가지 않는법은 뭐가 있을까? 그냥 checked 2차원을 만들어서 계속 초기화 하는게 정답인가?
// 동시에 쏠 수 있다는 조건을 다시 생각해보자...
// 한마디로, bfs를 3번 돌려야 한다...
// 근데 잠깐만...
// 아니다.
// 오른쪽에 있는애부터 bfs에 넣는다.
// 아니 누굴 먼저 넣든 상관 없을거같은데? 어차피 메커니즘이 정해져있잖아...
// 체크 순서는 좌... 상... 우..

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