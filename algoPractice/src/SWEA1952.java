import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 수영장

public class SWEA1952 {
	
	static int[] costs;
	static int[] plans;
	static int answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			answer = Integer.MAX_VALUE;
			
			costs = new int[4];
			stk = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				costs[i] = Integer.parseInt(stk.nextToken());
			}
			
			plans = new int[12];
			stk = new StringTokenizer(br.readLine());
			for (int i = 0; i < 12; i++) {
				plans[i] = Integer.parseInt(stk.nextToken());
			}
			
			getLeastMoney(0, 0);
			
			answer = Math.min(answer, costs[3]); // 1년치
			
			System.out.println("#" + tc + " " + answer);
		}
	}

	private static void getLeastMoney(int month, int cost) {
		if (month >= 12) {
			answer = Math.min(answer, cost);
			return;
		}
		
		if (plans[month] != 0) {
			getLeastMoney(month + 1, cost + costs[0] * plans[month]); // 1일치
			getLeastMoney(month + 1, cost + costs[1]); // 1달치
			getLeastMoney(month + 3, cost + costs[2]); // 3달치
		} else getLeastMoney(month + 1, cost);
	}

}