import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

// N과 M(11) - 중복된 수를 입력받는 중복순열

public class BOJ15665 {

	static int N, M;
	static int[] nums;
	static int[] result;
	static boolean[] visited;

	static HashSet<String> answer;
	static StringBuilder sb = new StringBuilder();

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
		answer = new HashSet<>();
		visited = new boolean[N];

		duplicationPerm(0);

		System.out.print(sb.toString());
	}

	public static void duplicationPerm(int depth) {
		// 재귀 종료조건
		if (depth == M) {
			StringBuilder temp = new StringBuilder();

			// 결과들은 temp에 넣기
			for (int res : result) {
				temp.append(res + " ");
			};

			// 중복된 값이 있는지 체크하기
			if (!answer.contains(temp.toString())) {
				sb.append(temp.toString() + "\n");
				answer.add(temp.toString());
			}

			return;
		}

		for (int i = 0; i < N; i++) {
			result[depth] = nums[i];
			duplicationPerm(depth + 1);
		}
	}

}
