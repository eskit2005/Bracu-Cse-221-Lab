
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Longest_SubArray_Sum {
    public static void main(String[]args)throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        int len=Integer.parseInt(tokens.nextToken());
        int [] arr=new int[len];
        long target=Long.parseLong(tokens.nextToken());
        tokens=new StringTokenizer(reader.readLine());
        for(int i=0;i<len;i++){
            arr[i]=Integer.parseInt(tokens.nextToken());
        }
        int left=0,streak=0;
        long sum=0;

        for(int right=0;right<len;right++){
            sum+=arr[right];
            while(sum>target){
                sum=sum-arr[left];
                left++;
            }
            int current_steak=right-left+1;
            if(current_steak>streak) streak=current_steak;
        }
        writer.println(streak);
        writer.close();
    }


}

//This is a sliding window problem
// Example:

// Input: arr=[4,1,2,1,5],k=4


// | Step | `left` | `right` | `sum` | Action                                                                                                                                        |
// | ---- | ------ | ------- | ----- | --------------------------------------------------------------------------------------------------------------------------------------------- |
// | 0    | 0      | 0       | 4     | valid → maxLen = 1                                                                                                                            |
// | 1    | 0      | 1       | 5     | >K → subtract `arr[0]` (4), left = 1, sum = 1                                                                                                 |
// | 2    | 1      | 1       | 1     | valid → maxLen = 1                                                                                                                            |
// | 3    | 1      | 2       | 3     | valid → maxLen = 2                                                                                                                            |
// | 4    | 1      | 3       | 4     | valid → maxLen = 3 ✅                                                                                                                          |
// | 5    | 1      | 4       | 9     | >K → subtract `arr[1]` (1), left = 2, sum = 8 → still too big, left = 3, sum = 6 → still too big, left = 4, sum = 5 → still too big, left = 5 |

