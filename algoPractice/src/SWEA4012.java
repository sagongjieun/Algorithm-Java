import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 요리사

public class SWEA4012 {
	
	static int N;
	static int[][] map;
	static boolean[] selected;
	static int answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
				}
			}
			
			selected = new boolean[N];
			answer = Integer.MAX_VALUE;
			
			divideFood(0, 0);
			
			sb.append("#" + tc + " " + answer + "\n");
		}
		
		System.out.print(sb);
	}

	// 조합으로 selected에 a재료들을 뽑기
	private static void divideFood(int depth, int count) {
		// a재료를 N/2개 뽑았다면
		if (count == N/2) {
			int result = getTaste();
			answer = Math.min(answer, result);
			return;
		}
		
		for (int i = depth; i < N; i++) {
			selected[depth] = true;
			divideFood(i + 1, count + 1);
			selected[depth] = false;
		}
	}

	private static int getTaste() {
		int at = 0; // A의 맛
		int bt = 0; // B의 맛
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j) continue;
				if (selected[i] && selected[j]) at += map[i][j]; // i, j 모두 A의 재료라면
				else if (!selected[i] && !selected[j]) bt += map[i][j]; // i, j 모두 A의 재료가 아니라면(=B의 재료)
			}
		}
		
		return Math.abs(at - bt);
	}
}