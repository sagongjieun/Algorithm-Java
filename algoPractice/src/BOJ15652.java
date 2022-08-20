import java.util.Scanner;

// N과 M (4) - 중복조합

public class BOJ15652 {
	
	static int N, M;
	static int[] answer;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		answer = new int[M];
		
		duplicationCombination(0, 1);
		
		System.out.print(sb);
	}

	public static void duplicationCombination(int depth, int start) {
		
		// 재귀 종료조건
		if (depth == M) {
			for (int ans : answer) {
				sb.append(ans + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = start; i <= N; i++) {
			answer[depth] = i;
			// 중복이 허용되기 때문에 재귀호출할 때 start를 자기 자신으로 해도 됨
			duplicationCombination(depth + 1, i);
		}
	}

}
