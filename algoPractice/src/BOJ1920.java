import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 수 찾기

public class BOJ1920 {
	
	static int N, M;
	static int[] A;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		stk = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(stk.nextToken());
		}
		Arrays.sort(A);
		
		M = Integer.parseInt(br.readLine());
		stk = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int num = Integer.parseInt(stk.nextToken());
			if (findNum(num)) sb.append("1\n");
			else sb.append("0\n");
		}
		
		System.out.print(sb);
	}

	private static boolean findNum(int num) {
		int left = 0;
		int right = N - 1;
		
		while (left <= right) {
			int middle = (left + right) / 2;
			
			if (num < A[middle]) right = middle - 1;
			else if (num > A[middle]) left = middle + 1;
			else return true;
		}
		
		return false;
	}

}