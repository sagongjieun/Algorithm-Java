import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class A_산악구조로봇 {
	
	static int N, answer;
	static int[][] map, visited;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	
	static class Node {
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
				}
			}
			
			visited = new int[N][N]; // 같은 곳에 더 적은 비용으로 오게 될 경우를 체크 -> 다익스트라
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					visited[i][j] = Integer.MAX_VALUE;
				}
			}
			
			answer = BFS();
			
			sb.append("#" + tc + " " + answer + "\n");
		}
		
		System.out.print(sb);
	}

	private static int BFS() {
		Queue<Node> q = new ArrayDeque<>();
		// 시작점은 0, 0
		q.add(new Node(0, 0));
		// 시작점 연료는 0으로 초기화
		visited[0][0] = 0;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int ny = cur.y + dy[d];
				int nx = cur.x + dx[d];
				
				if (isInRange(ny, nx)) {
					int cost = visited[cur.y][cur.x]; // 현재 위치의 연료 량 (가중치)
					
					if (map[cur.y][cur.x] == map[ny][nx]) cost += 1; // 높이가 같으면 연료 1 소모
					else if (map[cur.y][cur.x] < map[ny][nx]) cost += (map[ny][nx] - map[cur.y][cur.x]) * 2; // 더 높은 곳으로 이동하면 높이 차의 두 배의 연료 소모
					
					// 더 적은 비용으로 이동할 수 있다면 갱신
					if (visited[ny][nx] > cost) {
						q.add(new Node(ny, nx));
						visited[ny][nx] = cost;
					}
				}
			}
		}
		
		return visited[N-1][N-1];
	}

	private static boolean isInRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < N && x < N) return true;
		return false;
	}
}