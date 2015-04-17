import java.util.ArrayList;

public class Project {

    public static final int NIL = Integer.MIN_VALUE;

    public static final int NODE = 0;
    public static final int EDGE = 1;

    public static final int CHILD = 0;
    public static final int PARENT = 1;

    public static final int MARK = 1;

    public static void main(String[] args) {
        ArrayList<String> list = Utilities.readFromFile();

        for (int i = 0; true;) {
            int[] grph = Utilities.toIntArray(list.get(i).split(" "));
            if (grph[0] == 0 && grph[1] == 0)
                break;
            Graph g = new Graph(grph[NODE]);
            i++;
            for (int j = 0; j < grph[EDGE]; j++, i++) {
                int[] relation = Utilities.toIntArray(list.get(i).split(" "));
                System.out.print(relation[0] + "," + relation[1] + " ");
                g.add(relation[CHILD], relation[PARENT]);
            }
            System.out.println();
            System.out.println(g.status());
        }
    }

    public static class Graph {
        private final int[][] graph;
        private int edges = 0;

        public Graph(int size) {
            graph = new int[size + 1][size + 1];
        }

        public void add(int x, int y) {
            if (x < graph.length && y < graph.length) {
                graph[x][y] = MARK;
                edges++;
            }
        }

        public void print() {
            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph.length; j++)
                    System.out.print(graph[i][j] + " ");
                System.out.println();
            }
        }

        public boolean hasCycles() {
//            for ()
            return false;
        }

        public String status() {
            print();
            return "Testing";
        }
    }

}
