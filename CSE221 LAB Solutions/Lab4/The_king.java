import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class The_king {
    public static void main(String[]args)throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        int size=Integer.parseInt(reader.readLine());
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        int [] position=new int[2];
        position[0]=Integer.parseInt(tokens.nextToken());position[1]=Integer.parseInt(tokens.nextToken());
        int count=0;
        HashMap<Integer,Integer[]> map=new HashMap<>();
        
        int [][] positional_vector={{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{+1,-1},{+1,0},{+1,+1}};//king's possible moves

        for(int[]vector:positional_vector){
            Integer[] move=new Integer[2];
            move[0]=position[0]+vector[0];
            move[1]=position[1]+vector[1];
            if(in_range(move,size)){
                map.put(count,move);
                count++;
            }
        }
        writer.println(count);
        for(int i=0;i<count;i++){
            Integer[] arr=map.get(i);
            for(int num:arr){
                writer.print(num+ " ");
            }
            writer.println();
        }
        writer.close();

    }

    public static boolean in_range(Integer [] arr,int size){
        if((arr[0]>=1 && arr[0]<=size)&& (arr[1]>=1 && arr[1]<=size)){
            return true;
        }
        return false;
    }


}
