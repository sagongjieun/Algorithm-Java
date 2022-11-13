import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

// 장훈이의 높은 선반

public class SWEA1486 {
	
	static int N, B;
	static int[] heights;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			stk = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(stk.nextToken()); // 점원의 수
			B = Integer.parseInt(stk.nextToken()); // 선반의 높이
			heights = new int[N];
			
			stk = new StringTokenizer(br.readLine());
			// N명의 점원들의 각 키
			for (int i = 0; i < N; i++) {
				heights[i] = Integer.parseInt(stk.nextToken());
			}
			
			Integer[] H = Arrays.stream(heights).boxed().toArray(Integer[]::new);
			Arrays.sort(H, Collections.reverseOrder());
			
			int sum = 0;
			for (int i = 0; i < N; i++) {
				if (sum >= B) {
					System.out.println("#" + tc + " " + (sum-B));
					break;
				}
				sum += H[i];
			}
		}
	}
}