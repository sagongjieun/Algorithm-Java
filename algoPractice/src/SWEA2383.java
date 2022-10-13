import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 점심 식사시간

public class SWEA2383 {
	
	static int N;
	static Stair[] stairs;
	static ArrayList<Person> persons;
	static boolean[] visited;
	static Queue<Person>[] q;
	
	static int answer;
	
	static class Stair {
		int y, x, time;

		public Stair(int y, int x, int time) {
			this.y = y;
			this.x = x;
			this.time = time;
		}
	}
	
	static class Person {
		int y, x;
		int stair, timeToStair, stairTime;
		
		public Person(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
		private void getDistance() {
			this.timeToStair = Math.abs(y - stairs[stair].y) + Math.abs(x - stairs[stair].x);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			stairs = new Stair[2];	
			persons = new ArrayList<>();
			q = new LinkedList[2];
			q[0] = new LinkedList<>();
			q[1] = new LinkedList<>();
			answer = Integer.MAX_VALUE;
			
			int stairIndex = 0;
			
			for (int i = 1; i <= N; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					int num = Integer.parseInt(stk.nextToken());
					
					if (num == 0) continue;
					if (num == 1) persons.add(new Person(i, j)); // 사람 위치 저장
					else stairs[stairIndex++] = new Stair(i, j, num); // 계단 위치 저장
				}
			}
			
			movePerson(0);
			
			sb.append("#" + tc + " " + answer + "\n");
		}
		
		System.out.print(sb);
	}

	private static void movePerson(int index) {
		if (index == persons.size()) {
			int result = simulate();
			answer = Math.min(answer, result);
			return;
		}
		
		// 첫번째 계단 이용
		persons.get(index).stair = 0;
		persons.get(index).getDistance();
		movePerson(index + 1);
		
		// 두번째 계단 이용
		persons.get(index).stair = 1;
		persons.get(index).getDistance();
		movePerson(index + 1);
	}

	private static int simulate() {
		int count = 0;
		int time = 1;
		visited = new boolean[persons.size()];
		
		while (true) {
			for (int i = 0; i < q.length; i++) {
				int size = q[i].size();
				
				for (int j = 0; j < size; j++) {
					Person p = q[i].poll();
					Stair s = stairs[p.stair];
					
					if (p.stairTime + s.time <= time) continue;
					
					q[i].add(p);
				}
			}
			
			if (count == persons.size() && q[0].isEmpty() && q[1].isEmpty()) return time;
			
			for (int i = 0; i < persons.size(); i++) {
				if (visited[i]) continue;
				
				Person p = persons.get(i);
				Queue<Person> pq = q[p.stair];
				
				if (p.timeToStair + 1 <= time && pq.size() < 3) {
					p.stairTime = time;
					visited[i] = true;
					pq.add(p);
					count++;
				}
			}
			
			time++;
		}
	}
}