import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2293 {

	static int n, k;
	static int[] DP;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;

		stk = new StringTokenizer(br.readLine());
		n = Integer.parseInt(stk.nextToken());
		k = Integer.parseInt(stk.nextToken());

		DP = new int[k + 1];
		DP[0] = 1;

		for (int i=0; i<n; i++) {
			int coin = Integer.parseInt(br.readLine());

			for (int j=1; j<=k; j++) {
				if (j >= coin) DP[j] += DP[j - coin];
			}
		}
		
		System.out.print(DP[k]);
	}
}