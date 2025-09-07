import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Friendship {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        StringTokenizer tokens = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(tokens.nextToken());
        int k = Integer.parseInt(tokens.nextToken());

        int[] par = new int[n + 1];  // parent of each node
        int[] cnt = new int[n + 1];  // size of the set

        // initially each person is their own parent and size = 1
        for (int i = 1; i <= n; i++) {
            par[i] = i;
            cnt[i] = 1;
        }

        // process friendships one by one
        while (k-- > 0) {
            tokens = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());

            int ans = join(a, b, par, cnt);
            writer.println(ans);
        }

        writer.close(); // close instead of flush
    }

    // find representative of x with path compression
    static int root(int x, int[] par) {
        while (x != par[x]) {
            par[x] = par[par[x]]; // flatten tree
            x = par[x];
        }
        return x;
    }

    // union two sets, return size of merged circle
    static int join(int a, int b, int[] par, int[] cnt) {
        int ra = root(a, par);
        int rb = root(b, par);

        if (ra == rb) return cnt[ra]; // already in same group

        // union by size: attach smaller set to bigger one
        if (cnt[ra] < cnt[rb]) {
            int tmp = ra;
            ra = rb;
            rb = tmp;
        }

        par[rb] = ra;          // make ra the parent
        cnt[ra] += cnt[rb];    // increase group size
        return cnt[ra];
    }
}
