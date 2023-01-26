package pl.edu.pw.ee;

public class Edge implements  Comparable<Edge>{
    char from;
    char to;
    int weight;

    public Edge(char from, char to, int weight) {
        this.to = to;
        this.weight = weight;
        this.from = from;
    }

    public char getTo() {
        return to;
    }
    public char getFrom() {
        return from;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge o) {
        if(getWeight() < o.getWeight()){
            return -1;
        }
        else if(getWeight() > o.getWeight()){
            return 1;
        }
        return 0;
    }
    
}
