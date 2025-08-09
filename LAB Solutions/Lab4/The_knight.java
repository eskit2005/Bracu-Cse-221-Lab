import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class The_knight {
    public static void main(String[]args) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        int row=Integer.parseInt(tokens.nextToken());
        int column=Integer.parseInt(tokens.nextToken());
        int k=Integer.parseInt(tokens.nextToken());
        boolean [][]matrix=new boolean[row+1][column+1];
        int [][] positional_vector={{-2,-1},{-2,+1},{-1,-2},{+1,-2},{2,-1},{2,+1},{-1,+2},{+1,+2}};

        //first place all of the 8 possible moves of knight in the chessboard and
        // then see if the position of a another knight is already taken 
        for(int i=0;i<k;i++){
            tokens=new StringTokenizer(reader.readLine());
            int x=Integer.parseInt(tokens.nextToken());
            int y=Integer.parseInt(tokens.nextToken());
            if(matrix[x][y]==true){// seeing if the position of the knight is already taken
                writer.println("YES");
                writer.close();
                return;
            }
            for(int[] vector:positional_vector){//placing 8 possible moves of the knight in the chessboard
                int temp=x,temp1=y;
                temp=temp+vector[0];
                temp1=temp1+vector[1];
                if((temp>0 && temp<row+1) && (temp1>0 && temp1<column+1)){
                    matrix[temp][temp1]=true;
                }

            }

        }
        writer.println("NO");
        writer.close();


    }

}
