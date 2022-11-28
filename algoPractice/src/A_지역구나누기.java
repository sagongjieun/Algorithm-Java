import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class A_지역구나누기 {
	
	static int N, half, answer;
	static int[][] map;
	static boolean[] selected;
	static int[] areaCount, personCount, P;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			half = N/2;
			answer = Integer.MAX_VALUE;
			
			map = new int[N][N];
			selected = new boolean[N]; // 조합에서 뽑힌 마을 (true: 선거구A / false: 선거구B)
			areaCount = new int[2]; // 각 선거구의 인접 구역 수
			personCount = new int[2]; // 같은 선거구의 인구수
			P = new int[N]; // 각 마을의 유권자의 수
			
			for (int i = 0; i < N; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
				}
			}
			
			stk = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				P[i] = Integer.parseInt(stk.nextToken());
			}
			
			// A선거구에 속할 마을을 1개 ~ half까지 시도
			for (int i = 1; i <= half; i++) {
				divide(0, 0, i);
			}
			
			if (answer == Integer.MAX_VALUE) answer = -1;
			sb.append("#" + tc + " " + answer + "\n");
		}
		
		System.out.print(sb);
	}

	// 조합으로 선거구 나누기 (A선거구에 속할 마을 뽑으면 뽑히지 않은 마을이 자연스럽게 B선거구 확정)
	private static void divide(int start, int count, int target) {
		if (count == target) {
			// 각 선거구의 연결성 확인
			compare();
			return;
		}
		
		for (int i = start; i < N; i++) {
			selected[i] = true; // i마을 선택
			divide(i + 1, count + 1, target);
			selected[i] = false; // i 마을 선택 되돌리기
		}
	}

	private static void compare() {
		ArrayList<Integer> listA = new ArrayList<>(); // 선거구 A리스트 : selected가 true인 마을
		ArrayList<Integer> listB = new ArrayList<>(); // 선거구 B리스트
		
		for (int i = 0; i < N; i++) {
			if (selected[i]) listA.add(i);
			else listB.add(i);
		}
		
		// A,B 선거구의 인접구역 수와 인구 수 0으로 초기화
		areaCount[0] = areaCount[1] = personCount[0] = personCount[1] = 0;
		
		// 선거구A의 연결성 확인
		// 연결성 확인을 통해 방문한 마을 수와 선거구 A리스트의 크기가 같다면 모두 연결된 것
		DFS(listA, new boolean[N], listA.get(0), 0);
		if (areaCount[0] != listA.size()) return;
		
		// 선거구B의 연결성 확인
		DFS(listB, new boolean[N], listB.get(0), 1);
		if (areaCount[1] != listB.size()) return;
		
		answer = Math.min(answer, Math.abs(personCount[0] - personCount[1])); // 유권자의 수 차이의 최소를 정답으로 갱신
	}

	/**
	 * 각 선거구의 연결성 확인
	 * @param list : A, B 선거구에 포함된 지역 리스트
	 * @param visited : 이미 방문한 지역인지 체크
	 * @param cur : 현재 지역
	 * @param type : A인지 B인지 (0: A, 1: B)
	 */
	private static void DFS(ArrayList<Integer> list, boolean[] visited, int cur, int type) {
		visited[cur] = true;
		areaCount[type]++; // A || B 선거구의 인접 구역 수 +1
		personCount[type] += P[cur]; // A || B 선거구의 인구 수를 현재 cur 지역의 유권자 수만큼 늘리기
		
		for (int i : list) {
			// 현재 cur 지역과 i 지역이 연결돼있으면서 아직 방문하지 않은 지역이라면 재귀
			if (map[cur][i] == 1 && !visited[i]) DFS(list, visited, i, type);
		}
	}
}