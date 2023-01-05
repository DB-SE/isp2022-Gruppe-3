
import java.awt.Color;

import main.Graph;
import main.Graph.Node;

public class Main {
	
	public static void main(String[] args) {		
		// Testing
		
		System.out.println("Start");
		
		Graph g = new Graph();
		
		Color[] colors = new Color[]{Color.BLACK, Color.BLUE, Color.CYAN, Color.GRAY, Color.GREEN, Color.MAGENTA, Color.ORANGE};
		
		Node[] nodes = new Node[7];
		for(int i = 0; i < 7; i++) {
			nodes[i] = new Node();
			g.addNode(nodes[i], colors[i], "Node " + (i+1));
		}
		
		for(int i = 0; i < 7; i++) {
			System.out.println(nodes[i].label + " " + nodes[i].color);
		}

		
		g.addEdge(nodes[1 - 1], nodes[2 - 1], 5);
		g.addEdge(nodes[2 - 1], nodes[5 - 1], 4);
		g.addEdge(nodes[1 - 1], nodes[4 - 1], 7);
		g.addEdge(nodes[1 - 1], nodes[3 - 1], 2);
		g.addEdge(nodes[3 - 1], nodes[4 - 1], 4);
		g.addEdge(nodes[4 - 1], nodes[5 - 1], 7);
		g.addEdge(nodes[4 - 1], nodes[6 - 1], 2);
		g.addEdge(nodes[4 - 1], nodes[7 - 1], 6);
		g.addEdge(nodes[6 - 1], nodes[7 - 1], 5);
		g.addEdge(nodes[5 - 1], nodes[7 - 1], 1);

		System.out.println("Nodes : " + g.size());

		boolean search = g.find(nodes[6]);
		
		System.out.println("Search result : " + search);

		
		//Adjacency Matrix Test
		boolean[][] matr = g.getAdjMatrix();
		printMat(matr);
		
		//Adjacency Matrix Test
		int[][] matr2 = g.getWeightMatrix();
		printMat(matr2);
		
		
		Graph mst = g.mst();
		System.out.println("Nodes : " + mst.size());
		
		//Adjacency Matrix Test
		boolean[][] matr3 = mst.getAdjMatrix();
		printMat(matr3);
		
	}
	
	public static void printMat(int[][] mat) {
		String pr = "";
		for(int i = 0; i < mat.length; i++) {
			for(int j = 0; j < mat.length; j++) {
				pr += mat[i][j] + " ";
			}
			pr += "\n";
		}
		System.out.println(pr);
	}

	public static void printMat(boolean[][] mat) {
		String pr = "";
		for(int i = 0; i < mat.length; i++) {
			for(int j = 0; j < mat.length; j++) {
				pr += (mat[i][j] ? 1 : 0) + " ";
			}
			pr += "\n";
		}
		System.out.println(pr);
	}
	
}
