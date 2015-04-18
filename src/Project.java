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
                g.add(relation[CHILD], relation[PARENT]);
            }
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

        public int isLinear() {
            int count = 0, temp = 0;
            for (int i = 0; i < size + 1; i++) {
                temp = 0;
                for (int j = 0; j < size + 1; j++)
                    if (graph[j][i] == MARK)
                        temp++;
                if (temp > count)
                    count = temp;
            }
            return count;
        }

        public boolean isCycle() {
            for (int i = 0; i < graph.length; i++)
                for (int j = 0; j < graph.length; j++)
                    if (graph[i][j] == MARK && graph[j][i] == MARK)
                        return true;
            return false;
        }


        public String status() {
            if (isCycle())
                return "Infeasible game";
            if (isLinear() > 1)
                return "Nonlinear gameplay possible";
            else
                return "Linear gameplay possible";
        }
    }

}
