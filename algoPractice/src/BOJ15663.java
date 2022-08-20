import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

// N과 M(9) - 중복된 수를 입력받는 순열

public class BOJ15663 {

	static int N, M;
	static int[] nums;
	static int[] result;
	static boolean[] visited;
	
	static LinkedHashSet<String> answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());

		nums = new int[N];
		result = new int[M];
		stk = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(stk.nextToken());
		}

		Arrays.sort(nums); // 사전 순 출력을 위한 오름차순 정렬
		answer = new LinkedHashSet<>();
		visited = new boolean[N];

		perm(0);

		answer.forEach(System.out::println);
	}
	
	public static void perm(int depth) {
		// 재귀 종료조건
		if (depth == M) {
			StringBuilder sb = new StringBuilder();
			
			for (int res : result) {
				sb.append(res + " ");
			}
			answer.add(sb.toString());
			return;
		}

		for (int i = 0; i < N; i++) {
			// 중복되면 안되니 방문하지 않은 수에 대해서만
			if (!visited[i]) {
				visited[i] = true;
				result[depth] = nums[i];
				perm(depth + 1);
				visited[i] = false;
			}
		}
	}
}
