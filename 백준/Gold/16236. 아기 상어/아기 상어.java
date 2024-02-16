import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//와 엎어야된다 신난다
//15분만에 깨닫고 엎었으면 선방했다
// 병준아 너는 이게 왜 될거라고 생각했니....?

//사실 시물레이션이 정해져 있는 문제

public class Main {
	static int shark_size;
	static int shark_eat;
	static int[][] _map;
	static int N;

	static class Shark {
		int _i;
		int _j;

		public Shark(int _i, int _j) {
			super();
			this._i = _i;
			this._j = _j;
		}

	}

	static int[][] _next = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
	static boolean[][] visited;

	static int bfs(int _i, int _j) {
		shark_eat = 0;
		visited = new boolean[N + 2][N + 2];
		Queue<Shark> _queue = new ArrayDeque<>();
		_queue.add(new Shark(_i, _j));
		int result = 0;
		int time = 0;
		while (!_queue.isEmpty()) {
			time += 1;
			int _size = _queue.size();
			Queue<Shark> pq = new PriorityQueue<>(new Comparator<Shark>() {
				public int compare(Shark o1, Shark o2) {
					if (o1._i - o2._i > 0) {
						return 1;
					} else if (o1._i - o2._i < 0) {
						return -1;
					}
					else{
						if (o1._j - o2._j > 0) {
							return 1;
						} else if (o1._j - o2._j < 0) {
							return -1;
						}
						else {
							return 0;
						}
					}

				};
			});
			
			for (int rep = 0; rep < _size; rep++) {
				Shark tmp = _queue.poll();
				for (int ahead = 0; ahead < 4; ahead++) {
//					System.out.println(Arrays.toString(_next[ahead]));
					int next_i = tmp._i + _next[ahead][0];
					int next_j = tmp._j + _next[ahead][1];
					int next_size = _map[next_i][next_j];
					if (visited[next_i][next_j]) {
						continue;
					}
					if (next_size == 0 || next_size == shark_size) {
						visited[next_i][next_j] = true;
						_queue.add(new Shark(next_i, next_j));
					} else if (next_size < shark_size) {
						pq.add(new Shark(next_i, next_j));
					}
				}
			}
			if(!pq.isEmpty()) {
				Shark shark = pq.poll();
				_queue = new ArrayDeque<>();
				_queue.add(shark);
				shark_eat++;
				if (shark_eat == shark_size) {
					shark_size++;
					shark_eat = 0;
				}
				_map[shark._i][shark._j] = 0;
				result += (time);
				time = 0;
				visited = new boolean[N + 2][N + 2];
			}

		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		_map = new int[N + 2][N + 2];

		shark_size = 2;
		int shark_i = 0;
		int shark_j = 0;

		for (int i = 0; i < N + 2; i++) {
			for (int j = 0; j < N + 2; j++) {
				_map[i][j] = Integer.MAX_VALUE;
			}
		}

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				_map[i][j] = Integer.parseInt(st.nextToken());
				if (_map[i][j] == 9) {
					shark_i = i;
					shark_j = j;
					_map[i][j] = 0;
				}
			}
		}

		int result = bfs(shark_i, shark_j);
		System.out.println(result);
	}
}