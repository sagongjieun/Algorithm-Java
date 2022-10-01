import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1, 2, 3 더하기 2

public class BOJ12101 {
	
	static int N, K;
	static int count;
	static String answer = null;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		
		backtracking(0);
		
		System.out.print(answer == null ? -1 : answer);
	}

	private static void backtracking(int sum) {
		if (sum == N) {
			// 합이 N일 때의 카운트를 세다가
			count += 1;
			// K번째가 되면 정답에 넣기
			if (count == K) {
				// 마지막 + 기호는 빼주기
				answer = sb.substring(0, sb.length() - 1);
			}
		}
		// 합이 N을 넘어가면 백트래킹
		else if (sum > N) return;
		
		for (int i = 1; i <= 3; i++) {
			sb.append(i + "+"); // 수 넣기
			backtracking(sum + i); // N의 수를 만들때까지 재귀
			sb.delete(sb.length() - 2, sb.length()); // 수 빼기
		}
	}
}