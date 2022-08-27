import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Shuffle-O-Matic

public class SWEA03 {
	
	static int N;
	static int[] cards;
	static int answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); // 카드 개수
			
			stk = new StringTokenizer(br.readLine());
			cards = new int[N];
			for (int i = 0; i < N; i++) {
				cards[i] = Integer.parseInt(stk.nextToken());
			}
			
			answer = Integer.MAX_VALUE;
			
			DFS(0, cards);
			
			if (answer == Integer.MAX_VALUE) answer = -1;
			
			System.out.println("#" + tc + " " + answer);
		}
	}

	/**
	 * 
	 * @param count : 정렬 횟수
	 * @param cards : 카드 배열
	 */
	private static void DFS(int count, int[] cards) {
		if (count > 5) return;
		if (count >= answer) return;
		
		if (isSorted(cards)) {
			answer = Math.min(answer, count);
			return;
		}
		
		for (int x = 1; x < N; x++) {
			int[] newCards = shuffle(x, cards);
			DFS(count + 1, newCards);
		}
	}

	private static int[] shuffle(int x, int[] cards) {
		int[] left = new int[N/2];
		int[] right = new int[N/2];
		int[] newCards = new int[N];
		
		// x가 N/2 이전의 수라면 왼쪽에 cards의 왼쪽 카드, 오른쪽에 cards의 오른쪽 카드
		if (x < N/2) {
			for (int i = 0; i < N/2; i++) {
				left[i] = cards[i];
				right[i] = cards[i + N/2];
			}
		}
		// 아니라면 왼쪽에 cards의 오른쪽 카드, 오른쪽에 cards의 왼쪽 카드
		else {
			for (int i = 0; i < N/2; i++) {
				right[i] = cards[i];
				left[i] = cards[i + N/2];
			}
			// x의 수가 N/2를 기준으로 대칭이므로 대칭된 수에 맞게 세팅
			x = N - x - 1;
		}
		
		// left에서 N/2 - x의 개수만큼 가져와 newCards의 왼쪽에 저장
		int index = 0;
		for (int i = 0; i < N/2 - x; i++) {
			newCards[index++] = left[i];
		}
		
		int count = 0;
		while (count < x) {
			// left, right 카드의 수를 newCards에 번갈아가며 저장
			newCards[index++] = right[count];
			newCards[index++] = left[N/2 - x + count];
			count++;
		}
		
		for (int i = x; i < N/2; i++) {
			newCards[index++] = right[i];
		}
		
		return newCards;
	}

	private static boolean isSorted(int[] cards) {
		boolean isUp = true;
		boolean isDown = true;
		
		for (int i = 0; i < N; i++) {
			if (cards[i] != i + 1) isUp = false;
			if (cards[i] != N - i) isDown = false;
			
			if (!isUp && !isDown) return false;
		}
		
		return true;
	}
}