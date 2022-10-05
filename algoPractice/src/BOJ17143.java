import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 낚시왕

public class BOJ17143 {

	static int R, C, M;
	static Shark[][] map;
	static ArrayList<Shark> sharks = new ArrayList<>();
	static int[] maxSizes;
	static int answer;

	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};

	static class Shark {
		int r, c, s, d, z;

		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());

		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());

		map = new Shark[R][C];
		for (int i = 0; i < M; i++) {
			stk = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(stk.nextToken()); // y
			int c = Integer.parseInt(stk.nextToken()); // x
			int s = Integer.parseInt(stk.nextToken()); // 속력
			int d = Integer.parseInt(stk.nextToken()); // 이동방향
			int z = Integer.parseInt(stk.nextToken()); // 크기

			// 방향 0: 상, 1: 우, 2: 하, 3:좌 로 바꿔주기
			if (d == 1) d = 0;
			else if (d == 3) d = 1;
			else if (d == 4) d = 3;

			// 인덱스 맞추기
			r = r-1;
			c = c-1;

			Shark shark = new Shark(r, c, s, d, z);
			sharks.add(shark);
			map[r][c] = shark;
		}

		maxSizes = new int[R * C];
		answer = 0; // 낚시꾼이 잡은 상어 크기의 합

		moveFisherman();

		System.out.print(answer);
	}

	private static void moveFisherman() {
		int fc = 0; // 낚시꾼의 현재 위치 (열)

		// 낚시꾼이 가장 오른쪽 열에 다다를 때까지 반복
		while (fc < C) {
			// 낚시꾼의 현재 열과 같은 열에 있는 상어가 있다면 가장 위에꺼 빼내고 break
			for (int i = 0; i < R; i++) {
				if (map[i][fc] != null) {
					answer += map[i][fc].z; // 낚시꾼이 상어를 잡고 무게 추가
					sharks.remove(map[i][fc]); // list에서 해당 상어 없애기
					break;
				}
			}

			moveSharks(); // 상어의 이동
			fc++; // 낚시꾼 위치 옆으로 이동
		}
	}

	private static void moveSharks() {
		for (Shark shark : sharks) {
			int y = shark.r;
			int x = shark.c;
			int dir = shark.d;

			// 속력 s만큼 위치이동
			for (int i = 0; i < shark.s; i++) {
				int ny = y + dy[dir];
				int nx = x + dx[dir];

				if (!isInRange(ny, nx)) {
					dir = (dir + 2) % 4;

					ny = y + dy[dir];
					nx = x + dx[dir];
				}

				y = ny;
				x = nx;
			}

			shark.r = y;
			shark.c = x;
			shark.d = dir;
		}

		checkSharks(); // 이동을 마친 후에 한 칸에 두마리 이상의 상어들이 있는지 확인하기
	}

	private static void checkSharks() {
		map = new Shark[R][C];

		for (int i = sharks.size() - 1; i >= 0; i--) {
			Shark cur = sharks.get(i);

			if (map[cur.r][cur.c] == null) {
				map[cur.r][cur.c] = cur; 
			} else {
				// 지금 상어가 더 크면 그걸로 바꿔주고
				if (cur.z > map[cur.r][cur.c].z) {
					sharks.remove(map[cur.r][cur.c]);
					map[cur.r][cur.c] = cur; 
				} 
				// 아니라면 지금 상어는 없애기
				else sharks.remove(cur);
			}
		}
	}

	private static boolean isInRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < R && x < C) return true;
		return false;
	}
}