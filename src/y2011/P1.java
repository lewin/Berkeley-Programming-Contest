package y2011;
import java.util.*;
import java.io.*;

class P1 {
	private static int [][] comp;
	private static char [][] grid;
	private static int N, M;
	private static char [][] ans;
	private static char [] key;
	private static boolean [][] use;
	private static char [] colors = new char [] {'R', 'G', 'B', 'Y'};
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out, true);
		
		String line = in.readLine ();
		ArrayList <String> lines = new ArrayList <String> ();
		N = line.length();
		while (line != null) {
			lines.add (line);
			line = in.readLine ();
		}
		
		M = lines.size();
		grid = new char [M][N];
		for (int j = 0; j < lines.size(); j++) {
			for (int i = 0; i < lines.get(j).length(); i++)
				grid [j][i] = lines.get(j).charAt (i);
		}
		
		comp = new int [M][N];
		for (int i = 0; i < M; i++)
			Arrays.fill (comp [i], -1);
		int idx = 0;
		ans = new char [M][N];
		for (int i = 0; i < M; i++)
			for (int j = 0; j < N; j++) {
				if (comp [i][j] == -1) {
					fill (i, j, idx++, grid [i][j]);
				}
			}
		
		eadj = new int [2500 * 2500];
		elast = new int [idx + 1];
		eprev = new int [2500 * 2500];
		eidx = 0;
		Arrays.fill (elast, -1);
		
		for (int i = 0; i < M; i++)
			for (int j = 0; j < N; j++) {
				if (i - 1 >= 0) {
					if (comp [i - 1][j] != comp [i][j])
						addEdge (comp [i - 1][j], comp [i][j]);
				}
				if (j - 1 >= 0) {
					if (comp [i][j - 1] != comp [i][j])
						addEdge (comp [i][j - 1], comp [i][j]);
				}
			}
		
		use = new boolean [idx + 1][5];
		key = new char [idx + 1];
		vis = new boolean [idx + 1];
		
		color (0);
		for (int i = 0; i < M; i++)
			for (int j = 0; j < N; j++)
				if (comp [i][j] != -1)
					fill2 (i, j, comp [i][j], key [comp [i][j]]);
		
		for (int i = 0; i < M; i++)
			out.println (new String (ans [i], 0, N));
		
		out.close();
		System.exit(0);
	}
	private static boolean [] vis;
	private static void color(int node) {
		if (vis [node]) return;
		vis [node] = true;
		int q = 0;
		while (use [node][q]) q++;
		key [node] = colors [q];
		
		for (int e = elast [node]; e != -1; e = eprev [e])
			use [eadj [e]][q] = true;
		
		for (int e = elast [node]; e != -1; e = eprev [e])
			color (eadj [e]);
	}
	
	private static int [] eadj, eprev, elast;
	private static int eidx;
	
	private static void addEdge (int a, int b) {
		eadj [eidx] = b; eprev [eidx] = elast [a]; elast [a] = eidx++;
		eadj [eidx] = a; eprev [eidx] = elast [b]; elast [b] = eidx++;
	}
	
	private static void fill2 (int x, int y, int cur, char color) {
		if (x < 0 || y < 0 || x >= M || y >= N || comp [x][y] != cur) return;
		comp [x][y] = -1;
		ans [x][y] = color;
		fill2 (x - 1, y, cur, color);
		fill2 (x + 1, y, cur, color);
		fill2 (x, y - 1, cur, color);
		fill2 (x, y + 1, cur, color);
	}
	
	private static void fill (int x, int y, int idx, char sym) {
		if (x < 0 || y < 0 || x >= M || y >= N || grid [x][y] != sym) return;
		if (comp [x][y] != -1) return;
		comp [x][y] = idx;
		fill (x - 1, y, idx, sym);
		fill (x + 1, y, idx, sym);
		fill (x, y - 1, idx, sym);
		fill (x, y + 1, idx, sym);
	}
}
