import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Lighting_Mcqueen {
    public static void main(String[]args) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        int N=Integer.parseInt(tokens.nextToken());
        int M=Integer.parseInt(tokens.nextToken());
        int S=Integer.parseInt(tokens.nextToken());
        int D=Integer.parseInt(tokens.nextToken());

        int[] u=new int[M];
        int [] v=new int[M];
        tokens=new StringTokenizer(reader.readLine());
        for(int i=0;i<M;i++){
            u[i]=Integer.parseInt(tokens.nextToken());
        }

        tokens=new StringTokenizer(reader.readLine());
        for(int i=0;i<M;i++){
            v[i]=Integer.parseInt(tokens.nextToken());
        }

        ArrayList<List<Integer>> adj=new ArrayList<>(N+1);
        for(int i=0;i<=N;i++){
            adj.add(new LinkedList<Integer>());
        }

        while(M>0){
            int vertex1=u[M-1];
            int vertex2=v[M-1];
            List<Integer> list=adj.get(vertex1);
            list.add(vertex2);
            list=adj.get(vertex2);
            list.add(vertex1);
            M--;
        }

        for(int i=1;i<=N;i++){
            Collections.sort(adj.get(i));//to ensure we get the sorted 
                                        //shortest distance path
        }

        shortest_path(adj, N, S, D);

    }


    public static void shortest_path(ArrayList<List<Integer>> adj,int n,int source,int des){
        PrintWriter writer=new PrintWriter(System.out);
        boolean [] visited=new boolean[n+1];
        Integer [] parent=new Integer[n+1];
        Queue<Integer> queue=new LinkedList<>();
        visited[source]=true;
        parent[source]=null;
        queue.offer(source);
        while(!queue.isEmpty()){
            int u=queue.poll();
            List<Integer>list=adj.get(u);
            for(int num:list){
                if(!visited[num]){
                    visited[num]=true;
                    parent[num]=u;
                    queue.offer(num);
                }
            }
        }
        if(des==source){// if destination and source is the same
            writer.println(0);
            writer.println(1); 
            writer.close();
            return;
        }
        if(!visited[des]){//no path found
            writer.println(-1);
            writer.close();
            return;
        }

        ArrayList<Integer> path=new ArrayList<>();
        for(Integer temp=des;temp!=null;temp=parent[temp]){//backtracking till source vertex
            path.addFirst(temp);
        }
        writer.println(path.size()-1);
        for(Integer num:path){
            writer.print(num+" ");
        }
        writer.println();
        writer.close();
    }
}
