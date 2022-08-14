import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BOJ1954 {

	public static void main(String[] args) throws FileNotFoundException {
		//System.setIn(new FileInputStream("input.txt")); // 제출할 땐 주석하고 제출
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		
		// 우 하 좌 상
		int[] dy = {0, 1, 0, -1};
		int[] dx = {1, 0, -1, 0};
		
		for (int tc=1; tc<=T; tc++) {
			int N = sc.nextInt();
			int[][] snail = new int[N][N];
			
			int row = 0, col = 0; // 시작점
			int dir = 0; // dy, dx의 인덱스
			
			for (int i=1; i<N*N+1; i++) {
				snail[row][col] = i; // 해당 칸에 숫자 기입
				
				// 한 칸씩 이동
				row += dy[dir];
				col += dx[dir];
				
				// 다음 위치가 범위를 벗어나거나 방문하지 않은 칸이라면
				if (row < 0 || col < 0 || row >= N || col >= N || snail[row][col] != 0) {
					// 부딪히는 곳에서 위치 조절하여 방향 틀기
					row -= dy[dir];
					col -= dx[dir];
					
					dir = (dir + 1) % 4;
					
					row += dy[dir];
					col += dx[dir];
				}
			}
			
			System.out.println("#" + tc);
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					System.out.printf("%d ", snail[i][j]);
				}
				System.out.println();
			}
		}
	}
}
