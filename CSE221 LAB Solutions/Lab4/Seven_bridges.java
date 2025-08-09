import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Seven_bridges {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);

        StringTokenizer tokens = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokens.nextToken());
        int m = Integer.parseInt(tokens.nextToken());

        int[] u = new int[m];
        int[] v = new int[m];
        int[] degree_count = new int[n + 1];

        tokens = new StringTokenizer(reader.readLine());
        for (int i = 0; i < m; i++) {
            u[i] = Integer.parseInt(tokens.nextToken());
        }

        tokens = new StringTokenizer(reader.readLine());
        for (int i = 0; i < m; i++) {
            v[i] = Integer.parseInt(tokens.nextToken());
        }

        for (int i = 0; i < m; i++) {
            degree_count[u[i]]++;
            degree_count[v[i]]++;
        }

        int oddDegreeCount = 0;
        for (int i = 1; i <= n; i++) {
            if (degree_count[i] % 2 != 0) {
                oddDegreeCount++;
            }
        }

        if (oddDegreeCount == 0 || oddDegreeCount == 2) {
            writer.println("YES");
        } else {
            writer.println("NO");
        }

        writer.close();
    }
}
