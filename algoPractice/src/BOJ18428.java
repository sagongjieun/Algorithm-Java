import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 감시 피하기

public class BOJ18428 {
	
	static int N;
	static char[][] map;
	static ArrayList<Node> students = new ArrayList<>();
	static String answer;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	
	static class Node {
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = stk.nextToken().charAt(0);
				
				if (map[i][j] == 'S') students.add(new Node(i, j));
			}
		}
		
		setObstacle(0);
		
		System.out.print("NO");
	}

	private static void setObstacle(int obstacle) {
		// 3개의 장애물을 다 배치했으면
		if (obstacle == 3) {
			checkTeachers();
			return;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'X') {
					map[i][j] = 'O';
					setObstacle(obstacle + 1);
					map[i][j] = 'X';
				}
			}
		}
	}

	private static void checkTeachers() {
		Queue<Node> q = new LinkedList<>();
		char[][] newMap = new char[N][N];
		boolean[][] visited = new boolean[N][N];
		
		// 새로운 map copy
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				newMap[i][j] = map[i][j];
			}
		}
		
		// 선생님 위치 q에 저장
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (newMap[i][j] == 'T') {
					q.add(new Node(i, j));
					visited[i][j] = true;
				}
			}
		}
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int ny = cur.y + dy[d];
				int nx = cur.x + dx[d];
				
				while (isInRange(ny, nx)) {
					if (newMap[ny][nx] != 'O') {
						visited[ny][nx] = true;
						ny += dy[d];
						nx += dx[d];
					} else break;
				}
			}
		}
		
		if (!catchStudent(visited)) {
			System.out.print("YES");
			System.exit(0);
		}
	}

	private static boolean catchStudent(boolean[][] visited) {
		// 선생님한테 잡힌 학생이 한명이라도 있으면 true return
		for (Node stu : students) {
			if (visited[stu.y][stu.x]) return true;
		}
		return false;
	}

	// 범위 체크 함수
	private static boolean isInRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < N && x < N) return true;
		return false;
	}
}