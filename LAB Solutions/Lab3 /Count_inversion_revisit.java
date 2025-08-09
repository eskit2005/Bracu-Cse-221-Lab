import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Count_inversion_revisit{
    public static void main(String[]args)throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        int len=Integer.parseInt(reader.readLine());
        long [] arr=new long[len];
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        for(int i=0;i<len;i++){
            arr[i]=Long.parseLong(tokens.nextToken());
        }

        long inversions=merge_sort(arr);
        writer.println(inversions);
        writer.close();
    }


    public static long merge_sort(long[] arr){
        if(arr.length==1)return 0;

        long inversions=0;
        int mid=arr.length/2;
        long[] left_arr=new long[mid];long[] right_arr=new long[arr.length-mid];

        for(int i=0;i<mid;i++){
            left_arr[i]=arr[i];
        }

        for(int i=mid;i<arr.length;i++){
            right_arr[i-mid]=arr[i];
        }

        inversions+=merge_sort(left_arr);
        inversions+=merge_sort(right_arr);
        inversions+=merge(left_arr,right_arr,arr);
        return inversions;
    }

    public static long merge(long[]left_arr,long[] right_arr,long[] arr){
        int i=0;int j=0;int inversions=0; int index=0;
        for(long num:right_arr){
            long value=num*num;
            inversions+=binary_search(left_arr, value);
        }

        while(i<left_arr.length && j<right_arr.length){
            if(left_arr[i]<right_arr[j]){
                arr[index]=left_arr[i++];
            }
            else if(left_arr[i]==right_arr[j]){
                arr[index]=left_arr[i++];
            }
            else{
                arr[index]=right_arr[j++];
            }

            index++;
        }

        while(i<left_arr.length) arr[index++]=left_arr[i++];

        while(j<right_arr.length) arr[index++]=right_arr[j++];

        return inversions;
    }


    public static long binary_search(long[] left_arr,long target){
        int left=0;int right=left_arr.length-1;
        int index=left_arr.length;
        while(left<=right){
            int mid=(left+right)/2;
            if(left_arr[mid]>target){
                index=mid;
                right=mid-1;
            }
            else{
                left=mid+1;
            }
        }

        return (left_arr.length-index);


    }




}

