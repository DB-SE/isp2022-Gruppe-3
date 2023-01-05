package main;

import java.awt.Color;

public class Graph {
	public static class Node {
		public Color color;
	}
	
	public Node addNode(Node node, Color c, String l) throws IllegalArgumentException {
		Node n = original(node, c, l);
		n.color = c;
		return n;
	}
	
}
