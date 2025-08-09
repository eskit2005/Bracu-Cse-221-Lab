import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Three_Sum {

    //this problem is essitentially turning threesum into two sum problem. First u pick a number,
    //then newsum=sum-num(a number that u picked), finally search for the newsum using two pointers method
    // like how you would in a classic two sum qn
    public static void main(String[]args) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        int len=Integer.parseInt(tokens.nextToken());
        int sum=Integer.parseInt(tokens.nextToken());
        tokens=new StringTokenizer(reader.readLine());
        int [] arr=new int[len];int [] idx=new int[len]; // this idx will be responsible for 
        //keeping the original indexes of the elements 
        for(int i=0;i<len;i++){
            arr[i]=Integer.parseInt(tokens.nextToken());
            idx[i]=i+1;//1 based indexing 

        }
        //selection sort and also making sure idx is in sync(original indexes)
        for(int i=0;i<arr.length;i++){
            int min_index=i;
            for(int j=i+1;j<arr.length;j++){
                if(arr[j]<arr[min_index]){
                    min_index=j;
                }
            }
            int temp=arr[i];int temp1=idx[i];
            arr[i]=arr[min_index];idx[i]=idx[min_index];
            arr[min_index]=temp;idx[min_index]=temp1;

        }




        int first=0,second=0,third=0;
        Integer.hashCode(first);
        boolean found=false;
        for(int i=0;i<len-2;i++){
                int left=i+1;
                int right=len-1;
                int remaining_two_sum=sum-arr[i];
                while(left<right){

                    if(arr[left]+arr[right]==remaining_two_sum){
                        first=i;second=left;third=right;
                        found=true;
                        break;
                    }

                    else if(arr[left]+arr[right]>remaining_two_sum){
                        right--;
                    }
                    else left++;

                }
                if(found) break;
            
        }
        writer.println(found ? (idx[first]+" "+idx[second]+" "+idx[third]):-1);
        writer.close();

    }
}
