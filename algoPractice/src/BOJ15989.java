import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 1, 2, 3 더하기 4

public class BOJ15989 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] DP = new int[4][N+1];
			
			for (int i = 1; i <= 3; i++) {
				for (int j = 1; j <= N; j++) {
					if (i == j) DP[i][j] += 1;
					DP[i][j] += DP[i-1][j];
					if (j > i) DP[i][j] += DP[i][j-i];
				}
			}

			sb.append(DP[3][N] + "\n");
		}
		
		System.out.print(sb);
	}
}