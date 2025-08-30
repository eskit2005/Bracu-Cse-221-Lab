public class GraphGenerator {
    public static void main(String[] args) {
        int lastTwoDigits1=1; 
        int lastTwoDigits2=3;

        int d1=lastTwoDigits1+1;
        int d2=lastTwoDigits2+1;
        System.out.println("d1 = " + d1 + ", d2 = " + d2);
        int edgeCount=0;
        int i=0;
        boolean[][]used=new boolean[10][10]; 

        while (edgeCount<15) { 
            int u=((i ^ d1) + i * i) % 10;
            int v=((i ^ d2) + i) % 10;
            if (u!=v && !used[u][v]) {
                used[u][v]=true;
                edgeCount++;
                System.out.println("i=" + i + "  Edge: " + u + " -> " + v);
            }

            i++;
        }

        System.out.println("Total edges generated: " + edgeCount);
    }
}
