import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int result = 0;
	static boolean isUsed[];

	static void rec(int cnt) {
		if(result >= 2_000_000_000) {
			return;
		}
		if (cnt == N + 1) {
			result++;
			return;
		}
		if(isUsed[cnt] == true) {
			rec(cnt+1);
		}
		else {
			for (int i = cnt - 1; i <= cnt + 1; i++) {
				if (i <= 0 || i > N) {
					continue;
				}
				if(isUsed[i] == true) {
					continue;
				}
				isUsed[i]= true;
				rec(cnt + 1);
				isUsed[i]= false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		isUsed = new boolean[N+1];
		int m = Integer.parseInt(br.readLine());

		for (int i = 0; i < m; i++) {
			int tmp = Integer.parseInt(br.readLine());
			isUsed[tmp] = true;
		}

		rec(1);
		System.out.println(result);
	}
}