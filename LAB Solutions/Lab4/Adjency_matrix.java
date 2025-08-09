import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Adjency_matrix{
    public static void main(String[]args) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        int n=Integer.parseInt(tokens.nextToken());
        int m=Integer.parseInt(tokens.nextToken());
        int[][] matrix=new int[n][n];
        while(m>0){
            tokens=new StringTokenizer(reader.readLine());
            int u=Integer.parseInt(tokens.nextToken());
            int v=Integer.parseInt(tokens.nextToken());
            int w=Integer.parseInt(tokens.nextToken());
            matrix[u-1][v-1]=w;
            m--;

        }

        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                writer.print(matrix[i][j]+ " ");
            }
            writer.println();
        }
        writer.close();
    }
}