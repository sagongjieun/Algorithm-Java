import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 키 순서 - 플로이드 와샬

public class SWEA5643_floyd_warshall {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());

			boolean[][] infos = new boolean[N+1][N+1];
			// row가 더 작은사람을 의미
			for (int i = 0; i < M; i++) {
				stk = new StringTokenizer(br.readLine());
				infos[Integer.parseInt(stk.nextToken())][Integer.parseInt(stk.nextToken())] = true;
			}

			// 키 순서대로 연결해주기
			for (int k = 1; k <= N; k++) {
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						if (!infos[i][j] && infos[i][k] && infos[k][j]) infos[i][j] = true;
					}	
				}
			}

			int answer = 0;
			// k번 학생들에 대해
			for (int k = 1; k <= N; k++) {
				int count = 0;
				// k번 학생보다 키가 작은 학생의 수와
				for (int i = 1; i <= N; i++) if (infos[k][i]) count++;
				// k번 학생보다 키가 큰 학생의 수의 합이 
				for (int i = 1; i <= N; i++) if (infos[i][k]) count++;
				// N-1 명이면 제대로 된 순서가 있다는 의미
				if (count == N-1) answer++;
			}

			System.out.println("#" + tc + " " + answer);
		}
	}
}
