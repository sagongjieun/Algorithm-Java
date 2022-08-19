import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 숨바꼭질

public class BOJ1697 {

	static int N, K;
	static boolean[] visited;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());

		N = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());

		visited = new boolean[100001];

		answer = BFS(N);

		System.out.print(answer);
	}

	public static int BFS(int V) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {V, 0}); // 정점의 수, count
		visited[V] = true; // 방문 체크

		// 큐가 빌 때까지 반복
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int curV = cur[0];
			int count = cur[1];

			// 종료 조건 = 수빈이의 위치가 동생의 위치와 같아지면 횟수 return
			if (curV == K) return count;

			// 범위체크하고 방문하지 않은 수라면 큐에 삽입하고 방문 체크
			
			if (curV - 1 >= 0 && !visited[curV - 1]) {
				q.offer(new int[] {curV - 1, count + 1});
				visited[curV - 1] = true;
			}
			
			if (curV + 1 < 100001 && !visited[curV + 1]) {
				q.offer(new int[] {curV + 1, count + 1});
				visited[curV + 1] = true;
			}
			
			if (curV * 2 < 100001 && !visited[curV * 2]) {
				q.offer(new int[] {curV * 2, count + 1});
				visited[curV * 2] = true;
			}
		}
		
		return -1;
	}
}
