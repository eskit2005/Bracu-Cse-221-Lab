import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Shortest_path {
    public static void main(String[]args)throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        int n=Integer.parseInt(tokens.nextToken());
        int m=Integer.parseInt(tokens.nextToken());
        int s=Integer.parseInt(tokens.nextToken());
        int d=Integer.parseInt(tokens.nextToken());
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

        boolean [] visited=new boolean[n+1];
        int [] parent=new int[n+1];
        int [] distance=new int[n+1];
        ArrayList<Integer>path=dijkstra(adj,s, d,visited,distance,parent);
        if(distance[d]==Integer.MAX_VALUE){ // destination is not reachable from source
            writer.println(-1);
            writer.close();return;
        }

        writer.println(distance[d]);
        for(int num:path){
            writer.print(num+" ");
        }
        writer.println();
        writer.close();

    }

    public static ArrayList<Integer> dijkstra(ArrayList<List<Pair>>adj,int source,int destination,boolean[] visited,int[] distance,int [] parent){
        Arrays.fill(distance,Integer.MAX_VALUE);
        PriorityQueue<Pair> queue=new PriorityQueue<>((a,b)->Integer.compare(a.weight, b.weight));//the pair with the smallest weight instace variable will be at the top of the minheap or priority queue
        distance[source]=0;
        parent[source]=-1;
        queue.offer(new Pair(source,0));//creating a source node with distance zero
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
                    parent[neighbor_node]=node;
                    queue.offer(new Pair(neighbor_node, distance[neighbor_node]));//creating a node with updated distance

                }
            }
        }

        ArrayList<Integer> path=new ArrayList<>();
        if(!visited[destination]) {//when destination is not reachable from source
            path.add(-1);
            return path;
        }
        for(int i=destination;i!=-1;i=parent[i]){
            path.add(i);
        }
        Collections.reverse(path);
        return path;

    }
}


class Pair{
    int value;
    int weight;
    public Pair(int value,int weight){ // this class is used for both adjency matrix and priority queue with seperate pair.
        this.value=value;
        this.weight=weight;
    }
}