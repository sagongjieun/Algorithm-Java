import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 낚시터 자리잡기

public class SWEA01 {
	
	static int N;
	static int[] seats; // 낚시터
	static Gate[] gate; // 게이트
	static boolean[] selected; // 순열 selected 배열
	static int answer;
	
	static class Gate {
		int location, fisher;

		public Gate(int location, int fisher) {
			this.location = location;
			this.fisher = fisher;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk =  null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			
			N = Integer.parseInt(br.readLine()); // 낚시터 자리의 개수
			seats = new int[N + 1]; // 낚시터 (1 ~ N)
			gate = new Gate[4]; // 게이트 (1 ~ 3)
			
			for (int i = 1; i <= 3; i++) {
				stk = new StringTokenizer(br.readLine());
				int location = Integer.parseInt(stk.nextToken());
				int fisher = Integer.parseInt(stk.nextToken());
				
				gate[i] = new Gate(location, fisher);
			}
			
			selected = new boolean[N + 1];
			answer = Integer.MAX_VALUE;
			
			DFS(0, 0);
		}
	}

	private static void DFS(int depth, int count) {
		
		if (count >= answer) return;
		// 백트래킹 조건
		if (depth == 3) {
			answer = Math.min(answer, count);
			return;
		}
		
		for (int i = 1; i <= 3; i++) {
			// 이미 선택된 게이트라면 패스
			if (selected[i]) continue;
			
			// i 번째 게이트 선택
			selected[i] = true;
			
			// 낚시꾼 배치
			DFS(depth + 1, count + inFishers(i, gate[i].fisher, true));
			outFisheres(i);
			
			// 낚시꾼의 수가 짝수라면
			if (gate[i].fisher % 2 == 0) {
				DFS(depth + 1, count + inFishers(i, gate[i].fisher, false));
				outFisheres(i);
			}
			
			// i 번째 게이트 선택 해제
			selected[i] = false;
		}
	}

	private static int inFishers(int i, int fisher, boolean b) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private static void outFisheres(int i) {
		// TODO Auto-generated method stub
		
	}

}