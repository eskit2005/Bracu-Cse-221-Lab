import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public  class count_numbers{
    public static void main(String[]args) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        int len=Integer.parseInt(tokens.nextToken());
        int cases=Integer.parseInt(tokens.nextToken());
        tokens=new StringTokenizer(reader.readLine());
        int []arr= new int[len];
        for(int i=0;i<len;i++){
            arr[i]=Integer.parseInt(tokens.nextToken());
        }

        for(int i=0;i<cases;i++){
            tokens=new StringTokenizer(reader.readLine());
            int target1=Integer.parseInt(tokens.nextToken());
            int target2=Integer.parseInt(tokens.nextToken());
            int left=lowerbound(arr,target1);
            int right=upperbound(arr, target2);
            int range=(right-left);
           
            writer.println(range);
            
            
        }
        writer.close();

    }

    //Smallest index (i) for which array[i]>=target
    public static int lowerbound(int [] arr,int target){
        int lower=arr.length;
        int left=0,right=arr.length-1;
        while(left<=right){
            int mid=(right+left)/2;
            if(arr[mid]>=target){
                lower=mid;
                right=mid-1;
            }
            else left=mid+1;
        }
        return lower;

    }
    //Smallest index (i) for which array[i]>target
    public static int upperbound(int [] arr,int target){
        int higher=arr.length;
        int left=0,right=arr.length-1;
        while(left<=right){
            int mid=(right+left)/2;
            if(arr[mid]>target){
                higher=mid;
                right=mid-1;
            }
            else left=mid+1;
        }
        return higher;
    }
}