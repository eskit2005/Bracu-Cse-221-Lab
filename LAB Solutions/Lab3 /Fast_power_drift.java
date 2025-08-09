import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Fast_power_drift {
    public static void main(String[]args)throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        long a=Long.parseLong(tokens.nextToken());
        long b=Long.parseLong(tokens.nextToken());
        writer.println(fast_power(a,b,1));
        writer.close();


    }


    public static long fast_power(long a,long b,long ans){
        if(b==1) return (ans*a)%107;

        if(b%2==0){
            a=(a*a)%107;
            b=b/2;
        }
        else{
            ans=(ans*a)%107;
            a=(a*a)%107;
            b=(b-1)/2;
        }
        return fast_power(a,b,ans);
    }
}
