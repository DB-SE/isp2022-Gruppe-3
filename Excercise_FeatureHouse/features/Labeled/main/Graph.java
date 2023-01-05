package main;

import java.awt.Color;

public class Graph {
	public static class Node {
		public String label;
	}
	
	public Node addNode(Node node, Color c, String l) throws IllegalArgumentException {
		Node n = original(node, c, l);
		n.label = l;
		return n;
	}
}
