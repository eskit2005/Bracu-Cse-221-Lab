import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Ordering {
    public static void main(String[]args) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        int N=Integer.parseInt(reader.readLine());
        String [] string=new String[N];
        boolean [] track=new boolean[26];
        int [] in_degree=new int[26];
        for(int i=0;i<N;i++){
            string[i]=reader.readLine();
            for (char cha : string[i].toCharArray()) 
                track[cha - 'a'] = true;
        }
        ArrayList<List<Integer>> adj=new ArrayList<>(26);
        for(int i=0;i<26;i++)adj.add(new LinkedList<>());
        


        for(int i=0;i<N-1;i++){
            String temp=string[i];
            String temp1=string[i+1];
            int len=Math.min(temp.length(),temp1.length());
            boolean valid=false;
            for(int k=0;k<len;k++){
                if(temp.charAt(k)!=temp1.charAt(k)){
                    adj.get(temp.charAt(k)-'a').add(temp1.charAt(k)-'a');
                    in_degree[temp1.charAt(k)-'a']++;
                    valid=true;
                    break;
                    
                }
            }
            
            if(!valid && temp.length()>temp1.length()){
                writer.println(-1);
                writer.close();
                return;

            }
        }


        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < 26; i++) {
            if (track[i] && in_degree[i] == 0) queue.offer(i);
        }

        String result = "";
        while (!queue.isEmpty()) {
            int u = queue.poll();
            result += (char) (u + 'a'); 
            for (int v : adj.get(u)) {
                in_degree[v]--;
                if (in_degree[v] == 0) queue.offer(v);
            }
        }

        int countPresent = 0;
        for (boolean b : track) if (b) countPresent++;
        if (result.length() != countPresent) {
            writer.println(-1);
            writer.close();
            return;
        }

        writer.println(result);
        writer.close();


    }
}
