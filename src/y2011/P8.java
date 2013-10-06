package y2011;
import java.io.*;
import java.util.*;

class P8 {
	private static int [] eadj, eprev, elast;
	private static int eidx;
	private static int N;
	
	public static void main (String [] args) throws IOException {
		in = new BufferedReader (new InputStreamReader (System.in));
		out = new PrintWriter (System.out, true);
		
		String next = next();
		while (next != null) {
			N = Integer.parseInt (next);
			eadj = new int [2 * N];
			elast = new int [N + 1];
			eprev = new int [2 * N];
			eidx = 0;
			Arrays.fill (elast, -1);
			for (int i = 0; i < N - 1; i++)
				addEdge (nextInt(), nextInt());
			stops = Integer.MAX_VALUE;
			// during live contest, the multiply by 2 was missing
			for (int i = 0; i < 2 * (N - 1); i += 2) {
				calc (eadj [i], eadj [i ^ 1]);
			}
			
			out.printf ("Cancel flight %d:%d and add %d:%d for maximum of %d stops.\n",
					ra, rb, ca, cb, stops - 1);
			
			next = next();
		}
	}
	
	private static void addEdge (int a, int b) {
		eadj [eidx] = b; eprev [eidx] = elast [a]; elast [a] = eidx++;
		eadj [eidx] = a; eprev [eidx] = elast [b]; elast [b] = eidx++;
	}
	
	private static int ca, cb, ra, rb, stops;
	
	private static int [] prev;
	private static void calc (int a, int b) {
		// find center of longest path in A and B
		prev = new int [N + 1];
		
		int [] la = longestPath (a, b);
		int [] lb = longestPath (b, a);
		
		int mida = la [0] / 2 + (la [0] % 2);
		int midb = lb [0] / 2 + (lb [0] % 2);
		int test = Math.max (Math.max (la [0], lb [0]), mida + midb + 1);
		
		if (test < stops) {
			int na = la [1], nb = lb [1];
			for (int i = 0; i < mida; i++) na = prev [na];
			for (int i = 0; i < midb; i++) nb = prev [nb];

			stops = test;
			ca = na; cb = nb;
			ra = a; rb = b;
		}
	}
	
	private static int x;
	private static int [] longestPath (int root, int forbidden) {
		x = forbidden;
		int [] arr = longest (root, forbidden);
		return longest (arr [1], -1);
	}
	
	private static int [] longest (int node, int p) {
		int max = 0, deep = node;
		for (int e = elast [node]; e != -1; e = eprev [e]) {
			if (eadj [e] != p && eadj [e] != x) {
				prev [eadj [e]] = node;
				int [] arr = longest (eadj [e], node);
				if (arr [0] + 1 > max) {
					max = arr [0] + 1;
					deep = arr [1];
				}
			}
		}
		return new int [] {max, deep};
	}
	
	private static StringTokenizer st;
	private static BufferedReader in;
	private static PrintWriter out;
	
	private static String next() throws IOException {
		while (st == null || !st.hasMoreTokens ()) {
			String line = in.readLine ();
			if (line == null) return null;
			st = new StringTokenizer (line);
		}
		return st.nextToken ();
	}
	
	private static int nextInt () throws IOException {
		return Integer.parseInt (next());
	}
}
