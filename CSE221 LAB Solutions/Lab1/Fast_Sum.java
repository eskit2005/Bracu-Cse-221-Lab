import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class Fast_Sum {
    public static void main(String[] args) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        int T=Integer.parseInt(reader.readLine());
        for (int t = 0; t < T; t++) {
            long N=Integer.parseInt(reader.readLine());
            long sum = (N * (1+N)) / 2;
            writer.println(sum);
        }
        writer.close();
    }

}

