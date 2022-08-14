import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 섬의 개수

public class BOJ4963_BFS {
	
	static int w, h;
	static int[][] map;
	static boolean[][] visited;
	static int island;
	// 8방위 탐색
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		while (true) {
			stk = new StringTokenizer(br.readLine());
			w = Integer.parseInt(stk.nextToken());
			h = Integer.parseInt(stk.nextToken());
			
			if (w == 0 && h == 0) break; // 0 0을 만나면 종료
			
			map = new int[h][w];
			for (int i=0; i<h; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j=0; j<w; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
				}
			}
			
			island = 0;
			visited = new boolean[h][w];
			for (int i=0; i<h; i++) {
				for (int j=0; j<w; j++) {
					if (map[i][j] == 1 && !visited[i][j]) {
						island += BFS(i, j);
					}
				}
			}
			
			sb.append(island + "\n");
		}
		
		System.out.print(sb);
	}
	
	public static int BFS(int y, int x) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {y, x});
		visited[y][x] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int curY = cur[0];
			int curX = cur[1];
			
			for (int i=0; i<8; i++) {
				int ny = curY + dy[i];
				int nx = curX + dx[i];
				
				if (rangeCheck(ny, nx) && !visited[ny][nx] && map[ny][nx] == 1) {
					visited[ny][nx] = true;
					q.add(new int[] {ny, nx});
				}
			}
		}
		
		return 1;
	}
	
	public static boolean rangeCheck(int y, int x) {
		if (y>=0 && x>=0 && y<h && x<w) return true;
		return false;
	}
}
