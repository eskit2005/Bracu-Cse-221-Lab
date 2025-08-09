// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.io.PrintWriter;
// import java.util.ArrayList;
// import java.util.StringTokenizer;

// public class Ordering_binary_tree {
//     public static void main(String[]args)throws IOException{
//         BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
//         PrintWriter writer=new PrintWriter(System.out);
//         int len=Integer.parseInt(reader.readLine());
//         int[] arr=new int[len];
//         StringTokenizer tokens=new StringTokenizer(reader.readLine());
//         for(int i=0;i<len;i++){
//             arr[i]=Integer.parseInt(tokens.nextToken());
//         }
//         ArrayList<Integer> result=new ArrayList<>();
//         balancing(arr,result,0,arr.length-1);
//         for(int num:result){
//             writer.print(num+ " ");
//         }
//         writer.println();
//         writer.close();


//     }

//     public static void balancing(int[] arr,ArrayList<Integer> result,int start,int end){
//         if(start>end) return;

//         int mid=(start+end)/2;
//         result.add(arr[mid]);
//         balancing(arr,result,start,mid-1);
//         balancing(arr,result,mid+1,end);
//     }
// }

