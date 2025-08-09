import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class CoPrime_graph {
    // gcd 
    public static int gcd(int a,int b){
        while(b!=0){
            int temp=b;
            b=a%b;
            a=temp;
        }
        return a;
    }

    public static void main(String[] args)throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        
        int nodeCount=Integer.parseInt(tokens.nextToken());
        int queryCount=Integer.parseInt(tokens.nextToken());

        ArrayList<Integer>[] adjacency=new ArrayList[nodeCount + 1];
        for(int i=1;i<=nodeCount;i++){
            adjacency[i]=new ArrayList<>();
        }

        for(int i=1;i<=nodeCount;i++){
            for(int j=i+1;j<=nodeCount;j++){
                if(gcd(i,j)==1){
                    adjacency[i].add(j);
                    adjacency[j].add(i);
                }
            }
        }


        for(int q=0; q<queryCount;q++){
            tokens=new StringTokenizer(reader.readLine());
            int X=Integer.parseInt(tokens.nextToken());
            int K=Integer.parseInt(tokens.nextToken());

            if(K<=adjacency[X].size()){
                writer.println(adjacency[X].get(K-1));
            } else {
                writer.println(-1);
            }
        }

        writer.close();
    }
}
