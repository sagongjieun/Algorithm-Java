import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 서로소 집합

public class SWEA3289 {

	static int N, M;
	static String answer;
	static int[] parent;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;

		int T = Integer.parseInt(br.readLine()); // 테스트 케이스

		for (int tc = 1; tc <= T; tc++) {
			stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			M = Integer.parseInt(stk.nextToken());

			answer = "";
			parent = new int[N + 1];
			// 1 ~ N까지의 수에 대한 부모는 자기 자신으로 초기화
			for (int i = 1; i <= N; i++) parent[i] = i;

			for (int i = 0; i < M; i++) {
				stk = new StringTokenizer(br.readLine());
				int command = Integer.parseInt(stk.nextToken());
				int a = Integer.parseInt(stk.nextToken());
				int b = Integer.parseInt(stk.nextToken());

				if (command == 0) {
					// 집합 합치기
					unionParent(a, b);
				}
				else {
					// 같은 집합인지 확인하기
					if (getParent(a) == getParent(b)) answer += "1";
					else answer += "0";
				}
			}

			System.out.println("#" + tc + " " + answer);
		}
	}

	// 같은 부모를 가지는지 확인
	private static int getParent(int v) {
		if (parent[v] == v) return v; // 부모가 자기자신이 되면 해당 번호 return
		else return parent[v] = getParent(parent[v]); // 아니라면 부모를 타고타고 올라가 처음 부모 찾기
	}

	// 부모 합치기
	private static void unionParent(int a, int b) {
		int aP = getParent(a); // a의 부모
		int bP = getParent(b); // b의 부모

		// a, b의 부모가 같지 않을 때만 b의 부모를 a의 부모로 만들어줘서 합치기
		if (aP != bP) parent[bP] = aP;
	}
}