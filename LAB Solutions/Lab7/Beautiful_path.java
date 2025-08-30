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

public class Beautiful_path {
    public static void main(String[]args)throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        int n=Integer.parseInt(tokens.nextToken());
        int m=Integer.parseInt(tokens.nextToken());
        int s=Integer.parseInt(tokens.nextToken());
        int d=Integer.parseInt(tokens.nextToken());
        int [] w=new int[n+1];
        ArrayList<List<Integer>> adj=new ArrayList<>();
        for(int i=0;i<=n;i++) adj.add(new LinkedList<>());

        tokens=new StringTokenizer(reader.readLine());
        for(int i=1;i<=n;i++){
            w[i]=Integer.parseInt(tokens.nextToken());
        }

        for(int i=0;i<m;i++){
            tokens=new StringTokenizer(reader.readLine());
            int u=Integer.parseInt(tokens.nextToken());
            int v=Integer.parseInt(tokens.nextToken());
            adj.get(u).add(v);
        }
        writer.println(dijkstra(adj,w,n,s,d));
        writer.close();
    }

    public static int dijkstra(ArrayList<List<Integer>>adj,int[] w,int n,int source,int destination){
        boolean [] visited=new boolean[n+1];
        int [] distance=new int[n+1];
        int [] parent=new int[n+1];
        parent[source]=-1;
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[source]=w[source];
        PriorityQueue<Pair> queue=new PriorityQueue<>((a,b)->Integer.compare(a.weight,b.weight));
        queue.offer(new Pair(source,w[source]));
        while(!queue.isEmpty()){
            Pair temp=queue.poll();
            int node=temp.value;
            if(visited[node])continue;
            visited[node]=true;
            for(int neighbor:adj.get(node)){
                if(distance[neighbor]>distance[node]+w[neighbor]){
                    distance[neighbor]=distance[node]+w[neighbor];
                    parent[neighbor]=node;
                    queue.offer(new Pair(neighbor,distance[neighbor]));
                }
            }
        }

        if(distance[destination]==Integer.MAX_VALUE) return -1;
        return distance[destination];
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
