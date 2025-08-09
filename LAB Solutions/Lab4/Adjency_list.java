
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Adjency_list {
    public static void main(String[]args) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        int n=Integer.parseInt(tokens.nextToken());
        int m=Integer.parseInt(tokens.nextToken());
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

        ArrayList<List<Pair>> adjency=new ArrayList<>(n+1);// node starts from 1
        for (int i = 0; i < n+1; i++) { 
            adjency.add(new LinkedList<Pair>());
        }
        for(int i=0;i<m;i++){
            List<Pair> list=adjency.get(u[i]);
            list.add(new Pair(v[i],w[i]));
        }

        for(int i=1;i<n+1;i++) {    // starts from node 1 hence why n+1;
            writer.print(i+": ");
            List<Pair>list=adjency.get(i);
            for(Pair pair:list){
                writer.print("("+pair.node_value+","+pair.weight+") ");
            }
            writer.println();
        }

        writer.close();
    }
}

class Pair{
    public int node_value;
    public int weight;
    public Pair(int node_value,int weight){
        this.node_value=node_value;
        this.weight=weight;
    }
}
