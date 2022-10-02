import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 벽 부수고 이동하기 2

public class BOJ14442 {
	
	static int N, M, K;
	static int[][] map;
	static boolean[][][] visited;
	
	static int answer = -1;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	
	static class Node {
		int y, x, count, distance;

		public Node(int y, int x, int count, int distance) {
			this.y = y;
			this.x = x;
			this.count = count;
			this.distance = distance;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken()); // K개까지 벽을 부술 수 있음
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		
		visited = new boolean[N][M][K+1];
		
		BFS(0, 0);
		
		System.out.print(answer);
	}

	private static void BFS(int y, int x) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(y, x, 0, 1)); // 시작 칸도 세기 때문에 distance는 1로 초기화
		visited[y][x][0] = true; // (y,x)좌표를 벽을 부수지 않고 방문했다는 의미
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			// 목적지에 도착하면 종료
			if (cur.y == N-1 && cur.x == M-1) {
				answer = cur.distance;
				break;
			}
			
			// 벽을 부수고 갈 때
			if (cur.count < K) {
				for (int i = 0; i < 4; i++) {
					int ny = cur.y + dy[i];
					int nx = cur.x + dx[i];
					
					// 해당 좌표를 벽을 부수고 간적이 없을때만
					if (isInRange(ny, nx) && map[ny][nx] == 1 && !visited[ny][nx][cur.count+1]) {
						q.add(new Node(ny, nx, cur.count+1, cur.distance+1));
						visited[ny][nx][cur.count+1] = true;
					}
				}
			}
			
			// 벽을 부수지 않을 때
			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if (isInRange(ny, nx) && map[ny][nx] == 0 && !visited[ny][nx][cur.count]) {
					q.add(new Node(ny, nx, cur.count, cur.distance+1));
					visited[ny][nx][cur.count] = true;
				}
			}
		}
	}

	private static boolean isInRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < N && x < M) return true;
		return false;
	}
}