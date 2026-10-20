// Breadth-First-Search invariants: 

// Vertex visits are done by distance from the starting vertex. 
// Example: all vertices 1 unit away from the start point are visited before all distance-2 vertices, before distance-3 vertices, etc. 

// All visited but unprocessed vertices are enqueued. 
// All discovered vertices are marked as visited 

import java.util.ArrayList; 
import java.util.Set; 
import java.util.HashSet; 
import java.util.List; 
import java.util.Queue; 
import java.util.ArrayDeque; 

public class BreadthFirstSearch<T> {
  private final AdjacencyMatrixGraph<T> graph; 

  public BreadthFirstSearch(AdjacencyMatrixGraph<T> graph) {
    this.graph = graph; 
  }

  public List<T> traverse(T start) {
    Objects.requireNonNull(start, "start vertex cannot be null"); 

    if (!graph.containsVertex(start)) {
      throw new IllegalArgumentException("Vertex does not exist"); 
    }

    List<T> traversalOrder = new ArrayList<>(); 
    Set<T> visited = new HashSet<>(); 
    Queue<T> queue = new ArrayDeque<>(); 

    queue.offer(start); 
    visited.add(start); 

    while (!queue.isEmpty()) {
      T current = queue.poll(); 
      traversalOrder.add(current); 

      for (T neighbor : graph.neighborsOf(current)) {
        if (!visited.contains(neighbour)) {

          visited.add(neighbor); 
          queue.offer(neighbor); 
        }
      }
    }

    return traversalOrder; 
  }
