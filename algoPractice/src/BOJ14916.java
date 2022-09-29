import java.util.Scanner;

// 거스름돈

public class BOJ14916 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int goalMoney = sc.nextInt(); // 목표 금액
		int[] DP = new int[goalMoney + 1]; // DP[i] : i금액을 만드는 최소 동전의 수
		
		DP[0] = 0; // 0원에 대한 최적해는 0 (꼭 필요한 코드는 아님)
		for (int i = 1; i <= goalMoney; i++) {
			DP[i] = 50000; // 가장 작은 화폐단위를 가장 많이 써도 만들수 없는 가장 큰 값, +1 처리 시 오버플로우 발생하지 않는 값으로 초기화
			
			if (i >= 2) DP[i] = Math.min(DP[i], DP[i-2] + 1);
			if (i >= 5) DP[i] = Math.min(DP[i], DP[i-5] + 1);
		}
		
		System.out.print(DP[goalMoney] == 50000 ? -1 : DP[goalMoney]);
	}
}