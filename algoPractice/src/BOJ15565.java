import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 귀여운 라이언

public class BOJ15565 {
	
	static int N, K;
	static int[] dolls;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		
		dolls = new int[N];
		stk = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) dolls[i] = Integer.parseInt(stk.nextToken());
		
		int answer = Integer.MAX_VALUE;
		int ryanCount = 0, left = 0, right = 0;
		
		while (left <= right && right <= N) {
			// 라이언 개수가 K가 안됐다면
			if (ryanCount < K) {
				if (right == N) break; // 범위를 벗어나면 break
				if (dolls[right] == 1) ryanCount++; // 라이언이면 개수 +1
				right++; // right++ 하며 계속진행
			}
			// 라이언 개수가 K개이거나 넘으면
			else {
				answer = Math.min(answer, right-left); // 최소의 개수 찾기
				if (dolls[left] == 1) ryanCount--; // left의 위치에 라이언이면 left++ 하며 최소의 개수 찾기 진행
				left++; // left++ 하며 계속진행
			}
		}
		
		if (answer == Integer.MAX_VALUE) System.out.print(-1);
		else System.out.print(answer);
	}
}
