import java.util.ArrayList;

public class Project1 {

    public static final int NIL = Integer.MIN_VALUE;
    public static final int MAX = Integer.MAX_VALUE;

    public static final int NODE = 0;
    public static final int EDGE = 1;

    public static final int CHILD = 0;
    public static final int PARENT = 1;

    public static final int MARK = 1;

    public static void main(String[] args) {
        ArrayList<String> list = Utilities.readFromFile("TestCase_P1.txt");

        for (int i = 0; true;) {
            int[] grph = Utilities.toIntArray(list.get(i).split(" "));
            if (grph[0] == 0 && grph[1] == 0)
                break;
            Graph g = new Graph(grph[NODE]);
            i++;
            for (int j = 0; j < grph[EDGE]; j++, i++) {
                int[] relation = Utilities.toIntArray(list.get(i).split(" "));
                g.add(relation[CHILD], relation[PARENT]);
            }
            g.print();
//            System.out.println(g.isLinear());
            System.out.println(g.status());

        }
    }

    public static class Graph {
        private final int[][] graph;
        private final int[][] cycle;
        private final int size;

        private int edges = 0;
        private boolean linear = true;

        public Graph(int size) {
            this.size = size;
            graph = new int[size + 1][size + 1];
            cycle = new int[2][size + 1];
        }

        public void add(int x, int y) {
            if (x < graph.length && y < graph.length) {
                graph[x][y] = MARK;
                edges++;
            }
        }

        private final int d = 0;
        private final int p = 1;

        public void relax() {
            for (int i = 0; i < size; i++) {
                cycle[d][i] = MAX;
                cycle[p][i] = NIL;
            }

            cycle[d][0] = 0;
            for (int i = 0; i < size -1; i++)
                for (int j = 0; j < edges; j++) {

                }
//                    if (cycle[d][])
        }

        public void print() {
            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph.length; j++)
                    System.out.print(graph[i][j] + " ");
                System.out.println();
            }
        }

        public String status() {
            if (null == null)
                return "Infeasible game";
            if (linear)
                return "Linear gameplay possible";
            else
                return "Nonlinear gameplay possible";
        }
    }

}
