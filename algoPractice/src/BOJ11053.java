import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 가장 긴 증가하는 부분 수열

public class BOJ11053 {
	
	static int N;
	static int[] nums;
	static int[] DP;
	static int answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		nums = new int[N];
		DP = new int[N];
		
		StringTokenizer stk = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(stk.nextToken());
		}
		
		for (int cur = 0; cur < N; cur++) {
			DP[cur] = 1; // 현재 인덱스의 수열 개수 1로 세팅
			
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
		
		for (int i = 0; i < N; i++) {
			answer = Math.max(answer, DP[i]);
		}
		
		System.out.print(answer);
	}
}