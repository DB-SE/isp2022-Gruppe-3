import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import interfaces.Property;
import main.Graph.Edge;
import main.Graph.Node;

public class Undirected implements Property {

	HashMap<Node, List<Edge>> edges = new HashMap<Node, List<Edge>>();
	HashMap<Edge, Edge> twins = new HashMap<Edge, Edge>();
	
	@Override
	public void print() {
		System.out.println("Undirected");
	}

	@Override
	public void add(Node n, Color c, String l) {
		//do nothing
	}

	@Override
	public Color getColor(Node n) {
		return null;
	}

	@Override
	public String getLabel(Node n) {
		return null;
	}

	@Override
	public void add(Edge e, int w) {
		Node dest = e.getDest();
		if(!edges.containsKey(dest)) {
			edges.put(dest, new ArrayList<Edge>());
		}
		Edge newEdge = new Edge(dest, e.getSrc());
		edges.get(dest).add(newEdge);
		twins.put(newEdge, e);
	}

	@Override
	public int getWeight(Edge e) {
		return Integer.MIN_VALUE;
	}

	@Override
	public List<Edge> getAddEdges(Node n) {
		return edges.get(n);
	}

	@Override
	public Edge getTwin(Edge e) {
		return twins.get(e);
	}

}
