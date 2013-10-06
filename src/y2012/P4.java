package y2012;
import java.util.*;
import java.io.*;

class P4 {
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out, true);

		String line, line2;
		while ((line = in.readLine()) != null) {
			line2 = in.readLine();
			
			out.println (calc (line.toCharArray(), line2.toCharArray()));
		}
		out.close();
		System.exit(0);
	}
	
	private static int calc (char [] a, char [] b) {
		int [][] dp = new int [a.length + 1][b.length + 1];
		for (int i = 0; i <= a.length; i++)
			Arrays.fill (dp [i], 1 << 29);
		dp [0][0] = 0;
		for (int i = 1; i <= a.length; i++) {
			dp [i][0] = i;
			for (int j = 1; j <= b.length; j++) {
				if (a [i - 1] == b [j - 1])
					dp [i][j] = dp [i - 1][j - 1];
				dp [i][j] = Math.min (dp [i][j], Math.min (dp [i - 1][j], dp [i][j - 1]) + 1);
			}
		}
		return dp [a.length][b.length];
	}
}
