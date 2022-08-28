import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 야구

public class BOJ17281 {
	
	static int N;
	static int[][] player;
	static int[] lineup = new int[10];
	static boolean[] visited = new boolean[10];
	static int answer = Integer.MIN_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		N = Integer.parseInt(br.readLine());
		player = new int[N][10];
		
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 9; j++) {
				player[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		lineup[4] = 1; // 4번타순에 1번 선수 세팅
		visited[4] = true;
		
		setPlayer(2); // 2번 선수부터 타순찾기
		
		System.out.print(answer);
	}


	private static void setPlayer(int playerNo) {
		// 타순을 모두 정했으면
		if (playerNo > 9) {
			// 해당 타순으로 게임했을 때의 점수를 얻기
			int result = playing();
			// 최대점수 갱신
			answer = Math.max(answer, result);
			return;
		}
		
		// 몇 번 타순에 놓을 지 정하기
		for (int i = 1; i <= 9; i++) {
			if (!visited[i]) {
				// i번째 타순에 타자가 없을때만 선수 세팅
				visited[i] = true;
				lineup[i] = playerNo;
				
				setPlayer(playerNo + 1);
				
				visited[i] = false;
				lineup[i] = 0;
			}
		}
	}


	private static int playing() {
		int score = 0;
		int turn = 1; // 몇 번째 타순인지
		
		for (int inning = 0; inning < N; inning++) {
			int outCount = 0;
			boolean[] base = new boolean[4];
			
			while (outCount < 3) {
				// 라인업에서 해당 타순에는 몇번의 선수가 있는지
				int ability = player[inning][lineup[turn]];
				
				switch(ability) {
				case 0: // 아웃
					outCount += 1;
					break;
				case 1: // 안타
					if (base[3]) {
						base[3] = false;
						score += 1;
					}
					if (base[2]) {
						base[2] = false;
						base[3] = true;
					}
					if (base[1]) {
						base[2] = true;
					}
					base[1] = true;
					break;
				case 2: // 2루타
					if (base[3]) {
						base[3] = false;
						score += 1;
					}
					if (base[2]) {
						base[2] = false;
						score += 1;
					}
					if (base[1]) {
						base[1] = false;
						base[3] = true;
					}
					base[2] = true;
					break;
				case 3: // 3루타
					if (base[3]) {
						base[3] = false;
						score += 1;
					}
					if (base[2]) {
						base[2] = false;
						score += 1;
					}
					if (base[1]) {
						base[1] = false;
						score += 1;
					}
					base[3] = true;
					break;
				case 4: // 홈런
					if (base[3]) {
						base[3] = false;
						score += 1;
					}
					if (base[2]) {
						base[2] = false;
						score += 1;
					}
					if (base[1]) {
						base[1] = false;
						score += 1;
					}
					score += 1;
					break;
				}
				
				if (turn == 9) turn = 1;
				else turn += 1;
			}
		}
		
		return score;
	}
}