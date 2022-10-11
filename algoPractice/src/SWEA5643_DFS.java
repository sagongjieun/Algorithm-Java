import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 키 순서 - DFS

public class SWEA5643_DFS {

	static int N, M;
	static int[][] map;
	static int answer;
	static int gtCount, ltCount;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());

			map = new int[N+1][N+1];
			for (int i = 0; i < M; i++) {
				stk = new StringTokenizer(br.readLine());
				map[Integer.parseInt(stk.nextToken())][Integer.parseInt(stk.nextToken())] = 1;
			}

			answer = 0;

			for (int i = 1; i <= N; i++) {
				gtCount = 0;
				ltCount = 0;
				gtDFS(i, new boolean[N+1]);
				ltDFS(i, new boolean[N+1]);

				if (gtCount + ltCount == N-1) answer++;
			}

			System.out.println("#" + tc + " " + answer);
		}
	}

	private static void gtDFS(int target, boolean[] visited) {
		visited[target] = true;

		for (int i = 1; i <= N; i++) {
			if (!visited[i] && map[target][i] == 1) {
				gtCount++;
				gtDFS(i, visited);
			}
		}
	}

	private static void ltDFS(int target, boolean[] visited) {
		visited[target] = true;

		for (int i = 1; i <= N; i++) {
			if (!visited[i] && map[i][target] == 1) {
				ltCount++;
				ltDFS(i, visited);
			}
		}
	}
}