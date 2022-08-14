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
		
		// 종료 조건을 만나기 전까지 반복
		while (true) {
			stk = new StringTokenizer(br.readLine());
			w = Integer.parseInt(stk.nextToken());
			h = Integer.parseInt(stk.nextToken());
			
			// 종료 조건
			// 0 0 을 만나면 종료
			if (w == 0 && h == 0) break;
			
			map = new int[h][w];
			for (int i=0; i<h; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j=0; j<w; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
				}
			}
			
			// 매 테스트케이스마다 섬의 개수와 visited 배열 초기화
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
			
			// 8방위 탐색을 하며 조건에 부합하면 해당 좌표를 q에 담기
			for (int i=0; i<8; i++) {
				int ny = curY + dy[i];
				int nx = curX + dx[i];
				
				if (rangeCheck(ny, nx) && !visited[ny][nx] && map[ny][nx] == 1) {
					visited[ny][nx] = true;
					q.add(new int[] {ny, nx});
				}
			}
		}
		
		// 인접해있는 섬들을 다 탐색하고 나면 1 return
		return 1;
	}
	
	// 범위 체크 함수
	public static boolean rangeCheck(int y, int x) {
		if (y>=0 && x>=0 && y<h && x<w) return true;
		return false;
	}
}
