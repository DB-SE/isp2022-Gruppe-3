package main;

public class Main {
	
	public static void main(String[] args) {		
		Graph g = new Graph();
		g.addNode("N1");
		g.addNode("N2");
		g.addNode("N3");
		g.addNode("N4");
		g.addNode("N5");
		g.addEdge("N1", "N2", 1);
		g.addEdge("N1", "N3", 2);
		g.addEdge("N2", "N4", 2);
		g.addEdge("N4", "N5", 2);
		System.out.println("Nodes : " + g.size());
		g.dfs();
		System.out.println("N1: " + g.getNode("N1").getVisits());
		System.out.println("N2: " + g.getNode("N2").getVisits());
		System.out.println("N3: " + g.getNode("N3").getVisits());
		System.out.println("N4: " + g.getNode("N4").getVisits());
		System.out.println("N5: " + g.getNode("N5").getVisits());
	}
	
}
