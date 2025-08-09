import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Beautiful_Sorted_List {
    public static void main(String[]args) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        int len1=Integer.parseInt(reader.readLine());
        int[] arr1=new int[len1];
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        for(int i=0;i<len1;i++){
            arr1[i]=Integer.parseInt(tokens.nextToken());
        }
        int len2=Integer.parseInt(reader.readLine());
        int[] arr2=new int[len2];
        tokens=new StringTokenizer(reader.readLine());
        for(int i=0;i<len2;i++){
            arr2[i]=Integer.parseInt(tokens.nextToken());
        }

        int i=0,j=0, index=0;
        int [] arr=new int[len1+len2];
        while(i<len1 && j<len2){
            if(arr1[i]<arr2[j]){
                arr[index]=arr1[i];
                i++;
            }
            else if(arr1[i]==arr2[j]) {
                arr[index]=arr1[i];
                i++;
            }
            else{
                arr[index]=arr2[j];
                j++;
            }
            index++;
        }
        while(i<len1){
            arr[index]=arr1[i];
            i++;index++;
        }
        while(j<len2){
            arr[index]=arr2[j];
            j++;index++;
        }


        for(int k=0;k<arr.length;k++){
            if(k==arr.length-1){
                writer.print(arr[k]+"\n");
                break;
            }
            writer.print(arr[k]+ " ");
            
        }
        writer.close();

    }
}
