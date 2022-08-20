import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1182 {
	
	static int N, S;
	static int[] nums;
	static boolean[] visited;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken()); // 정수의 개수 N
		S = Integer.parseInt(stk.nextToken()); // 원소의 합 S
		
		nums = new int[N];
		stk = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) nums[i] = Integer.parseInt(stk.nextToken());
		
		visited = new boolean[N];
		
		powerSet(0, 0);
		
		// 공집합도 포함되기 때문에 S가 0인 경우에는 정답 -1
		answer = S == 0 ? answer - 1 : answer;
		
		System.out.print(answer);
	}

	public static void powerSet(int depth, int sum) {
		
		// 백트래킹 조건
		if (depth == N) {
			if (sum == S) {
				answer++;
			}
			return;
		}
		
		// depth번째 index의 수를 포함하지 않을 때
		visited[depth] = false;
		powerSet(depth + 1, sum);
		
		// depth번째 index의 수를 포함할 때
		visited[depth] = true;
		powerSet(depth + 1, sum + nums[depth]);
		
	}
}