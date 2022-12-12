package interfaces;

import java.awt.Color;
import java.util.List;

import main.Graph.Edge;
import main.Graph.Node;

public interface Property {
	public void print();
	public void add(Node n, Color c, String l);
	public Color getColor(Node n);
	public String getLabel(Node n);
	
	public void add(Edge e, int w);
	public int getWeight(Edge e);
	public List<Edge> getAddEdges(Node n);
	public Edge getTwin(Edge e);
}
