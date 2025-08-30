import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;;

public class Shortest_path_revisit {
    public static void main(String[]args)throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        int n=Integer.parseInt(tokens.nextToken());
        int m=Integer.parseInt(tokens.nextToken());
        int s=Integer.parseInt(tokens.nextToken());
        int d=Integer.parseInt(tokens.nextToken());
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

        writer.println(dijkstra(adj, n, s, d));
        writer.close();
    }

    public static int dijkstra(ArrayList<List<Pair>> adj,int n,int source,int destination){
        int [] distance1=new int[n+1];
        int [] distance2=new int[n+1];
        PriorityQueue<Pair> queue=new PriorityQueue<>((a,b)->Integer.compare(a.weight,b.weight));
        Arrays.fill(distance1,Integer.MAX_VALUE);
        Arrays.fill(distance2,Integer.MAX_VALUE);
        distance1[source]=0;
        queue.offer(new Pair(source,0));
        while(!queue.isEmpty()){
            Pair temp=queue.poll();
            int node=temp.value;
            int weight=temp.weight;
            if(distance2[node]<weight) continue;

            for(Pair neighbor:adj.get(node)){
                int neighbor_node=neighbor.value;
                int temp_distance=neighbor.weight+weight;
                if(temp_distance<distance1[neighbor_node]){
                    distance2[neighbor_node]=distance1[neighbor_node];
                    distance1[neighbor_node]=temp_distance;
                    queue.offer(new Pair(neighbor_node,temp_distance));
                }
                else if(temp_distance<distance2[neighbor_node]){
                    distance2[neighbor_node]=temp_distance;
                    queue.offer(new Pair(neighbor_node,temp_distance));
                }
            }
        }

        if(distance2[destination]==Integer.MAX_VALUE)return -1;
        return distance2[destination];
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
