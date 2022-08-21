import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 가장 긴 증가하는 부분 수열

public class BOJ11053 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] nums = new int[N];
		int[] DP = new int[N];
		
		StringTokenizer stk = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(stk.nextToken());
			DP[i] = 1; // 모든 수열의 수열 개수를 1로 세팅
		}
		
		for (int cur = 0; cur < N; cur++) {
			// 현재 이전의 원소들에 대해
			for (int prev = 0; prev < cur; prev++) {
				// 현재의 수가 이전의 수보다 크고
				// 현재의 수열 개수가 이전 수의 수열개수 +1 보다 작으면
				if (nums[cur] > nums[prev] && DP[cur] < DP[prev] + 1) {
					// 현재의 수열 개수에 이전의 수열 개수 +1 해서 갱신
					DP[cur] = DP[prev] + 1;
				}
			}
		}
		
		int answer = 0;
		
		for (int i = 0; i < N; i++) {
			answer = Math.max(answer, DP[i]);
		}
		
		System.out.print(answer);
	}
}