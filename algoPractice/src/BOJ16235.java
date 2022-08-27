import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ16235 {
	
	static int N, M, K;
	static int[][] A; // 겨울에 각 땅에 공급받을 양분의 값
	static int[][] land; // 각 땅의 현재 양분의 값
	
	static ArrayList<Tree> trees = new ArrayList<>();
	static Deque<Integer> deadTrees = new LinkedList<>();
	
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
	
	static class Tree {
		int y, x, age;
		boolean isDead;
		
		public Tree (int y, int x, int age) {
			this.y = y;
			this.x = x;
			this.age = age;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		
		A = new int[N][N];
		land = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(stk.nextToken());
				land[i][j] = 5; // 각 땅의 초기 양분은 5
			}
		}
		
		for (int i = 0; i < M; i++) {
			stk = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(stk.nextToken()) - 1;
			int x = Integer.parseInt(stk.nextToken()) - 1;
			int age = Integer.parseInt(stk.nextToken());
			
			trees.add(new Tree(y, x, age));
		}
		
		// 처음에 주어지는 나무들에 대해 나이순으로 오름차순 정렬
		Collections.sort(trees, (o1, o2) -> o1.age - o2.age);
		
		seasons();
		
		System.out.print(trees.size());
	}

	public static void seasons() {
		
		while (K > 0) {
			
			// 봄
			for (int i = 0; i < trees.size(); i++) {
				Tree tree = trees.get(i);
				
				if (tree.age > land[tree.y][tree.x]) {
					deadTrees.add(i);
				}
				else {
					land[tree.y][tree.x] -= tree.age;
					tree.age += 1;
				}
			}
			
			// 여름
			while (!deadTrees.isEmpty()) {
				int deadTreeIndex = deadTrees.pollLast();
				
				Tree deadTree = trees.get(deadTreeIndex);
				int nutrient = deadTree.age / 2;
				land[deadTree.y][deadTree.x] += nutrient;
				
				deadTree.isDead = true;
			}
			
			// 가을
			ArrayList<Tree> newTrees = new ArrayList<>();
			for (int i = 0; i < trees.size(); i++) {
				Tree tree = trees.get(i);
				
				if (!tree.isDead) {
					if (tree.age % 5 == 0) {
						for (int j = 0; j < 8; j++) {
							int ny = tree.y + dy[j];
							int nx = tree.x + dx[j];
							
							if (rangeCheck(ny, nx)) {
								newTrees.add(new Tree(ny, nx, 1));
							}
						}
					}
				}
			}
			
			for (Tree tree : trees) {
				if (!tree.isDead) newTrees.add(tree);
			}
			
			trees = newTrees;
			
			// 겨울
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					land[i][j] += A[i][j];
				}
			}
			
			K--;
		}
		
	}

	public static boolean rangeCheck(int y, int x) {
		if (y >= 0 && x >= 0 && y < N && x < N) return true;
		return false;
	}

}
