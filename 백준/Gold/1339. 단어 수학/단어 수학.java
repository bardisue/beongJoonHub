import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	static boolean[] isUsed;
	static int _pick;
	static List<int[]> _list;
	static int _max = 0;
	static Map<Character, Integer> alpa_map;
	static List<Integer> max_list;
	static List<Integer> max_values;
	
	static void per(int cnt, List<Integer> tmp_list) {
		if(cnt == _pick) {
			int _sum =0;
			List<Integer> values = new ArrayList<Integer>(alpa_map.values());
			for(int i =0; i < tmp_list.size(); i++) {
//				System.out.println(tmp_list.get(i) + " " + values.get(i));
				_sum += tmp_list.get(i) * values.get(i);
			}
//			_max = Math.max(_max, _sum);
			if(_sum > _max) {
				_max = _sum;
			}
			
		}
		else {
			for(int i = 9; i >= 10 -_pick; i--) {
				if(isUsed[i] == true) {
					continue;
				}
				List<Integer> to_per = new ArrayList<Integer>(tmp_list);
				to_per.add(i);
				isUsed[i] = true;
				per(cnt+1, to_per);
				isUsed[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		alpa_map = new HashMap<Character, Integer>();
		
		for (int i = 0; i < n; i++) {
			String _input = br.readLine();
			for (int j =0; j < _input.length(); j++) {
				char _tmp = _input.charAt(j);
				int plus_value = (int) (Math.pow(10, _input.length()-j-1));
//				System.out.println(plus_value);
				if(alpa_map.containsKey(_input.charAt(j))){
					alpa_map.put(_tmp, alpa_map.get(_tmp)+plus_value);
				}
				else {
					alpa_map.put(_tmp, plus_value);
				}
			}
		}
		
		_pick = alpa_map.keySet().size();
//		System.out.println(_pick);
		isUsed = new boolean[10];
		_list = new ArrayList<int[]>();
		per(0, new ArrayList<Integer>());
		System.out.println(_max);
	}
}