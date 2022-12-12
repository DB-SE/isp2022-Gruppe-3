import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

import interfaces.Property;
import interfaces.Search;
import main.Graph.Edge;
import main.Graph.Node;

public class BFS implements Search {
	
	Queue<Node> queue = new LinkedBlockingQueue<>();
	HashSet<Node> visited = new HashSet<Node>();
	
	@Override
	public boolean find(Set<Node> nodes, List<Property> props, Node n) {
		
		System.out.println("BFS search");
		
		queue.clear();
		visited.clear();
		
		for(Node node : nodes) {
			
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
