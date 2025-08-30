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

public class Parity_edge {
    public static void main(String[]args)throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        int n=Integer.parseInt(tokens.nextToken());
        int m=Integer.parseInt(tokens.nextToken());
        ArrayList<List<Pair>> adj=new ArrayList<>();
        int [] u=new int[m];
        int [] v=new int[m];
        int [] w=new int[m];
        tokens=new StringTokenizer(reader.readLine());
        for(int i=0;i<m;i++){
            u[i]=Integer.parseInt(tokens.nextToken());
        }

        tokens=new StringTokenizer(reader.readLine());
        for(int i=0;i<m;i++){
            v[i]=Integer.parseInt(tokens.nextToken());
        }

        tokens=new StringTokenizer(reader.readLine());
        for(int i=0;i<m;i++){
            w[i]=Integer.parseInt(tokens.nextToken());
        }

        for(int i=0;i<=n;i++){
            adj.add(new LinkedList<>());
        }

        for(int i=0;i<m;i++){
            adj.get(u[i]).add(new Pair(v[i],w[i]));
        }

        int  distance=disjkstra(adj, n, 1);
        writer.println(distance);
        writer.close();

    }

    public static int  disjkstra(ArrayList<List<Pair>> adj,int n,int source){
        boolean [][] visited=new boolean[n+1][2];
        int [][] distance=new int[n+1][2];
        PriorityQueue<Pair> queue=new PriorityQueue<>((a,b)->Integer.compare(a.weight,b.weight));
        for(int i=0;i<=n;i++)Arrays.fill(distance[i],Integer.MAX_VALUE);
        distance[source][0]=0;
        distance[source][1]=0;
        queue.offer(new Pair(source,0,0));
        queue.offer(new Pair(source,0,1));
        while(!queue.isEmpty()){
            Pair temp=queue.poll();
            int node=temp.value;
            int parity=temp.parity;
            if(visited[node][parity])continue;
            visited[node][parity]=true;
            for(Pair neighbor:adj.get(node)){
                int neighbor_node=neighbor.value;
                int neighbor_weight=neighbor.weight;
                int neighbor_parity=neighbor_weight%2;
                if(distance[neighbor_node][neighbor_parity]>distance[node][parity]+neighbor_weight && (parity!=neighbor_parity)){

                    distance[neighbor_node][neighbor_parity]=distance[node][parity]+neighbor_weight;
                    queue.offer(new Pair(neighbor_node,distance[neighbor_node][neighbor_parity],neighbor_parity));
                }
            }
        }
        if(!visited[n][0] && !visited[n][1]) return -1;
        return Integer.min(distance[n][0],distance[n][1]);
    }

}

class Pair{
    int value;
    Integer parity=null;
    int weight;
    public Pair(int value,int weight){ // this class is used for both adjency matrix and priority queue with seperate pair.
        this.value=value;
        this.weight=weight;
    }
    public Pair(int value,int weight,Integer parity){
        this.value=value;
        this.weight=weight;
        this.parity=parity;
    }
}
