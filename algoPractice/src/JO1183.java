import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 동전 자판기 하

public class JO1183 {
	
	static int W;
	static int[] input;
	static int[] coins;
	static int sum;
	static int answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		W = Integer.parseInt(br.readLine());
		input = new int[6];
		coins = new int[] {500, 100, 50, 10, 5, 1};
		
		// 코인별 금액과 개수를 곱한 값을 sum에 더해주어 총 금액 구하기
		stk = new StringTokenizer(br.readLine());
		for (int i=0; i<6; i++) {
			input[i] = Integer.parseInt(stk.nextToken());
			sum += input[i] * coins[i];
		}
		
		sum = sum - W; // 총 금액 sum에서 물건 금액 W를 빼주어 거스름돈 액수 구하기
		
		int index = 0;
		// 현재 가진 돈으로 거스름돈 금액을 맞출 때까지 반복
		while (sum > 0) {
			// 현재 동전의 개수가 없거나 거스름돈이 현재 동전의 금액보다 작으면
			// 다음 동전으로 경우의 수 찾기
			if (input[index] == 0 || sum < coins[index]) {
				index++; // 다음 동전으로 넘어가기
				continue;
			}
			
			// 아니라면 거스름돈에서 현재 동전의 금액만큼 빼주기
			sum = sum - coins[index];
			// 현재 동전을 사용했으니 개수 줄이기
			input[index]--;
		}
		
		for (int rest : input) answer += rest;
		System.out.println(answer);
		for (int rest : input) System.out.printf("%d ", rest);
	}
}