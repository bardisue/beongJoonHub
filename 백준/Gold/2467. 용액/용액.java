import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		String[] _input = br.readLine().split(" ");
		List<Integer> numbers = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			numbers.add(Integer.parseInt(_input[i]));
		}
		Collections.sort(numbers, (a, b) -> Integer.compare(Math.abs(a), Math.abs(b)));

		int result = Integer.MAX_VALUE;
		int resultA = 0;
		int resultB = 0;

		for (int i = 0; i < n - 1; i++) {
			int tmp = numbers.get(i) + numbers.get(i + 1);
			if (Math.abs(tmp) < result) {
				result = Math.abs(tmp);
				resultA = numbers.get(i);
				resultB = numbers.get(i + 1);
			}

		}

		System.out.printf("%d %d", Math.min(resultA, resultB), Math.max(resultA, resultB));
	}
}