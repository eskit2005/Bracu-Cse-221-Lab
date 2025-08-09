import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Arithmetic{
    public static void main(String[]args) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        int num=Integer.parseInt(tokens.nextToken());
        for(int i=num;i>0;i--){
            tokens=new StringTokenizer(reader.readLine());
            tokens.nextToken();
            double num1=Double.parseDouble(tokens.nextToken());
            String sign=tokens.nextToken();
            double num2=Double.parseDouble(tokens.nextToken());
            if(sign.equals("+")) writer.printf("%.6f\n",num1+num2);
            else if(sign.equals("-")) writer.printf("%.6f\n",num1-num2);
            else if(sign.equals("*")) writer.printf("%.6f\n",num1*num2);
            else writer.printf("%.6f\n",num1/num2);


        }
        writer.close();

    }
}