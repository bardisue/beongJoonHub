import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static Room[][] room_map;
	static int _count;
	static boolean[][] visited;

	static class Room {
		int _i;
		int _j;
		boolean is_light;
		List<Room> _room;

		public Room(int _i, int _j) {
			super();
			this._i = _i;
			this._j = _j;
			this._room = new ArrayList<>();
		}
	}

	static int[][] _next = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	static void bfs(int _i, int _j) {
		Queue<Room> _queue = new ArrayDeque<>();
		_queue.add(room_map[_i][_j]);
		visited[_i][_j] = true;
		room_map[_i][_j].is_light = true;
		while (!_queue.isEmpty()) {
			Room tmp = _queue.poll();
//			System.out.println(tmp._i + " " + tmp._j);
			for (Room light : tmp._room) {
				if (!room_map[light._i][light._j].is_light) {
					room_map[light._i][light._j].is_light = true;
					_count++;
				}
				if (visited[light._i][light._j]) {
					continue;
				}
				for (int ahead = 0; ahead < 4; ahead++) {
					int next_i = light._i + _next[ahead][0];
					int next_j = light._j + _next[ahead][1];
					if (visited[next_i][next_j] == true) {
						visited[light._i][light._j] = true;
						_queue.add(room_map[light._i][light._j]);
						break;
					}
				}
			}
			for (int ahead = 0; ahead < 4; ahead++) {
				int next_i = tmp._i + _next[ahead][0];
				int next_j = tmp._j + _next[ahead][1];
				if (visited[next_i][next_j] == false && room_map[next_i][next_j].is_light) {
					visited[next_i][next_j] = true;
					_queue.add(room_map[next_i][next_j]);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		room_map = new Room[N + 2][N + 2];
		_count = 1;
		visited = new boolean[N + 2][N + 2];
		for (int i = 0; i < N + 2; i++) {
			for (int j = 0; j < N + 2; j++) {
				room_map[i][j] = new Room(i, j);
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a_i = Integer.parseInt(st.nextToken());
			int a_j = Integer.parseInt(st.nextToken());
			int b_i = Integer.parseInt(st.nextToken());
			int b_j = Integer.parseInt(st.nextToken());
			room_map[a_i][a_j]._room.add(room_map[b_i][b_j]);
		}
		visited[0][1] = true;
		bfs(1, 1);
		System.out.println(_count);
	}
}
