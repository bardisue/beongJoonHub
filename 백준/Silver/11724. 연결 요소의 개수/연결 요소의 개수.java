import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
	static int[] _parent;

	static int find_parent(int a) {
		if (_parent[a] != a) {
			return find_parent(_parent[a]);
		} else {
			return a;
		}
	}

	static void union_find(int a, int b) {
		a = find_parent(a);
		b = find_parent(b);

		int _max = Math.max(a, b);
		_parent[a] = _max;
		_parent[b] = _max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] _input = br.readLine().split(" ");
		int n = Integer.parseInt(_input[0]);
		int m = Integer.parseInt(_input[1]);
		_parent = new int[n];

		for (int i = 0; i < n; i++) {
			_parent[i] = i;
		}

		for (int i = 0; i < m; i++) {
			_input = br.readLine().split(" ");
			int a = Integer.parseInt(_input[0])-1;
			int b = Integer.parseInt(_input[1])-1;
			union_find(a, b);
		}
		
		Set<Integer> _set = new HashSet<Integer>();
		for (int i = 0; i < n; i++) {
			_set.add(find_parent(i));
		}
		System.out.println(_set.size());
	}
}