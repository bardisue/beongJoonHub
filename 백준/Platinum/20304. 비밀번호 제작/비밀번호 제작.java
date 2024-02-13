import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
	static int position;
	static int _max;
	static int[] safety;

	static class ChangeNode {
		int value;
		int changed;

		public ChangeNode(int value, int changed) {
			super();
			this.value = value;
			this.changed = changed;
		}
	}

	static void bfs(List<Integer> tries) {
		Queue<ChangeNode> _queue = new ArrayDeque<>();
		for(int _try : tries) {
			_queue.add(new ChangeNode(_try, 0));
		}
		while (!_queue.isEmpty()) {
			ChangeNode tmp = _queue.poll();
			int tmp_value = tmp.value;
			int tmp_changed = tmp.changed;
//			System.out.println(tmp_value + " " + tmp_changed + " " + tmp_idx);
			for (int i = 0; i < position; i++) {
				int _xor = 1 << i;
				int next_int = _xor ^ tmp_value;
				int next_changed = tmp_changed + 1;
				if (next_int > _max) {
					continue;
				}
				if (safety[next_int] <= next_changed) {
					continue;
				} else {
					safety[next_int] = next_changed;
					_queue.add(new ChangeNode(next_int, next_changed));
				}
			}

		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		_max = Integer.parseInt(br.readLine());
		int copy_max = _max;
		position = 0;
		while (copy_max != 0) {
			copy_max = copy_max >> 1;
			position++;
		}
//		System.out.println(position);

		safety = new int[_max + 1];
		for (int i = 0; i < _max + 1; i++) {
			safety[i] = Integer.MAX_VALUE;
		}
		int M = Integer.parseInt(br.readLine());
		String[] _input = br.readLine().split(" ");
		List<Integer> tries = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			int tmp = Integer.parseInt(_input[i]);
			safety[tmp] = 0;
			tries.add(tmp);
		}

		bfs(tries);

		int result = 0;

		for (int i = 0; i < _max + 1; i++) {
			result = Math.max(safety[i], result);
		}
		System.out.println(result);
	}
}