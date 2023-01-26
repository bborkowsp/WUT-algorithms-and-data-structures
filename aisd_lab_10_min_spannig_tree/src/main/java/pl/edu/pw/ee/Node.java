package pl.edu.pw.ee;

public class Node {
    char from;
    char to;
     int weight;

    public Node(char from) {
        this.from = from;
    }

    public char getFrom() {
        return from;
    }

    public char getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }
    public Node(char to,int weight){
        this.weight = weight;
         this.to = to;
    }
}
