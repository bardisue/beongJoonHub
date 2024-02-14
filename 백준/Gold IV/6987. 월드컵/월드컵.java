import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static List<Team> _list;
	static StringBuilder _sb;
	static List<Team[]> _matches;

	static String _input;
	static int _result;
	static long count;
	static class Team {
		int win;
		int draw;
		int lose;

		public Team() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Team(int win, int draw, int lose) {
			super();
			this.win = win;
			this.draw = draw;
			this.lose = lose;
		}

		@Override
		public String toString() {
			return win + " " + draw + " " + lose;
		}
	}

			
	static void Dfs(int cnt) {
//		System.out.println(cnt);
		if(cnt == 5) {
//			System.out.println(_list.get(0).toString() );
//			System.out.println(_input.substring(0, 5));
			if(!_list.get(0).toString().equals(_input.substring(0, 5))) {
				return;
			}
		}
		if(cnt == 9) {
			
			if(!_list.get(1).toString().equals(_input.substring(6, 11))) {
				return;
			}
		}
		if(cnt == 12) {
			if(!_list.get(2).toString().equals(_input.substring(12, 17))) {
				return;
			}
		}
		if(cnt == 14) {
			if(!_list.get(3).toString().equals(_input.substring(18, 23))) {
				return;
			}
		}
		if(cnt == 15) {
			if(!_list.get(4).toString().equals(_input.substring(24, 29)) || !_list.get(5).toString().equals(_input.substring(30, 35))) {
				return;
			}
			else {
				_result = 1;
				return;
			}
		}
		Team teamA = _matches.get(cnt)[0];
		Team teamB = _matches.get(cnt)[1];
		teamA.win += 1;
		teamB.lose += 1;
		Dfs(cnt+1);
		teamA.win -= 1;
		teamB.lose -= 1;
		teamA.lose += 1;
		teamB.win += 1;
		Dfs(cnt+1);
		teamA.lose -= 1;
		teamB.win -= 1;
		teamA.draw += 1;
		teamB.draw += 1;
		Dfs(cnt+1);
		teamA.draw -= 1;
		teamB.draw -= 1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int test_case = 0; test_case < 4; test_case++) {
			count = 0;
			_result = 0;
			_list = new ArrayList<>();
			_matches = new ArrayList<>();
			_input = br.readLine();
			_sb = new StringBuilder();
			for (int i = 0; i < 6; i++) {
				_list.add(new Team());
			}
			for (int i = 0; i < 5; i++) {
				for (int j = i + 1; j < 6; j++) {
					_matches.add(new Team[] { _list.get(i), _list.get(j) });
				}
			}
			Dfs(0);
			bw.write(_result + " ");
		}
		bw.flush();
		bw.close();
	}
}