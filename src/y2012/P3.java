package y2012;
import java.util.*;
import java.io.*;

class P3 {
	private static int W, H;
	private static int [] rowp, rown, colp, coln;
	private static char [][] grid, ans;
	private static boolean found;
	private static Scanner in;
	private static int count;
	public static void main (String [] args) throws IOException {
		in = new Scanner (System.in);
		PrintWriter out = new PrintWriter (System.out, true);
		count = 0;
		int t = 0;
		while (true) {
			W = in.nextInt(); H = in.nextInt();
			if (W == 0 && H == 0) break;
			colp = new int [W]; coln = new int [W];
			rowp = new int [H]; rown = new int [H];
			readArray(colp);
			readArray(coln);
			readArray(rowp);
			readArray(rown);
			
			
			grid = new char [H][W];
			for (int i = 0; i < H; i++)
				grid [i] = in.next().toCharArray();
			ans = new char [H][W];
			for (int i = 0; i < H; i++)
				Arrays.fill (ans [i], ' ');
			
			found = false;
			solve(0, 0);
			
			out.printf ("Set %d:\n", ++t);
			for (int i = 0; i < H; i++)
				out.printf ("   %s\n", new String (ans [i]));
			out.println();
		}
		out.close();
		System.exit(0);
	}
		
	private static void readArray (int [] arr) {
		for (int i = 0; i < arr.length; i++) {
			arr [i] = in.nextInt();
		}
	}
	
	private static void solve (int x, int y) {
		if (found) return;
		if (ans [x][y] != ' ') {
			solveNext (x, y);
		}
		char space = grid [x][y];
		if (space == 'R' || space == 'B') return;
		int ox = x, oy = y;
		switch (space) {
		case 'L': oy = y+1; break;
		case 'T': ox = x+1; break;
		}
		check (rowp, colp, '+', rown, coln, '-', x, y, ox, oy);
		if (found) return;
		check (rown, coln, '-', rowp, colp, '+', x, y, ox, oy);
		if (found) return;
		ans [x][y] = 'x'; ans [ox][oy] = 'x';
		solveNext (x, y);
		if (found) return;
		ans [x][y] = ' '; ans [ox][oy] = ' ';
	}
	
	private static void check (int [] rows, int [] cols, char s, int [] rowo, int [] colo, char o, int x, int y, int ox, int oy) {
		if (ok (rows [x], cols [y]) && check2 (x, y, s) 
				&& ok (rowo [ox], colo [oy]) && check2 (ox, oy, o)) {
			ans [x][y] = s;
			ans [ox][oy] = o;
			
			if (rows[x] > 0) --rows [x]; 
			if (cols[y] > 0) --cols [y];
			if (rowo[ox] > 0) --rowo [ox];
			if (colo[oy] > 0) --colo [oy];
			
			solveNext (x, y);
			
			if (rows [x] >= 0) ++rows [x];
			if (cols [y] >= 0) ++cols [y];
			if (rowo [ox] >= 0) ++rowo [ox];
			if (colo [oy] >= 0) ++colo [oy];
			
			if (found) return;
			ans [x][y] = ' ';
			ans [ox][oy] = ' ';
		}
	}
	
	private static boolean check2 (int x, int y, char c) {
		if (x > 0 && ans [x - 1][y] == c) return false;
		if (y > 0 && ans [x][y - 1] == c) return false;
		if (x + 1 < H && ans [x + 1][y] == c) return false;
		if (y + 1 < W && ans [x][y + 1] == c) return false;
		return true;
	}
	
	private static boolean ok (int row, int col) {
		return (row == -1 || row > 0) && (col == -1 || col > 0);
	}
	
	private static void solveNext(int x, int y) {
		if (x + 1 == H) {
			if (y + 1 == W) {
				boolean ok = true;
				for (int i = 0; ok && i < H; i++) 
					ok &= (rowp [i] <= 0 && rown [i] <= 0);
				for (int i = 0; ok && i < W; i++)
					ok &= (colp [i] <= 0 && coln [i] <= 0);
				if (ok) found = true;
				return;
			}
			solve (0, y + 1);
		}
		else 
			solve (x + 1, y);
	}
}
