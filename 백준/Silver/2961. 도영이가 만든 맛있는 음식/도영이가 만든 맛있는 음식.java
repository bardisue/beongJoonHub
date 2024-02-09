import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static class Ingredient {
		long sour;
		long bitter;

		public Ingredient(long sour, long bitter) {
			super();
			this.sour = sour;
			this.bitter = bitter;
		}
	}

	static int N;
	static long result;
	static Ingredient[] ing;
	static boolean[] selected;

	static void module() {
		long sour = 1;
		long bitter = 0;
		for (int i = 0; i < N; i++) {
			if (selected[i]) {
				sour *= ing[i].sour;
				bitter += ing[i].bitter;
			}
		}
		if(bitter == 0) {
			return;
		}
		long taste_sub = Math.abs(sour- bitter);
		result = Math.min(result, taste_sub);
	}

	static void subset(int cnt) {
		if (cnt == N) {
			module();
			return;
		}
		selected[cnt] = true;
		subset(cnt+1);
		selected[cnt] = false;
		subset(cnt+1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ing = new Ingredient[N];
		selected = new boolean[N];
		result = Long.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			String[] _input = br.readLine().split(" ");
			long sour = Long.parseLong(_input[0]);
			long bitter = Long.parseLong(_input[1]);
			Ingredient tmp = new Ingredient(sour, bitter);
			ing[i] = tmp;
		}
		subset(0);
		System.out.println(result);
	}
}