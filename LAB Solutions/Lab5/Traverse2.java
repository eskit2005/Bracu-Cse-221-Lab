import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Traverse2 {
    public static void main(String[]args)throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        int cities=Integer.parseInt(tokens.nextToken());
        int roads=Integer.parseInt(tokens.nextToken());
        List<List<Integer>> adj=new ArrayList<>(cities+1);//1 based indexing
        for(int i=0;i<=cities;i++){
            adj.add(new LinkedList<>());
        }
        int [] u=new int[roads];
        int [] v=new int[roads];
        tokens=new StringTokenizer(reader.readLine());
        for(int i=0;i<roads;i++){
            u[i]=Integer.parseInt(tokens.nextToken());
        }
        tokens=new StringTokenizer(reader.readLine());
        for(int i=0;i<roads;i++){
            v[i]=Integer.parseInt(tokens.nextToken());
        }

        for(int i=0;i<roads;i++){
            int vertex1=u[i];
            int vertex2=v[i];
            List<Integer> list=adj.get(vertex1);
            list.add(vertex2);
            List<Integer> list1=adj.get(vertex2);
            list1.add(vertex1);
        }

        
        dfs(adj,cities);

    }

    public static void dfs(List<List<Integer>> adj,int cities){
        boolean [] visited=new boolean[cities+1];
        PrintWriter writer=new PrintWriter(System.out);
        int source=1;
        Stack<Integer> stack=new Stack<>();
        stack.push(source);
        while(!stack.isEmpty()){
            int u=stack.pop();
            if(!visited[u]){
                visited[u]=true;
                writer.print(u+ " ");
                List<Integer> list=adj.get(u);
                for(int num:list){
                    if(!visited[num]){
                        stack.push(num);
                    }
                }
            }
        }

        writer.println();
        writer.close();
        
    }
}
