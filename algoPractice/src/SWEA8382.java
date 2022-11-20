import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 방향 전환

public class SWEA8382 {
	
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			stk = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(stk.nextToken());
			int y1 = Integer.parseInt(stk.nextToken());
			int x2 = Integer.parseInt(stk.nextToken());
			int y2 = Integer.parseInt(stk.nextToken());
			
			int dx = Math.abs(x2-x1);
			int dy = Math.abs(y2-y1);
			
			int answer = 0;
			
			if ((dx+dy) % 2 == 0) answer = Math.max(dx, dy) * 2;
			else answer = Math.max(dx, dy) * 2 - 1;
			
			sb.append("#" + tc + " " + answer + "\n");
		}
		
		System.out.print(sb);
	}
}