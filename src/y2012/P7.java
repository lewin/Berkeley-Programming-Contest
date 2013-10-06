package y2012;
import java.util.*;
import java.io.*;

class P7 {
	public static void main (String [] args) throws IOException {
		Scanner in = new Scanner (System.in);
		PrintWriter out = new PrintWriter (System.out, true);

		
		while (in.hasNext()) {
			String exp = in.next();
			double [] a = eval(exp);
			out.printf ("Average value of %s is %.2f\n", exp, a[0] * a[1] / (a[1]+1));
		}
		out.close();
		System.exit(0);
	}
	
	private static double[] eval (String exp) {
		int i = 0;
		int cur = 0, terms = 0;
		double ave = 0;
		
		while (i < exp.length()) {
			double prev = 0, weight = 0, pave = 0;
			String pexp = "";
			switch (exp.charAt(i)) {
			case '(':
				int j = i, count = 1;
				while (count > 0) {
					j++;
					if (exp.charAt(j) == '(') count++;
					else if (exp.charAt(j) == ')') count--;
				}
				double [] a = eval (exp.substring (i + 1, j));
				pave = a[0]; prev = a[2]; weight = a[1];
				pexp = exp.substring(i+1, j);
				i = j;
				break;
			case '+':
				pave = 1;
				prev = 1;
				pexp = "+";
				weight = 1;
				break;
			case '-':
				pave = -1;
				prev = -1;
				pexp = "-";
				weight = 1;
				break;
			case '.':
				pave = 0;
				prev = 0;
				pexp = ".";
				weight = 1;
				break;
			}
			i++;
			
			int num = 0;
			while (i < exp.length() && exp.charAt(i) >= '0' && exp.charAt(i) <= '9') {
				num *= 10;
				num += exp.charAt(i) - '0';
				i++;
			}
			if (num == 0) num = 1;
			
			while (num-- > 0) {
				ave *= (double)terms / (terms += weight);
				ave += (double)weight * (double)(pave + cur) / terms;
				cur += prev;
			}
		}
		return new double [] {ave, terms, cur};
	}
}
