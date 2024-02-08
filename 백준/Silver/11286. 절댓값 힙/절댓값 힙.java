import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] _heap;
	static int idx;

	static int remove() {
		if(idx == 1) {
			return 0;
		}
		int result = _heap[1];
		_heap[1] = 0;
		swap(_heap, 1, idx-1);

		int travel = 1;
		int left = travel * 2;
		int right = travel * 2 + 1;
		while (Math.abs(_heap[right]) <= Math.abs(_heap[travel]) || Math.abs(_heap[left]) <= Math.abs(_heap[travel])) {
			int toGo = 0;
			if (_heap[right] == 0 && _heap[left] == 0) {
				break;
			}
			if (_heap[right] == 0) {
				toGo = left;
			} else {
				if (Math.abs(_heap[left]) > Math.abs(_heap[right])) {
					toGo = right;
				} else if (Math.abs(_heap[right]) > Math.abs(_heap[left])) {
					toGo = left;
				} else {
					if (_heap[right] > _heap[left]) {
						toGo = left;
					} else {
						toGo = right;
					}
				}
			}

			if (Math.abs(_heap[travel]) > Math.abs(_heap[toGo])) {
				swap(_heap, travel, toGo);
			} else if (Math.abs(_heap[travel]) == Math.abs(_heap[toGo])) {
				if (_heap[travel] > _heap[toGo]) {
					swap(_heap, travel, toGo);
				} else {
					break;
				}
			} else {
				break;
			}
			travel = toGo;
			left = toGo*2;
			right = toGo * 2 + 1;
		}
		idx--;
//		System.out.println("out");
		return result;
	}

	static void _print(int node) {
		if (_heap[node] == 0) {
			return;
		}
		if (_heap[node * 2] == 0 && _heap[node * 2 + 1] == 0) {
			System.out.println(_heap[node] + " " + node);
			return;
		}
		System.out.println(_heap[node] + " " + node);
		_print(node * 2);
		_print(node * 2 + 1);
	}

	static void add(int input) {
		_heap[idx] = input;

		int travel = idx;
		int parent = travel / 2;
		while (Math.abs(_heap[travel]) <= Math.abs(_heap[parent])) {
			if (Math.abs(_heap[travel]) < Math.abs(_heap[parent])) {
				swap(_heap, travel, parent);
				travel = parent;
				parent = travel / 2;
				if (travel == 0) {
					break;
				}
			} else if (Math.abs(_heap[travel]) == Math.abs(_heap[parent]) && _heap[travel] < _heap[parent]) {
				swap(_heap, travel, parent);
				travel = parent;
				parent = travel / 2;
				if (travel == 0) {
					break;
				}
			} else {
				break;
			}
		}
		idx++;
	}

	static void swap(int[] p, int x, int y) {
		int tmp = p[x];
		p[x] = p[y];
		p[y] = tmp;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		_heap = new int[300000];
		idx = 1;

		for (int cnt = 0; cnt < n; cnt++) {
			int _input = Integer.parseInt(br.readLine());
			if (_input == 0) {
//				System.out.println("--------------------------------------------");
				System.out.println(remove());
//				_print(1);
			} else {
//				System.out.println("--------------------------------------------");
				add(_input);
//				_print(1);
			}
		}
	}
}

//4
//1
//-1
//1
//5