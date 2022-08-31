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

	/**
	 * 순열을 이용하여 추들의 순서 조합 구하기
	 * @param depth : index
	 */
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
				chu[depth] = nums[i]; // 이번 턴의 추 순서 조합
				combChu(depth + 1);
				isUsed[i] = false;
			}
		}
	}

	/**
	 * 순열을 이용해 구한 추들의 각 순서 조합에 부분집합을 이용해 무게 확인하기
	 * @param index
	 * @param leftSum : 왼쪽 저울의 합
	 * @param rightSum : 오른쪽 저울의 합
	 * @param combedChu : 조합된 추의 배열
	 */
	private static void checkWeight(int index, int leftSum, int rightSum, int[] combedChu) {
		// 재귀 종료조건
		if (index == N) {
			// 조건을 만족하는 조합이 생기면 정답 + 1
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