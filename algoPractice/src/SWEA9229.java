import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA9229 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(stk.nextToken()); // 과자 봉지의 개수
			int M = Integer.parseInt(stk.nextToken()); // 무게 합 제한
			
			int[] weights = new int[N]; // 과자 봉지의 무게를 담을 배열
			stk = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) weights[i] = Integer.parseInt(stk.nextToken());
			
			int max = 0, sum = 0;
			
			// 투포인터를 이용하여 배열의 요소를 앞에서부터 2개씩 선택하여
			// 두 요소를 더한값을 max 값과 비교하여 갱신
			for (int i=0; i<N-1; i++) {
				for (int j=i+1; j<N; j++) {
					sum = weights[i] + weights[j];
					
					if (sum <= M && max < sum) max = sum;
				}
			}
			
			if (max == 0) max = -1;
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(tc).append(" ").append(max);
			
			System.out.println(sb);
		}
	}
}