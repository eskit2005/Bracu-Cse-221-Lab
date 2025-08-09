import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class two_sum{
    public static void main(String[]args) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        int len=Integer.parseInt(tokens.nextToken());
        long target=Long.parseLong(tokens.nextToken());
        long [] arr=new long[len];
        tokens=new StringTokenizer(reader.readLine());
        for(int i=0;i<len;i++){
            arr[i]=Long.parseLong(tokens.nextToken());
        }

        int left=0,right=len-1;
        while(left<right){
            Long sum=arr[ left]+arr[right];
            if(target==sum){
                
                writer.println(++left + " "+ ++right);
                writer.close();
                return;
            }
            if(target<sum){
                right--;
            }
            else{
                left++;
            }
        }

        writer.println(-1);

        writer.close();

    }
}