import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 불!

public class BOJ4179 {
	
	static int R, C;
	static char[][] map;
	static boolean[][] visited;
	
	static int answer = Integer.MAX_VALUE;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	
	static class Node {
		int y, x, time;

		public Node(int y, int x, int time) {
			this.y = y;
			this.x = x;
			this.time = time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		
		// 시작위치 (지훈이의 초기위치)
		int sy = 0;
		int sx = 0;
		
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
				
				if (map[i][j] == 'J') {
					sy = i;
					sx = j;
				}
			}
		}
		
		visited = new boolean[R][C];
		
		BFS(sy, sx);
		
		System.out.print(answer == Integer.MAX_VALUE ? "IMPOSSIBLE" : answer);
	}

	private static void BFS(int y, int x) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(y, x, 0));
		visited[y][x] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			if (cur.time > answer) continue;
			
			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				// 다음 좌표가 범위를 벗어나는데 지금 좌표가 빈 칸이라면 탈출구라는 의미
				if (!isInRange(ny, nx) && (map[cur.y][cur.x] == '.' || map[cur.y][cur.x]== 'J')) {
					answer = Math.min(answer, cur.time + 1);
					break;
				}
				if (isInRange(ny, nx) && !visited[ny][nx] && map[ny][nx] == '.') {
					q.add(new Node(ny, nx, cur.time + 1));
					visited[ny][nx] = true;
				}
			}
		}
	}

	private static boolean isInRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < R && x < C) return true;
		return false;
	}
}