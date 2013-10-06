package y2011;
import java.util.*;
import java.io.*;

class P3 {
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out, true);
		
		String line = in.readLine ();
		while (line != null) {
			int [] freq = new int [26];
			int ans = 0;
			boolean first = true;
			for (char c : line.toCharArray()) {
				if (c == ' ') {first = true; ans += 7; continue;}
				
				if (!first) ans += 3;
				first = false;
				
				freq [c - 'A']++;
			}
			
			System.out.println (Arrays.toString (freq));
			Arrays.sort (freq);
			
			PriorityQueue <Integer> pq = new PriorityQueue <Integer> ();
			pq.add (1); pq.add (3);
			for (int i = 25; i >= 0; i--) {
				int cost = pq.poll();
				System.out.println (freq [i] + " " + cost);
				ans += freq [i] * cost;
				if (pq.size() < 100) {
					pq.add (cost + 2);
					pq.add (cost + 4);
				}
			}
			out.println (ans);
			line = in.readLine ();
		}
		out.close();
		System.exit(0);
	}
}
