import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Ancient_Sorting {
    public static void main(String[]args) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter((System.out));
        int size=Integer.parseInt(reader.readLine());
        int[] numbers=new int[size];
        StringTokenizer tokens= new StringTokenizer(reader.readLine());
        for(int i=0;i<size;i++){
            numbers[i]=Integer.parseInt(tokens.nextToken());
        }
        for(int i=0;i<size;i++){
            for(int j=0;j<size-1-i;j++){
                if((numbers[j]>numbers[j+1]) && numbers[j]%2==0 && numbers[j+1]%2==0){
                    int temp=numbers[j];
                    numbers[j]=numbers[j+1];
                    numbers[j+1]=temp;
                }
                else if((numbers[j]>numbers[j+1]) && numbers[j]%2!=0 && numbers[j+1]%2!=0){
                    int temp=numbers[j];
                    numbers[j]=numbers[j+1];
                    numbers[j+1]=temp;
                }
            }
        }

       for(int i=0;i<size;i++ ){
        if(i==size-1){
            writer.print(numbers[i]+"\n");
        }
        else{
            writer.print(numbers[i]+ " ");
        }
       }
        writer.close();
    }

    
}

