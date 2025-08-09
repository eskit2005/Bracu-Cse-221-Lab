import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SortingAgain {
    public static void main(String[]args) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer= new PrintWriter(System.out);
        int size=Integer.parseInt(reader.readLine());
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        int [] ids=new int[size];
        int [] marks=new int [size];
        for(int i=0;i<size;i++){
            ids[i]=Integer.parseInt(tokens.nextToken());
        }
        tokens=new StringTokenizer(reader.readLine());

        for(int i=0;i<size;i++){
            marks[i]=Integer.parseInt(tokens.nextToken());
        }
        int swaps=0;
        for(int i=0;i<size;i++){
            int max=marks[i];
            int max_index=i;
            for(int j=i+1;j<size;j++){
                if(max<marks[j]){
                    max_index=j;
                    max=marks[j];
                }
                else if(max==marks[j]){
                    if(ids[max_index]>ids[j]){
                        max_index=j;
                        max=marks[j];
                    }
                }
            }
            if(i!=max_index){
                int temp=marks[i];
                marks[i]=marks[max_index];
                marks[max_index]=temp;

                temp=ids[i];
                ids[i]=ids[max_index];
                ids[max_index]=temp;

                swaps++;
            }
        }

        writer.println("Minimum swaps: "+swaps);
        for(int i=0;i<size;i++){
            writer.println("ID: "+ids[i]+" Mark: "+marks[i]);
        }
        writer.close();

    }
}
