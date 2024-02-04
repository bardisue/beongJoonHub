import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			List<Integer> _list = new ArrayList<>();
			String _input = br.readLine();
			for (int i = 0; i < _input.length(); i++) {
				char _tmp = _input.charAt(i);
				_list.add(_tmp - '0');
			}
			

			for (int i = 1; i < _list.size(); i++) {
				int tmp = _list.get(i);
				int _pre = _list.get(i - 1);
				if(i == 1) {
					if (tmp == 1 && _pre == 0) {
						_list.set(i, 2);
						_list.set(i -1, 2);
					}
					continue;
				}
				int _ppre = _list.get(i - 2);

				if (tmp == 1 && _pre == 0 && (_ppre == 1 || _ppre == 2) ) {
					_list.set(i, 2);
					_list.set(i -1, 2);
				}
			}
			for (int i = 0; i < _list.size() - 2; i++) {
				int tmp = _list.get(i);
				int _next = _list.get(i + 1);
				int _nnext = _list.get(i + 2);
				if (tmp == 1 && _nnext == 0 && _next == 0) {
					_list.set(i, 3);
					_list.set(i + 1, 3);
				}
			}
			int first_len = _list.size() - 1;
			for (int i = first_len-1; i >= 0; i--) {
				if(i == 0) {
					if(_list.get(i) == _list.get(i+1)) {
						_list.remove(i);
					}
					continue;
				}
				if(_list.get(i) == _list.get(i+1) || _list.get(i) == _list.get(i-1)) {
					_list.remove(i);
				}
			}
			//System.out.println(_list.toString());
			String _bool = "YES";
			Stack<Integer> _stack = new Stack<Integer>();
			while(!_list.isEmpty()) {
				int tmp = _list.remove(0);
				if(tmp == 2) {
					if(!_stack.isEmpty()) {
						_bool = "NO";
						break;
					}
				}
				if(tmp == 3) {
					if(!_stack.isEmpty()) {
						_bool = "NO";
						break;
					}
					_stack.push(tmp);
				}
				if(tmp == 0) {
					if(_stack.isEmpty()) {
						_bool = "NO";
						break;
					}
					if(_stack.peek() != 3) {
						_bool = "NO";
						break;
					}
					_stack.push(tmp);
				}
				if(tmp == 1) {
					if(_stack.isEmpty()) {
						_bool = "NO";
						break;
					}
					if(_stack.peek() != 0) {
						_bool = "NO";
						break;
					}
					_stack.clear();
				}
			}
			if(!_stack.isEmpty())
			{
				_bool = "NO";
			}
			System.out.println(_bool);
		}
	}
}