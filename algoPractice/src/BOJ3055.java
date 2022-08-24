import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 탈출

public class BOJ3055 {

	static int R, C;
	static char[][] map;
	static int answer = Integer.MAX_VALUE;

	static Queue<int[]> qDochi = new LinkedList<>();
	static Queue<int[]> qWater = new LinkedList<>();

	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());

		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());

		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);

				// 고슴도치의 좌표 저장
				if (map[i][j] == 'S') { 
					qDochi.add(new int[] {i, j, 0});
				} 
				// 물의 위치 좌표 저장    
				else if (map[i][j] == '*') {
					qWater.add(new int[] {i, j});
				}         
			}
		}

		BFS(); // 고슴도치의 좌표에서 탐색시작

		System.out.print(answer == Integer.MAX_VALUE ? "KAKTUS" : answer);
	}

	private static void BFS() {

		// 도치가 이동할 수 있을때까지 반복
		while (!qDochi.isEmpty()) {

			// 물 확장
			int wsize = qWater.size();
			for (int i = 0; i < wsize; i++) {
				int[] cur = qWater.poll();
				int curY = cur[0];
				int curX = cur[1];

				for (int j = 0; j < 4; j++) {
					int ny = curY + dy[j];
					int nx = curX + dx[j];

					// 다음 좌표가 범위 안에 있고 빈칸이라면
					if (rangeCheck(ny, nx) && map[ny][nx] == '.') {
						map[ny][nx] = '*'; // 물로 확장
						qWater.add(new int[] {ny, nx});
					}
				}
			}

			// 도치 이동
			int dsize = qDochi.size();
			for (int i = 0; i < dsize; i++) {
				int[] cur = qDochi.poll();
				int curY = cur[0];
				int curX = cur[1];
				int time = cur[2];

				for (int j = 0; j < 4; j++) {
					int ny = curY + dy[j];
					int nx = curX + dx[j];

					if (rangeCheck(ny, nx)) {
						if (map[ny][nx] == 'D') {
							// 비버를 만나면 answer 를 최소값으로 계속 갱신
							answer = Math.min(answer, time + 1);
							return;
						}
						else if (map[ny][nx] == '.') {
							// 이동할 수 있는 칸이면 도치 이동시키고 time 을 늘리고 q에 삽입
							map[ny][nx] = 'S';
							qDochi.add(new int[] {ny, nx, time + 1});
						}
					}
				}
			}
		}
	}

	// 범위 체크 함수
	private static boolean rangeCheck(int y, int x) {
		if (y >= 0 && x >= 0 && y < R && x < C) return true;
		return false;
	}
}