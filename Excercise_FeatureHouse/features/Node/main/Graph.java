import java.awt.Color;
import java.util.ArrayList;


public class Graph {
	
	public static class Node {
		public int index = -1;
		public ArrayList<Edge> edgeList = new ArrayList<Edge>();

		public Node() {}
		
		public Node(int i) {
			index = i;
		}

		public void addEdge(Edge e) {
			edgeList.add(e);
		}		
	}
	
	public Node addNode(Node node, Color c, String l) throws IllegalArgumentException {
		if (nodeSet.contains(node))throw new IllegalArgumentException("The Node already exists in this graph");
		nodeSet.add(node);
		if(node.index == -1)
			node.index = ind;
		ind++;
		
		return node;
	}
	
}
