import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Again_MST {
    static final int LOG = 10;
    static final int NEG = -1;
    static int[][] parent;
    static int[][] mx1;
    static int[][] mx2;
    static int[] depth;
    static ArrayList<List<Pair>> mst_adj;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        StringTokenizer tokens = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokens.nextToken());
        int M = Integer.parseInt(tokens.nextToken());
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(reader.readLine());
            int u = Integer.parseInt(tokens.nextToken());
            int v = Integer.parseInt(tokens.nextToken());
            int w = Integer.parseInt(tokens.nextToken());
            edges.add(new Edge(u, v, w));
        }

        edges.sort(Edge::compareTo);

        UnionFind uf = new UnionFind(N + 1);
        long mst_cost = 0;
        List<Edge> mst_edges = new ArrayList<>();
        for (Edge e : edges) {
            if (uf.find(e.u) != uf.find(e.v)) {
                uf.union(e.u, e.v);
                mst_cost += e.w;
                mst_edges.add(e);
            }
        }

        if (mst_edges.size() < N - 1) {
            writer.println(-1);
            writer.close();
            return;
        }

        mst_adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            mst_adj.add(new LinkedList<>());
        }
        for (Edge e : mst_edges) {
            mst_adj.get(e.u).add(new Pair(e.v, e.w));
            mst_adj.get(e.v).add(new Pair(e.u, e.w));
        }

        parent = new int[LOG][N + 1];
        mx1 = new int[LOG][N + 1];
        mx2 = new int[LOG][N + 1];
        depth = new int[N + 1];
        for (int[] arr : parent) Arrays.fill(arr, -1);
        for (int[] arr : mx1) Arrays.fill(arr, NEG);
        for (int[] arr : mx2) Arrays.fill(arr, NEG);

        dfs(1, -1, 0, 0);

        for (int k = 1; k < LOG; k++) {
            for (int i = 1; i <= N; i++) {
                int anc = parent[k - 1][i];
                if (anc != -1) {
                    parent[k][i] = parent[k - 1][anc];
                    int[] merged = merge(mx1[k - 1][i], mx2[k - 1][i], mx1[k - 1][anc], mx2[k - 1][anc]);
                    mx1[k][i] = merged[0];
                    mx2[k][i] = merged[1];
                }
            }
        }

        HashSet<String> mst_set = new HashSet<>();
        for (Edge e : mst_edges) {
            int a = Math.min(e.u, e.v);
            int b = Math.max(e.u, e.v);
            mst_set.add(a + " " + b);
        }

        long second = Long.MAX_VALUE;
        boolean found = false;
        for (Edge e : edges) {
            int a = Math.min(e.u, e.v);
            int b = Math.max(e.u, e.v);
            if (mst_set.contains(a + " " + b)) continue;
            int[] top = get_top2_on_path(e.u, e.v);
            int max_1 = top[0];
            int max_2 = top[1];
            int to_remove = (max_1 < e.w) ? max_1 : max_2;
            if (to_remove == NEG) continue;
            long cand = mst_cost - to_remove + e.w;
            if (cand > mst_cost) {
                second = Math.min(second, cand);
                found = true;
            }
        }

        if (found) {
            writer.println(second);
        } else {
            writer.println(-1);
        }
        writer.close();
    }

    static void dfs(int node, int par, int dep, int edge_w) {
        depth[node] = dep;
        if (par != -1) {
            parent[0][node] = par;
            mx1[0][node] = edge_w;
            mx2[0][node] = NEG;
        }
        for (Pair p : mst_adj.get(node)) {
            if (p.value != par) {
                dfs(p.value, node, dep + 1, p.weight);
            }
        }
    }

    static int get_lca(int u, int v) {
        if (depth[u] > depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        int diff = depth[v] - depth[u];
        for (int k = 0; k < LOG; k++) {
            if ((diff & (1 << k)) != 0) {
                v = parent[k][v];
            }
        }
        if (u == v) return u;
        for (int k = LOG - 1; k >= 0; k--) {
            if (parent[k][u] != parent[k][v]) {
                u = parent[k][u];
                v = parent[k][v];
            }
        }
        return parent[0][u];
    }

    static int[] get_top2_to_anc(int x, int target_depth) {
        int[] top = new int[]{NEG, NEG};
        int diff = depth[x] - target_depth;
        for (int k = 0; k < LOG; k++) {
            if ((diff & (1 << k)) != 0) {
                top = merge(top[0], top[1], mx1[k][x], mx2[k][x]);
                x = parent[k][x];
            }
        }
        return top;
    }

    static int[] get_top2_on_path(int u, int v) {
        int l = get_lca(u, v);
        int[] top1 = (depth[u] > depth[l]) ? get_top2_to_anc(u, depth[l]) : new int[]{NEG, NEG};
        int[] top2 = (depth[v] > depth[l]) ? get_top2_to_anc(v, depth[l]) : new int[]{NEG, NEG};
        return merge(top1[0], top1[1], top2[0], top2[1]);
    }

    private static int[] update(int[] res, int t) {
        int r1 = res[0];
        int r2 = res[1];
        if (t > r1) {
            res[0] = t;
            res[1] = r1;
        } else if (t < r1 && t > r2) {
            res[1] = t;
        }
        return res;
    }

    private static int[] merge(int a1, int a2, int b1, int b2) {
        int[] res = new int[]{NEG, NEG};
        res = update(res, a1);
        res = update(res, a2);
        res = update(res, b1);
        res = update(res, b2);
        return res;
    }

    static class Edge implements Comparable<Edge> {
        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }

    static class Pair {
        int value;
        int weight;

        public Pair(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }

    static class UnionFind {
        int[] parent;
        int[] rank;

        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        void union(int x, int y) {
            int px = find(x), py = find(y);
            if (px == py) return;
            if (rank[px] < rank[py]) {
                parent[px] = py;
            } else {
                parent[py] = px;
                if (rank[px] == rank[py]) rank[px]++;
            }
        }
    }
}