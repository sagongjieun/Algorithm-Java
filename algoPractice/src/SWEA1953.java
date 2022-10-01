import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 탈주범 검거

public class SWEA1953 {

	static int N, M, R, C, L;
	static int[][] map;
	static boolean[][] visited;
	
	static int answer;
	// 상 우 하 좌
	static int[] dy;
	static int[] dx;
	
	static class Node {
		int y, x, time, count;

		public Node(int y, int x, int time, int count) {
			this.y = y;
			this.x = x;
			this.time = time;
			this.count = count;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			stk = new StringTokenizer(br.readLine());

			N = Integer.parseInt(stk.nextToken()); // 지도 r
			M = Integer.parseInt(stk.nextToken()); // 지도 c
			R = Integer.parseInt(stk.nextToken()); // 맨홀 y
			C = Integer.parseInt(stk.nextToken()); // 맨홀 x
			L = Integer.parseInt(stk.nextToken()); // 소요시간

			map = new int[N][M];
			for (int i = 0; i < N; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
				}
			}

			visited = new boolean[N][M];
			answer = 0;

			BFS(R, C);
			
			sb.append("#" + tc + " " + answer + "\n");
		}
		
		System.out.print(sb);
	}

	private static void BFS(int y, int x) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(y, x, 1, 1));
		visited[y][x] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			// 소요시간이 다되면 탈주범이 위치할 수 있는 장소의 개수를 저장하고 종료
			if (cur.time >= L) {
				answer = cur.count;
				break;
			}
			
			int type = map[cur.y][cur.x];
			switch (type) {
			case 1:
				dy = new int[]{-1, 0, 1, 0};
				dx = new int[]{0, 1, 0, -1};
				
				for (int i = 0; i < 4; i++) {
					int ny = cur.y + dy[i];
					int nx = cur.x + dx[i];
					
					if (isInRange(ny, nx) && !visited[ny][nx] && map[ny][nx] != 0) {
						q.add(new Node(ny, nx, cur.time + 1, cur.count + 1));
						visited[ny][nx] = true;
					}
				}
				break;
			case 2:
				dy = new int[]{-1, 1};
				dx = new int[]{0, 0};
				
				for (int i = 0; i < 2; i++) {
					int ny = cur.y + dy[i];
					int nx = cur.x + dx[i];
					
					if (isInRange(ny, nx) && !visited[ny][nx] && map[ny][nx] != 0 
							&& map[ny][nx] != 3) {
						q.add(new Node(ny, nx, cur.time + 1, cur.count + 1));
						visited[ny][nx] = true;
					}
				}
				break;
			case 3:
				dy = new int[]{0, 0};
				dx = new int[]{1, -1};
				
				for (int i = 0; i < 2; i++) {
					int ny = cur.y + dy[i];
					int nx = cur.x + dx[i];
					
					if (isInRange(ny, nx) && !visited[ny][nx] && map[ny][nx] != 0
							&& map[ny][nx] != 2) {
						q.add(new Node(ny, nx, cur.time + 1, cur.count + 1));
						visited[ny][nx] = true;
					}
				}
				break;
			case 4:
				dy = new int[]{-1, 0};
				dx = new int[]{0, 1};
				
				for (int i = 0; i < 2; i++) {
					int ny = cur.y + dy[i];
					int nx = cur.x + dx[i];
					
					if (isInRange(ny, nx) && !visited[ny][nx] && map[ny][nx] != 0) {
						q.add(new Node(ny, nx, cur.time + 1, cur.count + 1));
						visited[ny][nx] = true;
					}
				}
				break;
			case 5:
				dy = new int[]{0, 1};
				dx = new int[]{1, 0};
				
				for (int i = 0; i < 2; i++) {
					int ny = cur.y + dy[i];
					int nx = cur.x + dx[i];
					
					if (isInRange(ny, nx) && !visited[ny][nx] && map[ny][nx] != 0) {
						q.add(new Node(ny, nx, cur.time + 1, cur.count + 1));
						visited[ny][nx] = true;
					}
				}
				break;
			case 6:
				dy = new int[]{1, 0};
				dx = new int[]{0, -1};
				
				for (int i = 0; i < 2; i++) {
					int ny = cur.y + dy[i];
					int nx = cur.x + dx[i];
					
					if (isInRange(ny, nx) && !visited[ny][nx] && map[ny][nx] != 0) {
						q.add(new Node(ny, nx, cur.time + 1, cur.count + 1));
						visited[ny][nx] = true;
					}
				}
				break;
			case 7:
				dy = new int[]{-1, 0};
				dx = new int[]{0, -1};
				
				for (int i = 0; i < 2; i++) {
					int ny = cur.y + dy[i];
					int nx = cur.x + dx[i];
					
					if (isInRange(ny, nx) && !visited[ny][nx] && map[ny][nx] != 0) {
						q.add(new Node(ny, nx, cur.time + 1, cur.count + 1));
						visited[ny][nx] = true;
					}
				}
				break;
			}
		}
	}

	private static boolean isInRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < N && x < M) return true;
		return false;
	}
}