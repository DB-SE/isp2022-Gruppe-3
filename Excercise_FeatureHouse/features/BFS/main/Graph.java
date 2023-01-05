package main;

import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class Graph {

	Queue<Node> queue = new LinkedBlockingQueue<Node>();
	HashSet<Node> visited = new HashSet<Node>();
	
	public boolean find(Node n) {
		
		System.out.println("BFS search");
		
		queue.clear();
		visited.clear();
		
		for(Node node : nodeSet) {
			
			queue.add(node);
			
			while(!queue.isEmpty()) {
				Node cn = queue.poll();
				visited.add(cn);
				
				if(cn == n) return true;
				
				for(Edge e : cn.edgeList) {
					Node dest = e.getDest();
					if(!visited.contains(dest)) {
						queue.add(dest);
					}
				}				
			}			
		}		
		return false;
	}
}
