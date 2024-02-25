import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] _map;
	static Deque<Point> snake;
	static int snakeAhead;
	static int[] timeTurn;

	static int[][] _next = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	static class Point {
		int _i;
		int _j;

		public Point(int _i, int _j) {
			super();
			this._i = _i;
			this._j = _j;
		}
	}

	static void _print() {
		for (int i = 0; i < N + 2; i++) {
			for (int j = 0; j< N + 2; j++) {
				System.out.print(_map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("______________________________________________________________");
	}

	static int moveSnake(int _i, int _j, int time) {
//		_print();
		_map[_i][_j] = 2;
		int next_i = _i + _next[snakeAhead][0];
		int next_j = _j + _next[snakeAhead][1];
		int next_map = _map[next_i][next_j];
		if (next_map == 0) {
			snake.addFirst(new Point(next_i, next_j));
			Point poll_point = snake.pollLast();
			_map[poll_point._i][poll_point._j] = 0;
			snakeAhead = (snakeAhead + timeTurn[time]) % 4;
			return moveSnake(next_i, next_j, time + 1);
		} else if (next_map == 1) {
			snake.addFirst(new Point(next_i, next_j));
			snakeAhead = (snakeAhead + timeTurn[time]) % 4;
			return moveSnake(next_i, next_j, time + 1);
		} else {
			return time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		_map = new int[N + 2][N + 2];
		for (int i = 0; i < N + 2; i++) {
			for (int j = 0; j < N + 2; j++) {
				_map[i][j] = -1;
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				_map[i][j] = 0;
			}
		}

		snake = new ArrayDeque<>();
		snakeAhead = 0;
		timeTurn = new int[N * N];
		int K = Integer.parseInt(br.readLine());

		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int _i = Integer.parseInt(st.nextToken());
			int _j = Integer.parseInt(st.nextToken());
			_map[_i][_j] = 1;
		}

		int L = Integer.parseInt(br.readLine());

		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			String ahead = st.nextToken();
			if (ahead.equals("L")) {
				timeTurn[time] = +3;
			} else {
				timeTurn[time] = 1;
			}
		}

		snake.add(new Point(1, 1));
		System.out.println(moveSnake(1, 1, 1));
	}
}