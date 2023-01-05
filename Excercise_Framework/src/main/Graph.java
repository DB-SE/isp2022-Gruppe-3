package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import interfaces.Property;
import interfaces.Search;
import interfaces.Transformation;
import loader.PluginLoader;

public class Graph {
	
	List<Property> properties = PluginLoader.load(Property.class);
	List<Search> searches = PluginLoader.load(Search.class);
	List<Transformation> transformations = PluginLoader.load(Transformation.class);
	
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

	public static class Node {
		public int index = -1;
		public ArrayList<Edge> edgeList = new ArrayList<Edge>();

		public Node() {}
		
		public Node(int i) {
			index = i;
		}

		public void addEdge(Edge e) {
			edgeList.add(e);
		}		
	}

	private HashSet<Node> nodeSet = new HashSet<Node>();
	private int ind = 0;
	
	public Graph() {
		for(Property p : properties) {
			p.print();
		}
	}
	
	public Node addNode(Node node, Color c, String l) throws IllegalArgumentException {
		if (nodeSet.contains(node))throw new IllegalArgumentException("The Node already exists in this graph");
		nodeSet.add(node);
		if(node.index == -1)
			node.index = ind;
		ind++;
		
		for(Property p : properties) {
			p.add(node, c, l);
		}
		
		return node;
	}
	
	public Color getColor(Node n) { 
		for(Property p : properties) {
			Color c = p.getColor(n);
			if(c != null)
				return c;
		}
		return null;
	}
	
	public String getLabel(Node n) {	
		for(Property p : properties) {
			String l = p.getLabel(n);
			if(l != null)
				return l;
		}
		return null;
	}
	
	public void addEdge(Node n1, Node n2, int weight) {
		Edge e = new Edge(n1,n2);
		n1.addEdge(e);
		
		for(Property p : properties) {
			p.add(e, weight);
		}
	}
	
	public int size() {
		return nodeSet.size();
	}
	
	public boolean find(Node n) {
		for(Search s : searches) {
			boolean result = s.find(nodeSet, properties, n);
			if(result) return true;
		}
		return false;
	}
	
	public boolean[][] getAdjMatrix(){
		boolean[][] out = new boolean[size()][size()];
		HashMap<Integer, Integer> labels = new HashMap<Integer, Integer>();
		
		List<Node> entries = new ArrayList<Node>(nodeSet);
		
		for(int i = 0; i < entries.size(); i++) {
			labels.put(i, entries.get(i).index);
		}
		
		for(int i = 0; i < size(); i++) {
			Node n = entries.get(i);
			
			List<Edge> edges = getAdjEdges(n);
			
			for(Edge e : edges) {
				out[labels.get(i)][e.dest.index] = true;
			}
		}
		
		return out;
	}
	
	public int[][] getWeightMatrix(){
		int[][] out = new int[size()][size()];
		HashMap<Integer, Integer> labels = new HashMap<Integer, Integer>();
		
		List<Node> entries = new ArrayList<Node>(nodeSet);
		
		for(int i = 0; i < entries.size(); i++) {
			labels.put(i, entries.get(i).index);
		}
		
		for(int i = 0; i < size(); i++) {
			Node n = entries.get(i);
			
			List<Edge> edges = getAdjEdges(n);
			
			for(Edge e : edges) {
				out[labels.get(i)][e.dest.index] = getWeight(e);
			}
		}
		
		return out;
	}
	
	public int getWeight(Edge e) {
		int w = Integer.MIN_VALUE;
		
		for(Property p : properties) {
			w = p.getWeight(e);
			if(w != Integer.MIN_VALUE) break;
		}
		if(w == Integer.MIN_VALUE) {
			for(Property p : properties) {
				Edge twin = null;
				for(Property p2 : properties) {
					twin = p2.getTwin(e);
					if(twin != null) break;
				}
				w = p.getWeight(twin);
				if(w != Integer.MIN_VALUE) break;
			}
		}
		
		return w;
	}
	
	public List<Edge> getAdjEdges(Node n){
		List<Edge> edges = new ArrayList<Edge>(n.edgeList);
		
		for(Property p : properties) {
			List<Edge> l = p.getAddEdges(n);
			if(l != null)
				edges.addAll(l);
		}
		return edges;
	}
	
	public Set<Node> getNodes(){
		return nodeSet;
	}
	
	public Graph mst() {
		for(Transformation t : transformations) {
			return t.transform(this);
		}
		return null;
	}
	
}


