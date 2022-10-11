import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 키 순서 - BFS

public class SWEA5643_BFS {
	
	static int N, M;
	static int[][] map;
	static int answer;

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
				// i번째 학생을 기준으로 더 작은키의 학생수와
				// 큰 키의 학생수의 합이 N-1명이면 순서가 정해져있다는 의미이므로 정답 +1
				if (gtBFS(i) + ltBFS(i) == N-1) answer++;
			}
			
			System.out.println("#" + tc + " " + answer);
		}
	}

	private static int gtBFS(int target) {
		int count = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visited = new boolean[N+1];
		q.add(target);
		visited[target] = true;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (int i = 1; i <= N; i++) {
				if (!visited[i] && map[cur][i] == 1) {
					count++;
					visited[i] = true;
					q.add(i);
				}
			}
		}
		
		return count;
	}

	private static int ltBFS(int target) {
		int count = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visited = new boolean[N+1];
		q.add(target);
		visited[target] = true;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (int i = 1; i <= N; i++) {
				if (!visited[i] && map[i][cur] == 1) {
					count++;
					visited[i] = true;
					q.add(i);
				}
			}
		}
		
		return count;
	}
}