import java.util.Scanner;

// N과 M (2) - 조합

public class BOJ15650 {

	static int N, M;
	static int[] answer;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		answer = new int[M];

		combination(0, 1);

		System.out.print(sb);
	}

	public static void combination(int depth, int start) {
		// 재귀 종료조건
		if (depth == M) {
			for (int num : answer) sb.append(num).append(" ");
			sb.append("\n");

			return;
		}

		// 반복문의 시작을 start 부터 하여 방문체크 할 필요가 없고
		// start 보다 아래의 수는 거치지 않으므로 자동으로 중복 방지
		for (int i=start; i<=N; i++) {
			answer[depth] = i;
			combination(depth+1, i+1);
		}
	}
}