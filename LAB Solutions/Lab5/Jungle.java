import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.List;
import java.util.Queue;

public class Jungle {
    public static void main(String[]args)throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        int n=Integer.parseInt(tokens.nextToken());
        int m=Integer.parseInt(tokens.nextToken());
        int s=Integer.parseInt(tokens.nextToken());
        int d=Integer.parseInt(tokens.nextToken());
        int k=Integer.parseInt(tokens.nextToken());
        ArrayList<List<Integer>> adj=new ArrayList<>(n+1);
        for(int i=0;i<=n;i++){
            adj.add(new LinkedList<Integer>());
        }
        while(m>0){
            tokens=new StringTokenizer(reader.readLine());
            int u=Integer.parseInt(tokens.nextToken());
            int v=Integer.parseInt(tokens.nextToken());
            adj.get(u).add(v);
            m--;
        }
        ArrayList<Integer> path1=shortest_path(adj, s, k, n);
        ArrayList<Integer> path2=shortest_path(adj, k, d, n);
        if(path1.get(0)==-1 || path2.get(0)==-1){
            writer.println(-1); 
            writer.close();
        }

        writer.println(path1.size()+path2.size()-1-1);
        for(int num:path1) writer.print(num+" ");
        for(int num:path2){
            if(num!=k){
                writer.print(num+" ");
            }
        }
        writer.println();
        writer.close();

    }

    public static ArrayList<Integer> shortest_path(ArrayList<List<Integer>> adj,int source,int des,int n){
        Integer [] parent=new Integer[n+1];
        boolean [] visited=new boolean[n+1];
        Queue<Integer> queue=new LinkedList<>();
        visited[source]=true;
        queue.offer(source);
        while(!queue.isEmpty()){
            int u=queue.poll();
            List<Integer> list=adj.get(u);
            for(int num:list){
                if(!visited[num]){
                    visited[num]=true;
                    parent[num]=u;
                    queue.offer(num);
                }
            }
        }
        ArrayList<Integer> path=new ArrayList<>();
        if(source==des){
            path.add(source); return path;
        }
        if(!visited[des]){
            path.add(-1); return path;
        }

        for(Integer temp=des;temp!=null;temp=parent[temp]){
            path.add(temp);
        }
        Collections.reverse(path);
        return path;
    }
}
