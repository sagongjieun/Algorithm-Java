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

			setMenu(0);

			System.out.println("#" + tc + " " + answer);
		}
	}

	private static void setMenu(int depth) {
		if (depth == 12) {
			int result = getPrice(orders);
			answer = Math.min(answer, result);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (plans[depth] != 0) {
				orders[depth] = i;
			}
			setMenu(depth + 1);
		}
	}

	private static int getPrice(int[] arr) {
		int price = 0;

		for (int i = 0; i < 12; i++) {
			// 이용할 계획이 없는 달은 패쓰
			if (arr[i] == -1) continue;

			// 1일치 이용
			if (arr[i] == 0) {
				price += plans[i] * menus[0];
			}
			// 1달치 이용
			else if (arr[i] == 1) {
				price += menus[1];
			}
			// 3달치 이용
			else if (arr[i] == 2) {
				price += menus[2];
				i += 3; // 다음 2달은 건너뛰기
			}
			// 1년치 이용
			else {
				price = 0;
				price += menus[3];
				break;
			}
		}

		return price;
	}
}