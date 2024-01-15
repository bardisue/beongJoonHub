import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] timeN = br.readLine().split(" ");

		int time = Integer.parseInt(timeN[0]);
		int n = Integer.parseInt(timeN[1]);

		int[] _time = new int[10001];

		for (int i = 0; i < n; i++) {
			String[] _tmp = br.readLine().split(" ");
			int[] copy_time = Arrays.copyOf(_time, _time.length);
			int tmp_value = Integer.parseInt(_tmp[0]);
			int tmp_time = Integer.parseInt(_tmp[1]);
			copy_time[tmp_time] = tmp_value;
			if (tmp_time > time) {
				continue;
			}
			for (int j = 0; j + tmp_time< _time.length; j++) {

				if(tmp_value + _time[j] > copy_time[j + tmp_time]) {
					copy_time[j + tmp_time] = tmp_value + _time[j];
				}
			}
			_time = copy_time;
		}

		int result = 0;
		for (int i = 0; i <= time; i++) {
			if (_time[i] != 0) {
				result = Math.max(result, _time[i]);
			}
		}

		System.out.print(result);
	}
}