package interfaces;

import java.util.HashSet;
import java.util.List;

import main.Graph;
import main.Graph.Node;

public interface Transformation {
	public Graph transform(Graph g);
}
