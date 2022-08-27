import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 낚시터 자리잡기

public class SWEA01 {
	
	static int N;
	static int[] seats; // 낚시터 정보 저장
	static Gate[] gates; // 게이트 정보 저장
	static boolean[] selected; // 순열 selected 배열
	static int answer;
	static int fisherCount;
	
	static class Gate {
		int location, fisher;

		public Gate(int location, int fisher) {
			this.location = location; // 게이트의 위치 인덱스
			this.fisher = fisher; // 게이트의 낚시꾼 수
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk =  null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			
			N = Integer.parseInt(br.readLine()); // 낚시터 자리의 개수
			seats = new int[N + 1]; // 낚시터 (1 ~ N)
			gates = new Gate[4]; // 게이트 (1 ~ 3)
			
			for (int i = 1; i <= 3; i++) {
				stk = new StringTokenizer(br.readLine());
				int location = Integer.parseInt(stk.nextToken());
				int fisher = Integer.parseInt(stk.nextToken());
				
				gates[i] = new Gate(location, fisher);
			}
			
			selected = new boolean[N + 1];
			answer = Integer.MAX_VALUE;
			
			DFS(0, 0);
			
			System.out.println("#" + tc + " " + answer);
		}
	}

	/**
	 * 
	 * @param depth : 게이트 수
	 * @param sum : 현재까지의 거리의 합
	 */
	private static void DFS(int depth, int sum) {
		
		if (sum >= answer) return;
		// 백트래킹 조건
		if (depth == 3) {
			answer = Math.min(answer, sum);
			return;
		}
		
		for (int i = 1; i <= 3; i++) {
			// 이미 선택된 게이트라면 패스
			if (selected[i]) continue;
			
			// i 번째 게이트 선택
			selected[i] = true;
			
			// 낚시꾼 배치
			DFS(depth + 1, sum + arrangeSeat(i, gates[i].fisher, true));
			rearrangeSeat(i); // 배치했던 낚시꾼들을 원래 상태로 되돌림
			
			// 낚시꾼의 수가 짝수일 때만 마지막 낚시꾼의 위치를 오른쪽으로 배치하는 방법도 탐색
			if (gates[i].fisher % 2 == 0) {
				DFS(depth + 1, sum + arrangeSeat(i, gates[i].fisher, false));
				rearrangeSeat(i);
			}
			
			// i 번째 게이트 선택 해제
			selected[i] = false;
		}
	}

	/**
	 * 낚시꾼 배치 함수
	 * @param gateNo : 게이트 번호
	 * @param fisher : 이번 게이트의 낚시꾼의 수
	 * @param isLtoR : true = 왼 -> 오 / false = 오 -> 왼
	 * @return
	 */
	private static int arrangeSeat(int gateNo, int fisher, boolean isLtoR) {
		int sum = 0;
		int distance = 0; // 게이트위치로부터 떨어진 거리
		fisherCount = 0;
		
		// 낚시꾼들이 다 배치될때까지 반복
		while (fisherCount < fisher) {
			if (isLtoR) sum += left(gateNo, distance);
			else sum += right(gateNo, distance);
			
			if (fisherCount == fisher) break;
			
			if (isLtoR) sum += right(gateNo, distance);
			else sum += left(gateNo, distance);
			
			distance++;
		}
		
		return sum;
	}
	
	private static int left(int gateNo, int distance) {
		int left = gates[gateNo].location - distance;
		
		// 자리 번호가 범위를 벗어나지 않고
		// 해당 자리에 낚시꾼이 배치되지 않았다면
		if (left > 0 && seats[left] == 0) {
			seats[left] = gateNo; // 해당 자리에 게이트번호로 해당 게이트의 낚시꾼이 앉음을 표시
			fisherCount++; // 다음 낚시꾼 차례
			return distance + 1; // 게이트위치로부터 +1 떨어짐
		}
		
		// 자리에 다 앉았다면 0 return
		return 0;
	}
	
	private static int right(int gateNo, int distance) {
		int right = gates[gateNo].location + distance;
		
		if (right <= N && seats[right] == 0) {
			seats[right] = gateNo;
			fisherCount++;
			return distance + 1;
		}
		
		return 0;
	}

	private static void rearrangeSeat(int gateNo) {
		int count = 0;
		
		for (int i = 1; i <= N; i++) {
			if (seats[i] == gateNo) {
				seats[i] = 0;
				count++;
			}
			
			if (count == gates[gateNo].fisher) return;
		}
	}

}