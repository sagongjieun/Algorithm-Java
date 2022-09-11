import java.util.Scanner;

// 검증수

public class BOJ2475 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int sum = 0;
		for (int i = 0; i < 5; i++) {
			int num = sc.nextInt();
			sum += (int) Math.pow(num, 2);
		}
		
		System.out.print(sum % 10);
	}

}