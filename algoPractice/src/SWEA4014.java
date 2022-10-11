import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 활주로 건설

public class SWEA4014 {
	
	static int N, X;
	static int[][] map;
	static int answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			stk = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(stk.nextToken());
			X = Integer.parseInt(stk.nextToken());
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
				}
			}
			
			answer = 0;
			
			for (int i = 0; i < N; i++) {
				if (rowCheck(i)) answer++; // 수평 체크
				if (colCheck(i)) answer++; // 수직 체크
			}
			
			System.out.println("#" + tc + " " + answer);
		}
	}

	private static boolean rowCheck(int r) {
		int[] row = new int[N];
		for (int i = 0; i < N; i++) row[i] = map[r][i];
		
		int beforeHeight = row[0]; // 이전 칸의 높이
		int size = 0; // 높이가 같은 칸들이 연속해서 몇 개 있는지
		int index = 0; // 몇 번째 칸인지
		
		while (index < N) {
			// 동일한 높이라면
			if (beforeHeight == row[index]) {
				size++; // 연속한 칸의 개수 증가
				index++; // 인덱스 이동
			}
			// 다음 칸이 1 높다면 (오르막 경사로)
			else if (beforeHeight + 1 == row[index]) {
				if (size < X) return false; // 연속된 칸의 개수가 X보다 작으면 경사로 설치 불가
				
				beforeHeight++; // 오르막 경사로 설치로 높이 +1
				size = 1; // 연속된 칸의 개수 초기화
				index++; // 인덱스 이동
			}
			// 다음 칸이 1 낮다면 (내리막 경사로)
			else if (beforeHeight - 1 == row[index]) {
				int count = 0;
				
				for (int i = index; i < N; i++) {
					// 해당 칸이 X개가 되기 전에 높이차가 1이 아닌 칸이 나타나면 false return
					if (row[i] != beforeHeight - 1) return false;
					count++; // 아니라면 카운트 세고
					if (count == X) break; // 해당 칸이 X개가 되면 멈추기
				}
				
				if (count < X) return false; // 연속된 칸의 개수가 X보다 작으면 경사로 설치 불가
				
				beforeHeight--; // 내리막 경사로 설치로 높이 -1
				index += X; // 경사로 밑면 크기인 X만큼 인덱스 이동
				size = 0; // 연속된 칸의 개수 초기화
			}
			// 높이가 2 이상 차이나면
			else return false;
		}
		
		return true;
	}

	private static boolean colCheck(int c) {
		int[] col = new int[N];
		for (int i = 0; i < N; i++) col[i] = map[i][c];
		
		int beforeHeight = col[0];
		int size = 0;
		int index = 0;
		
		while (index < N) {
			if (beforeHeight == col[index]) {
				size++;
				index++;
			}
			else if (beforeHeight + 1 == col[index]) {
				if (size < X) return false;
				
				beforeHeight++;
				size = 1;
				index++;
			}
			else if (beforeHeight - 1 == col[index]) {
				int count = 0;
				
				for (int i = index; i < N; i++) {
					if (col[i] != beforeHeight - 1) return false;
					if (++count == X) break;
				}
				
				if (count < X) return false;
				
				beforeHeight--;
				index += X;
				size = 0;
			}
			else return false;
		}
		
		return true;
	}
}