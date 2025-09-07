import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Arrays;

public class HelpTheKing {
    public static void main(String[] args) throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        StringTokenizer tokens=new StringTokenizer(reader.readLine());

        int n=Integer.parseInt(tokens.nextToken());
        int m=Integer.parseInt(tokens.nextToken());

        int[][] e=new int[m][3]; // edges: u, v, w
        for (int i=0;i<m;i++) {
            tokens=new StringTokenizer(reader.readLine());
            e[i][0]=Integer.parseInt(tokens.nextToken());
            e[i][1]=Integer.parseInt(tokens.nextToken());
            e[i][2]=Integer.parseInt(tokens.nextToken());
        }

        Arrays.sort(e,(a, b)->Integer.compare(a[2],b[2])); // sort by weight

        int[] par=new int[n+1];
        int[] count=new int[n+1];
        for (int i=1;i<=n;i++) {
            par[i]=i;
            count[i]=1;
        }

        long ans=0;
        for (int i=0;i<m;i++) {
            int u=e[i][0],v=e[i][1],w=e[i][2];
            if (join(u,v,par,count)) {
                ans+=w;
            }
        }

        writer.println(ans);
        writer.close();
    }

    public static int root(int x,int[] par) {
        while (x!=par[x]) {
            par[x]=par[par[x]];
            x=par[x];
        }
        return x;
    }

    public static boolean join(int a,int b,int[] par,int[] count) {
        int ra=root(a,par),rb=root(b,par);
        if (ra==rb) return false;

        if (count[ra]<count[rb]) {
            int tmp=ra;
            ra=rb;
            rb=tmp;
        }
        par[rb]=ra;
        count[ra]+=count[rb];
        return true;
    }
}

