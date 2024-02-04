import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		Deque<Integer> _list = new ArrayDeque<Integer>();
		// Queue<Integer> list_next = new ArrayDeque<Integer>();
		for (int i = 1; i <= n; i++) {
			_list.add(i);
		}

		boolean flag = true;
		while (_list.size() != 1) {
//			System.out.println(_list.toString());
			if (flag) {
				_list.poll();
			} else {
				int tmp = _list.poll();
				_list.add(tmp);
			}
			flag = !flag;

		}
		System.out.println(_list.poll());
	}
}