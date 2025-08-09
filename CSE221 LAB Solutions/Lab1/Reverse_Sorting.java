import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Reverse_Sorting {
    public static void main(String[]args) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        int size=Integer.parseInt(reader.readLine());
        int [] numbers=new int[size];
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        for(int i=0;i<size;i++){
            numbers[i]=Integer.parseInt(tokens.nextToken());
        }
        for(int i=0;i<size;i++){
            for(int j=0;j<(size-i-2);j++){ // reversing using the operation
                int temp=numbers[j+2];
                if(numbers[j+2]<numbers[j]){
                    numbers[j+2]=numbers[j];
                    numbers[j]=temp;
                }
                
            }
        }
        
        
        boolean sorted=true;
        for(int i=0;i<size-1;i++){ // checking if the array is sorted
            if(numbers[i]>numbers[i+1]) {
                sorted=false;
                break;
            }
        }
        if(sorted) writer.println("YES");
        else writer.println("NO");

        writer.close();

    }
}
