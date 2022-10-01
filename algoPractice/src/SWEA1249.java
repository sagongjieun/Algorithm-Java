import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 보급로

public class SWEA1249 {

	static int N;
	static int[][] map;
	static boolean[][] visited;

	static int answer;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};

	static class Node implements Comparable<Node> {
		int y, x, time;

		public Node(int y, int x, int time) {
			this.y = y;
			this.x = x;
			this.time = time;
		}

		@Override
		public int compareTo(Node o) {
			// 시간 오름차순
			return time - o.time;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			// 입력받기
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = s.charAt(j) - '0';
				}
			}
			visited = new boolean[N][N];
			answer = 0;
			
			BFS(0, 0);
			
			sb.append("#" + tc + " " + answer + "\n");
		}
		
		System.out.print(sb);
	}

	private static void BFS(int y, int x) {
		// 시간을 오름차순으로 q에 넣기 위해 pq 선언
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(y, x, 0));
		visited[y][x] = true;
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			// 목적지에 도착하면 종료
			if (cur.y == N-1 && cur.x == N-1) {
				answer = cur.time;
				break;
			}
			
			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				// 범위안에 있고 방문하지 않았다면 pq에 넣기
				if (isInRange(ny, nx) && !visited[ny][nx]) {
					visited[ny][nx] = true;
					pq.add(new Node(ny, nx, cur.time + map[ny][nx]));
				}
			}
		}
	}

	private static boolean isInRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < N && x < N) return true;
		return false;
	}
}