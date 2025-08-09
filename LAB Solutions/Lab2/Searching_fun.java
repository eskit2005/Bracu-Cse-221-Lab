import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Searching_fun {
    //Equation for solving this qn is : ans-(ans/x)floor function=k. Now when you ingnore the 
    //floor function and make ans the subject you are going to get (kx)/(x-1)=ans. That's y
    // long left=1(qn tells us to only search for +ve integers ) and long right=kx
    //(which is the upper bound or highest possible ans you could get).

    public static void main(String[]args) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        int cases=Integer.parseInt(reader.readLine());
        for(int i=0;i<cases;i++){
            StringTokenizer tokens=new StringTokenizer(reader.readLine());
            long k=Long.parseLong(tokens.nextToken());
            long x=Long.parseLong(tokens.nextToken());
            long left=1,right=k*x;
            long ans=k*x;
            while(left<=right){
                long mid=(left+right)/2;
                long count=mid-(mid/x);//numbers of integers that aren't divisible by x before x.
                if(count>=k){
                    ans=mid;
                    right=mid-1;
                }
                else left=mid+1;
            }
            writer.println(ans);
        }
        writer.close();
    }
}
