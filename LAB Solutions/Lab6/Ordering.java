import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Ordering{
    public static void main(String[]args)throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        int n=Integer.parseInt(reader.readLine());
        String [] words=new String[n];
        boolean [] track=new boolean[26];
        int [] indegrees=new int[26];
        ArrayList<List<Integer>> adj=new ArrayList<>();
        for(int i=0;i<=26;i++){
            adj.add(new LinkedList<>());
        }
        for(int i=0;i<n;i++){
            words[i]=reader.readLine();
            for(int k=0;k<words[i].length();k++){
                char c=words[i].charAt(k);
                track[c-'a']=true;
            }
        }

        for(int i=0;i<n-1;i++){
            String temp=words[i];
            String temp1=words[i+1];
            boolean valid=false;
            int len=Math.min(temp.length(),temp1.length());
            for(int k=0;k<len;k++){
                if(temp.charAt(k)!=temp1.charAt(k)){
                    valid=true;
                    indegrees[temp1.charAt(k)-'a']++;
                    adj.get(temp.charAt(k)-'a').add(temp1.charAt(k)-'a');
                    break;
                }
            }

            if(!valid && temp.length()>temp1.length()){
                writer.println(-1);
                writer.close();
                return;
            }
        }

        PriorityQueue<Integer> queue=new PriorityQueue<>();
        for(int i=0;i<26;i++){
            if(track[i] && indegrees[i]==0) queue.offer(i);
        }

        String result="";
        while(!queue.isEmpty()){
            int u=queue.poll();
            char c=(char)((int)u+'a');
            result+=c;
            for(int num:adj.get(u)){
                indegrees[num]--;
                if(indegrees[num]==0) queue.offer(num);
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
