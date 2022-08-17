import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Z

public class BOJ1074 {

	static int N, r, c;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());

		N = Integer.parseInt(stk.nextToken());
		r = Integer.parseInt(stk.nextToken());
		c = Integer.parseInt(stk.nextToken());
		
		int answer = recur(r, c, N);

		System.out.print(answer); // 정답 출력
	}

	public static int recur(int y, int x, int n) {
		// 종료조건
		// [r,c]번째에 오면 return
		if (n == 0) return 0;
		
		int half = 1<<n-1; // 2 ^ n-1
		
		// 왼쪽 위
		if (y<half && x<half) return recur(y, x, n-1);
		// 오른쪽 위
		else if (y<half && x>=half) return half * half + recur(y, x-half, n-1);
		// 왼쪽 아래
		else if (y>=half && x<half)return 2 * half * half + recur(y-half, x, n-1);
		// 오른쪽 아래
		else return 3 * half * half + recur(y-half, x-half, n-1);
	}
}
