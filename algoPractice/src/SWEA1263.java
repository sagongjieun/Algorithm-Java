import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 사람 네트워크2

public class SWEA1263 {
	
	static final int INF = 987654321;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			stk = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(stk.nextToken());
			int[][] network = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					network[i][j] = Integer.parseInt(stk.nextToken());
					
					if (i != j && network[i][j] == 0) network[i][j] = INF;
				}
			}
			
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (i == j) continue;

						network[i][j] = Math.min(network[i][j], network[i][k] + network[k][j]);
					}
				}
			}
			
			int answer = 987654321;
			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < N; j++) {
					sum += network[i][j];
				}
				answer = Math.min(answer, sum);
			}
			
			System.out.println("#" + tc + " " + answer);
		}
	}
}