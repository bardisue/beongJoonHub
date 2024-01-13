import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Arrays;

class ToSort {
	int two;
	int three;
	long _value;

	public ToSort(int two, int three, long _value) {
		this.two = two;
		this.three = three;
		this._value = _value;
	}
}


class CustomComparator implements  Comparator<ToSort>{
	int twoThree = 0;
	public CustomComparator(int n) {
		this.twoThree = n;
	}
	
	@Override
	public int compare(ToSort t1, ToSort t2) {
		if(twoThree == 2)
			return t1.two - t2.two;
		else
			return t2.three - t1.three;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] tmp_arr = br.readLine().split(" ");
		ToSort[] ts_list = new ToSort[n];
		for (int i = 0; i < n; i++) {
			long tmp = Long.parseLong(tmp_arr[i]);
			int two_mod = 0;
			
			while(tmp != 0) {
				if(tmp%2 == 0) {
					tmp = tmp/2;
					two_mod++;
				}
				else {
					break;
				}
			}
			
			tmp = Long.parseLong(tmp_arr[i]);
			int three_mod = 0;
			
			while(tmp != 0) {
				if(tmp%3 == 0) {
					tmp = tmp/3;
					three_mod++;
				}
				else {
					break;
				}
			}
			tmp = Long.parseLong(tmp_arr[i]);
			ToSort ts = new ToSort(two_mod, three_mod, tmp);
			ts_list[i] = ts;
		}
		Arrays.sort(ts_list, new CustomComparator(2));
		
//		for (int i = 0; i < n; i++) {
//			System.out.print(ts_list[i]._value + " ");
//		}
		//System.out.println();
		Arrays.sort(ts_list, new CustomComparator(3));


		for (int i = 0; i < n; i++) {
			System.out.print(ts_list[i]._value + " ");
		}
	}
}