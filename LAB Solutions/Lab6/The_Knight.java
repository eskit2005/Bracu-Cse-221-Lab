import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.BufferOverflowException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class The_Knight {
    public static void main(String[]args) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(reader.readLine());
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        int[][] board=new int[n+1][n+1];
        int [] source=new int[2];
        source[0]=Integer.parseInt(tokens.nextToken());source[1]=Integer.parseInt(tokens.nextToken());
        int [] des=new int[2];
        des[0]=Integer.parseInt(tokens.nextToken());des[1]=Integer.parseInt(tokens.nextToken());
        bfs(board, n, source, des);
    }

    public static void bfs(int [][]board,int n,int [] source,int [] des){
        int [][]pos_vectors={{-2,1},{-2,-1},{-1,-2},{1,-2},{-1,+2},{1,+2},{+2,-1},{+2,+1}};
        boolean [][] visited=new boolean[n+1][n+1];
        int [][] dis=new int[n+1][n+1];
        dis [source[0]][source[1]]=0;
        visited[source[0]][source[1]]=true;
        Queue<Integer> x_pos=new LinkedList<>();
        Queue<Integer> y_pos=new LinkedList<>();
        x_pos.offer(source[0]); y_pos.offer(source[1]);
        while(!x_pos.isEmpty()){
            int x=x_pos.poll();
            int y=y_pos.poll();
            for(int [] dir:pos_vectors){
                int a=x+dir[0];
                int b=y+dir[1];
                if((a>0 && a<=n) && (b>0 && b<=n) && (!visited[a][b])){
                    visited[a][b]=true;
                    dis[a][b]=1+dis[x][y];
                    x_pos.offer(a);
                    y_pos.offer(b);
                }
            }
        }
        
        PrintWriter writer=new PrintWriter(System.out);

        if(!visited[des[0]][des[1]]){
            writer.println(-1);
            writer.close();
        }

        writer.println(dis[des[0]][des[1]]);
        writer.close();
    }
}
