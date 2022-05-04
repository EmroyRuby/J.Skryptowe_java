public class Edge<T> {
    T edge;
    int weight;
    Edge(T s, int w){
        edge = s;
        weight = w;
    }

    public T getEdge() {
        return edge;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString(){
        return "(" + edge + ", weight: " + weight + ")";
    }
}
