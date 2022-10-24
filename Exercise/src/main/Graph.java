package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class Graph {
	public static class Edge {
		Node src;
		Node dest;
		int weight;

		public Edge(Node src_node, Node dest_node, int c) {
			src = src_node;
			dest = dest_node;
			weight = c;
		}
	}

	public static class Node {
		String label;
		int visits = 0;
		ArrayList<Edge> edgeList = new ArrayList<Edge>();

		public Node(String s) {
			label = s;
		}

		public void addEdge(Edge e) {
			edgeList.add(e);
		}
		
	}

	private HashMap<String,Node> nodeSet =
			new HashMap<String, Node>();
	
	public Graph() {}
	
	public Node addNode(String label) throws IllegalArgumentException {
		if (nodeSet.containsKey(label))
			throw new IllegalArgumentException("The Node already exists in this graph");;
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
	
	public void addEdge(String label1, String label2, int cost) {
		Node node1 = getNode(label1);
		Node node2 = getNode(label2);
		node1.addEdge(new Edge(node1, node2, cost));
		node2.addEdge(new Edge(node2, node1, cost));
	}
	
	public int size() {
		return nodeSet.size();
	}

	
	public void dfs() {
		//Set all nodes to "not visited"
		for(Entry<String, Node> entry : nodeSet.entrySet()) {
			entry.getValue().visits = 0;
		}
		
		for(Entry<String, Node> entry : nodeSet.entrySet()) {
			if(entry.getValue().visits == 0)
				dfs_visit(entry.getKey());
		}
	}
	
	void dfs_visit(String label) {
		Node n = nodeSet.get(label);
		n.visits++;
		for(Edge e : n.edgeList) {
			Node d = e.dest;
			if (d.visits == 0)
				dfs_visit(d.label);
		}
		n.visits++;
	}
	
	public Graph mst() {

		//Set all nodes to "not visited"
		for(Entry<String, Node> entry : nodeSet.entrySet()) {
			entry.getValue().visits = 0;
		}
		
		Graph g = new Graph(); // output graph
		
		Node n = ((Entry<String, Node>)nodeSet.entrySet().toArray()[0]).getValue();
		
		g.addNode(n.label);
		n.visits++;
		
		// edges available from current mst
		TreeMap<Integer, Edge> edges = new TreeMap<Integer, Edge>();
		
		//add outgoing edged from current node to available edges
		for(Edge e : n.edgeList) {
			edges.put(e.weight, e);
		}
		
		while(true) {
			Node m = null;
			Edge f = null;
			List<Entry<Integer,Edge>> obsolete = new ArrayList<Entry<Integer,Edge>>();
			for(Entry<Integer, Edge> entry : edges.entrySet()) {
				Edge e = entry.getValue();
				if(e.dest.visits == 0) {
					m = e.dest;
					f = e;
					break;
				}
				else {
					obsolete.add(entry);
				}
			}
			
			if(m == null) break;
			
			for(Entry<Integer,Edge> e : obsolete)
				edges.remove(e.getKey(), e.getValue());

			//add outgoing edged from current node to available edges
			for(Edge e : m.edgeList) {
				edges.put(e.weight, e);
			}
			
			g.addNode(m.label);
			g.addEdge(f.src.label, f.dest.label, f.weight);
			m.visits++;
			
		}
		return g;
	}
}