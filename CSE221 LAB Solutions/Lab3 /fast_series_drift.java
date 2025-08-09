import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class fast_series_drift{
   public static void main(String[]args)throws IOException{
       BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
       PrintWriter writer=new PrintWriter(System.out);
       int cases=Integer.parseInt(reader.readLine());


       while(cases>0){
           StringTokenizer tokens=new StringTokenizer(reader.readLine());
           long a=Long.parseLong(tokens.nextToken());
           long n=Long.parseLong(tokens.nextToken());
           long m=Long.parseLong(tokens.nextToken());
           long ans=fast_series(a,n,m);
           writer.println(ans);
           cases--;
       }
       writer.close();
   }




   public static long fast_series(long a, long n, long m) {
       if (n==1) return a%m;


       long half=n/2;
       long sum_half=fast_series(a,half,m);              
       long power_half=fast_power(a,half,m);             


       long result=(sum_half+(power_half * sum_half)) % m;


       if (n%2==1) {
           long n_term = fast_power(a,n,m);                
           result = (result + n_term) % m;
       }


       return result;
   }




   public static long fast_power(long a,long b,long mod){
       long result=1;
       while(b>0){
           if((b & 1)==1){ //checking LSB for odd or even
               result=(result*a)%mod;
           }
           a=(a*a)%mod;
           b=b/2;
       }
       return result;
   }


  
}




// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.io.PrintWriter;
// import java.util.StringTokenizer;


// public class fast_series_drift{
//    public static void main(String[]args)throws IOException{
//        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter writer=new PrintWriter(System.out);
//        int cases=Integer.parseInt(reader.readLine());


//        while(cases>0){
//            StringTokenizer tokens=new StringTokenizer(reader.readLine());
//            long a=Long.parseLong(tokens.nextToken());
//            long n=Long.parseLong(tokens.nextToken());
//            long m=Long.parseLong(tokens.nextToken());
//            long ans=fast_series(a,n,m);
//            writer.println(ans);
//            cases--;
//        }
//        writer.close();
//    }




//    public static long fast_series(long a, long n, long m) {
//        if (n==1) return a%m;


//        long half=n/2;
//        long sum_half=fast_series(a,half,m);              
//        long power_half=fast_power(a,half,1,m);             


//        long result=(sum_half+(power_half * sum_half)% m) % m;


//        if (n%2==1) {
//            long n_term = fast_power(a,n,1,m);                
//            result = (result + n_term) % m;
//        }


//        return result;
//    }




//    public static long fast_power(long a,long b,long ans,long mod){
//        if(b==1) return (ans*a)%mod;


//        if(b%2==0){
//            a=(a*a)%mod;
//            b=b/2;
//        }
//        else{
//            ans=(ans*a)%mod;
//            a=(a*a)%mod;
//            b=(b-1)/2;
//        }
//        return fast_power(a,b,ans,mod);
//    }


  
// }




