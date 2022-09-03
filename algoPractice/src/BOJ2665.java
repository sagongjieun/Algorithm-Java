import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 미로만들기

public class BOJ2665 {
	
	static int n;
	static int[][] map;
	static boolean[][] visited;
	
	static int answer;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	
	static class Node implements Comparable<Node> {
		int y, x, count;

		public Node(int y, int x, int count) {
			this.y = y;
			this.x = x;
			this.count = count;
		}

		@Override
		public int compareTo(Node o) {
			return count - o.count;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < n; j++) {
				int n = s.charAt(j) - '0';
				map[i][j] = n;
			}
		}
		
		visited = new boolean[n][n];
		
		BFS(0, 0);
		
		System.out.print(answer);
	}

	private static void BFS(int y, int x) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		visited[y][x] = true;
		pq.add(new Node(y, x, 0));
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (cur.y == n-1 && cur.x == n-1) {
				answer = cur.count;
				break;
			}
			
			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if (isInRange(ny, nx) && !visited[ny][nx]) {
					// 흰 방이라면
					if (map[ny][nx] == 1) {
						visited[ny][nx] = true;
						pq.add(new Node(ny, nx, cur.count));
					}
					// 검은 방이라면
					else {
						visited[ny][nx] = true;
						pq.add(new Node(ny, nx, cur.count + 1));
					}
				}
			}
		}
	}

	private static boolean isInRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < n && x < n) return true;
		return false;
	}

}