import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 플로이드

public class BOJ11404 {
	
	static final int INF = 987654321;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int[][] nosun = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				nosun[i][j] = INF;
			}
		}
		 
		for (int i = 0; i < M; i++) {
			stk = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(stk.nextToken());
			int to = Integer.parseInt(stk.nextToken());
			int cost = Integer.parseInt(stk.nextToken());
			
			// 입력 받을 때부터 from -> to 로 갈 수 있는 경로가 여러 개라면 최소비용으로 넣어주기
			nosun[from][to] = Math.min(cost, nosun[from][to]);
		}
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (i == j) nosun[i][j] = 0; // 출발지와 목적지가 같다면 비용은 0
					nosun[i][j] = Math.min(nosun[i][j], nosun[i][k] + nosun[k][j]); // i->j 비용과 i->k->j 비용을 비교해 최소값으로 갱신
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (nosun[i][j] == INF) nosun[i][j] = 0; // 값이 갱신되지 않은 곳은 경로가 없다는 의미이므로 0
				sb.append(nosun[i][j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
}