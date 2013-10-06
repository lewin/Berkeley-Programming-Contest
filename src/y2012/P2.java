package y2012;
import java.util.*;
import java.io.*;

class P2 {
	
	private static boolean [] isPrime;
    private static int [] prime;
    private static int idx, len = 1 << 16;
    private static void generatePrimes () {
        isPrime = new boolean [len + 1];
        prime = new int [len / 2];
        isPrime [2] = true; prime [idx++] = 2;
        for (int i = 3; i <= len; i += 2) isPrime [i] = true;
        for (int i = 3; i <= len; i += 2) {
            if (isPrime [i]) {
                prime [idx++] = i;
                for (int j = i + i; j <= len; j += i)
                    isPrime [j] = false;
            }
        }
    }
	private static HashMap<Long, Integer> num, den;
	public static void main (String [] args) throws IOException {
		Scanner in = new Scanner (System.in);
		PrintWriter out = new PrintWriter (System.out, true);

		generatePrimes();
		num = new HashMap<Long, Integer> ();
		den = new HashMap<Long, Integer> ();
		while (in.hasNext()) {
			String[] frac = in.next().split ("/");
			addNum (Integer.parseInt(frac[0]), num);
			addNum (Integer.parseInt(frac[1]), den);
		}
		long na = 1, da = 1;
		for (Long v : num.keySet()) {
			if (!den.containsKey(v)) {
				int a = num.get(v);
				while (a-- > 0) na *= v;
			} else {
				int a = num.get(v), b = den.get(v);
				if (a > b) {
					int c = a - b;
					while (c-- > 0) na *= v;
				} else {
					int c = b - a;
					while (c-- > 0) da *= v;
				}
			}
			den.remove(v);
		}
		
		for (Long v : den.keySet()) {
			int a = den.get(v);
			while (a-- > 0) da *= v;
		}

		out.printf ("%d/%d\n", na, da);
	
		out.close();
		System.exit(0);
	}
	
	private static void addNum (long number, HashMap<Long, Integer> mp) {
		for (int i = 0; number > 1 && i < idx; i++) {
			while (number % prime [i] == 0) {
				number /= prime [i];
				add (prime [i], mp);
			}
		}
		if (number != 1) {
			add (number, mp);
		}
	}
	
	private static void add (long num, HashMap<Long, Integer> mp) {
		if (mp.containsKey (num)) {
			mp.put (num, mp.get (num) + 1);
		} else {
			mp.put (num, 1);
		}
	}
}
