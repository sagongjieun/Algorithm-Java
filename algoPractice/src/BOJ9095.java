import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 1, 2, 3 더하기

public class BOJ9095 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] DP = new int[11];
			
			DP[1] = 1; DP[2] = 2; DP[3] = 4;
			
			for (int i = 4; i <= N; i++) {
				DP[i] = DP[i-1] + DP[i-2] + DP[i-3];
			}
			
			System.out.println(DP[N]);
		}
	}
}