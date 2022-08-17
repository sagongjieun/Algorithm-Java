import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 감시

public class BOJ15683 {

	static int N, M;
	static int[][] space;
	static int answer;
	static List<int[]> cameras = new ArrayList<>();

	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;

		stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());

		space = new int[N][M];
		for (int i=0; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				space[i][j] = Integer.parseInt(stk.nextToken());
			}
		} 

		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				// 0인 수의 개수로 answer값 초기화
				if (space[i][j] == 0) answer++;
				// 카메라를 만나면 해당 위치 좌표를 저장
				else if(space[i][j] >= 1 && space[i][j] <=5) {
					cameras.add(new int[] {i, j});
				}
			}
		}

		// 방향이 4방향이므로 나올수 있는 경우의 수는 4^카메라 개수
		int size = 1<<cameras.size()*2;

		// 모든 경우의 수에 대해
		for (int i=0; i<size; i++) {
			int[][] copySpace = new int[N][M]; // space를 깊은복사할 배열 생성
			for (int k=0; k<space.length; k++) {
				// 깊은 복사
				System.arraycopy(space[k], 0, copySpace[k], 0, space[k].length);
			}

			int num = i;

			// 각 카메라에 대해
			for (int j=0; j<cameras.size(); j++) {
				// 상우하좌 중 방향얻기
				int dir = num % 4;
				num = num / 4;

				// 카메라의 좌표 얻기
				int y = cameras.get(j)[0];
				int x = cameras.get(j)[1];

				// 1번 카메라면 한방향 탐색
				if (copySpace[y][x] == 1) {
					observe(copySpace, dir+1, y, x);
				}
				// 2번 카메라면 서로 반대가 되는 방향 탐색
				if (copySpace[y][x] == 2) {
					observe(copySpace, dir+1, y, x);
					observe(copySpace, dir+3, y, x);
				}
				// 3번 카메라면 90도 방향 탐색
				if (copySpace[y][x] == 3) {
					observe(copySpace, dir, y, x);
					observe(copySpace, dir+1, y, x);
				}
				// 4번 카메라면 세 방향 탐색
				if (copySpace[y][x] == 4) {
					observe(copySpace, dir+1, y, x);
					observe(copySpace, dir+3, y, x);
					observe(copySpace, dir, y, x);
				}
				// 5번 카메라면 4방향 탐색
				if (copySpace[y][x] == 5) {
					observe(copySpace, dir+1, y, x);
					observe(copySpace, dir+2, y, x);
					observe(copySpace, dir+3, y, x);
					observe(copySpace, dir, y, x);
				}
			}

			int count = 0;
			for (int r=0; r<N; r++) {
				for (int c=0; c<M; c++) {
					// 이번 턴에서 얻은 빈칸의 개수를 구하기
					if (copySpace[r][c] == 0) count++;
				}
			}

			// 빈칸의 개수중 최소값 구하기
			answer = Math.min(count, answer);
		}

		System.out.println(answer);
	}

	public static void observe (int[][] arr, int dir, int y, int x) {
		// 상우하좌 중 방향 얻기
		dir = dir % 4;

		while (true) {
			// dir 방향으로의 좌표 구하기
			y = y + dy[dir];
			x = x + dx[dir];

			// 범위를 벗어나거나 벽을 만나면 break
			if (!rangeCheck(y,x) || arr[y][x] == 6) break;

			// 해당 방향으로 빈칸을 만나면 방문 표시남기기
			if (arr[y][x] == 0) {
				arr[y][x] = 7; // 임시의 방문 표시
			}
		}
	}

	// 범위 체크 함수
	public static boolean rangeCheck (int y, int x) {
		if (y >= 0 && x >= 0 && y < N && x < M) return true;
		return false;
	}
}
