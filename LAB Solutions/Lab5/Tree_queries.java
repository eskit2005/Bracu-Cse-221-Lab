import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Tree_queries {
    public static void main(String[]args)throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        int n=Integer.parseInt(tokens.nextToken());
        int r=Integer.parseInt(tokens.nextToken());
        int edges=n-1;
        ArrayList<List<Integer>> adj=new ArrayList<>(n+1);
        for(int i=0;i<=n;i++){
            adj.add(new LinkedList<Integer>());
        }
        for(int i=0;i<edges;i++){
            tokens=new StringTokenizer(reader.readLine());
            int u=Integer.parseInt(tokens.nextToken());
            int v=Integer.parseInt(tokens.nextToken());
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        boolean [] visited=new boolean[n+1];int [] subtree=new int[n+1];
        Arrays.fill(subtree,0);
        dfs(adj,r,visited,subtree);
        int q=Integer.parseInt(reader.readLine());
        for(int i=0;i<q;i++){
            int u=Integer.parseInt(reader.readLine());
            writer.println(subtree[u]);
        }
        writer.close();

    }

    public static void dfs(ArrayList<List<Integer>> adj,int r,boolean[] visited,int[] subtree){
        subtree[r]=1;
        visited[r]=true;
        List<Integer> list=adj.get(r);
        for(int num:list){
            if(!visited[num]){
                dfs(adj,num,visited,subtree);
                subtree[r]+=subtree[num];
            }
        }
    }
}
