import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Advising{
    public static void main(String[]args)throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        int N=Integer.parseInt(tokens.nextToken());
        int M=Integer.parseInt(tokens.nextToken());
        ArrayList<List<Integer>> adj=new ArrayList<>(N+1);
        for(int i=0;i<=N;i++){
            adj.add(new LinkedList<>());
        }
        for(int i=0;i<M;i++){
            tokens=new StringTokenizer(reader.readLine());
            int u=Integer.parseInt(tokens.nextToken());
            int v=Integer.parseInt(tokens.nextToken());
            adj.get(u).add(v);
        }
        Stack<Integer> stack=dfs(adj, N);
        while(!stack.isEmpty()){
            writer.print(stack.pop()+" ");
        }
        writer.println();
        writer.close();


    }

    public static Stack<Integer> dfs(ArrayList<List<Integer>> adj,int N){
        String [] color=new String[N+1];
        Arrays.fill(color,"white");
        Stack<Integer> stack=new Stack<>();
        boolean has_cycle=false;
        for(int i=1;i<=N && !has_cycle;i++){
            if(color[i].equals("white")){
                has_cycle=dfs_topo(adj, color, stack, i);
            }
        }
        if(!has_cycle) return stack;
        
        stack.clear();
        stack.push(-1);
        return stack;

    }


    public static  boolean dfs_topo(ArrayList<List<Integer>>adj,String[] color,Stack<Integer> stack,int source){
        color[source]="grey";
        List<Integer> list=adj.get(source);
        for(int num:list){
            if(color[num].equals("white")){
                dfs_topo(adj, color, stack, num);
            }
            if(color[num].equals("grey")){
                return true;
            }
        }
        color[source]="black";
        stack.push(source);
        return  false;
    }
}