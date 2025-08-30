import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Minimize_danger {
    public static void main(String[]args)throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        int n=Integer.parseInt(tokens.nextToken());
        int m=Integer.parseInt(tokens.nextToken());
        ArrayList<List<Pair>> adj=new ArrayList<>();
        for(int i=0;i<=n;i++){
            adj.add(new LinkedList<>());
        }

        for(int i=0;i<m;i++){
            tokens=new StringTokenizer(reader.readLine());
            int u=Integer.parseInt(tokens.nextToken());
            int v=Integer.parseInt(tokens.nextToken());
            int w=Integer.parseInt(tokens.nextToken());
            adj.get(u).add(new Pair(v,w));
            adj.get(v).add(new Pair(u,w));
        }

        boolean [] visited=new boolean[n+1];
        int [] danger=new int[n+1];
        dijkstra(adj,1,visited,danger);
        for(int i=1;i<=n;i++){
            writer.print(danger[i]+" ");
        }
        writer.println();
        writer.close();

    }

    public static void dijkstra(ArrayList<List<Pair>>adj,int source,boolean[] visited,int[] danger){
        Arrays.fill(danger,-1);
        PriorityQueue<Pair> queue=new PriorityQueue<>((a,b)->Integer.compare(a.weight, b.weight));
        danger[source]=0;
        queue.offer(new Pair(source,0));
        while(!queue.isEmpty()){
            Pair temp=queue.poll();
            int node=temp.value;

            if(visited[node]) continue;
            visited[node]=true;

            for(Pair neighbor:adj.get(node)){
                int neighbor_node=neighbor.value;
                int neighbor_weight=neighbor.weight;
                if(danger[neighbor_node]==-1 || danger[neighbor_node]>Integer.max(danger[node],neighbor_weight)){
                    danger[neighbor_node]=Integer.max(danger[node],neighbor_weight);
                    queue.offer(new Pair(neighbor_node, danger[neighbor_node]));

                }
            }
        }

    }
}


class Pair{
    int value;
    int weight;
    public Pair(int value,int weight){ 
        this.value=value;
        this.weight=weight;
    }
}