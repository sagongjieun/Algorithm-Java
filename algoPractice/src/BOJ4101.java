import java.util.Scanner;

// 크냐?

public class BOJ4101 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		while (true) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			if (a == 0 && b == 0) break;
			
			if (a > b) sb.append("Yes\n");
			else sb.append("No\n");
		}
		
		System.out.print(sb);
	}

}