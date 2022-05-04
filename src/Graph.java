import java.util.*;
import py4j.GatewayServer;

public class Graph<T> {
    int number = 0;
    private Map<T, List<Edge <T>>> map = new HashMap<>();

    public void addVertex(T s){
        number += 1;
        map.put(s, new ArrayList<Edge <T>>());
    }

    public void addEdge(T source, T dest, int weight){
        if (!map.containsKey(source)){
            addVertex(source);
        }
        if (!map.containsKey(dest)){
            addVertex(dest);
        }
        map.get(source).add(new Edge<T>(dest, weight));
    }

    public void addEdge(T source, T dest){
        if (!map.containsKey(source)){
            addVertex(source);
        }
        if (!map.containsKey(dest)){
            addVertex(dest);
        }
        map.get(source).add(new Edge<T>(dest, 1));
    }

    private String DFSUtil(T v, Map<T, Boolean> visited, String s){
        if (!visited.containsKey(v)){
            visited.put(v, true);
        }
        System.out.print(v + " ");
        s += v + " ";
        for (Edge<T> n : map.get(v)) {
            if (!visited.containsKey(n.getEdge()))
                s = DFSUtil(n.getEdge(), visited, s);
        }
        return s;
    }

    public String DFS(T v){
        Map<T, Boolean> visited = new HashMap<>();

        return DFSUtil(v, visited, "");
    }

    public String BFS(T v)
    {
        Map<T, Boolean> visited = new HashMap<>();

        LinkedList<T> queue = new LinkedList<T>();

        StringBuilder s = new StringBuilder();
        visited.put(v, true);
        queue.add(v);

        while (queue.size() != 0)
        {
            v = queue.poll();
            System.out.print(v+" ");
            s.append(v).append(" ");
            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            for (Edge<T> n: map.get(v)) {
                if (!visited.containsKey(n.getEdge())) {
                    visited.put(n.getEdge(), true);
                    queue.add(n.getEdge());
                }
            }
        }
        return s.toString();
    }

    public Graph<T> getGraph(){
        return new Graph<>();
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        for (T v : map.keySet()) {
            builder.append(v.toString()).append(": ");
            for (Edge <T> w : map.get(v)) {
                builder.append(w.toString()).append(" ");
            }
            builder.append("\n");
        }

        return (builder.toString());
    }

    public static void main(String[] args) {
        GatewayServer gatewayServer = new GatewayServer(new Graph<Integer>());
        gatewayServer.start();
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 3);
        graph.addEdge(4, 3);
        graph.addEdge(3, 5);
        System.out.println(graph);
        graph.DFS(1);
        System.out.println();
        graph.BFS(1);
    }
}
