package main;

public class Graph {
	
	public Edge addEdge(Node n1, Node n2, int weight) {
		
		Edge oe = original(n1, n2, weight);
		Edge e = new Edge(n2, n1);
		e.weight = oe.weight;
		n2.addEdge(e);
		
		return e;
	}
}
