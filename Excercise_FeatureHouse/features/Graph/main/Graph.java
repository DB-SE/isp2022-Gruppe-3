package main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import java.util.Set;


public class Graph {
	
	private HashSet<Node> nodeSet = new HashSet<Node>();
	private int ind = 0;
	
	public Graph() {}
	
	public Set<Node> getNodes(){
		return nodeSet;
	}
	
	public int size() {
		return nodeSet.size();
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
	
}


