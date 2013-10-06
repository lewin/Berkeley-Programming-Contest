package y2011;
import java.io.*;
import java.util.*;

class P6 {
	public static void main (String [] args) throws IOException {
		in = new BufferedReader (new InputStreamReader (System.in));
		out = new PrintWriter (System.out, true);
		
		int [] matches = new int [] {6, 2, 5, 5, 4, 5, 6, 3, 7, 5}; 
		long [] ways = new long [81];
		ways [0] = 1;
		for (int k = 0; k <= 80; k++)
			for (int i = 0; i < matches.length; i++)
				if (k >= matches [i])
					ways [k] += ways [k - matches [i]];
		for (int i = 1; i <= 80; i++)
			ways [i] += ways [i - 1];
		
		String next = next();
		while (next != null) {
			int num = Integer.parseInt (next);
			long ans = 0;
			for (int i = 1; i < matches.length; i++)
				if (num >= matches [i])
					ans += ways [num - matches [i]];
			if (num >= 6) ans++;
			out.println (ans);
			next = next();
		}
		out.close();
		System.exit(0);
	}
	
	private static StringTokenizer st;
	private static BufferedReader in;
	private static PrintWriter out;
	
	private static String next() throws IOException{
		while (st == null || !st.hasMoreTokens ()) {
			String line = in.readLine ();
			if (line == null) return null;
			st = new StringTokenizer (line);
		}
		return st.nextToken ();
	}
}
