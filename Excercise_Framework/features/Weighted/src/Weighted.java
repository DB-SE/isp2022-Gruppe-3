import java.awt.Color;
import java.util.HashMap;
import java.util.List;

import interfaces.Property;
import main.Graph.Edge;
import main.Graph.Node;

public class Weighted implements Property {


	private HashMap<Edge,Integer> map = new HashMap<Edge,Integer>();
	
	@Override
	public void add(Node n, Color c, String l) {
		//do nothing		
	}

	@Override
	public void add(Edge e, int w) {
		map.put(e, w);
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
	public int getWeight(Edge e) {
		if(!map.containsKey(e)) 
			return Integer.MIN_VALUE;
		return map.get(e);
	}
		
	@Override
	public void print() {
		System.out.println("Weighted");
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
