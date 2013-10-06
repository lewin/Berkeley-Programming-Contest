package y2012;
import java.util.*;
import java.io.*;

class P6 {
	private static int [] dx = new int [] {0, -1, -1, 0, 1, 1};
	private static int [] dy = new int [] {-1, -1, 0, 1, 1, 0};
	public static void main (String [] args) throws IOException {
		Scanner in = new Scanner (System.in);
		PrintWriter out = new PrintWriter (System.out, true);
		int N = 10010;
		int [] x = new int [N], y = new int [N];
		int cx = 0, cy = 0, dir = 0, len = 1, clen = 0;
		for (int i = 2; i < N; i++) {
			while (clen == len || (dir == 1 && clen + 1 == len)) {
				dir = (dir + 1) % 6;
				if (dir == 0) len++;
				clen = 0;
			}
			cx += dx [dir]; cy += dy [dir];
			x [i] = cx; y [i] = cy;
			clen++;
		}
		
		while (true) {
			int a = in.nextInt(), b = in.nextInt();
			if (a == 0 && b == 0) break;
			int c = x [a] - x [b], d =  y [a] - y [b];
			int ans = 0;
			if (c > 0 && d > 0) {
				ans = Math.max (c, d);
			} else if (c < 0 && d < 0) {
				ans = Math.max (-c, -d);
			} else {
				ans = Math.abs (c - d);
			}
			out.printf ("The distance between cells %d and %d is %d.\n", a, b, ans);
		}
		
		out.flush();
		out.close();
		System.exit(0);
	}
}
