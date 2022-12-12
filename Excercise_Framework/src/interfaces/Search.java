package interfaces;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import main.Graph.Node;

public interface Search {
	public boolean find(Set<Node> nodes, List<Property> props, Node n);
}
