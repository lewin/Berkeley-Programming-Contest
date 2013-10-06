package y2012;
import java.util.*;
import java.io.*;

class P8 {
	public static void main (String [] args) throws IOException {
		Scanner in = new Scanner (System.in);
		PrintWriter out = new PrintWriter (System.out, true);

		while (in.hasNext()) {
			long N = in.nextLong();
			out.printf ("%d is number %d in the sequence of numbers with %d 1-bits.\n", N, solve(N), Long.bitCount(N));
		}
		out.flush();
		out.close();
		System.exit(0);
	}
	
	private static long solve (long N) {
		long ans = 0;
		long low = 1;
		while (N > 0) {
			long k = N & -N;
			ans += comb (Long.numberOfTrailingZeros(k), low++);
			N -= k;
		}
		return ans;
	}
	
	private static long comb (long n, long k) {
		if (k > n) return 0;
        long a = Math.min (k, n - k);
        long res = 1;
        for (long i = 1; i <= a; i++) {
            res *= n--; res /= i;
        }
        return res;
    }
}
