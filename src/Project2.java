import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Project2 {

    public static class Graph {

        public static class Edge {
            protected final String val1, val2;
            protected final int distEdge;
            protected final boolean auto;

            public Edge(String v1, String v2, int _dist, boolean isAuto) {
                this.val1 = v1;
                this.val2 = v2;
                this.auto = isAuto;
                if (isAuto)
                    this.distEdge = _dist;
                else
                    this.distEdge = _dist * 2;
            }

        }

        public static class Vertex implements Comparable<Vertex> {
            protected final String name;
            protected final Map<Vertex, Integer> neighbours = new HashMap<>();
            protected int dist = Integer.MAX_VALUE;
            protected Vertex previous = null;

            public Vertex(String _name) {
                this.name = _name;
            }

            public void printPath() {
                if (this == this.previous)
                    System.out.printf("%s( 0)", this.name);
                else if (this.previous == null)
                    System.out.printf("%s(unreached)", this.name);
                else {
                    this.previous.printPath();
                    System.out.printf(" -> %s(%2d)", this.name, this.dist);
                }
            }

            public int distance() {
                if (this == this.previous)
                    return 0;
                else if (this.previous == null)
                    return -1;
                else {
                    int t = this.previous.distance();
                    return (t < dist ? dist : t);
                }
            }

            @Override
            public int compareTo(Vertex o) {
                return Integer.compare(dist, o.dist);
            }
        }

        private final Map<String, Vertex> graph;

        public Graph(Edge[] _edge) {
            graph = new HashMap<>(_edge.length);

            for (Edge e : _edge) {
                if (!graph.containsKey(e.val1))
                    graph.put(e.val1, new Vertex(e.val1));
                if (!graph.containsKey(e.val2))
                    graph.put(e.val2, new Vertex(e.val2));
            }

            for (Edge e : _edge) {
                graph.get(e.val1).neighbours.put(graph.get(e.val2), e.distEdge);
                graph.get(e.val2).neighbours.put(graph.get(e.val1), e.distEdge);
            }
        }

        public void dijkstra(String startName) {
            if (!graph.containsKey(startName))
                return;

            final Vertex source = graph.get(startName);
            TreeSet<Vertex> q = new TreeSet<>();

            for (Vertex v : graph.values()) {
                v.previous = v == source ? source : null;
                v.dist = v == source ? 0 : Integer.MAX_VALUE;
                q.add(v);
            }

            dijkstra(q);
        }

        private void dijkstra(final TreeSet<Vertex> q) {
            Vertex u, v;
            while (!q.isEmpty()) {
                u = q.pollFirst();
                if (u.dist == Integer.MAX_VALUE)
                    break;
                for (Map.Entry<Vertex, Integer> a : u.neighbours.entrySet()) {
                    v = a.getKey();

                    final int alternateDist = u.dist + a.getValue();
                    if (alternateDist < v.dist) {
                        q.remove(v);
                        v.dist = alternateDist;
                        v.previous = u;
                        q.add(v);
                    }
                }
            }
        }

        public void printPath(String endName) {
            if (!graph.containsKey(endName))
                return;

            graph.get(endName).printPath();
            System.out.println();
        }

        public int getDistance(String endName) {
            if (!graph.containsKey(endName))
                return -1;

            return graph.get(endName).distance();
        }

        public void printAllPaths() {
            for (Vertex v : graph.values()) {
                v.printPath();
                System.out.println();
            }
        }

    }

    public static void main(String[] args) {
        ArrayList<String> list = Utilities.readFromFile("TestCase_P2.txt");

        String[] array = null;
        Graph.Edge[] edgeArray = null;
        Graph graph = null;

        int i = 2;
        int egdes = Utilities.toIntArray(list.get(i).split(" "))[0];
        i++;
        edgeArray = new Graph.Edge[egdes];
        for (int j = 0; j < egdes; j++, i++) {
            array = list.get(i).split(" ");
            edgeArray[j] = new Graph.Edge(array[0], array[1], Integer.parseInt(array[2]), (array[3].equals("a")));
        }
        String[] t = list.get(i).split(" ");
        int check = Utilities.toIntArray(t)[0];
        graph = new Graph(edgeArray);
        i++;
        for (int j = 0; j < check; j++, i++) {
            array = list.get(i).split(" ");
            graph.dijkstra(array[j++]);
            graph.printPath(array[j]);
        }
    }

}
