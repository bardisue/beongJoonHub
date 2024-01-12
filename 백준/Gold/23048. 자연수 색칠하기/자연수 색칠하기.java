import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] color_arr = new int[n + 1];

		int tmp_color = 1;
		color_arr[1] = 1;

		//System.out.println("flag");
		for (int i = 2; i <= n; i++) {
			//System.out.println("flag2");
			if (color_arr[i] == 0) {
				tmp_color++;
				for (int j = i; j <= n; j += i) {
					//System.out.println(j);
					color_arr[j] = tmp_color;
				}
			}
		}

		System.out.println(tmp_color--);
		for (int i = 1; i <= n; i++) {
			System.out.print(color_arr[i] + " ");
		}
	}
}