import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 헌터

public class SWEA02 {
	
	static int N;
	static ArrayList<Node> monsters;
	static ArrayList<Node> customers;
	static boolean[] visitedMonsters;
	static boolean[] visitedCustomers;
	static int answer;
	
	static class Node {
		int y, x, No;

		public Node(int y, int x, int No) {
			this.y = y;
			this.x = x;
			this.No = No;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
		
		// 테스트 케이스만큼 반복
		for (int tc = 1; tc <= T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			
			monsters = new ArrayList<>();
			customers = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int No = Integer.parseInt(stk.nextToken());
					
					// 몬스터라면
					if (No >= 1 && No <= 4) {
						monsters.add(new Node(i, j, No));
					}
					// 고객이라면
					else if (No < 0) {
						customers.add(new Node(i, j, No));
					}
				}
			}
			
			visitedMonsters = new boolean[monsters.size() + 1]; // 몬스터 만남을 체크하는 배열
			visitedCustomers = new boolean[customers.size() + 1]; // 고객 만남을 체크하는 배열
			answer = Integer.MAX_VALUE;
				
			DFS(0, 0, 0, 0);
			
			System.out.println("#" + tc + " " + answer);
		}
	}

	/**
	 * 
	 * @param count : 몬스터, 고객을 만난 횟수
	 * @param distance : 누적된 거리
	 * @param hy : 헌터의 행 좌표
	 * @param hx : 헌터의 열 좌표
	 */
	private static void DFS(int count, int distance, int hy, int hx) {
		if (distance >= answer) return; // 현재 누적된 최소거리값보다 커지면 백트래킹
		// 몬스터 고객을 모두 다 만나면
		if (count == monsters.size() * 2) {
			// 최소 거리값을 answer에 갱신
			answer = Math.min(answer, distance);
		}
		
		// 몬스터 잡기
		for (Node monster : monsters) {
			if (visitedMonsters[monster.No]) continue; // 이미 잡은 몬스터라면 패스
			
			int d = getDistance(monster, hy, hx); // 헌터와 몬스터와의 거리
			visitedMonsters[monster.No] = true; // 이번 몬스터 방문 처리
			DFS(count + 1, distance + d, monster.y, monster.x); // 이번 몬스터 방문 처리한 채로 다음 탐색
			visitedMonsters[monster.No] = false; // 백트래킹하면서 해당 몬스터는 방문하지 않은 채로 다음 탐색
		}
		
		// 고객 방문
		for (Node customer : customers) {
			int no = Math.abs(customer.No); // 고객 번호 절대값처리
			if (visitedCustomers[no] || !visitedMonsters[no]) continue; // 이미 만난 고객이거나 해당번호의 몬스터를 아직 안잡았다면 패스
			
			int d = getDistance(customer, hy, hx); // 헌터와 고객과의 거리
			visitedCustomers[no] = true; // 이번 고객 방문 처리
			DFS(count + 1, distance + d, customer.y, customer.x); // 이번 고객 방문 처리한 채로 다음 탐색
			visitedCustomers[no] = false; // 백트래킹하면서 해당 고객은 방문하지 않은 채로 다음 탐색
		}
	}

	/**
	 * 
	 * @param target : 몬스터인지 고객인지
	 * @param hy : 헌터의 행 좌표
	 * @param hx : 헌터의 열 좌표
	 * @return : target과 헌터의 거리
	 */
	public static int getDistance(Node target, int hy, int hx) {
		return Math.abs(target.y - hy) + Math.abs(target.x - hx);
	}
}