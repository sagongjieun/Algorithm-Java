import java.util.Scanner;

// N과 M (2)

public class BOJ15650 {

	static int N, M;
	static int[] answer;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		answer = new int[M];

		DFS(0, 1);

		System.out.print(sb);
	}

	public static void DFS(int depth, int start) {
		// 재귀 종료조건
		if (depth == M) {
			for (int num : answer) sb.append(num).append(" ");
			sb.append("\n");

			return;
		}

		for (int i=start; i<=N; i++) {
			answer[depth] = i;
			DFS(depth+1, i+1);
		}
	}
}