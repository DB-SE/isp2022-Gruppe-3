package main;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Graph {
	HashMap<Node, Integer> visits = new HashMap<Node, Integer>();
	
	public boolean find(Node n) {
		
		System.out.println("DFS search");
		
		visits.clear();

		for(Node node : nodeSet) {
			if(node == n) return true;
					
			boolean result = false;
			if(!visits.containsKey(node)) {
				result = dfs_visit(node, n);
			}
			if (result) return true;
		}
		return false;
	}
	
	private boolean dfs_visit(Node currentNode, Node node) {
		visits.put(currentNode, 1);
		for(Edge e : currentNode.edgeList) {
			Node d = e.getDest();
			if(d == node) return true;
			if (!visits.containsKey(d))
				return dfs_visit(d, node);
		}
		return false;
	}
}
