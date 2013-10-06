package y2011;
import java.io.*;
import java.util.*;

class P5 {
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out, true);
		
		String line = in.readLine ();
		while (line != null) {
			long lpara = 0, lparb = 1;
			long rpara = 1, rparb = 0;
			long cura = 1, curb = 1;
			for (char c : line.toCharArray ()) {
				if (c == 'R') {
					long ta = cura + lpara;
					long tb = curb + lparb;
					long gcd = gcd (ta, tb);
					ta /= gcd; tb /= gcd;
					
					rpara = cura; rparb = curb;
					cura = ta; curb = tb;
				} else {
					long ta = cura + rpara;
					long tb = curb + rparb;
					long gcd = gcd (ta, tb);
					ta /= gcd; tb /= gcd;
					
					lpara = cura; lparb = curb;
					cura = ta; curb = tb;
				}
			}
			
			out.println (curb + "/" + cura);
			
			line = in.readLine ();
		}
		out.close();
		System.exit(0);
	}
	
	private static long gcd (long x, long y) {
		for (; x != 0; x ^= y, y ^= x, x ^= y, x %= y);
		return y;
	}
}
