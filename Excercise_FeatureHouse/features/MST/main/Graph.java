package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;

public class Graph {
	
	HashSet<Node> visited = new HashSet<Node>();
	HashMap<Node, Node> twins = new HashMap<Node, Node>();
	
	public Graph mst() {
		
		visited.clear();
		
		Graph g = this;
		
		Graph out = new Graph(); // output graph
		
		Node n = (Node)g.getNodes().toArray()[0];
		
		Node new_n = new Node(n.index);
		
		twins.put(n, new_n);
		
		out.addNode(new_n, n.color, n.label);
		visited.add(n);
		
		// edges available from current mst, sorted by weight
		TreeMap<Integer, Edge> edges = new TreeMap<Integer, Edge>();
		
		//add outgoing edged from current node to available edges
		for(Edge e : g.getAdjEdges(n)) {
			edges.put(e.weight, e);
		}
		
		while(true) {
			Node m = null;
			Edge f = null;
			
			List<Entry<Integer,Edge>> obsolete = new ArrayList<Entry<Integer,Edge>>();
			
			//pick edge with lowest weight
			for(Entry<Integer, Edge> entry : edges.entrySet()) {
				Edge e = entry.getValue();
				if(!visited.contains(e.getDest())) {
					m = e.getDest();
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
			for(Edge e : g.getAdjEdges(m)) {
				edges.put(e.weight, e);
			}
			
			//add node and edge to output graph
			Node new_m = new Node(m.index);
			
			twins.put(m, new_m);
			
			out.addNode(new_m, m.color, m.label);
			visited.add(m);
			out.addEdge(twins.get(f.getSrc()), new_m, f.weight);			
		}	
		return out;
	}
}
