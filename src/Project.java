import java.util.ArrayList;
import java.util.List;

public class Project {

    public static final int NIL = Integer.MIN_VALUE;
    public static final int MAX = Integer.MAX_VALUE;

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
                System.out.print(relation[0] + "," + relation[1] + " : ");
                g.add(relation[CHILD], relation[PARENT]);
                System.out.print(g.hasCycles(relation[PARENT]) + " ");
            }
            System.out.println();
            System.out.println(g.status());
        }
    }

    public static class Graph {
        private ArrayList<Node> list;

        public Graph(int size) {
            list = new ArrayList<Node>(size);
            for (int i = 0; i < size; i++)
                list.add(new Node(i));
        }

        public void add(int x, int y) {
            list.get(x).parent = list.get(y);
        }

        public void print() {
            for (int i = 0; i < list.size(); i++)
                System.out.println(list.get(i).toString());
        }

        public Node getParent(int s) {
            return null;
        }

        public boolean hasCycles(int source) {
            return false;
        }

        public String status() {
            print();
            return "Testing";
        }
    }

    public static class Node {

        public static final int noParent = -17;

        public Node parent;
        final int index;

        Node(int i) {
            index = i;
        }

        public int getParent() {
            if (parent != null)
                return parent.index;
            else
                return noParent;
        }

        public int getEndParent() {
            if (parent != null)
                return parent.getEndParent();
            else
                return index;
        }

        @Override
        public String toString() {
            String parent = "";
            if (parent != null)
                parent = this.parent.toString();
            return index + "->" + parent;
        }
    }

}
