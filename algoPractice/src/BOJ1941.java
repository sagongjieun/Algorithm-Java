import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// 소문난 칠공주

public class BOJ1941 {

	static char[][] map;
	static boolean[] visited;
	static int[] selected;
	
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};

	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new char[5][5];
		for (int i = 0; i < 5; i++) {
			String s = br.readLine();
			for (int j = 0; j < 5; j++) {
				map[i][j] = s.charAt(j);
			}
		}

		visited = new boolean[25];
		selected = new int[7];
		
		Comb(0, 0);

		System.out.print(answer);
	}

	// 25명 중에서 7명을 고르는 조합 함수
	private static void Comb(int depth, int start) {
		if (depth == 7) {
			if (isPossible()) answer += 1;
			return;
		}
		
		for (int i = start; i < 25; i++) {
			if (!visited[i]) {
				visited[i] = true;
				selected[depth] = i;
				Comb(depth + 1, i + 1);
				visited[i] = false;
			}
		}
	}

	// 고른 7명이 조건에 충족한 지 확인하는 함수
	private static boolean isPossible() {
		int yCount = 0;
		
		ArrayList<Integer> list = new ArrayList<>();
		for (int s : selected) {
			int seatY = s / 5;
			int seatX = s % 5;
			
			if (map[seatY][seatX] == 'Y') yCount += 1;
			list.add(s);
		}
		
		if (yCount > 3) return false;
		
		Queue<Integer> q = new LinkedList<>();
		q.add(selected[0]);
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int ny = (cur / 5) + dy[i];
				int nx = (cur % 5) + dx[i];
				
				// 범위를 벗어나지 않고 지금 조합에 해당 좌표가 포함돼있으면
				if (isInRange(ny, nx) && list.contains((ny * 5) + nx)) {
					list.remove(Integer.valueOf((ny * 5) + nx));
					q.add((ny * 5) + nx);
				}
			}
		}
		
		if (!list.isEmpty()) return false;
		return true;
	}

	// 범위 체크 함수
	private static boolean isInRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < 5 && x < 5) return true;
		return false;
	}
}