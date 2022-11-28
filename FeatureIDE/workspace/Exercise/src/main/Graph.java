package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class Graph {
	
	public static class Edge {
		Node src, dest;
		
		public Edge(Node src_node, Node dest_node) {
			src = src_node;
			dest = dest_node;
		}
		
		//#if Weighted
		int weight = 1;

		public Edge(Node src_node, Node dest_node, int w) {
			src = src_node;
			dest = dest_node;
			weight = w;
		}
		//#endif
	}

	public static class Node {
		String label = "";
		ArrayList<Edge> edgeList = new ArrayList<Edge>();

		public Node() {
		}
		
		//#if Labeled
		public Node(String s) {
			label = s;
		}
		//#endif

		public void addEdge(Edge e) {
			edgeList.add(e);
		}
		//#if DFS || MST
		int visits = 0;
		//#endif
		
		//#if Colored
//@		Color color;
//@		public void setColor(Color color) {
//@			this.color = color;
//@		}
//@		
//@		public Color getColor() {
//@			return color;
//@		}
		//#endif
		
	}

	private HashMap<String,Node> nodeSet = new HashMap<String, Node>();
	private int ind = 0;
	
	public Graph() {}
	
	public Node addNode(Node node) throws IllegalArgumentException {
		if (nodeSet.containsValue(node))throw new IllegalArgumentException("The Node already exists in this graph");
		nodeSet.put(ind + "",node);
		node.label = ind + "";
		ind++;
		return node;
	}
	
	//#if Labeled
	public Node addNode(String label) throws IllegalArgumentException {
		if (nodeSet.containsKey(label))
			throw new IllegalArgumentException("The Node already exists in this graph");
		Node n = new Node(label);
		nodeSet.put(label,n);
		ind++;
		return n;
	}

	public Node getNode(String label)
		throws NoSuchElementException {
		Node n = nodeSet.get(label);
		if (n == null)
			throw new NoSuchElementException();
		return n;
	}
	
		//#if Weighted
	
	public void addEdge(String label1, String label2, int weight) {
		Node node1 = getNode(label1);
		Node node2 = getNode(label2);
		addEdge(node1, node2, weight);
	}
		//#endif
		//#if !Weighted	
//@	public void addEdge(String label1, String label2) {
//@		Node node1 = getNode(label1);
//@		Node node2 = getNode(label2);
//@		addEdge(node1, node2);
//@	}
		//#endif
	//#endif

	//#if Weighted
	public void addEdge(Node node1, Node node2, int weight) {
		node1.addEdge(new Edge(node1, node2, weight));
		//#if !directed
			node2.addEdge(new Edge(node2, node1, weight));
		//#endif
	}
	//#endif
	//#if !Weighted
//@	public void addEdge(Node node1, Node node2) {
//@		node1.addEdge(new Edge(node1, node2));
		//#if !directed
//@			node2.addEdge(new Edge(node2, node1));
		//#endif
//@	}
	//#endif
	
	public int size() {
		return nodeSet.size();
	}

	//#if DFS 
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
	//#endif
	
	//#if MST
//@	public Graph mst() { 
//@		
//@		//Set all nodes to "not visited"
//@		for(Entry<String, Node> entry : nodeSet.entrySet()) {
//@			entry.getValue().visits = 0;
//@		}
//@		
//@		Graph g = new Graph(); // output graph
//@		
//@		Node n = ((Entry<String, Node>)nodeSet.entrySet().toArray()[0]).getValue();
//@		
		//#if Labeled
//@		g.addNode(n.label);
		//#else
//@		Node n_ = new Node();
//@		g.addNode(n_);
		//#endif
//@		n.visits++;
//@		
//@		// edges available from current mst, sorted by weight
//@		TreeMap<Integer, Edge> edges = new TreeMap<Integer, Edge>();
//@		
//@		//add outgoing edged from current node to available edges
//@		for(Edge e : n.edgeList) {
//@			edges.put(e.weight, e);
//@		}
//@		
//@		while(true) {
//@			Node m = null;
//@			Edge f = null;
//@			
//@			List<Entry<Integer,Edge>> obsolete = new ArrayList<Entry<Integer,Edge>>();
//@			
//@			//pick edge with lowest weight
//@			for(Entry<Integer, Edge> entry : edges.entrySet()) {
//@				Edge e = entry.getValue();
//@				if(e.dest.visits == 0) {
//@					m = e.dest;
//@					f = e;
//@					break;
//@				}
//@				else {
//@					obsolete.add(entry);
//@				}
//@			}
//@			
//@			if(m == null) break; //no available, unvisited nodes left
//@			
//@			//remove edges pointing to visited nodes
//@			for(Entry<Integer,Edge> e : obsolete)
//@				edges.remove(e.getKey(), e.getValue());
//@
//@			//add outgoing edged from current node to available edges
//@			for(Edge e : m.edgeList) {
//@				edges.put(e.weight, e);
//@			}
//@			
//@			//add node and edge to output graph
			//#if Labeled
//@			g.addNode(m.label);
//@			g.addEdge(f.src.label, f.dest.label, f.weight);
			//#else
//@			Node m_ = new Node();
//@			g.addNode(m_);
//@			g.addEdge(n_, m_, f.weight);
			//#endif
//@			m.visits++;
//@			
//@		}
//@		return g;
//@	}
	//#endif
	
	public boolean[][] getAdjMatrix(){
		boolean[][] out = new boolean[size()][size()];
		HashMap<String, Integer> labels = new HashMap<String, Integer>();
		
		for(int i = 0; i < size(); i++) {
			labels.put(((Entry<String, Node>)nodeSet.entrySet().toArray()[i]).getKey(), i);
		}
		
		for(int i = 0; i < size(); i++) {
			Node n = ((Entry<String, Node>)nodeSet.entrySet().toArray()[i]).getValue();
			for(Edge e : n.edgeList) {
				out[i][labels.get(e.dest.label)] = true;
			}
		}
		
		return out;
	}
}
