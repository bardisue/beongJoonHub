import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static class Bar implements Comparable<Bar> {
		int idx;
		int height;

		public Bar(int idx, int height) {
			super();
			this.idx = idx;
			this.height = height;
		}

		@Override
		public int compareTo(Bar o) {
			// TODO Auto-generated method stub
			if (this.idx > o.idx) {
				return 1;
			} else if (this.idx == o.idx) {
				return 0;
			} else {
				return -1;
			}
		}

		@Override
		public String toString() {
			return "Bar [idx=" + idx + ", height=" + height + "]";
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[][] _map = new int[1001][1001];
		
		List<Bar> _list = new ArrayList<>();

		for (int num = 0; num < N; num++) {
			String[] _input = br.readLine().split(" ");
			Bar bar = new Bar(Integer.parseInt(_input[0]), Integer.parseInt(_input[1]));
			_list.add(bar);
		}
		
		Bar max_bar = null;
		int max_height = -1;
		int max_idx = -1;
		
		_list.sort(null);
		
		for(Bar bar : _list) {
//			System.out.println(bar.toString());
			if(bar.height > max_height ) {
				max_bar = bar;
				max_height = bar.height;
				max_idx = bar.idx;
			}
		}
		
		for(Bar bar : _list) {
			if(bar.idx <= max_idx) {
				for(int i = bar.idx; i<=max_idx; i++) {
					for(int height = 0; height < bar.height; height++) {
						_map[i][height] = 1;
					}
				}
			}
			else {
				for(int i = max_idx+1; i<=bar.idx; i++) {
					for(int height = 0; height < bar.height; height++) {
						_map[i][height] = 1;
					}
				}
			}
		}
		int result = 0;
		
		for(int i = 1; i<1001; i++) {
			for(int height = 0; height < 1001; height++) {
				if(_map[i][height] == 1) {
					result++;
				}
			}
		}
		System.out.println(result);
	}
}