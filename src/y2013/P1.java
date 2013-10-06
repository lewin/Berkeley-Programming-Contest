package y2013;
import java.util.*;
import java.io.*;

class P1 {
    private static Scanner in;
    public static void main (String [] args) throws IOException {
        in = new Scanner (System.in);
        PrintWriter out = new PrintWriter (System.out, true);
        
        while (in.hasNext()) {
            int N = in.nextInt();
            int cx = in.nextInt(),
                cy = in.nextInt(),
                cz = in.nextInt();
            int len = in.nextInt();
            int ex = cx + len, ey = cy + len, ez = cz + len;
            boolean ok = true;
            for (int i = 1; i < N; i++) {
                int nx = in.nextInt(), ny = in.nextInt(), nz = in.nextInt(), nl = in.nextInt();
                int px = nx + nl, py = ny + nl, pz = nz + nl;
                if (px < cx || ex < nx || py < cy || ey < ny || pz < cz || ez < nz)
                    ok = false;
                
                cx = Math.max (nx, cx);
                ex = Math.min (ex, px);
                cy = Math.max (ny, cy);
                ey = Math.min (ey, py);
                cz = Math.max (nz, cz);
                ez = Math.min (ez, pz);
            }
            if (!ok) {
                out.println (0);
                continue;
            }
            out.println ((ex - cx) * (ey - cy) * (ez - cz));
        }
        out.close();
        System.exit(0);
    }
}
