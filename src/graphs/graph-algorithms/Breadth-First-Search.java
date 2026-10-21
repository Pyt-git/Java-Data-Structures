// Breadth-First-Search invariants: 

// Vertex visits are done by distance from the starting vertex. 
// Example: all vertices 1 unit away from the start point are visited before all distance-2 vertices, before distance-3 vertices, etc. 

// All visited but unprocessed vertices are enqueued. 
// All discovered vertices are marked as visited 

// Example with shortestPath: Let A discover B and C, B discover D and C discover E. 
// Processing: B -> A, C -> A, D -> B, E -> C, A -> null. 
// call shortestPath(A, E) returns [E, C, A] and [A, C, E] after reversing. 

// Example with FIFO: Let A discover B and C, B discover D and E. 
// Let B be discovered before C and D discovered before E. 
// From [A]: pop A. From [B, C]: pop B, then C. From [D, E]: pop D, then E. 
// Traversal order: [A, B, C, D, E]

import java.util.ArrayList; 
import java.util.Set; 
import java.util.HashSet; 
import java.util.List; 
import java.util.Queue; 
import java.util.ArrayDeque; 
import java.util.Map; 
import java.util.Collections; 

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

  public Map<T, T> parentMap(T start) {
    Objects.requireNonNull(start, "Starting vertex does not exist"); 

    Set<T> visited = new HashSet<>(); 
    Queue<T> queue = new ArrayDeque<>(); 
    Map<T, T> parents = new HashMap<>(); 

    queue.offer(start); 
    visited.add(start); 

    parents.put(start, null); 
    while (!queue.isEmpty()) {
      T current = queue.poll(); 

      if (!visited.contains(neighbor)) {
        visited.add(neighbor); 
        parentMap.put(neighbor, current); // linking neighbor (child) to current (parent)
        queue.offer(neighbor); 
      }
    }

    return parentMap; 
  }

  public List<T> shortestPath(T start, T target) {
    Objects.requireNonNull(start, "Starting vertex does not exist"); 

    Map<T, T> parentMap = parentMap(start); 
    List<T> path = new ArrayList<>(); 

    if (!parentMap.contains(target)) {
      throw new IllegalArgumentException("Target is unreachable"); 
    }

    T current = target; 
    while (current != null) {
      path.add(current); 
      current = parentMap.get(current); 
    }

    Collections.reverse(path); 
    return path; 
  }
}
