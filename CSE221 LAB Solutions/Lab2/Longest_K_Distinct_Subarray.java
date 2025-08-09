import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Longest_K_Distinct_Subarray {
    public static void main(String[]args) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        int len=Integer.parseInt(tokens.nextToken());
        int [] arr=new int[len];
        long k=Long.parseLong(tokens.nextToken());
        tokens=new StringTokenizer(reader.readLine());
        for(int i=0;i<len;i++){
            arr[i]=Integer.parseInt(tokens.nextToken());
        }

        HashMap<Integer,Integer> freqmap=new HashMap<>();
        int left=0,right=0;
        long max_len=0;
        
        while(right<len){
            if(freqmap.get(arr[right])==null){
                freqmap.put(arr[right],1);
                
            }
            else if(freqmap.get(arr[right])!=null){
                freqmap.put(arr[right],freqmap.get(arr[right])+1);
            }

            while(freqmap.size()>k){
                freqmap.put(arr[left],freqmap.get(arr[left])-1);
                if(freqmap.get(arr[left])==0){
                    freqmap.remove(arr[left]);
                }
                left++;
            }

            

            long length=right-left+1;
            max_len=Math.max(max_len,length);
            right++;

        }
        

        writer.println(max_len);
        writer.close();
    }
}
