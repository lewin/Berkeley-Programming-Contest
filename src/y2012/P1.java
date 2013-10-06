package y2012;
import java.io.*;
import java.util.*;

class P1 {

	private static HashMap<String, Integer> mp;
	private static int idx, K = 150;
	public static void main (String [] args) throws IOException {
		Reader in = new Reader();
		PrintWriter out = new PrintWriter (System.out);
		
		int t = 0;
		while (true) {
			int N = in.nextInt();
			if (N == 0) break;
			
			mp = new HashMap<String, Integer>();
			idx = 0;
			int [][] dist = new int [K][K];
			for (int i = 0; i < K; i++) {
				Arrays.fill (dist [i], 1 << 29);
				dist [i][i] = 0;
			}
			
			for (int i = 0; i < N; i++) {
				int a = getIdx(in.next()), b = getIdx(in.next());
				int c = in.nextInt();
				dist [a][b] = dist [b][a] = c;
			}
			
			for (int k = 0; k < idx; k++)
				for (int i = 0; i < idx; i++)
					for (int j = 0; j < idx; j++)
						if (dist [i][k] + dist [k][j] < dist [i][j])
							dist [i][j] = dist [i][k] + dist [k][j];
			
			int ans = 0;
			for (int i = 0; i < idx; i++)
				for (int j = 0; j < idx; j++)
					if (dist [i][j] > ans)
						ans = dist [i][j];
			
			out.printf ("Set #%d: %d miles\n", ++t, ans);
		}
		out.close();
		System.exit (0);
	}
	
	private static int getIdx (String s) {
		if (mp.containsKey(s)) {
			return mp.get(s);
		} else {
			mp.put (s, idx++);
			return idx - 1;
		}
	}

	/** Faster input **/
	static class Reader {
		final private int			BUFFER_SIZE	= 1 << 16;
		
		private DataInputStream	din;
		private byte []			buffer;
		private int					bufferPointer, bytesRead;
		
		public Reader () {
			din = new DataInputStream (System.in);
			buffer = new byte [BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}
		
		public Reader (String file_name) throws IOException {
			din = new DataInputStream (new FileInputStream (file_name));
			buffer = new byte [BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}
		
		public String readLine () throws IOException {
			byte [] buf = new byte [64]; // line length
			int cnt = 0, c;
			while ((c = read ()) != -1) {
				if (c == '\n')
					break;
				buf[cnt++] = (byte) c;
			}
			return new String (buf, 0, cnt);
		}
		
		public String next () throws IOException {
			byte [] buf = new byte [64];
			int cnt = 0; byte c = read();
			while (c <= ' ') c = read();
			do {
				if (c <= ' ') break;
				buf [cnt++] = c;
			} while ((c = read()) != -1);
			return new String (buf, 0, cnt);
		}
		
		public int nextInt () throws IOException {
			int ret = 0;
			byte c = read ();
			while (c <= ' ')
				c = read ();
			boolean neg = c == '-';
			if (neg)
				c = read ();
			do {
				ret = ret * 10 + c - '0';
				c = read ();
			} while (c >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}
		
		public long nextLong () throws IOException {
			long ret = 0;
			byte c = read ();
			while (c <= ' ')
				c = read ();
			boolean neg = c == '-';
			if (neg)
				c = read ();
			do {
				ret = ret * 10 + c - '0';
				c = read ();
			} while (c >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}
		
		public double nextDouble () throws IOException {
			double ret = 0, div = 1;
			byte c = read ();
			while (c <= ' ')
				c = read ();
			boolean neg = c == '-';
			if (neg)
				c = read ();
			do {
				ret = ret * 10 + c - '0';
				c = read ();
			} while (c >= '0' && c <= '9');
			if (c == '.')
				while ((c = read ()) >= '0' && c <= '9') {
					div *= 10;
					ret = ret + (c - '0') / div;
				}
			if (neg)
				return -ret;
			return ret;
		}
		
		private void fillBuffer () throws IOException {
			bytesRead = din.read (buffer, bufferPointer = 0, BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}
		
		private byte read () throws IOException {
			if (bufferPointer == bytesRead)
				fillBuffer ();
			return buffer[bufferPointer++];
		}
		
		public void close () throws IOException {
			if (din == null)
				return;
			din.close ();
		}
	}

}
