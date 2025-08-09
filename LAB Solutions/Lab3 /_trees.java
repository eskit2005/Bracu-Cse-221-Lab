import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class _trees {
    public static int [] inorder;
    public static int[] postorder;
    public static int post_index;
    public static void main(String[]args)throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        int len=Integer.parseInt(reader.readLine());
        inorder=new int[len];postorder=new int[len];
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        HashMap<Integer,Integer> map=new HashMap<>();
        post_index=len-1;


        for(int i=0;i<len;i++){
            inorder[i]=Integer.parseInt(tokens.nextToken());
            map.put(inorder[i],i);
        }
        tokens=new StringTokenizer(reader.readLine());
        for(int i=0;i<len;i++){
            postorder[i]=Integer.parseInt(tokens.nextToken());
        }

        
        ArrayList<Integer> list=new ArrayList<>();
        post_order(postorder,0,len-1,list,map);
        for(int num:list){
            writer.print(num+ " ");
        }
        writer.println();
        writer.close();

    }

    public static void post_order(int [] postorder,int start,int end,ArrayList<Integer>list,HashMap<Integer,Integer> map){
        if(start>end) return;

        int root=postorder[post_index];
        int root_index_inorder=map.get(root);
        list.add(root);
        post_index=root_index_inorder-start-1;

        post_order(postorder,start,root_index_inorder-1,list,map);

        post_index=end-1;
        
        post_order(postorder,root_index_inorder+1,end,list,map);
       

        
    


    }



    
}
