import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 토마토 - 3차원

public class BOJ7569 {
	
	static int M, N, H;
	static int[][][] boxes;
	static Queue<Tomato> q = new LinkedList<>();
	
	// 앞 오 뒤 왼 위 아래
	static int[] dy = {-1, 0, 1, 0, 0, 0};
	static int[] dx = {0, 1, 0, -1, 0, 0};
	static int[] dh = {0, 0, 0, 0, 1, -1};
	
	static class Tomato {
		int h, y, x;

		public Tomato(int h, int y, int x) {
			this.h = h;
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(stk.nextToken()); // 열
		N = Integer.parseInt(stk.nextToken()); // 행
		H = Integer.parseInt(stk.nextToken()); // 높이
		
		boxes = new int[H][N][M];
		for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					boxes[h][i][j] = Integer.parseInt(stk.nextToken());
					
					if (boxes[h][i][j] == 1) q.add(new Tomato(h, i, j));
				}
			}
		}
		
		int answer = BFS();
		
		System.out.print(answer);
	}

	private static int BFS() {
		
		int result = Integer.MIN_VALUE;
		
		while (!q.isEmpty()) {
			Tomato cur = q.poll();
			
			int h = cur.h;
			int y = cur.y;
			int x = cur.x;
			
			for (int i = 0; i < 6; i++) {
				int nh = h + dh[i];
				int ny = y + dy[i];
				int nx = x + dx[i];
				
				if (isInRange(nh, ny, nx)) {
					if (boxes[nh][ny][nx] == 0) {
						q.add(new Tomato(nh, ny, nx));
						boxes[nh][ny][nx] = boxes[h][y][x] + 1;
					}
				}
			}
		}
		
		for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					// 안 익은 토마토가 있으면
					if (boxes[h][i][j] == 0) return -1;
					result = Math.max(result, boxes[h][i][j]);
				}
			}
		}
		
		if (result == 1) return 0;
		else return result - 1;
	}

	// 범위 체크 함수
	private static boolean isInRange(int h, int y, int x) {
		if (h >= 0 && y >= 0 && x >= 0 && h < H && y < N  && x < M) return true;
		return false;
	}

}