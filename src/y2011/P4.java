package y2011;
import java.util.*;
import java.io.*;

class P4 {
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out, true);
		
		String line = in.readLine ();
		while (line != null) {
			Expression [] stack = new Expression [1000];
			int back = 0;
			StringTokenizer st = new StringTokenizer (line);
			while (st.hasMoreTokens ()) {
				String s = st.nextToken ();
				if (s.equals ("_")) {
					Expression exp1 = stack [--back];
					if (exp1.op < 3) exp1.exp = "(" + exp1.exp + ")";
					stack [back++] = new Expression ("-" + exp1.exp, 3);
				}
				else if (s.equals ("*") || s.equals ("/")) {
					Expression exp2 = stack [--back];
					Expression exp1 = stack [--back];
					if (exp1.op < 2) exp1.exp = "(" + exp1.exp + ")";
					if (exp2.op <= 2) exp2.exp = "(" + exp2.exp + ")";
					stack [back++] = new Expression (exp1.exp + s + exp2.exp, 2);
				} else if (s.equals ("-") || s.equals ("+")) {
					Expression exp2 = stack [--back];
					Expression exp1 = stack [--back];
					if (exp1.op < 1) exp1.exp = "(" + exp1.exp + ")";
					if (exp2.op <= 1) exp2.exp = "(" + exp2.exp + ")";
					stack [back++] = new Expression (exp1.exp + s + exp2.exp, 1);
				} else {
					stack [back++] = new Expression (s, 4);
				}
			}
			
			out.println (stack [0]);
			line = in.readLine ();
		}
	}
	
	static class Expression {
		public String exp;
		public int op;
		
		public Expression (String exp, int op) {
			this.exp = exp;
			this.op = op;
		}
		
		public String toString() {
			return exp;
		}
	}
}
