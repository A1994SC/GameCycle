import java.io.*;
import java.util.*;

public class BellmanFord  {
    LinkedList<Edge> edges;
    int d[], p[];
    int node, edge, size;
    final int INFINITY=999;

    private static class Edge  {
        int u,v,w;

        public Edge(int a, int b, int c)     {
            u=a;
            v=b;
            w=c;
        }
    }

    BellmanFord () throws IOException {
        int item;
        edges = new LinkedList<Edge>();
        BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));

        System.out.print("Enter number of vertices ");
        node = Integer.parseInt(inp.readLine());

        System.out.println("Cost Matrix");
        for(int i=0;i< node;i++) {
            for(int j=0;j< node;j++)   {
                item = Integer.parseInt(inp.readLine());
                if(item != 0)
                    edges.add(new Edge(i,j,item));
            }
        }

        edge = edges.size();
        d = new int[node];
        p = new int[node];

        System.out.print("Enter the source vertex ");
        size = Integer.parseInt(inp.readLine());
    }

    void relax() {
        int i,j;
        for(i=0;i< node;++i) {
            d[i]=INFINITY;
            p[i] = -1;
        }

        d[size] = 0;
        for (i = 0; i < node - 1; ++i) {
            for (j = 0; j < edge; ++j) { //here i am calculating the shortest path
                if (d[edges.get(j).u] + edges.get(j).w < d[edges.get(j).v]) {
                    d[edges.get(j).v] = d[edges.get(j).u] + edges.get(j).w;
                    p[edges.get(j).v] = edges.get(j).u;
                }
             }
         }
    }

    boolean cycle() {
        int j;
        for (j = 0; j < edge; ++j)
            if (d[edges.get(j).u] + edges.get(j).w < d[edges.get(j).v])
                 return false;
        return true;
    }

    void print() {
        for (int i = 0; i < node; i++) {
            System.out.println("Vertex " + i + " has predecessor " + p[i]);
        }
    }

    public static void main(String args[]) throws IOException   {
        BellmanFord  r = new BellmanFord ();
        r.relax();
        if(r.cycle()) {
            for(int i=0;i<r.node;i++)
                System.out.println(r.size +" ==> "+r.d[i]);
        } else {
            System.out.println(" There is a negative edge cycle ");
        }

        r.print();
    }
}