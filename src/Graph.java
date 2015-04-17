import java.util.ArrayList;

public class Graph {

    public static final int NIL = Integer.MIN_VALUE;
    public static final int MARK = 1;

    public static void main(String[] args) {
        ArrayList<String> list = Utilities.readFromFile();

        for (int i = 0; i < list.size(); i++)
            System.out.println(list.get(i));

//        for (int i = 0; true;) {
//
//        }
    }

    public final int[][] graph;

    public Graph(int size) {
        graph = new int[size + 1][size + 1];
    }

    public void add(int x, int y) {
        if (x < graph.length && y < graph.length)
            graph[x][y] = MARK;
    }

    public void print() {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++)
                System.out.print(graph[i][j] + " ");
            System.out.println();
        }
    }

}
