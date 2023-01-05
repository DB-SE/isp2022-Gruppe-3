import java.util.ArrayList;
import java.util.List;

public class Graph {
	
	public static class Edge {
		private Node src, dest;
		
		public Edge(Node src_node, Node dest_node) {
			src = src_node;
			dest = dest_node;
		}
		
		public Node getSrc() {
			return src;
		}
		
		public Node getDest() {
			return dest;
		}
		
	}
	
	public Edge addEdge(Node n1, Node n2, int weight) {
		Edge e = new Edge(n1,n2);
		n1.addEdge(e);
		return e;
	}
	
	public List<Edge> getAdjEdges(Node n){
		List<Edge> edges = new ArrayList<Edge>(n.edgeList);
		return edges;
	}
	
}