import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 내려가기

public class BOJ2096 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] maxDP = new int[3];
		int[] minDP = new int[3];
		
		for (int i = 0; i < N; i++) {
			
			stk = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(stk.nextToken());
			int b = Integer.parseInt(stk.nextToken());
			int c = Integer.parseInt(stk.nextToken());
			
			if (i == 0) {
				maxDP[0] = minDP[0] = a;
				maxDP[1] = minDP[1] = b;
				maxDP[2] = minDP[2] = c;
				continue;
			}
			
			// DP의 값을 바꿔주는 과정에서 계속 갱신되므로 이전의 값들은 따로 변수에 저장해두기
			int prevMax0 = maxDP[0], prevMax1 = maxDP[1], prevMax2 = maxDP[2];
			maxDP[0] = Math.max(prevMax0, prevMax1) + a;
			maxDP[1] = Math.max(prevMax0, Math.max(prevMax1, prevMax2)) + b;
			maxDP[2] = Math.max(prevMax1, prevMax2) + c;
			
			int prevMin0 = minDP[0], prevMin1 = minDP[1], prevMin2 = minDP[2];
			minDP[0] = Math.min(prevMin0, prevMin1) + a;
			minDP[1] = Math.min(prevMin0, Math.min(prevMin1, prevMin2)) + b;
			minDP[2] = Math.min(prevMin1, prevMin2) + c;
		}
		
		int max = Math.max(maxDP[0], Math.max(maxDP[1], maxDP[2])); 
		int min = Math.min(minDP[0], Math.min(minDP[1], minDP[2]));
		
		System.out.print(max + " " + min);
	}

}