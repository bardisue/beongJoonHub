import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int _value;
		Node pre_node;
		Node next_node;

		public Node(int _value, Node next_node) {
			super();
			this._value = _value;
			this.next_node = next_node;
		}

		public Node(int _value) {
			super();
			this._value = _value;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		/***
		 * 풀이 1 Queue<Integer> _queue = new ArrayDeque<>(); Queue<Integer> _result = new
		 * ArrayDeque<>();
		 * 
		 * for (int i = 1; i <= N; i++) { _queue.add(i); }
		 * 
		 * while (!_queue.isEmpty()) { for (int repeat = 0; repeat < K; repeat++) { int
		 * tmp = _queue.poll(); if(repeat == K-1) { _result.add(tmp); } else {
		 * _queue.add(tmp); } } } StringBuilder sb = new StringBuilder();
		 * sb.append(_result.toString()); sb.replace(0, 1, "<");
		 * sb.replace(sb.length()-1, sb.length(), ">"); System.out.println(sb);
		 ****/
		/***
		 * 풀이 2 int[] big_arr = new int[5000*5000 + 1]; for (int i = 1; i <= N; i++) {
		 * big_arr[i] = i; } StringBuilder sb = new StringBuilder(); sb.append("<"); int
		 * idx = 1; int insert_idx = 1+N; for(int i = 0; i < N; i++) { for(int repeat
		 * =0; repeat < K-1; repeat++) { big_arr[insert_idx++] = big_arr[idx++]; } if(i
		 * == N-1) { sb.append(big_arr[idx++]+">"); } else { sb.append(big_arr[idx++]
		 * +", "); } } System.out.println(sb);
		 ***/
		Node head = new Node(1);
		Node now = head;
		for (int i = 2; i <= N; i++) {
			Node new_node = new Node(i);
			new_node.pre_node = now;
			now.next_node = new_node;
			now = new_node;
		}
		now.next_node = head;
		head.pre_node = now;

		now = head;
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		int repeat = 0;
		while (now.next_node._value != now._value) {
			Node tmp = now;
//			System.out.println(now._value);
			if (repeat == K - 1) {
				sb.append(tmp._value + ", ");
				tmp.pre_node.next_node = tmp.next_node;
				tmp.next_node.pre_node = tmp.pre_node;
				repeat = 0;
			}
			else {
				repeat++;
			}
			now = tmp.next_node;
		}

		sb.append(now._value + ">");
		System.out.println(sb);
	}
}