import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

// 격자판의 숫자 이어 붙이기

public class SWEA2819 {

	static int[][] map;
	static int[] result;
	static HashSet<String> set;

	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			map = new int[4][4];

			for (int i = 0; i < 4; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
				}
			}

			result = new int[7];
			set = new HashSet<>(); // 중복 제거를 위한 set 사용

			// 각 좌표마다 DFS탐색
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					DFS(i, j, 0);
				}
			}

			System.out.println("#" + tc + " " + set.size());
		}
	}

	private static void DFS(int y, int x, int depth) {
		// 재귀 종료조건
		// 7개의 수가 만들어지면
		if (depth == 7) {
			String temp = "";
			// result에 저장된 숫자들을 string으로 변환
			for (int i = 0; i < 7; i++) {
				temp += result[i];
			}
			set.add(temp); // set에 저장하여 중복값은 저장안되게 하기
			return;
		}

		result[depth] = map[y][x];

		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			if (rangeCheck(ny, nx)) {
				DFS(ny, nx, depth + 1);
			}
		}
	}

	private static boolean rangeCheck(int y, int x) {
		if (y >= 0 && x >= 0 && y < 4 && x < 4) return true;
		return false;
	}
}