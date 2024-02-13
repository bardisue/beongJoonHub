import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int frac(int s, int n, int k, long _i, long _j) {
		if (s == 0) {
			return 0;
		} else {
			long _len = (long) Math.pow(n, s);
			long diven = _len / n;
			int distance = (n - k) / 2;

			if (distance * diven <= _i && _i < _len - distance * diven  && distance * diven  <= _j && _j < _len - distance * diven) {

				return 1;

			} else {
				while (_i >= diven) {
					_i -= diven;
				}
				while (_j >= diven) {
					_j -= diven;
				}
				return frac(s - 1, n, k, _i, _j);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 1 5 3 0 4 0 4
		String[] _input = br.readLine().split(" ");
		int S = Integer.parseInt(_input[0]);
		int N = Integer.parseInt(_input[1]);
		int K = Integer.parseInt(_input[2]);
		long i1 = Long.parseLong(_input[3]);
		long i2 = Long.parseLong(_input[4]);
		long j1 = Long.parseLong(_input[5]);
		long j2 = Long.parseLong(_input[6]);
		for (long i = i1; i <= i2; i++) {
			for (long j = j1; j <= j2; j++) {
				System.out.print(frac(S, N, K, i, j));
			}
			System.out.println();
		}

	}
}