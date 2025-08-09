import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Fast_matrix_drift {
    public static void main(String[]args) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        int cases=Integer.parseInt(reader.readLine());
        while(cases>0){
            StringTokenizer tokens=new StringTokenizer(reader.readLine());
            long [][]matrix=new long[2][2];
            for(int i=0;i<matrix.length;i++){
                for(int j=0;j<matrix[i].length;j++){
                    matrix[i][j]=Long.parseLong(tokens.nextToken());
                }
            }
            long x=Long.parseLong(reader.readLine());
            long[][]identity={{1,0},{0,1}};
            matrix=fast_matrix_power(identity, x, matrix);
            for(int i=0;i<matrix.length;i++){
                for(int j=0;j<matrix[i].length;j++){
                    writer.print(matrix[i][j]+ " ");
                }
                writer.println();
            }

            cases--;
        }
        writer.close();


        


    }

    public  static long[][] multiply(long[][] a,long[][] b) {
        long mod=((long)Math.pow(10,9))+7;
        long first=(a[0][0]*b[0][0]+a[0][1]*b[1][0])%mod;
        long second=(a[0][0]*b[0][1]+a[0][1]*b[1][1])%mod;
        long third=(a[1][0]*b[0][0]+a[1][1]*b[1][0])%mod;
        long fourth=(a[1][0]*b[0][1]+a[1][1]*b[1][1])%mod;
        a[0][0]=first;a[0][1]=second;a[1][0]=third;a[1][1]=fourth;
        return a;

    }
    public static long[][] fast_matrix_power(long[][]ans,long x,long[][]matrix){
        if(x==1) return multiply(ans,matrix);

        if(x%2==0){
            matrix=multiply(matrix,matrix);
            x=x/2;
        }
        else{
            ans=multiply(ans,matrix);
            matrix=multiply(matrix,matrix);
            x=(x-1)/2;
        }
        return fast_matrix_power(ans, x, matrix);

    }
}

