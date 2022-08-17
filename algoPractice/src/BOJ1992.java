import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 쿼드트리

public class BOJ1992 {
	static int N;
	static int[][] map;
	static ArrayList<String> answer = new ArrayList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i=0; i<N; i++) {
			String[] command = br.readLine().split("");
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(command[j]);
			}
		}

		recur(0, 0, N);

		for(int i=0; i<answer.size(); i++) {
			System.out.print(answer.get(i));
		}
	}

	public static void recur (int y, int x, int n) {
		int result = 0;

		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				result += map[x+j][y+i];
			}
		}
		
		// n x n 행렬의 모든 합이 0이면 정답은 0
		if (result == 0) answer.add("0");
		// n x n 행렬의 모든 수가 1이면(합이 n*n) 정답은 1
		else if (result == n*n) answer.add("1");
		else {
			n = n/2; // 위의 조건에 맞지 않으면 구역을 반으로

			// 기존 N x N 행렬에서 하나라도 압축되면
			answer.add("(");
			recur(y,x,n);
			recur(y+n,x,n);
			recur(y,x+n,n);
			recur(y+n,x+n,n);
			answer.add(")");
		}
	}
}