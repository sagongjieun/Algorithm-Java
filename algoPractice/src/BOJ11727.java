import java.util.Scanner;

// 2×n 타일링 2

public class BOJ11727 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] DP = new int[1001];
		
		DP[1] = 1; DP[2] = 3;
		for (int i = 3; i <= N; i++) {
			DP[i] = (DP[i-1] + DP[i-2] * 2) % 10007;
		}
		
		System.out.print(DP[N]);
	}
}