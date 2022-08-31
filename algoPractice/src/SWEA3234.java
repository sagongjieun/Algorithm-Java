import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 준환이의 양팔저울

public class SWEA3234 {
	
	static int N;
	static int[] nums, chu;
	static boolean[] isUsed;
	static ArrayList<Integer> left = new ArrayList<>();
	static ArrayList<Integer> right = new ArrayList<>();
	static int answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			answer = 0; // 정답 초기화
			
			N = Integer.parseInt(br.readLine()); // 무게추의 개수
			
			nums = new int[N];
			chu = new int[N];
			isUsed = new boolean[N];
			
			stk = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(stk.nextToken());
			}
			
			combChu(0);
			
			System.out.println("#" + tc + " " + answer);
		}
	}

	private static void combChu(int depth) {
		// 재귀 종료조건
		if (depth == N) {
			// 조합된 추들로 무게 확인
			checkWeight(0, 0, 0, chu);
			return;
		}
		
		// 순열로 추들의 순서 조합 구하기
		for (int i = 0; i < N; i++) {
			if (!isUsed[i]) {
				isUsed[i] = true;
				chu[depth] = nums[i];
				combChu(depth + 1);
				isUsed[i] = false;
			}
		}
	}

	private static void checkWeight(int index, int leftSum, int rightSum, int[] combedChu) {
		if (index == N) {
			answer++;
			return;
		}
		
		// 왼쪽 저울에 index 번째 추 올리기
		checkWeight(index + 1, leftSum + combedChu[index], rightSum, combedChu);
		
		// index 번째의 추 무게와 현재까지 오른쪽 저울의 무게가 왼쪽 저울의 무게보다 작을 때만
		// 오른쪽 저울에 추 올리고 무게 확인하기
		if (rightSum + combedChu[index] <= leftSum) {
			checkWeight(index + 1, leftSum, rightSum + combedChu[index], combedChu);
		}
	}
}