import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //Scanner scanner = new Scanner(System.in);
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int T = Integer.parseInt(br.readLine());
    	for(int test_case = 0; test_case < T; test_case++) {
    		String[] _nk = br.readLine().split(" ");
    		int n = Integer.parseInt(_nk[0]);
    		int k = Integer.parseInt(_nk[1]);
    		int[] isNode = new int[n];
    		String[] _cost = br.readLine().split(" ");
    		int[][] _link = new int[n][n];
    		int[][] _list = new int[k][2];
    		for(int i =0; i<k; i++) {
        		String[] _xy = br.readLine().split(" ");
        		int x = Integer.parseInt(_xy[0])-1;
        		int y = Integer.parseInt(_xy[1])-1;
        		isNode[y] += 1;
        		_list[i][0] = x;
        		_list[i][1] = y;
    		}
    		Queue<Integer> _stack = new LinkedList<>();
    		int[] cost_final = new int[n];
    		for(int i =0; i<n; i++) {
    			if(isNode[i] == 0) {
    				_stack.add(i);
    				cost_final[i] = Integer.parseInt(_cost[i]);
    				//System.out.println(i+1);
    			}
    		}

    		
    		while(!_stack.isEmpty()) {
    			int now =_stack.poll();
    			isNode[now] -= 1;
    			//System.out.println(now);
    			for(int i = 0; i < k; i++) {
    				if(_list[i][0] == now) {
    					int _next = _list[i][1];
                        isNode[_next] -= 1;
    					int next_cost = cost_final[now] + Integer.parseInt(_cost[_next]);
    					if(next_cost > cost_final[_next]) {
    						cost_final[_next] = next_cost;
                            if(isNode[_next] == 0){
                                _stack.add(_next);
                            }
    					}
    				}
    			}
    			if(_stack.isEmpty()) {
    	    		for(int i = 0; i <n; i++) {
    	    			if(isNode[i] == 0) {
    	    				_stack.add(i);
    	    			}
    	    		}
    			}
    		}
    		
    		int answer = Integer.parseInt(br.readLine());
    		System.out.println(cost_final[answer-1]);
    	}
    }
}