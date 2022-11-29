import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A_나무의키 {

	private static int N, maxHeight, sum, odd, even, answer;
	private static int[] height;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(stk.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			height = new int[N];
			maxHeight = 0; // 가장 키 큰 나무의 키
			sum = 0; // 모든 나무가 성장해야할 총 크기

			stk = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				height[i] = Integer.parseInt(stk.nextToken());
				maxHeight = Math.max(height[i], maxHeight);
			}

			odd = 0; // 1의 개수 (홀수 날짜의 개수)
			
			for (int i = 0; i < N; i++) {
				height[i] = maxHeight - height[i]; // 나무가 커야하는 크기
				sum += height[i]; // 모든 나무가 성장해야할 총 크기
				
				// 홀수날짜에는 반드시 (홀수날짜의 물)1을 한 번 이상 줘야한다
				if (height[i] % 2 != 0) {
					odd++; // 1의 개수 카운팅
				}
			}
			
			answer = 0; // 물을 줘야하는 최소 일 수
			even = (sum - odd) / 2; // 2를 사용할 개수
			// 1의 개수만큼 2의 개수 사용
			if (even == odd) {
				answer = odd + even;
			}
			// 1의 개수가 더 많은 경우
			else if (even < odd) {
				answer = odd * 2 - 1;
//				answer = even * 2 + (odd - even) * 2 - 1;
			}
			// 2의 개수가 더 많은 경우
			else {
				answer = odd + even + (even - odd + 2) / 3;
//				answer = odd * 2 + (even - odd) +  (even - odd + 2) / 3;
			}

			sb.append("#" + tc + " " + answer + "\n");
		}

		System.out.print(sb);
	}
}