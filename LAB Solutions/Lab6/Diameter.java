import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Diameter {

    // Basically we need to take a random vertex(we usually take 1) and find the vertex(temp) that is the 
    // farthest away from the random vertex(in our case 1) and then we need to get that farthest vertex 
    // and find its farthest vertex(temp1) too. So diamemter=(distance between temp and temp1)
    // we find the farthest vertex using bfs(we need to run bfs twice for this problem)\
    // more in depth explanation is given at the end of the code
    public static void main(String[]args)throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        int N=Integer.parseInt(reader.readLine());
        ArrayList<List<Integer>> adj=new ArrayList<>();
        for(int i=0;i<=N;i++){
            adj.add(new LinkedList<>());
        }

        for(int i=0;i< N-1;i++){
            StringTokenizer tokens=new StringTokenizer(reader.readLine());
            int u=Integer.parseInt(tokens.nextToken());
            int v=Integer.parseInt(tokens.nextToken());
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int [] temp=bfs(adj, N, 1);
        int [] result=bfs(adj, N, temp[0]);

        writer.println(result[1]);
        writer.println(temp[0]+" "+ result[0]);
        writer.close();
    }

    public static int []  bfs(ArrayList<List<Integer>>adj,int N,int source){
        int distant=source;
        boolean [] visited=new boolean[N+1];
        int [] dis=new int[N+1];
        Queue<Integer> queue=new LinkedList<>();
        visited[source]=true;
        dis[source]=0;
        queue.offer(source);
        while(!queue.isEmpty()){
            int u=queue.poll();
            List<Integer> list=adj.get(u);
            for(int num:list){
                if(!visited[num]){
                    visited[num]=true;
                    dis[num]=1+dis[u];
                    queue.offer(num);
                    if(dis[num]>dis[distant]) distant=num;
                }
            }
        }
        int [] result=new int[2];
        result[0]=distant;result[1]=dis[distant];
        return result;
    }
}


/*

We want the **longest path** in a tree. That path is called the **diameter**.

---

### Step 1: Imagine a simple tree

```
5 - 1 - 4 - 2 - 3
```

(That’s the first sample input, just redrawn as a line.)

Clearly, the longest path is from node `5` to node `3`, length = 4.

But how do we *find* it without guessing?

---

### Step 2: First BFS/DFS

Pick **any random node** (say node `1`).
Find the node **farthest away** from it.

From `1`:

* distance to 5 = 1
* distance to 4 = 1
* distance to 2 = 2
* distance to 3 = 3

👉 The farthest is node `3`.

So one end of the diameter is **3**.
(We didn’t know before, but this trick guarantees it!)

---

### Step 3: Second BFS/DFS

Now start BFS/DFS again, but this time from `3`.
Find the farthest node from `3`.

From `3`:

* distance to 2 = 1
* distance to 4 = 2
* distance to 1 = 3
* distance to 5 = 4

👉 The farthest is node `5`.

---

### Step 4: Done!

* Diameter length = 4
* Endpoints = (3, 5)

That matches the sample output.

---

### Why does this work?

Because in a **tree**, the farthest node from any random start will always be at one end of the diameter.
Then the second BFS finds the opposite end.

This is a **well-known trick** for tree diameter.
(Works in `O(N)` time, super efficient.)

---

✅ So the recipe is:

1. Pick any node (say `1`).
2. BFS/DFS to find the farthest node `A`.
3. BFS/DFS from `A` to find the farthest node `B`.
4. Path `A—B` is the diameter.

---

Would you like me to **draw this as a small dry-run with arrays/queues** (like step-by-step BFS with distances), so you see exactly how the algorithm runs?
 */