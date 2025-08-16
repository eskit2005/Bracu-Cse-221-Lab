import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Diamonds {
    public static void main(String[]args)throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        int r=Integer.parseInt(tokens.nextToken());
        int h=Integer.parseInt(tokens.nextToken());
        char [][]adj=new char[r][h];
        for(int i=0;i<r;i++){
            String line=reader.readLine();
            for(int j=0;j<h;j++){
                char c=line.charAt(j);
                adj[i][j]=c;
            }
        }

        diamond_search(adj);

    }

    public static void diamond_search(char[][]adj){
        boolean [][]visited=new boolean[adj.length][adj[0].length];
        int [][]pos_vectors={{0,-1},{0,+1},{+1,0},{-1,0}};
        int diamonds=0;
        for(int i=0;i<adj.length;i++){
            for(int j=0;j<adj[0].length;j++){
                if(!visited[i][j] && adj[i][j]!='#'){
                    int temp=bfs(adj,visited,pos_vectors,i,j);
                    if(temp>diamonds) diamonds=temp;
                }
            }
        }
        PrintWriter writer=new PrintWriter(System.out);
        writer.println(diamonds);
        writer.close();
    }

    public static int bfs(char[][]adj,boolean[][] visited,int[][] pos_vectors,int row_source,int col_source){
        int diamonds=0;
        Queue<Integer> row_queue=new LinkedList<>();
        Queue<Integer> col_queue=new LinkedList<>();
        visited[row_source][col_source]=true;
        row_queue.offer(row_source);
        col_queue.offer(col_source);
        while(!row_queue.isEmpty()){
            int row=row_queue.poll();
            int col=col_queue.poll();
            if(adj[row][col]=='D') diamonds++;
            for(int[] num:pos_vectors){
                int x=row+num[0];
                int y=col+num[1];
                if((x>=0 && x<adj.length) && (y>=0 && y<adj[0].length) && !visited[x][y] && adj[x][y]!='#'){
                    visited[x][y]=true;
                    row_queue.offer(x);
                    col_queue.offer(y);
                }
            }
        }

        return diamonds;
    }
}
