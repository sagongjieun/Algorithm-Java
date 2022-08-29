import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 차이를 최대로

public class BOJ10819 {

	static int N;
	static int[] nums, orderedNums;
	static boolean[] used;
	static int answer = Integer.MIN_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;

		N = Integer.parseInt(br.readLine());

		nums = new int[N];
		orderedNums = new int[N];
		stk = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(stk.nextToken());
		}
		
		used = new boolean[N];

		orderChange(0);
		
		System.out.print(answer);
	}

	private static void orderChange(int index) {
		if (index == N) {
			int result = calc(orderedNums);
			answer = Math.max(answer, result);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (!used[i]) {
				used[i] = true;
				orderedNums[index] = nums[i];
				orderChange(index + 1);
				used[i] = false;
			}
		}
	}

	private static int calc(int[] nums) {
		int result = 0;
		
		for (int i = 0; i < N - 1; i++) {
			result += Math.abs(nums[i] - nums[i+1]);
		}
		
		return result;
	}
}