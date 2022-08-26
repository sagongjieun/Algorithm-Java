import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 음악프로그램

public class BOJ2623 {

	static int N, M;
	static int[] indegree;
	static ArrayList<Integer>[] singers;
	static ArrayList<Integer> answer = new ArrayList<>();
	static Queue<Integer> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());

		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());

		indegree = new int[N + 1]; // 1 ~ N번까지의 가수의 진입차수 생성

		// 1 ~ N번까지의 가수의 인접리스트 생성
		singers = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			singers[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			stk = new StringTokenizer(br.readLine());

			int size = Integer.parseInt(stk.nextToken()); // 한 보조 PD가 담당하는 가수의 수
			int before = Integer.parseInt(stk.nextToken());

			for (int j = 1; j < size; j++) {
				int next = Integer.parseInt(stk.nextToken());

				singers[before].add(next); // next 가수는 반드시 before 가수 다음에 나와야함
				indegree[next]++; // next 가수의 진입차수 +1 증가

				before = next; // 이번의 next가 다음의 before 가수
			}
		}

		// 진입차수가 0인 가수들 찾기
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) q.add(i);
		}

		while (!q.isEmpty()) {
			int curSinger = q.poll();

			answer.add(curSinger); // 정답에 저장

			// 이번 가수를 출력하고 나면
			// 이번 가수와 연결된(반드시 이번 가수 뒤에 나와야하는) 가수의 진입차수 -1 감소
			for (int i = 0; i < singers[curSinger].size(); i++) {
				int nextSinger = singers[curSinger].get(i);
				indegree[nextSinger]--;

				if (indegree[nextSinger] == 0) q.add(nextSinger);
			}
		}

		// answer에 모든 가수가 담기지 않았다면 0 출력
		if (answer.size() != N) System.out.print(0);
		else {
			for (Integer ans : answer) System.out.println(ans);
		}
	}
}