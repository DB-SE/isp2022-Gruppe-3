import java.awt.Color;
import java.util.HashMap;
import java.util.List;

import interfaces.Property;
import main.Graph.Edge;
import main.Graph.Node;

public class Labeled implements Property {

	HashMap<Node, String> map = new HashMap<Node, String>();
	
	@Override
	public void print() {
		System.out.println("Labeled");
	}

	@Override
	public void add(Node n, Color c, String l) {
		map.put(n, l);
	}

	@Override
	public Color getColor(Node n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLabel(Node n) {
		return map.get(n);
	}

	@Override
	public void add(Edge e, int w) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getWeight(Edge e) {
		// TODO Auto-generated method stub
		return Integer.MIN_VALUE;
	}

	@Override
	public List<Edge> getAddEdges(Node n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Edge getTwin(Edge e) {
		// TODO Auto-generated method stub
		return null;
	}

}
