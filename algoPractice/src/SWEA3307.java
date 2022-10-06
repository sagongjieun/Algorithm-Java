import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 최장 증가 부분 수열 : O(N2)

public class SWEA3307 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			int answer = 0;
			
			int N = Integer.parseInt(br.readLine());
			
			int[] nums = new int[N];
			stk = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(stk.nextToken());
			}
			
			int[] DP = new int[N]; // DP[i] : i번째 원소를 마지막 수로 가지는 최장 증가 부분 수열의 길이
			DP[0] = 1;
			
			for (int cur = 1; cur < N; cur++) {
				DP[cur] = 1;
				
				for (int prev = 0; prev < cur; prev++) {
					// 이전수보다 작고 이전수를 붙였을 때 현재의 최장 증가 부분 수열 길이가 최대로 갱신된다면 갱신하기
					if (nums[cur] > nums[prev] && DP[cur] < DP[prev] + 1) {
						DP[cur] = DP[prev] + 1;
					}
				}
				
				answer = Math.max(DP[cur], answer);
			}
			
			System.out.println("#" + tc + " " + answer);
		}
	}
}