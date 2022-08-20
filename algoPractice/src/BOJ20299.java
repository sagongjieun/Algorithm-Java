import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 3대 측정

public class BOJ20299 {
	
	static int N, S, M;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(stk.nextToken());
		S = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		int answer = 0;
		
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(stk.nextToken());
			int x2 = Integer.parseInt(stk.nextToken());
			int x3 = Integer.parseInt(stk.nextToken());
			
			if (isPossible(x1, x2, x3)) {
				answer++;
				sb.append(x1 + " " + x2 + " " + x3 + " ");
			}
		}
		
		System.out.println(answer);
		System.out.print(sb);
	}

	public static boolean isPossible(int x1, int x2, int x3) {
		if (x1 >= M && x2 >= M && x3 >= M && (x1 + x2 + x3) >= S) return true;
		return false;
	}
}
