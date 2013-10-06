package y2012;
import java.io.*;
import java.util.*;

class Practice1 {
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out, true);
		
		String line = in.readLine();
		StringTokenizer st;
		ArrayList <Integer> nums = new ArrayList <Integer> ();
		while (line != null) {
			st = new StringTokenizer (line);
			while (st.hasMoreTokens ())
				nums.add (Integer.parseInt (st.nextToken ()));
			line = in.readLine();
		}
		
		long sum = 0;
		for (int p : nums) sum += p;
		double mean = (double) sum / nums.size();
		double diff = Double.MAX_VALUE;
		int [] ans = new int [2];
		int idx = 0;
		for (int p : nums) {
			if (Math.abs (p - mean) < diff) {
				diff = Math.abs (p - mean);
				ans [idx = 0] = p;
				idx = 1;
			} else if (Math.abs (p - mean) == diff) {
				ans [idx = 1] = p;
				idx = 2;
			}
		}
		
		if (idx == 2) {
			out.printf ("%d %d\n", Math.min (ans [0], ans [1]), Math.max (ans [0], ans [1]));
		} else {
			out.printf ("%d\n", ans [0]);
		}
		out.close();
		System.exit (0);
	}
	
}
