import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class two_sum_revisited {
    public static void main(String[] args) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        int len1=Integer.parseInt(tokens.nextToken());
        int len2=Integer.parseInt(tokens.nextToken());
        Long target=Long.parseLong(tokens.nextToken());
        int [] arr1=new int[len1];
        int[] arr2=new int[len2];
        tokens=new StringTokenizer(reader.readLine());
        for(int i=0;i<len1;i++){
            arr1[i]=Integer.parseInt(tokens.nextToken());
        }
        tokens=new StringTokenizer(reader.readLine());
        for(int i=0;i<len2;i++){
            arr2[i]=Integer.parseInt(tokens.nextToken());
        }
        int real_left=0,real_right=0;
        long min_diff=Long.MAX_VALUE;
        int left=0,right=len2-1;
        while(left<len1 && right>=0){
            long sum=arr1[left]+arr2[right];
            long diff=Math.abs(sum-target);
            if(diff<min_diff){
                min_diff=diff;
                real_left=left;real_right=right;
            }

            if(sum<target){
                left++;
            }
            else{
                right--;
            }
            
        }
        real_left++;real_right++;
        writer.println(real_left + " "+real_right);
        writer.close();

    }
}
