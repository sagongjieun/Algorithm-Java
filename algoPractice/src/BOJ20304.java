import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

// 비밀번호 제작

public class BOJ20304 {
	
	static int N, M;
	static int[] nums;
	static int answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		Deque<Integer> dq = new ArrayDeque<>();
		
		nums = new int[1000001];
		Arrays.fill(nums, Integer.MIN_VALUE);
		
		stk = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			int num = Integer.parseInt(stk.nextToken());
			nums[num] = 0;
			dq.offerLast(num);
		}
		
		while (!dq.isEmpty()) {
			int num = dq.pollFirst();
			
			for (int i = 0; i < 20; i++) {
				int nextNum = num ^ (1<<i);
				
				if (nextNum > N || nums[nextNum] != Integer.MIN_VALUE) continue;
				nums[nextNum] = nums[num] + 1;
				dq.offerLast(nextNum);
				
				answer = Math.max(answer, nums[nextNum]);
			}
		}
		
		System.out.print(answer);
	}
}