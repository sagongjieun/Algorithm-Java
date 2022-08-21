import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

// N과 M(10) - 중복된 수를 입력받는 조합

public class BOJ15664 {
	
	static int N, M;
	static int[] nums, result;
	static HashSet<String> answer = new HashSet<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		nums = new int[N];
		result = new int[M];
		stk = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(stk.nextToken());
		}
		
		Arrays.sort(nums);
		
		comb(0, 0);
		
		System.out.print(sb);
	}

	public static void comb(int depth, int index) {
		// 재귀 종료조건
		if (depth == M) {
			StringBuilder temp = new StringBuilder();
			
			// 이번 턴의 결과를 temp에 저장하고
			for (int res : result) temp.append(res + " ");
			
			// hashSet 에 temp 값이 없다면 중복된 결과가 없다는 의미이므로
			// hashSet 에 값을 넣고, 결과를 출력할 sb에도 값을 넣기
			if (!answer.contains(temp.toString())) {
				answer.add(temp.toString());
				sb.append(temp.toString() + "\n");
			}
			
			return;
		}
		
		for (int i = index; i < N; i++) {
			result[depth] = nums[i];
			comb(depth + 1, i + 1);
		}
	}

}
