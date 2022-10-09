import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 외판원 순회 2

public class BOJ10971 {
	
	static int N;
	static int[][] path;
	static boolean[] visited;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		N = Integer.parseInt(br.readLine());
		
		path = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				path[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		visited = new boolean[N+1];
		
		// 출발지점 선택하기
		for (int i = 1; i <= N; i++) {
			visited[i] = true;
			DFS(i, i, 1, 0);
			visited[i] = false;
		}
		
		System.out.print(answer);
	}

	/**
	 * @param start : 출발 도시번호
	 * @param cur : 현재 도시번호
	 * @param count : 몇개의 도시를 들렀는지
	 * @param cost : 현재까지의 비용
	 */
	private static void DFS(int start, int cur, int count, int cost) {
		// N개의 도시를 다 들렀으면
		if (count == N) {
			// 현재도시 -> 출발도시로의 경로가 있다면
			if (path[cur][start] != 0) {
				// 해당 경로 비용을 더한 값과 현재 answer 중 최소비용으로 갱신
				answer = Math.min(answer, cost + path[cur][start]);
			}
			// 백트래킹
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			// 아직 방문하지 않은 도시중에 현재도시에서 경로가 존재하는 도시라면
			if (!visited[i] && path[cur][i] != 0) {
				visited[i] = true;
				DFS(start, i, count + 1, cost + path[cur][i]);
				visited[i] = false;
			}
		}
	}
}