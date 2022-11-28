import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class A_알고스탁 {
	
	static int Ms, Ma, N, L;
	static int[][] map;
	
	static ArrayList<Stock> stockList;
	static int monthBenefit;
	
	static class Stock {
		int cur, next, diff;

		public Stock(int cur, int next) {
			this.cur = cur; // 현재 가격
			this.next = next; // 다음 가격
			this.diff = next - cur; // 이익 차액
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			stk = new StringTokenizer(br.readLine());
			Ms = Integer.parseInt(stk.nextToken()); // 초기 예치금 (300 ≤ Ms ≤ 1,000)
			Ma = Integer.parseInt(stk.nextToken()); // 월별 불입금액 (0 ≤ Ma ≤ 100)
			
			stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken()); // 종목 수 (3 ≤ N ≤ 15)
			L = Integer.parseInt(stk.nextToken()); // 데이터 기간 (8 ≤ L ≤ 15)
			
			map = new int[N][L+1]; // [종목 수][개월 수 0~L]
			
			for (int i = 0; i < N; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j <= L; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
				}
			}
			
			int money = Ms;
			
			for (int month = 0; month < L; month++) {				
				
				stockList = new ArrayList<>(); // month 번째 달에 구매할 주식 종목을 저장할 ArrayList 초기화
				
				// 각 종목에서 증가한(다음달 > 이번달) 종목을 체크해서 저장
				for (int j = 0; j < N; j++) {
					if (map[j][month+1] > map[j][month]) {
						stockList.add(new Stock(map[j][month], map[j][month+1]));
					}
				}
				
				monthBenefit = 0; // 이번달에 얻을 수 있는 최대 수익 초기화
				
				// ArrayList 개수가 1이라면 : 그 종목을 현재 money로 최대한 구매한다 (매수)
				if (stockList.size() == 1) {
					monthBenefit = money / stockList.get(0).cur * stockList.get(0).diff;
				} 
				// ArrayList 개수가 여러개라면 : 어떤 종목을 사는 게 최선인지 확인
				else if (stockList.size() > 1) {
					DFS(0, money, 0);
				}

				money += monthBenefit;
				money += Ma; // 매월 불입금액 Ma 추가
			}
			
			sb.append("#" + tc + " " + (money - (Ms+Ma*L)) + "\n"); // 최대 수익 출력
		}
		
		System.out.print(sb.toString());
	}
	
	/**
	 * 완탐을 이용하여 구매할 수 있는 종목의 모든 조합을 만들어보는 메소드
	 * @param index : 현재 종목 인덱스 (ArrayList에 있는)
	 * @param money : 현재까지 남은 금액
	 * @param benefit : 현재까지 얻은 이익
	 */
	private static void DFS(int index, int money, int benefit) {
		// 종료 조건
		if (index == stockList.size()) {
			monthBenefit = Math.max(monthBenefit, benefit);
			return;
		}
		
		DFS(index + 1, money, benefit); // 현재 종목은 구매하지 않고 다음 종목으로 넘어가기
		// 구매 가능한 경우에만 현재 종목을 구매하기
		if (money - stockList.get(index).cur >= 0) {
			DFS(index, money - stockList.get(index).cur, benefit + stockList.get(index).diff);
		}
	}
}