import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;


public class Football {
    public static void main(String[]args)throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        int N=Integer.parseInt(tokens.nextToken());
        int M=Integer.parseInt(tokens.nextToken());
        ArrayList<List<Integer>> adj=new ArrayList<>(N+1);
        for(int i=0;i<=N;i++){
            adj.add(new LinkedList<>());
        }
    
        for(int i=0;i<M;i++){
            tokens=new StringTokenizer(reader.readLine());
            int u=Integer.parseInt(tokens.nextToken());
            int v=Integer.parseInt(tokens.nextToken());
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        bfs(adj, N);

    }


    public static void bfs(ArrayList<List<Integer>>adj,int N){
        int highest_count=0;
        boolean [] visited=new boolean[N+1];
        int [] color=new int[N+1];
        for(int i=1;i<=N;i++){ // The graph could be disconnected so we need to run bfs as long 
                               // there are unvisited vertex
            if(!visited[i]){
                highest_count+=bi_partite(adj, i, color, visited);
            }
        }
        PrintWriter writer=new PrintWriter(System.out);
        writer.println(highest_count);
        writer.close();
    }

    public static int bi_partite(ArrayList<List<Integer>>adj,int source,int [] color,boolean [] visited){
        int humans=0,robots=0;
        Queue<Integer> queue=new LinkedList<>();
        queue.offer(source);
        visited[source]=true;
        color[source]=0;
        while(!queue.isEmpty()){
            int u=queue.poll();

            if(color[u]==0)humans++;
            else robots++;

            List<Integer> list=adj.get(u);
            for(int num:list){
                if(!visited[num]){
                    visited[num]=true;
                    color[num]=1-color[u];
                    queue.offer(num);
                }
            }
        }
        return Math.max(humans,robots);
    }
}
