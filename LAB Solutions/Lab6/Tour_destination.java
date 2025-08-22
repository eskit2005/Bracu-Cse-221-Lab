import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Tour_destination {
    //To do this qn learn about multi source bfs. Some of it is explained below
    public static void main(String[]args)throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer=new PrintWriter(System.out);
        StringTokenizer tokens=new StringTokenizer(reader.readLine());
        int N=Integer.parseInt(tokens.nextToken());
        int M=Integer.parseInt(tokens.nextToken());
        int source_size=Integer.parseInt(tokens.nextToken());
        int destinations_size=Integer.parseInt(tokens.nextToken());
        ArrayList<List<Integer>> adj=new ArrayList<>(N+1);
        for(int i=0;i<=N;i++) adj.add(new LinkedList<>());
        for(int i=0;i<M;i++){
            tokens=new StringTokenizer(reader.readLine());
            int u=Integer.parseInt(tokens.nextToken());
            int v=Integer.parseInt(tokens.nextToken());
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        int [] sources=new int[source_size];
        int [] destinations=new int[destinations_size];

        tokens=new StringTokenizer(reader.readLine());
        for(int i=0;i<source_size;i++){
            sources[i]=Integer.parseInt(tokens.nextToken());
        }

        tokens=new StringTokenizer(reader.readLine());
        for(int i=0;i<destinations_size;i++){
            destinations[i]=Integer.parseInt(tokens.nextToken());
        }

        int[] lengths=bfs(adj, sources, N);
        for(int i=0;i<destinations_size;i++){
            writer.print(lengths[destinations[i]]+" ");
        }
        writer.println();
        writer.close();  
    }


    public static int[]  bfs(ArrayList<List<Integer>> adj,int[] sources,  int N){
        int [] dis=new int[N+1];
        Queue<Integer> queue=new LinkedList<>();
        Arrays.fill(dis,-1); // dis[num]=-1 means the vertex is unvisited
        for(int source: sources){
            dis[source]=0;
            queue.offer(source);
        }

        while(!queue.isEmpty()){
            int u=queue.poll();
            List<Integer> list=adj.get(u);
            for(int num:list){
                if(dis[num]==-1){ // dis[num]=-1 means the vertex is unvisited
                    dis[num]=1+dis[u];
                    queue.offer(num);
                }
            }
        }
        return dis;


    }
}

/*
---

### 🔹 Standard BFS (single source)

When we do BFS from **one node**, we explore the graph layer by layer:

* distance = 0 → the source itself
* distance = 1 → all neighbors
* distance = 2 → neighbors of neighbors, and so on.

So at the end, `dist[x]` is the shortest path length from that source to node `x`.

---

### 🔹 What if we have multiple sources?

Suppose we want the distance from the **nearest source** to every node.

Naïve idea (your first code): run BFS once per source, then take the minimum distance.
But that repeats the same work many times.

Instead, BFS itself already finds the *minimum distance* if we **start from all sources at once**.

---

### 🔹 Multi-source BFS trick

We push **all source nodes into the queue initially**, each with `distance = 0`.
This is like saying:

> "I have multiple fires burning at the same time, and I want to know when each node catches fire."

🔥 Fire spreads level by level → BFS ensures the *first time a node is reached is the shortest path from the nearest fire (source)*.

---

### 🔹 Example

Graph:

```
1 - 2 - 3 - 4
```

Sources = `{1, 4}`
Destinations = `{2, 3}`

Multi-source BFS queue initially = `[1,4]`, with `dist[1]=0, dist[4]=0`.

* Step 1: process `1` → neighbor `2` gets `dist[2]=1`.
* Step 2: process `4` → neighbor `3` gets `dist[3]=1`.
* Step 3: process `2` → neighbor `3` already has distance `1`, so no update.

Final distances:

```
dist[1]=0, dist[4]=0, dist[2]=1, dist[3]=1
```

That’s exactly the nearest distance from *any source*.

---

### 🔹 Why it works

Because BFS explores in increasing distance order, the **first time a node is discovered** is guaranteed to be via the shortest path from the nearest source.
By putting all sources at distance `0` in the queue at once, they compete fairly, and BFS naturally gives the correct minimum distance.

---

So the big idea:

* Single BFS = nearest from one source.
* Multi-source BFS = nearest from *any* source.

---

 */