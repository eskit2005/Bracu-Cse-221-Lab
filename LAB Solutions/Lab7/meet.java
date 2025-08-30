import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class meet {
    public static void main(String[]args) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        int n=Integer.parseInt(tokens.nextToken());
        int m=Integer.parseInt(tokens.nextToken());
        int s=Integer.parseInt(tokens.nextToken());
        int t=Integer.parseInt(tokens.nextToken());
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
        }

        int [] distance1=dijkstra(adj, n, s);
        int [] distance2=dijkstra(adj,n,t);
        int smallest_distance=Integer.MAX_VALUE;
        int smallest_index=-1;
        for(int i=1;i<=n;i++){
            if(distance1[i]==Integer.MAX_VALUE || distance2[i]==Integer.MAX_VALUE) continue;

            int temp=Integer.max(distance1[i],distance2[i]);
            if(temp<smallest_distance) {
                smallest_distance=temp;
                smallest_index=i;
            }

        }

        if(smallest_index==-1) {
            writer.println(-1);
            writer.close();
            return;
        }

        writer.println(smallest_distance+" "+smallest_index);
        writer.close();
    }

    public static int[]  dijkstra(ArrayList<List<Pair>> adj,int n,int source){
         boolean [] visited=new boolean[n+1];
         int [] distance=new int[n+1];
         PriorityQueue<Pair> queue=new PriorityQueue<>((a,b)->Integer.compare(a.weight,b.weight));
         Arrays.fill(distance,Integer.MAX_VALUE);
         distance[source]=0;
         queue.offer(new Pair(source,0));

         while(!queue.isEmpty()){
            Pair temp=queue.poll();
            int node=temp.value;

            if(visited[node]) continue;
            visited[node]=true;
            for(Pair neighbor:adj.get(node)){
                int neighbor_node=neighbor.value;
                int neighbor_weight=neighbor.weight;
                if(distance[neighbor_node]>distance[node]+neighbor_weight){
                    distance[neighbor_node]=distance[node]+neighbor_weight;
                    queue.offer(new Pair(neighbor_node,distance[neighbor_node]));
                }
            }
        }
        return distance;
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
