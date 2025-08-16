import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Cycle {
    public static void main(String[]args)throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        int n=Integer.parseInt(tokens.nextToken());
        int m=Integer.parseInt(tokens.nextToken());
        ArrayList<List<Integer>> adj=new ArrayList<>(n+1);
        for(int i=0;i<=n;i++){
            adj.add(new LinkedList<>());
        }
        for(int i=0;i<m;i++){
            tokens=new StringTokenizer(reader.readLine());
            int u=Integer.parseInt(tokens.nextToken());
            int v=Integer.parseInt(tokens.nextToken());
            adj.get(u).add(v);
        }

        writer.println(has_Cycle(adj,n) ? "YES": "NO");
        writer.close();

    }

    public static boolean has_Cycle(ArrayList<List<Integer>> adj,int n){
        String [] color=new String[n+1];
        boolean cycle=false;
        Arrays.fill(color,"white");
        for(int i=1;i<=n && !cycle;i++){
            if(color[i].equals("white")){
                cycle=Cycle_dfs(adj, color, i);

            }
        }
        return cycle;
    }

    public static boolean Cycle_dfs(ArrayList<List<Integer>>adj,String[] color,int source){
        color[source]="grey";
        List<Integer> list=adj.get(source);
        for(int num:list){
            if(color[num].equals("white")){
                Cycle_dfs(adj, color, num);
            }
            if(color[num].equals("grey")){
                return true;
            }
        }
        color[source]="black";
        return false;
    }
}
