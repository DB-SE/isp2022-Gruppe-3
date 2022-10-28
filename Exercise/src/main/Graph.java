package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class Graph {
	
	public static byte WEIGHTED = 0b001;
	public static byte DIRECTED = 0b010;
	public static byte LABELED =  0b100;
	
	public static class Edge {
		Node src, dest;
		int weight;

		public Edge(Node src_node, Node dest_node, int w) {
			src = src_node;
			dest = dest_node;
			weight = w;
		}
		
		public Edge(Node src_node, Node dest_node) {
			src = src_node;
			dest = dest_node;
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
	private boolean weighted = false;
	private boolean directed = false;
	private boolean labeled = false;
	private int ind = 0;
	
	public Graph(int configFlags) {
		weighted = (configFlags & WEIGHTED) == WEIGHTED;
		directed = (configFlags & DIRECTED) == DIRECTED;
		labeled = (configFlags & LABELED) == LABELED;
	}
	public Graph() {}
	
	public int getConfigFlags() {
		int out = 0;
		if(weighted) out |= WEIGHTED;
		if(directed) out |= DIRECTED;
		if(labeled) out |= LABELED;
		return out;
	}
	
	public Node addNode(String label) throws IllegalArgumentException {
		if (nodeSet.containsKey(label))
			throw new IllegalArgumentException("The Node already exists in this graph");;
		Node n = new Node(label);
		nodeSet.put(label,n);
		ind++;
		return n;
	}
	public Node addNode(Node node) throws IllegalArgumentException {
		if (nodeSet.containsValue(node))throw new IllegalArgumentException("The Node already exists in this graph");
		nodeSet.put(ind + "",node);
		node.label = ind + "";
		ind++;
		return node;
	}
	
	public Node getNode(String label)
		throws NoSuchElementException {
		if(!labeled) throw new IllegalArgumentException("Trying to find a Node by label in an unlabeled graph.");
		Node n = nodeSet.get(label);
		if (n == null)
			throw new NoSuchElementException();
		return n;
	}
	
	public void addEdge(String label1, String label2, int weight) {
		if(!labeled) throw new IllegalArgumentException("Trying to apply label in an unlabeled graph.");
		if(!weighted) weight = 1;
		Node node1 = getNode(label1);
		Node node2 = getNode(label2);
		addEdge(node1, node2, weight);
	}
	
	public void addEdge(String label1, String label2) {
		if(!labeled) throw new IllegalArgumentException("Trying to apply label in an unlabeled graph.");
		Node node1 = getNode(label1);
		Node node2 = getNode(label2);
		addEdge(node1, node2);
	}

	public void addEdge(Node node1, Node node2, int weight) {
		if(!weighted) weight = 1;
		node1.addEdge(new Edge(node1, node2, weight));
		if(!directed)node2.addEdge(new Edge(node2, node1, weight));
	}
	
	public void addEdge(Node node1, Node node2) {
		node1.addEdge(new Edge(node1, node2));
		if(!directed)node2.addEdge(new Edge(node2, node1));
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
		if(directed) throw new IllegalArgumentException("MST not applicable to directed graph.");
		
		//Set all nodes to "not visited"
		for(Entry<String, Node> entry : nodeSet.entrySet()) {
			entry.getValue().visits = 0;
		}
		
		Graph g = new Graph(getConfigFlags()); // output graph
		
		Node n = ((Entry<String, Node>)nodeSet.entrySet().toArray()[0]).getValue();
		
		g.addNode(n.label);
		n.visits++;
		
		// edges available from current mst, sorted by weight
		TreeMap<Integer, Edge> edges = new TreeMap<Integer, Edge>();
		
		//add outgoing edged from current node to available edges
		for(Edge e : n.edgeList) {
			edges.put(e.weight, e);
		}
		
		while(true) {
			Node m = null;
			Edge f = null;
			
			List<Entry<Integer,Edge>> obsolete = new ArrayList<Entry<Integer,Edge>>();
			
			//pick edge with lowest weight
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
			
			if(m == null) break; //no available, unvisited nodes left
			
			//remove edges pointing to visited nodes
			for(Entry<Integer,Edge> e : obsolete)
				edges.remove(e.getKey(), e.getValue());

			//add outgoing edged from current node to available edges
			for(Edge e : m.edgeList) {
				edges.put(e.weight, e);
			}
			
			//add node and edge to output graph
			g.addNode(m.label);
			g.addEdge(f.src.label, f.dest.label, f.weight);
			m.visits++;
			
		}
		return g;
	}
}