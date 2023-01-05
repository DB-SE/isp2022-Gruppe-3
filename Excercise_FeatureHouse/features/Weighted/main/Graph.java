package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {
	public static class Edge {
		public int weight;
	}
	
	public Edge addEdge(Node n1, Node n2, int weight) {
		Edge e = original(n1, n2, weight);
		e.weight = weight;
		return e;
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
				out[labels.get(i)][e.dest.index] = e.weight;
			}
		}
		
		return out;
	}
}
