import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

// 단지번호붙이기

public class BOJ2667 {

	static int N;
	static int[][] map;
	static int count;
	static ArrayList<Integer> arr = new ArrayList<>();

	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String[] s = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
					count = 1; // 단지 내 집의 수 초기화
					map[i][j] = 0; // 방문체크하듯 해당 집은 0으로 만들기
					DFS(i, j);
					arr.add(count); // DFS 탐색 후 count 결과를 arr에 삽입
				}
			}
		}

		Collections.sort(arr); // 오름차순 정렬

		System.out.println(arr.size()); // 단지 수 출력
		for (Integer num : arr) System.out.println(num); // 단지내 집의 수 출력
	}

	private static void DFS(int y, int x) {

		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			if (isInRange(ny, nx) && map[ny][nx] == 1) {
				map[ny][nx] = 0; // 방문 체크하듯 한 번 지나간 집은 0 으로 만들기
				DFS(ny, nx);
				count += 1; // 단지내 집의 수 +1
			}
		}
	}

	// 방문 체크 함수
	private static boolean isInRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < N && x < N) return true;
		return false;
	}
}