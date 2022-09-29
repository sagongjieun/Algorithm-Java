import java.util.Scanner;

// 1로 만들기

public class BOJ1463 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] DP = new int[N + 1]; // DP[i] : i번째 수를 1로 만들 수 있는 경우의 수
		
		DP[1] = 0; // 1을 조건을 이용해 1로 만들 수 있는 경우의 수는 0임에 유의해야 함
		for (int i = 2; i <= N; i++) {
			DP[i] = 1000001; // N이 최대값일 때 나올 수 있는 경우의 수 중 최대의 값으로 초기화
			
			// i번째 수에 대해
			if (i % 3 == 0) DP[i] = Math.min(DP[i], DP[i/3] + 1); // 3으로 나누어 떨어지면 3으로 나눈 수의 DP값에 +1 한 값과 현재 i의 DP값 중 최소값으로 갱신
			if (i % 2 == 0) DP[i] = Math.min(DP[i], DP[i/2] + 1); // 2로 나누어 떨어지면 2로 나눈 수의 DP값에 +1 한 값과 현재 i의 DP값 중 최소값으로 갱신
			DP[i] = Math.min(DP[i], DP[i-1] + 1); // 현재 i의 DP값과 i-1번째의 DP값에 +1 한 값 중 최소값으로 갱신
			
			// +1 의 의미 : /3을 한다면 /3을 한 번 연산 한다는 의미이므로 +1
			// 3으로 나누어 떨어지는 경우와 2로 나누어 떨어지는 경우에 대해 if / else if 로 두면 안됨에 유의
		}
		
		System.out.print(DP[N]); // N번째 수를 1로 만들 수 있는 경우의 수 출력
	}

}