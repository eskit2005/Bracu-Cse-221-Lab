import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Graph_methamorphosis {
    public static void main(String[]args)throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        int n=Integer.parseInt(reader.readLine());
        int [][]matrix=new int[n][n];
        for(int i=0;i<n;i++){
            StringTokenizer tokens=new StringTokenizer(reader.readLine());
            int neighbors=Integer.parseInt(tokens.nextToken());
            while(neighbors>0){
                int j=Integer.parseInt(tokens.nextToken());
                matrix[i][j]=1;
                neighbors--;
            }
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
