import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 게리 맨더링

public class BOJ17471 {

	static int N;
	static int[] population;
	static ArrayList<Integer>[] map;
	static boolean[] isAreaA;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;

		N = Integer.parseInt(br.readLine());

		population = new int[N + 1];
		map = new ArrayList[N + 1];

		stk = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(stk.nextToken());
			map[i] = new ArrayList<>();
		}

		for (int i = 1; i <= N; i++) {
			stk = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(stk.nextToken());

			for (int j = 0; j < count; j++) {
				int to = Integer.parseInt(stk.nextToken());
				map[i].add(to);
			}
		}

		isAreaA = new boolean[N + 1];

		subSet(1);

		System.out.print(answer == Integer.MAX_VALUE ? -1 : answer);
	}

	private static void subSet(int depth) {
		// 재귀 종료조건
		// A에 해당하는 구역을 모두 선택했다면
		if (depth == N + 1) {
			// 각 선거구에 포함된 구역이 모두 연결됐다면
			if (isAllConnected()) {
				int sumA = 0, sumB = 0;
				
				for (int i = 1; i <= N; i++) {
					if (isAreaA[i]) sumA += population[i];
					else sumB += population[i];
				}
				
				answer = Math.min(answer, Math.abs(sumA - sumB));
			}

			return;
		}

		isAreaA[depth] = true; // depth 번째 구역을 A구역에 넣기
		subSet(depth + 1);

		isAreaA[depth] = false; // depth 번째 구역을 A구역에 넣지 않기
		subSet(depth + 1);
	}

	private static boolean isAllConnected() {
		// A선거구에 포함된 한 구역 찾기
		int areaA = -1;
		for (int i = 1; i <= N; i++) {
			if (isAreaA[i]) {
				areaA = i;
				break;
			}
		}

		// B선거구에 포함된 한 구역 찾기
		int areaB = -1;
		for (int i = 1; i <= N; i++) {
			if (!isAreaA[i]) {
				areaB = i;
				break;
			}
		}

		// 선거구에 포함된 구역이 아무것도 없다면 false return
		// 아니면 밑 코드를 실행하여 각 선거구의 구역들이 잘 연결됐는지 확인하기
		if (areaA == -1 || areaB == -1) return false;

		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];

		// A선거구에 포함된 구역들이 모두 연결됐는지 먼저 확인
		q.add(areaA);
		visited[areaA] = true;

		while (!q.isEmpty()) {
			int cur = q.poll();

			// 현재 구역과 연결된 구역들 확인
			int size = map[cur].size();
			for (int i = 0; i < size; i++) {
				// 연결된 구역들 수만큼 반복하여 확인하기
				int connectedArea = map[cur].get(i); 

				// 아직 확인하지 않은 구역이면서 A선거구에 포함돼있다면
				if (!visited[connectedArea] && isAreaA[connectedArea]) {
					visited[connectedArea] = true; // 연결확인 표시
					q.add(connectedArea);
				}
			}
		}

		// B선거구에 포함된 구역들이 모두 연결됐는지 먼저 확인
		q.add(areaB);
		visited[areaB] = true;

		while (!q.isEmpty()) {
			int cur = q.poll();

			// 현재 구역과 연결된 구역들 확인
			int size = map[cur].size();
			for (int i = 0; i < size; i++) {
				// 연결된 구역들 수만큼 반복하여 확인하기
				int connectedArea = map[cur].get(i); 

				// 아직 확인하지 않은 구역이면서 B선거구에 포함돼있다면
				if (!visited[connectedArea] && !isAreaA[connectedArea]) {
					visited[connectedArea] = true; // 연결확인 표시
					q.add(connectedArea);
				}
			}
		}
		
		// 한 곳이라도 연결되지 않았다면
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) return false;
		}

		return true;
	}

}