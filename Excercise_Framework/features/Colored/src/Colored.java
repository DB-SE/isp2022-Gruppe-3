import java.awt.Color;
import java.util.HashMap;
import java.util.List;

import interfaces.Property;
import main.Graph.Edge;
import main.Graph.Node;

public class Colored implements Property {

	private HashMap<Node,Color> map = new HashMap<Node,Color>();
	
	@Override
	public void add(Node n, Color c, String l) {
		map.put(n, c);	
	}

	@Override
	public void add(Edge e, int w) {
		//do nothing
	}

	@Override
	public Color getColor(Node n) {
		return map.get(n);
	}

	@Override
	public String getLabel(Node n) {
		return null;
	}

	@Override
	public int getWeight(Edge e) {
		return Integer.MIN_VALUE;
	}
	
	
	@Override
	public void print() {
		System.out.println("Colored");
	}

	@Override
	public List<Edge> getAddEdges(Node n) {
		return null;
	}

	@Override
	public Edge getTwin(Edge e) {
		// TODO Auto-generated method stub
		return null;
	}
}
