package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

public class Graph {
	// Kante
	static class Edge {
		Node dest; // Zielknoten
		int cost; // Kantengewicht

		public Edge(Node n, int c) {
			dest = n;
			cost = c;
		}

		public Node getDestNode() {
			return dest;
		}

		public int getCost() {
			return cost;
		}
	}

	// Knoten
	static class Node {
		String label; // Knotenbezeichner
		int visits = 0;
		// Adjazenzliste
		ArrayList<Edge> adjList = new ArrayList<Edge>();

		public Node(String s) {
			label = s;
		}

		public String getLabel() {
			return label;
		}

		public void addEdge(Edge e) {
			adjList.add(e);
		}

		public Iterator<Edge> getEdges() {
			return adjList.iterator();
		}

		public Edge getEdgeTo(Node n) {
			for (Edge e : adjList) {
				if (e.dest.equals(n))
					return e;
			}
			return null;
		}
		
		public int getVisits() {
			return visits;
		}
		
	}

	// Verzeichnis alles Knoten des Graphen
	private HashMap<String,Node> nodeSet =
			new HashMap<String, Node>();
	
	public Graph() {}
	
	public Node addNode(String label) {
//		throws NodeAlreadyDefinedException {
//		if (nodeSet.containsKey(label))
//			throw new NodeAlreadyDefinedException();
		Node n = new Node(label);
		nodeSet.put(label,n);
		return n;
	}
	
	public Node getNode(String label)
		throws NoSuchElementException {
		Node n = nodeSet.get(label);
		if (n == null)
			throw new NoSuchElementException();
		return n;
	}
	
	public void addEdge(String src, String dest, int cost) {
		Node srcNode = getNode(src);
		Node destNode = getNode(dest);
		srcNode.addEdge(new Edge(destNode, cost));
	}
	
	public int size() {
		return nodeSet.size();
	}

	
	public void dfs() {
		for(Entry<String, Node> entry : nodeSet.entrySet()) {
			entry.getValue().visits = 0;
		}
		
		for(Entry<String, Node> entry : nodeSet.entrySet()) {
			if(entry.getValue().visits == 0)
				visit(entry.getKey());
		}
		
	}
	
	void visit(String label) {
		Node n = nodeSet.get(label);
		System.out.println(label + ": " + n.visits);
		n.visits++;
		for(Edge e : n.adjList) {
			Node d = e.dest;
			if (d.visits == 0)
				visit(d.label);
		}
		n.visits++;
	}
	
	
}