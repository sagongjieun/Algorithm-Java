import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// RGB거리

public class BOJ1149 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		int N = Integer.parseInt(br.readLine());
		int[][] homes = new int[N][3];
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				homes[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		for (int i = 1; i < N; i++) {
			homes[i][0] += Math.min(homes[i-1][1], homes[i-1][2]);
			homes[i][1] += Math.min(homes[i-1][0], homes[i-1][2]);
			homes[i][2] += Math.min(homes[i-1][0], homes[i-1][1]);
		}
		
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			answer = Math.min(answer, homes[N-1][i]);
		}
		System.out.print(answer);
	}

}