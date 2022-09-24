import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 수영장

public class SWEA1952 {

	static int answer;
	static int[] menus, plans;
	static int[] orders;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			answer = Integer.MAX_VALUE;

			// 1일 1달 3달 1년 요금표
			menus = new int[4];
			stk = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				menus[i] = Integer.parseInt(stk.nextToken());
			}

			// 1월 ~ 12월 이용계획
			plans = new int[12];
			stk = new StringTokenizer(br.readLine());
			for (int i = 0; i < 12; i++) {
				plans[i] = Integer.parseInt(stk.nextToken());
			}

			orders = new int[12];
			for (int i = 0; i < 12; i++) {
				orders[i] = -1;
			}

			setMenu(0, 0);
			
			answer = Math.min(answer, menus[3]); // 1년치와 비교

			System.out.println("#" + tc + " " + answer);
		}
	}

	private static void setMenu(int month, int price) {
		if (month >= 12) {
			answer = Math.min(answer, price);
			return;
		}
		
		if (plans[month] == 0) setMenu(month + 1, price); // 이용계획 없는 달은 패쓰
		else {
			setMenu(month + 1, price + (plans[month] * menus[0])); // 1일치
			setMenu(month + 1, price + menus[1]); // 1달치
			setMenu(month + 3, price + menus[2]); // 3달치
		}
	}
}