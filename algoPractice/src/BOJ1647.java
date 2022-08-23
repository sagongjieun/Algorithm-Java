import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 도시 분할 계획

public class BOJ1647 {

	static int N, M;
	static int[] parent;
	static ArrayList<Node> nodes = new ArrayList<>();

	static class Node {
		int from, to, weight;

		public Node(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());

		N = Integer.parseInt(stk.nextToken()); // 집의 개수 = 정점의 개수
		M = Integer.parseInt(stk.nextToken()); // 길의 개수 = 간선의 개수
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) parent[i] = i;

		for (int i = 0; i < M; i++) {
			stk = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(stk.nextToken());
			int to = Integer.parseInt(stk.nextToken());
			int weight = Integer.parseInt(stk.nextToken());

			nodes.add(new Node(from, to, weight));
		}

		nodes.sort((o1, o2) -> o1.weight - o2.weight);

		int answer = 0;
		int maxWeight = 0;
		for (int i = 0; i < M; i++) {
			Node node = nodes.get(i);

			// 정점 from과 to가 부모가 다르다면 같은 집합으로 묶기
			if (unionParent(node.from, node.to)) {
				answer += node.weight; // 정답에 가중치 추가
				maxWeight = node.weight; // 마지막에 가장 큰 가중치만 빼주면 두 지역으로 나뉠 수 있음
			}
		}

		System.out.print(answer - maxWeight);
	}

	private static boolean unionParent(int from, int to) {
		int fp = getParent(from);
		int tp = getParent(to);

		if (fp != tp) {
			parent[tp] = fp;
			return true;
		}

		return false;
	}

	private static int getParent(int v) {
		if (parent[v] == v) return v;
		else return parent[v] = getParent(parent[v]);
	}
}