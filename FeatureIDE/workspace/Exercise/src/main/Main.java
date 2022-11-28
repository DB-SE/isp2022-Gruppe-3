package main;

import java.awt.Color;
import main.Graph.Edge;
import main.Graph.Node;

public class Main {
	
	public static void main(String[] args) {		
		// Testing
		Graph g = new Graph();
		
		//#if Labeled
		g.addNode("N1");
		g.addNode("N2");
		g.addNode("N3");
		g.addNode("N4");
		g.addNode("N5");
		g.addNode("N6");
		g.addNode("N7");
		
		//#if Colored
		g.getNode("N1").setColor(Color.BLACK);
		g.getNode("N2").setColor(Color.RED);
		g.getNode("N3").setColor(Color.BLUE);
		g.getNode("N4").setColor(Color.GREEN);
		g.getNode("N5").setColor(Color.YELLOW);
		g.getNode("N6").setColor(Color.MAGENTA);
		g.getNode("N7").setColor(Color.CYAN);
		//#endif
		
		//#if Weighted
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
		//#else
//@		g.addEdge("N1", "N2");
//@		g.addEdge("N2", "N5");
//@		g.addEdge("N1", "N4");
//@		g.addEdge("N1", "N3");
//@		g.addEdge("N3", "N4");
//@		g.addEdge("N4", "N5");
//@		g.addEdge("N4", "N6");
//@		g.addEdge("N4", "N7");
//@		g.addEdge("N6", "N7");
//@		g.addEdge("N5", "N7");
		//#endif
		
		//#else
//@		Node[] nodes = new Node[7];
//@		for(int i = 0; i < 7; i++) {
//@			nodes[i] = new Node();
//@			g.addNode(nodes[i]);
//@		}
//@		
		//#if Colored
//@		nodes[0].setColor(Color.BLACK);
//@		nodes[1].setColor(Color.RED);
//@		nodes[2].setColor(Color.BLUE);
//@		nodes[3].setColor(Color.GREEN);
//@		nodes[4].setColor(Color.YELLOW);
//@		nodes[5].setColor(Color.MAGENTA);
//@		nodes[6].setColor(Color.CYAN);
		//#endif
//@		
		//#if Weighted
//@		g.addEdge(nodes[0], nodes[1], 5);
//@		g.addEdge(nodes[1], nodes[4], 4);
//@		g.addEdge(nodes[0], nodes[3], 7);
//@		g.addEdge(nodes[0], nodes[2], 2);
//@		g.addEdge(nodes[2], nodes[3], 4);
//@		g.addEdge(nodes[3], nodes[4], 7);
//@		g.addEdge(nodes[3], nodes[5], 2);
//@		g.addEdge(nodes[3], nodes[6], 6);
//@		g.addEdge(nodes[5], nodes[6], 5);
//@		g.addEdge(nodes[4], nodes[6], 1);
		//#else
//@		g.addEdge(nodes[0], nodes[1]);
//@		g.addEdge(nodes[1], nodes[4]);
//@		g.addEdge(nodes[0], nodes[3]);
//@		g.addEdge(nodes[0], nodes[2]);
//@		g.addEdge(nodes[2], nodes[3]);
//@		g.addEdge(nodes[3], nodes[4]);
//@		g.addEdge(nodes[3], nodes[5]);
//@		g.addEdge(nodes[3], nodes[6]);
//@		g.addEdge(nodes[5], nodes[6]);
//@		g.addEdge(nodes[4], nodes[6]);
		//#endif 
//@		
		//#endif 
		
		System.out.println("Nodes : " + g.size());
		//#if DFS
		g.dfs();
		//#endif
		
		//Adjacency Matrix Test
		boolean[][] matr = g.getAdjMatrix();
		String pr = "";
		for(int i = 0; i < matr.length; i++) {
			for(int j = 0; j < matr.length; j++) {
				pr += (matr[i][j] ? 1 : 0) + " ";
			}
			pr += "\n";
		}
		System.out.println(pr);
		
		
		//#if MST
		Graph mst = g.mst();
		System.out.println("Nodes : " + mst.size());
		
		for(int i = 1; i <= 7; i++) {

			//#if Labeled
			Node n = mst.getNode("N" + i);
			//#else
//@			Node n = nodes[i-1];
			//#endif
			System.out.println(n.label);
			for(Edge e : n.edgeList) {
				System.out.println("    links to " + e.dest.label);
			}
		}
		//#endif
	}
	
}
