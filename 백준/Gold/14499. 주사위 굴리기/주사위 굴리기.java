import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] dice = { 1, 4, 3, 2, 5, 6 };
		int[] dice_reverse = { 6, 5, 4, 3, 2, 1 };
		int[] action_reverse = {2,1,4,3};
		int[] print_dice = new int[6];

		String[] _input = br.readLine().split(" ");

		int n = Integer.parseInt(_input[0]), m = Integer.parseInt(_input[1]), now_i = Integer.parseInt(_input[2]),
				now_j = Integer.parseInt(_input[3]), input_num = Integer.parseInt(_input[4]);
		int _map[][] = new int[n][m];

		for (int i = 0; i < n; i++) {
			String[] line_input = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				_map[i][j] = Integer.parseInt(line_input[j]);
			}
		}

		String[] action_input = br.readLine().split(" ");

		int now_side = 1;

		for (int i = 0; i < input_num; i++) {
			int now_action = Integer.parseInt(action_input[i]);

			int[][] _next = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

			int next_i = now_i + _next[now_action-1][0];
			int next_j = now_j + _next[now_action-1][1];

			if (next_i < 0 || next_i >= n || next_j < 0 || next_j >= m) {
				continue;
			}

			
			

			int next_side = dice[now_action]; // 다음에 위에 나오게 될 면
			dice[action_reverse[now_action-1]] = now_side; //지금 면의 다음 위치
			dice[now_action] = dice_reverse[now_side-1]; // 지금 면의 리버스의 위치
			dice[0] = next_side; // 다음 위치
			dice[5] = dice_reverse[next_side-1]; //반대 배치
			
			System.out.println(print_dice[next_side-1]);
			
			if (_map[next_i][next_j] == 0) {
				_map[next_i][next_j] = print_dice[dice[5]-1];
			} else {
				print_dice[dice[5]-1] = _map[next_i][next_j];
				_map[next_i][next_j] = 0;
			}

			now_side = next_side;
			now_i = next_i;
			now_j = next_j;

		}

	}
}