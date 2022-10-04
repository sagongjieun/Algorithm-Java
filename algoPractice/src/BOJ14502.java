import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 연구소

public class BOJ14502 {
	
	static int N, M;
	static int[][] map;
	
	static int safeArea = Integer.MIN_VALUE;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	
	static class Virus {
		int y, x;

		public Virus(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		setWalls(0);
		
		System.out.print(safeArea);
	}

	// DFS
	private static void setWalls(int count) {
		// 벽을 3개 다 세우면
		if (count == 3) {
			// 이 조합으로 바이러스 퍼지게 하기
			spreadVirus();
			return;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1; // 벽 세우기
					setWalls(count + 1); // 다음 벽 세우러 가기
					map[i][j] = 0; // 되돌리기
				}
			}
		}
	}

	// BFS
	private static void spreadVirus() {
		int[][] newMap = new int[N][M];
		Queue<Virus> q = new LinkedList<>();
		
		// 새로운 map에 원래 map copy
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newMap[i][j] = map[i][j];
			}
		}
		
		// 바이러스는 q에 넣기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (newMap[i][j] == 2) q.add(new Virus(i, j));
			}
		}
		
		while (!q.isEmpty()) {
			Virus cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if (isInRange(ny, nx) && newMap[ny][nx] == 0) {
					q.add(new Virus(ny, nx));
					newMap[ny][nx] = 2;
				}
			}
		}
		
		// 안전영역 개수 구하러가기
		getSafeArea(newMap);
	}

	private static void getSafeArea(int[][] map) {
		int safeAreaCount = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) safeAreaCount++;
			}
		}
		
		safeArea = Math.max(safeArea, safeAreaCount);
	}

	// 범위 체크 함수
	private static boolean isInRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < N && x < M) return true;
		return false;
	}
	
}

// 1. map에서 0인 곳에 벽을 3개 놓는 모든 조합을 구하고
// 2. 각 조합에서 벽을 세웠을 때 퍼질 수 있는 바이러스의 상태를 구하고
// 3. 그 바이러스의 상태에서 갱신된 map에서 얻을 수 있는 안전영역의 개수를 구하기 (최대값으로 갱신)