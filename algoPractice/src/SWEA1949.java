import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 등산로 조성

public class SWEA1949 {
	
	static int N, K;
	static int[][] map;
	static int maxHeight;
	static int answer;
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
			stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			K = Integer.parseInt(stk.nextToken());
			map = new int[N][N];
			maxHeight = 0;
			answer = 0;
			
			for (int i = 0; i < N; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
					maxHeight = Math.max(maxHeight, map[i][j]);
				}
			}
			
			ArrayList<Node> maxHeightList = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == maxHeight) {
						maxHeightList.add(new Node(i, j));
					}
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 0; k <= K; k++) {
						map[i][j] -= k;
						
						for (int h = 0; h < maxHeightList.size(); h++) {
							DFS(1, maxHeightList.get(h).y, maxHeightList.get(h).x);
						}
						
						map[i][j] += k;
					}
				}
			}
			
			sb.append("#" + tc + " " + answer + "\n");
		}
		
		System.out.print(sb);
	}

	private static void DFS(int count, int y, int x) {
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			if (isInRange(ny, nx) && map[y][x] > map[ny][nx]) {
				DFS(count + 1, ny, nx);
			}
		}
		
		answer = Math.max(answer, count);
	}

	private static boolean isInRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < N && x < N) return true;
		return false;
	}
}