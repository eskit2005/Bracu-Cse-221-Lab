import java.io.*;
import java.util.*;
public class even_or_odd {
    public static void main(String[]args) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        int n=Integer.parseInt(tokens.nextToken());
        while(n>0){
            tokens=new StringTokenizer(reader.readLine());
            int num=Integer.parseInt(tokens.nextToken());
            if(num%2==0) writer.println(num+ " is an Even number.");
            else writer.println(num +" is an Odd number.");
            n--;
        }

        writer.close();

    }
}
