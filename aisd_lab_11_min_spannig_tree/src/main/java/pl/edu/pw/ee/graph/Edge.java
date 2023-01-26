package pl.edu.pw.ee.graph;

import com.sun.org.apache.xpath.internal.operations.Equals;
import static java.lang.String.format;

public class Edge {

    private final Node src;
    private final Node dest;
    private final int weight;

    public Edge(Node src, Node dest, int weight) {
        validateInput(src, dest);

        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    public Node getSrc() {
        return src;
    }

    public Node getDest() {
        return dest;
    }

    public int getWeight() {
        return weight;
    }
    
    @Override
    public boolean equals(Object e){
        Edge edge = (Edge) e;
        if(this.getSrc() ==  edge.getSrc() && this.getDest() == edge.getDest()){
            return true;
        }
        else
            return false;
    }

    @Override
    public String toString() {
        return format("Edge[src: %s, dest: %s, weight: %d]", src, dest, weight);
    }

    private void validateInput(Node start, Node end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Input params (start or end) cannot be null!");
        }
    }

}
