package y2011;
import java.io.*;
import java.util.*;

class P2 {
	public static void main (String [] args) throws IOException {
		in = new BufferedReader (new InputStreamReader (System.in));
		out = new PrintWriter (System.out, true);
		
		String next = next();
		while (next != null) {
			int digits = Integer.parseInt (next);
			int base = Integer.parseInt (next());
			
			long [] ways = new long [digits / 2 * (base - 1) + 1];
			for (int i = 0; i < base; i++) ways [i] = 1;
			for (int k = 1; k < digits / 2; k++) {
				long [] temp = new long [ways.length];
				for (int i = 0; i < base; i++)
					for (int j = ways.length - 1; j >= i; j--)
						temp [j] += ways [j - i];
				ways = temp;
			}
			long ans = 0;
			for (int i = 0; i < ways.length; i++) {
				ans += (long) ways [i] * ways [i];
			}
			
			out.printf ("There are " + ans + " %d-digit base %d lucky numbers.\n", digits, base);
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
