import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Edge_queries {
    public static void main(String[]args)throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        int n=Integer.parseInt(tokens.nextToken());
        int m=Integer.parseInt(tokens.nextToken());
        int[] u=new int[m];
        int [] v=new int[m];
        int [] degree_count=new int[n+1];

        tokens=new StringTokenizer(reader.readLine());
        for(int i=0;i<m;i++ ){
            u[i]=Integer.parseInt(tokens.nextToken());
        }

        tokens=new StringTokenizer(reader.readLine());
        for(int i=0;i<m;i++ ){
            v[i]=Integer.parseInt(tokens.nextToken());
        }

        for(int i=0;i<m;i++){
            degree_count[u[i]]--;
            degree_count[v[i]]++;
        }

       for(int i=1;i<n+1;i++){
        writer.print(degree_count[i]+" ");
       }
       writer.println();
        writer.close();

    }
}
