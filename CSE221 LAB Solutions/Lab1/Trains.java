import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Trains {
    public static void main(String[]args) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        int size=Integer.parseInt(reader.readLine());
        String [] names=new String[size];
        String [] times=new String[size];
        String [] cities=new String[size]; // used for keeping a record of input format(as the destination cities are unique)
        
        for(int i=0;i<size;i++){
            StringTokenizer tokens=new StringTokenizer(reader.readLine());
            names[i]=tokens.nextToken();
            tokens.nextToken();tokens.nextToken();tokens.nextToken();
            cities[i]=tokens.nextToken();
            tokens.nextToken();
            times[i]=tokens.nextToken();

        }

        for(int i=1;i<size;i++){
            String temp=names[i];
            String citytemp=cities[i];
            String timetemp=times[i];
            int j=i-1;
            while(j>=0){
                String temp1=names[j];
                String citytemp1=cities[j];
                String timetemp1=times[j];
                if(!compare_names(temp,temp1)){
                    break;
                }
                else if(compare_names(temp, temp1) && !temp.equals(temp1)){
                    names[j+1]=temp1;
                    cities[j+1]=citytemp1;
                    times[j+1]=timetemp1;
                    
                }
                else if(compare_names(temp, temp1) && temp.equals(temp1)){
                    if(!compare_times(timetemp,timetemp1)) break;
                    else if(compare_times(timetemp, timetemp1) && get_times(timetemp)==get_times(timetemp1)){
                        break;
                    }
                    else if(compare_names(temp, temp1)){
                        names[j+1]=temp1;
                        cities[j+1]=citytemp1;
                        times[j+1]=timetemp1;
                    }
                }

                j--;
            }
            names[j+1]=temp;
            cities[j+1]=citytemp;
            times[j+1]=timetemp;
        }


        for(int i=0;i<size;i++){
            writer.println(names[i]+" will departure for "+cities[i]+" at "+times[i]);
        }

        writer.close();
    }


    public static boolean compare_names(String a, String b){
        int length=Math.min(a.length(),b.length());
        for(int i=0;i<length;i++){
            if(a.charAt(i)>b.charAt(i)) return false;
            else if(b.charAt(i)>a.charAt(i)) return true;
        }
        if(a.length()<b.length() || a.length()==b.length()) return true;
        return false;
    }

    public static boolean compare_times(String time1,String time2){
        double total1=get_times(time1);
        double total2=get_times(time2);

        if(total1>total2 || total1==total2) return true;
        return false;
    }

    public static double get_times(String time1) {
        StringTokenizer tokens = new StringTokenizer(time1, ":");
        double hour = Double.parseDouble(tokens.nextToken());
        double minute = Double.parseDouble(tokens.nextToken());
        return hour + (minute / 60.0);
    }
    
}
