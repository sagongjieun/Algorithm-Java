import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 선발 명단

public class BOJ3980 {
	
	static int[][] map;
	static int[] position;
	static boolean[] selected;
	static int answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while (T > 0) {
			map = new int[11][11];
			for (int i = 0; i < 11; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < 11; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
				}
			}
			
			position = new int[11];
			selected = new boolean[11];
			answer = Integer.MIN_VALUE;
			
			selectPlayer(0);

			sb.append(answer + "\n");
			T--;
		}
		
		System.out.println(sb);
	}

	private static void selectPlayer(int depth) {
		if (depth == 11) {
			int ability = 0;
			// 이 포지션으로 얻을 수 있는 선수들의 총 능력치 구하기
			for (int i = 0; i < 11; i++) {
				ability += map[position[i]][i];
			}
			answer = Math.max(answer, ability);
			return;
		}
		
		// 순열로 11명의 선수들을 배치하기
		for (int player = 0; player < 11; player++) {
			// 선수의 능력치가 0인 포지션에는 배치할 수 없음
			if (!selected[player] && map[player][depth] != 0) {
				selected[player] = true;
				position[depth] = player;
				selectPlayer(depth + 1);
				selected[player] = false;
			}
		}
	}
}