import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 탈주범 검거

public class SWEA1953 {

	static int N, M, R, C, L;
	static int[][] map;
	static boolean[][] visited;
	static int answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;

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

			getCriminal(R, C, 0, 0, map[R][C]);
			
			System.out.println("#" + tc + " " + answer);
		}
	}

	private static void getCriminal(int y, int x, int count, int time, int flag) {
		if (time >= L) {
			answer = count;
			return;
		}
		
		if (flag == 1) {
			if (isInRange(y-1, x) && map[y-1][x] != 0) getCriminal(y-1, x, count + 1, time + 1, map[y-1][x]);
			if (isInRange(y, x+1) && map[y][x+1] != 0) getCriminal(y, x+1, count + 1, time + 1, map[y][x+1]);
			if (isInRange(y+1, x) && map[y+1][x] != 0) getCriminal(y+1, x, count + 1, time + 1, map[y+1][x]);
			if (isInRange(y, x-1) && map[y][x-1] != 0) getCriminal(y, x-1, count + 1, time + 1, map[y][x-1]);
		}
		else if (flag == 2) {
			if (isInRange(y-1, x) && map[y-1][x] != 0) getCriminal(y-1, x, count + 1, time + 1, map[y-1][x]);
			if (isInRange(y+1, x) && map[y+1][x] != 0) getCriminal(y+1, x, count + 1, time + 1, map[y+1][x]);
		}
		else if (flag == 3) {
			if (isInRange(y, x+1) && map[y][x+1] != 0) getCriminal(y, x+1, count + 1, time + 1, map[y][x+1]);
			if (isInRange(y, x-1) && map[y][x-1] != 0) getCriminal(y, x-1, count + 1, time + 1, map[y][x-1]);
		}
		else if (flag == 4) {
			if (isInRange(y-1, x) && map[y-1][x] != 0) getCriminal(y-1, x, count + 1, time + 1, map[y-1][x]);
			if (isInRange(y, x+1) && map[y][x+1] != 0) getCriminal(y, x+1, count + 1, time + 1, map[y][x+1]);
		}
		else if (flag == 5) {
			if (isInRange(y, x+1) && map[y][x+1] != 0) getCriminal(y, x+1, count + 1, time + 1, map[y][x+1]);
			if (isInRange(y+1, x) && map[y+1][x] != 0) getCriminal(y+1, x, count + 1, time + 1, map[y+1][x]);
		}
		else if (flag == 6) {
			if (isInRange(y+1, x) && map[y+1][x] != 0) getCriminal(y+1, x, count + 1, time + 1, map[y+1][x]);
			if (isInRange(y, x-1) && map[y][x-1] != 0) getCriminal(y, x-1, count + 1, time + 1, map[y][x-1]);
		}
		else if (flag == 7) {
			if (isInRange(y, x-1) && map[y][x-1] != 0) getCriminal(y, x-1, count + 1, time + 1, map[y][x-1]);
			if (isInRange(y-1, x) && map[y-1][x] != 0) getCriminal(y-1, x, count + 1, time + 1, map[y-1][x]);
		}
	}

	private static boolean isInRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < N && x < M) return true;
		return false;
	}
}