import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Is_Sorted {
    public static void main(String[ ]args)throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        int n=Integer.parseInt(reader.readLine());
        for(int i=n;i>0;i--){
            int size=Integer.parseInt(reader.readLine());
            int [] numbers=new int[size];
            StringTokenizer tokens=new StringTokenizer(reader.readLine());
            for(int j=0;j<size;j++){
                numbers[j]=Integer.parseInt(tokens.nextToken());
            }
            boolean sorted=true;
            for(int k=0;k<size-1;k++){
                if(numbers[k]>numbers[k+1]){
                    sorted=false;
                    break;
                }

            }
            if(sorted) writer.println("YES");
            else writer.println("NO");
        }

        writer.close();

    }
    
}
