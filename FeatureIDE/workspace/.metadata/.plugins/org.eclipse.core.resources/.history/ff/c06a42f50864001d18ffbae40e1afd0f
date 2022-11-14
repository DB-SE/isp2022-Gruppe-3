package main;

import main.Graph.Edge;
import main.Graph.Node;

public class Main {
	
	public static void main(String[] args) {		
		
		// Testing
		Graph g = new Graph(Graph.WEIGHTED | Graph.LABELED);
		g.addNode("N1");
		g.addNode("N2");
		g.addNode("N3");
		g.addNode("N4");
		g.addNode("N5");
		g.addNode("N6");
		g.addNode("N7");
		g.addEdge("N1", "N2", 5);
		g.addEdge("N2", "N5", 4);
		g.addEdge("N1", "N4", 7);
		g.addEdge("N1", "N3", 2);
		g.addEdge("N3", "N4", 4);
		g.addEdge("N4", "N5", 7);
		g.addEdge("N4", "N6", 2);
		g.addEdge("N4", "N7", 6);
		g.addEdge("N6", "N7", 5);
		g.addEdge("N5", "N7", 1);
		System.out.println("Nodes : " + g.size());
		g.dfs();
		Graph mst = g.mst();

		System.out.println("Nodes : " + mst.size());
		
		for(int i = 1; i <= 7; i++) {

			Node n = mst.getNode("N" + i);
			System.out.println(n.label);
			for(Edge e : n.edgeList) {
				System.out.println("    links to " + e.dest.label);
			}
		}
	}
	
}
