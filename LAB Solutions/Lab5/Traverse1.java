import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class Traverse1{
    public static void main(String[]args)throws Exception{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        int cities=Integer.parseInt(tokens.nextToken());//no. of vertices
        int roads=Integer.parseInt(tokens.nextToken());//no. of edges
        List<List<Integer>> adj=new ArrayList<>(cities+1);//1 based indexing
        for(int i=0;i<=cities;i++){
            adj.add(new LinkedList<>());
        }
        while(roads>0){
            tokens=new StringTokenizer(reader.readLine());
            int vertex1=Integer.parseInt(tokens.nextToken());//This is a undirected graph
            int vertex2=Integer.parseInt(tokens.nextToken());
            List<Integer> list=adj.get(vertex1);
            list.add(vertex2);
            List<Integer>list1=adj.get(vertex2);
            list1.add(vertex1);// adding edges both ways
            roads--;
        }
        bfs(adj);


    }

    public static void bfs(List<List<Integer>>adj){
        PrintWriter writer=new PrintWriter(System.out);
        boolean [] visited=new boolean[adj.size()];
        visited[1]=true;//source vertex
        Queue<Integer> queue=new LinkedList<>();
        queue.offer(1);
        while(!queue.isEmpty()){
            int u=queue.poll();
            List<Integer> list=adj.get(u);
            for(int num:list){
                if(!visited[num]){
                    visited[num]=true;
                    queue.offer(num);
                }
            }
            writer.print(u+" ");
        }
        writer.println();
        writer.close();
    }
}