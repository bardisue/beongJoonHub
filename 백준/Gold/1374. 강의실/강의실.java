import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	static class Lecture {
		int num;
		int start_time;
		int end_time;

		public Lecture(int num, int start_time, int end_time) {
			super();
			this.num = num;
			this.start_time = start_time;
			this.end_time = end_time;
		}

		@Override
		public String toString() {
			return "Lecture [num=" + num + ", start_time=" + start_time + ", end_time=" + end_time + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Lecture> _list = new ArrayList<>();
		int result = 0;
		
		for (int i = 0; i < N; i++) {
			String[] _input = br.readLine().split(" ");
			int num = Integer.parseInt(_input[0]);
			int start = Integer.parseInt(_input[1]);
			int end = Integer.parseInt(_input[2]);
			_list.add(new Lecture(num, start, end));
		}
		_list.sort(new Comparator<Lecture>() {
			public int compare(Lecture o1, Lecture o2) {
				if (o1.start_time - o2.start_time > 0) {
					return 1;
				} else if (o1.start_time - o2.start_time < 0) {
					return -1;
				} else {
					return 0;
				}
			};
		});

		Queue<Lecture> pq = new PriorityQueue<>(new Comparator<Lecture>() {
			@Override
			public int compare(Lecture o1, Lecture o2) {
				if (o1.end_time - o2.end_time > 0) {
					return 1;
				}
				if (o1.end_time - o2.end_time < 0) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		for (Lecture lec : _list) {
			int strat = lec.start_time;
			while(pq.peek() != null)
			{
				if(pq.peek().end_time <= strat) {
					pq.poll();
				}
				else {
					break;
				}
			}
			pq.add(lec);
			int count = pq.size();
			result = Math.max(count, result);
		}
		System.out.println(result);
	}
}