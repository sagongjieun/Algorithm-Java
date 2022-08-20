import java.util.Scanner;

// N과 M (3) - 중복순열

public class BOJ15651 {
	
	static int N, M;
	static int[] answer;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		answer = new int[M];
		
		duplicationPermutation(0);
		
		System.out.print(sb);
	}

	public static void duplicationPermutation(int depth) {
		
		// 재귀 종료조건
		if (depth == M) {
			for (int ans : answer) {
				sb.append(ans + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < N; i++) {
			// 중복을 허용하므로 방문체크 필요X
			answer[depth] = i + 1;
			duplicationPermutation(depth + 1);
		}
		
	}
}