import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Count_inversion{
    public static void main(String[]args) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        int length=Integer.parseInt(reader.readLine());
        int [] arr=new int[length];
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        for(int i=0;i<arr.length;i++){
            arr[i]=Integer.parseInt(tokens.nextToken());
        }

        long inversions=merge_sort(arr);
        writer.println(inversions);
        for(int num: arr){
            writer.print(num+ " ");
        }
        writer.println();
        writer.close();
    }


    public static long merge_sort(int [] arr){
        if(arr.length==1) return 0;

        long inversions=0;

        int mid=arr.length/2;
        int [] left_arr=new int[mid];int[] right_arr=new int[arr.length-mid];
        for(int i=0;i<mid;i++){
            left_arr[i]=arr[i];
        }
        for(int i=mid;i<arr.length;i++){
            right_arr[i-mid]=arr[i];
        }


        inversions+=merge_sort(left_arr);
        inversions+=merge_sort(right_arr);
        inversions+=merge(left_arr,right_arr,arr);
        
        return inversions;

    }

    public static long merge(int [] left_arr,int[] right_arr,int[] arr ){
        int left=0;int right=0;int index=0;
        long inversions=0;

        while(left<left_arr.length && right<right_arr.length){
            if(left_arr[left]<right_arr[right]){
                arr[index]=left_arr[left++];
            }
            else if(left_arr[left]==right_arr[right]){
                arr[index]=left_arr[left++];
            }
            else{
                arr[index]=right_arr[right++];
                inversions+=(left_arr.length-left);
            }

            index++;
        }

        while(left<left_arr.length) arr[index++]=left_arr[left++];

        while(right<right_arr.length) arr[index++]=right_arr[right++];

        return inversions;
    }
}


// 🔍 Context: Merge Sort + Inversions

// The key idea is:
// When you're merging two sorted halves (left_arr and right_arr), and you find that an element from the right half is smaller than one from the left half, that's an inversion.

// Why?

// Because:

// All remaining elements in the left array (from left to end) are greater than the current right element.
// So each of them forms an inversion with the current right element.
// 🧠 Core Insight

// If you're comparing:

// left_arr[left] > right_arr[right]
// Then not just that pair, but every element remaining in left_arr from index left onward is greater than right_arr[right] (because left_arr is sorted!).

// So we count:

// left_arr.length - left
// as the number of new inversions.

// 🔧 Example: Merging [3, 5, 7] and [2, 6]

// Let’s simulate the merge:

// left_arr  = [3, 5, 7]
// right_arr = [2, 6]
// Start merging:

// 1st comparison:
// 3 > 2 → inversion!
// Since 3, 5, 7 are all > 2, we add:
// left_arr.length - left = 3 - 0 = 3 inversions
// Add 2 to merged array.
// 2nd comparison:
// 3 < 6 → no inversion, add 3
// 3rd comparison:
// 5 < 6 → no inversion, add 5
// 4th comparison:
// 7 > 6 → inversion!
// Only 7 remains → left_arr.length - left = 3 - 2 = 1
// Add 6 to merged array
// Total Inversions: 3 + 1 = 4
// 🔁 General Rule

// Whenever:

// left_arr[left] > right_arr[right]
// Add:

// left_arr.length - left
// Because all these left-side elements come before right_arr[right] (in original array) and are greater → inversion definition.

// ✅ Final Code (Relevant Part)

// if (left_arr[left] > right_arr[right]) {
//     arr[index++] = right_arr[right++];
//     inversions += (left_arr.length - left);
// }
// This is the key to counting inversions efficiently in O(n log n) time — you leverage the sorted property during merge to count multiple inversions at once.

// ✅ Summary

// You See	It Means
// left_arr[left] > right_arr[right]	All remaining left elements > right → inversions
// left_arr.length - left	Number of such inversions
// Let me know if you want a visual trace or dry run of a specific example array!