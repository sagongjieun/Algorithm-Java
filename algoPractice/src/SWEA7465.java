import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

// 창용 마을 무리의 개수

public class SWEA7465 {

	static int N, M;
	static int[] parent;
	static boolean[] visited;
	static ArrayList<Node> nodes;

	static class Node {
		int from, to;

		public Node(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken()); // 마을에 사는 사람의 수 = 정점의 수
			M = Integer.parseInt(stk.nextToken()); // 서로를 알고 있는 관계의 수 = 간선의 수

			// 1 ~ N 번까지의 초기 부모는 자기자신으로 세팅
			parent = new int[N + 1];
			for (int i = 1; i <= N; i++) parent[i] = i;

			nodes = new ArrayList<>();
			for (int i = 0; i < M; i++) {
				stk = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(stk.nextToken());
				int to = Integer.parseInt(stk.nextToken());

				nodes.add(new Node(from, to));
			}

			// 각 간선들에 대해 합치기
			for (Node node : nodes) {
				unionParent(node.from, node.to);
			}
			
			int answer = 0;
			for (int i = 1; i <= N; i++) {
				// 자기자신이 부모인 수를 세면 그게 총 부모들의 수이자 무리의 수
				if (parent[i] == i) answer++;
			}

			System.out.println("#" + tc + " " + answer);
		}
	}

	private static void unionParent(int from, int to) {
		int fp = getParent(from);
		int tp = getParent(to);
		// 부모가 같지 않을때만 합쳐주기
		if (fp != tp) parent[tp] = fp;
	}

	private static int getParent(int v) {
		if (parent[v] == v) return v;
		else return parent[v] = getParent(parent[v]);
	}
}