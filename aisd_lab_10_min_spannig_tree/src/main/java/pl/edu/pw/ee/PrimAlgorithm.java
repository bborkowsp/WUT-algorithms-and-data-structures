package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import pl.edu.pw.ee.services.MinSpanningTree;

public class PrimAlgorithm implements MinSpanningTree {

    private final PriorityQueue<Edge> PQ;
    private final ArrayList<Character> visited;
    private final Queue<Edge> MST;
    private long weight;

    public PrimAlgorithm(Graph graph) {
        this.weight = 0;
        this.PQ = new PriorityQueue<>(graph.getAdjacencyLists().size());
        this.visited = new ArrayList<>();
        this.MST = new LinkedList<>();
    }

    @Override
    public String findMST(Graph graph) {
        visitNode(graph, graph.getAdjacencyLists().get(0).get(0).getFrom());
        while (!PQ.isEmpty()) {
            Edge e = PQ.poll();
            if (visited.contains(e.getTo()) && visited.contains(e.getFrom())) {
                continue;
            }
            MST.offer(e);
            weight += e.getWeight();
            if (!visited.contains(e.getFrom())) {
                visitNode(graph, e.from);
            }
            if (!visited.contains(e.getTo())) {
                visitNode(graph, e.to);
            }
        }
        return makeResult();
    }

    private String makeResult() {
        String result = "";
        while (!MST.isEmpty()) {
            Edge e = MST.poll();
            result += e.getFrom() + "_" + e.getWeight() + "_" + e.getTo();
            result += "|";
        }
        return result.substring(0, result.length() - 1);
    }

    private void visitNode(Graph graph, char v) {
        visited.add(v);
        if (graph.getadjList(v) != null) {
            for (int i = 1; i < graph.getadjList(v).size(); i++) {
                if (!visited.contains(graph.getadjList(v).get(i).getTo())) {
                    PQ.offer(new Edge(v, graph.getadjList(v).get(i).getTo(), graph.getadjList(v).get(i).getWeight()));
                }
            }
        }
    }

}
