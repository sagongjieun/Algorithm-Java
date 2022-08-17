import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// N번째 큰 수 - Array Sort 사용

public class BOJ2075_arraysort {
	
	static int N;
	static int[] nums;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		N = Integer.parseInt(br.readLine());
		nums = new int[N*N];
		
		int index = 0;
		for (int i=0; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				nums[index++] = Integer.parseInt(stk.nextToken());
			}
		}
		
		Arrays.sort(nums);
		
		System.out.println(nums[N*N-N]);
	}
}