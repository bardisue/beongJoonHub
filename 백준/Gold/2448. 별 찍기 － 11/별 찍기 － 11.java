import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static void starRec(int n, int tmp_line, String[][] _map) throws IOException {
		int print_len = n * 2 - 1;
		List<Integer> tmp_list = new ArrayList<>();
		if (tmp_line == 1) {
			int first_point = print_len / 2 + 1;
			for (int i = 1; i <= print_len; i++) {
				if (i == first_point) {
					_map[tmp_line - 1][i - 1] = "*";
				}
			}
		} else {
			if (tmp_line % 3 == 1) {
				for (int i = 1; i < print_len - 1; i++) {
					if (_map[tmp_line - 2][i - 1] == "*" && _map[tmp_line - 2][i + 1] == null && _map[tmp_line - 2][i] == null) {
						_map[tmp_line - 1][i] = "*";
					} else if (_map[tmp_line - 2][i - 1] == null && _map[tmp_line - 2][i + 1] == "*" && _map[tmp_line - 2][i] == null) {
						_map[tmp_line - 1][i] = "*";
					}
				}

			}
		}
		if (tmp_line % 3 == 2) {
			for (int i = 1; i < print_len - 1; i++) {
				if (_map[tmp_line - 2][i] == "*") {
					_map[tmp_line - 1][i - 1] = "*";
					_map[tmp_line - 1][i + 1] = "*";
				}
			}
		}

		if (tmp_line % 3 == 0) {
			for (int i = 2; i < print_len - 2; i++) {
				if (_map[tmp_line - 3][i] == "*") {
					_map[tmp_line - 1][i - 2] = "*";
					_map[tmp_line - 1][i - 1] = "*";
					_map[tmp_line - 1][i] = "*";
					_map[tmp_line - 1][i + 1] = "*";
					_map[tmp_line - 1][i + 2] = "*";
				}

			}

		}

		if (tmp_line == n) {
			return;
		} else {
			starRec(n, tmp_line + 1, _map);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[][] _map = new String[n][n * 2 - 1];

		starRec(n, 1, _map);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n * 2 - 1; j++) {
				if (_map[i][j] == null) {
					bw.write(" ");
					continue;
				}
				bw.write(_map[i][j]);
			}
			bw.write("\n");
		}

		bw.flush();
		bw.close();
	}
}