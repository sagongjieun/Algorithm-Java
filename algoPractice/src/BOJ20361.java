import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 일우는 야바위꾼

public class BOJ20361 {

	static int N, X, K;
	static int[] nums;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;

		int answer = 0;

		stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		X = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());

		nums = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			nums[i] = i;
		}

		for (int i = 0; i < K; i++) {
			stk = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(stk.nextToken());
			int n2 = Integer.parseInt(stk.nextToken());

			int temp = nums[n1];
			nums[n1] = nums[n2];
			nums[n2] = temp;
		}

		for (int i = 1; i <= N; i++) {
			if (nums[i] == X) answer = i;
		}

		System.out.print(answer);
	}
}