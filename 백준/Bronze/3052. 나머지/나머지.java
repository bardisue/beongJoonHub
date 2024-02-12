import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws IOException {
		Set<Integer> _set = new HashSet<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int num = 0; num < 10; num++) {
			int _input = Integer.parseInt(br.readLine());
			_set.add(_input%42);
		}
		System.out.println(_set.size());
	}
}