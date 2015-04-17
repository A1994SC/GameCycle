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
//            for (int j = 0; j < grph[EDGE]; j++) {
            System.out.print(g.hasCycles() + " ");
//            }
            System.out.println();
            System.out.println(g.status());
        }
    }

    public static class Graph {
        private final int[][] graph;
        private final int size;

        public Graph(int size) {
            this.size = size;
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

        private final int MAX = Integer.MAX_VALUE;
        private final int MIN = Integer.MIN_VALUE;
        private final int maxI = 1;
        private final int minI = 0;

        public boolean hasCycles() {
            int[][] dist = new int[2][size + 1];
            for (int i = 0; i < size; i++)
                dist[maxI][i] = MAX;
            for (int i = 0; i < size; i++)
                dist[minI][i] = MIN;

            for (int k = 1; k <= size; k++)
                for (int i = 1; i <= size; i++)
                    for (int j = 1; j <= size; j++) {
                        if (graph[i][j] != MAX && dist[minI][j] > (dist[minI][i] + graph[i][j]))
                            dist[minI][i] = (dist[minI][i] + graph[i][j]);
                        if (graph[i][j] != MIN && dist[maxI][j] < (dist[maxI][i] + graph[i][j]))
                            dist[maxI][i] = (dist[maxI][i] + graph[i][j]);
                    }

            boolean flag = false;
            for (int i = 1; i <= size; i++)
                for (int j = 1; j <= size; j++) {
                    if (graph[i][j] != MAX && dist[minI][j] > (dist[minI][i] + graph[i][j]))
                        flag = true;
                    if (graph[i][j] != MIN && dist[maxI][j] < (dist[maxI][i] + graph[i][j]))
                        flag = true;
                }

            return flag;
        }

        public String status() {
            print();
            return "Testing";
        }
    }

}
