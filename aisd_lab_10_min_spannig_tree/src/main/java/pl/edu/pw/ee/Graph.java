package pl.edu.pw.ee;

import java.util.ArrayList;

public class Graph {

    private final ArrayList<ArrayList<Node>> adjacencyLists;

    public Graph() {
        this.adjacencyLists = new ArrayList<>();
    }

    public ArrayList<ArrayList<Node>> getAdjacencyLists() {
        return adjacencyLists;
    }

    public void addEdge(Node from, Node toWithWeigNode) {
        boolean flag = false;
        for (int i = 0; i < adjacencyLists.size(); i++) {
            if (adjacencyLists.get(i).get(0).getFrom() == (from.getFrom())) {
                flag = true;
            }
        }
        if (!flag) {
            ArrayList<Node> newList = new ArrayList<>();
            newList.add(from);
            adjacencyLists.add(newList);
            newList.add(toWithWeigNode);
        } else {
            for (int i = 0; i < adjacencyLists.size(); i++) {
                if (adjacencyLists.get(i).get(0).getFrom() == from.getFrom()) {
                    adjacencyLists.get(i).add(toWithWeigNode);
                }
            }
        }
    }
    
    public ArrayList<Node> getadjList(char v){
        for(int i = 0 ; i < adjacencyLists.size(); i++){
            if(v == adjacencyLists.get(i).get(0).getFrom()){
                return adjacencyLists.get(i);
            }
        }
        return null;
    }

    /*    public void readGraphFromFile(String pathToFile) throws IOException {
    BufferedReader bufferedReader = null;
    FileReader fileReader = null;
    File graphFile = new File(pathToFile + "\\graph.txt");
    String line = "";
    try {
    fileReader = new FileReader(graphFile);
    bufferedReader = new BufferedReader(fileReader);
    while ((line = bufferedReader.readLine()) != null) {
    String[] splittedLine = line.split("\\s++");
    validateSplittedLine(splittedLine);
    connectionList.add(new Edge(splittedLine[0].charAt(0), splittedLine[1].charAt(0), Integer.parseInt(splittedLine[2])));
    }
    } catch (FileNotFoundException e) {
    throw new FileNotFoundException("Error, the file not found!");
    } catch (IOException e) {
    throw new IOException("Error, cannot read from tests file");
    } finally {
    try {
    if (fileReader != null) {
    fileReader.close();
    }
    } catch (IOException e) {
    throw new IOException("Error, cannot close the input stream of file");
    }
    try {
    if (bufferedReader != null) {
    bufferedReader.close();
    }
    } catch (IOException e) {
    throw new IOException("Error, cannot close the object reading from the file");
    }
    }
    }
    
    private void validateSplittedLine(String[] splitttedLine) {
    if (splitttedLine.length != 3) {
    throw new IllegalArgumentException();
    }
    for (int i = 0; i < splitttedLine.length - 1; i++) {
    if (splitttedLine[i].length() != 1 || (splitttedLine[i].charAt(0) <= 64 || splitttedLine[i].charAt(0) >= 91)) {
    throw new IllegalArgumentException();
    }
    }
    if (Integer.parseInt(splitttedLine[2]) < 0) {
    throw new IllegalArgumentException();
    }
    }*/
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge(new Node('A'), new Node('B', 3));
        graph.addEdge(new Node('A'), new Node('C', 5));
        graph.addEdge(new Node('A'), new Node('D', 7));
        graph.addEdge(new Node('B'), new Node('C', 1));
        graph.addEdge(new Node('C'), new Node('D', 1));
        graph.addEdge(new Node('D'), new Node('E', 7));
        
        PrimAlgorithm pa = new PrimAlgorithm(graph);
  //     pa.findMST(graph);
        System.out.println(pa.findMST(graph) );
    }
}
