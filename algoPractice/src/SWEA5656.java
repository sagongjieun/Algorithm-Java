import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 벽돌 깨기

public class SWEA5656 {

	static int N, W, H;
	static int[][] map;
	static int answer;
	static int totalCount;
	static int burstCount;

	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			stk = new StringTokenizer(br.readLine());

			N = Integer.parseInt(stk.nextToken());
			W = Integer.parseInt(stk.nextToken());
			H = Integer.parseInt(stk.nextToken());

			totalCount = 0;
			map = new int[H][W];
			for (int i = 0; i < H; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
					if (map[i][j] != 0) totalCount++; // 전체 벽돌 개수 구하기
				}
			}

			answer = Integer.MAX_VALUE;
			DFS(0, map);

			sb.append("#" + tc + " " + answer + "\n");
		}

		System.out.print(sb);
	}

	private static boolean DFS(int count, int[][] map) {
		// 남은 벽돌이 없어지면 확인할 필요 없으므로 종료
		if (totalCount == 0) {
			answer = 0;
			return true;
		}

		// 던질 수 있는 구슬을 다 던졌을 경우
		if (count == N) {
			answer = Math.min(answer, totalCount);
			return false; // 다른 경우도 확인해봐야 하므로 false return
		}

		int[][] newMap = new int[H][W];
		// 모든 열에 한번씩 구슬 떨어뜨리기
		for (int x = 0; x < W; x++) {
			int y = 0;

			// x열에서 가장 위에있는 벽돌의 위치 찾기
			while (y < H && map[y][x] == 0) ++y;
			// x열에 벽돌이 없는 경우 패쓰
			if (y == H) continue;

			burstCount = 0; // 벽돌이 깨진 횟수 0으로 초기화
			copy(newMap); // 벽돌이 있다면 이전 구슬의 상태 복사해놓기
			burst(y, x, newMap, newMap[y][x]); // 구슬 떨어뜨리기
			downBrick(newMap);
			
			totalCount -= burstCount;
			if (DFS(count + 1, newMap)) return true;
		}

		return false;
	}

	private static void downBrick(int[][] map) {
		for (int x = 0; x < W; x++) {
			int y = H - 1;
			
			while (y > 0) {
				// 벽돌이 없는 빈 공간이라면
				if (map[y][x] == 0) {
					int ny = y - 1;
					
					while (ny > 0 && map[ny][x] == 0) ny--;
					
					map[y][x] = map[ny][x];
					map[ny][x] = 0;
				}
				
				y--;
			}
		}
	}

	private static void burst(int y, int x, int[][] map, int count) {
		map[y][x] = 0; // 벽돌이 깨지면 0 (최초에 구슬와 닿으니 깨짐)
		burstCount++;
		
		if (count == 1) return; // 벽돌의 파급력이 1이면 끝
		
		// 벽돌의 파급력이 2이상이면 상하좌우도 깨트리기
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < count - 1; j++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				
				if (isInRange(ny, nx) && map[ny][nx] != 0) burst(ny, nx, map, map[ny][nx]);
			}
		}
	}

	// 범위 체크 함수
	private static boolean isInRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < H && x < W) return true;
		return false;
	}

	// 2차원 배열 복사
	private static void copy(int[][] newMap) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				newMap[i][j] = map[i][j];
			}
		}
	}

}