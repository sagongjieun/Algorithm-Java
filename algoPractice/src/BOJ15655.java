import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// N과 M(6) - 입력받는 조합

public class BOJ15655 {

	static int N, M;
	static int[] nums;
	static int[] answer;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());

		nums = new int[N];
		stk = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(stk.nextToken());
		}

		Arrays.sort(nums); // 사전 순 출력을 위한 오름차순 정렬
		answer = new int[M];

		comb(0, 0);

		System.out.print(sb);

	}

	public static void comb(int depth, int start) {
		// 재귀 종료조건
		if (depth == M) {
			for (int ans : answer) {
				sb.append(ans + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = start; i < N; i++) {
			answer[depth] = nums[i];
			comb(depth + 1, i + 1);
		}
	}

}