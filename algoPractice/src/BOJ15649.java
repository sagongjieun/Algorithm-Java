import java.util.Scanner;

// N과 M (1)

public class BOJ15649 {
	
	static int N, M;
	static boolean[] visited;
	static int[] answer;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		visited = new boolean[N];
		answer = new int[M];
		
		DFS(0);
		
		System.out.print(sb);
	}
	
	public static void DFS(int depth) {
		// 재귀의 종료조건
		if (depth == M) {
			for (int ans : answer) {
				sb.append(ans).append(" ");
			}
			sb.append("\n");
			
			return;
		}
		
		for (int i=0; i<N; i++) {
			// 중복되면 안 되니 방문하지 않은 것에 대해서만
			if (!visited[i]) {
				visited[i] = true;
				answer[depth] = i+1;
				
				DFS(depth+1);
				
				visited[i] = false;
			}
		}
	}

}