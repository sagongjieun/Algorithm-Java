import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 캐슬 디펜스

public class BOJ17135 {
	
	static int N, M, D;
	static int[][] map, copyMap;
	static int[] isSelected = new int[3];
	static int answer = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		D = Integer.parseInt(stk.nextToken());
		
		map = new int[N][M];
		copyMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		putArcher(0, 0);
		
		System.out.print(answer);
	}

	private static void putArcher(int depth, int start) {
		// 3명의 궁수를 다 배치했다면
		if (depth == 3) {
			int result = killEnemy(isSelected);
			answer = Math.max(answer, result);
			return;
		}
		
		// M개의 열에서 궁수의 위치 3자리 고르기
		for (int i = start; i < M; i++) {
			isSelected[depth] = i;
			putArcher(depth + 1, i + 1);
		}
	}

	private static int killEnemy(int[] selectedArcher) {
		int killCount = 0; // kill 횟수
		int turn = 0; // 몇 번째 턴인지
		Queue<int[]> q = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			copyMap[i] = Arrays.copyOf(map[i], M);
		}
		
		while (turn < N) {
			// 각 궁수에 대해
			for (int i = 0; i < 3; i++) {
				// 궁수의 좌표
				int ay = N - turn;
				int ax = selectedArcher[i];
				
				int minDistance = Integer.MAX_VALUE, minY = -1, minX = -1;
				
				// 가장 가까우면서 왼쪽의 적 선정하기
				for (int ey = ay - 1; ey >= 0; ey--) {
					for (int ex = 0; ex < M; ex++) {
						// 적을 발견하면
						if (copyMap[ey][ex] == 1) {
							// 현재 궁수와 적의 거리를 구하기
							int distance = getDistance(ay, ax, ey, ex);
							
							// 가장 거리가 가까운 적으로 갱신
							if (distance < minDistance) {
								minDistance = distance;
								minY = ey;
								minX = ex;
							}
							else if (distance == minDistance) {
								// 거리가 같은 것 중에서도 왼쪽 적으로 갱신
								if (ex < minX) {
									minY = ey;
									minX = ex;
								}
							}
							else continue;
						}
					}
				}
				
				if (minY == -1 || minX == -1) continue;
				
				q.add(new int[] {minY, minX}); // i번째 궁수가 물리칠 적의 좌표 저장
			}
			
			while (!q.isEmpty()) {
				int[] enemy = q.poll();
				int ey = enemy[0];
				int ex = enemy[1];
				
				// 이미 물리친 적이 아니라면
				if (copyMap[ey][ex] == 1) {
					copyMap[ey][ex] = 0;
					killCount += 1;
				}
			}
			
			turn += 1;
		}
		
		return killCount;
	}

	private static int getDistance(int ay, int ax, int ey, int ex) {
		return (Math.abs(ay - ey) + Math.abs(ax - ex));
	}
}