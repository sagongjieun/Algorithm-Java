import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test1_7th {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			
			int[] stadium = new int[10];
			stk = new StringTokenizer(br.readLine());
			for (int i = 0; i < 10; i++) {
				stadium[i] = Integer.parseInt(stk.nextToken());
			}
			
			int answer = 0;
			
			for (int i = 0; i < 5; i++) {
				stk = new StringTokenizer(br.readLine());
				int uplimit = Integer.parseInt(stk.nextToken());
				int downlimit = Integer.parseInt(stk.nextToken());
				boolean flag = true;
				
				// i번째 토끼가 경기장을 넘을 수 있는지 확인
				for (int j = 0; j < 9; j++) {
					int cur = stadium[j];
					int next = stadium[j+1];
					
					// 오르막이면
					if (cur < next) {
						if (next - cur > uplimit) {
							flag = false;
							break;
						}
					}
					// 내리막이면
					else if (cur > next) {
						if (cur - next > downlimit) {
							flag = false;
							break;
						}
					}
				}
				
				if (flag) answer++;
			}
			
			sb.append("#" + tc + " " + answer + "\n");
		}
		
		System.out.print(sb);
	}
}