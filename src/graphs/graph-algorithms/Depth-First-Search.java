// Depth first search graph algorithm: 
// Invariants: 

// DFS uses stack (LIFO). 
// All discovered vertices are marked visited while being pushed onto the stack. 
// Stack contains unprocessed vertices. 
// Complexity: O(V + E) since every vertex is visited at most once. 

// Example with LIFO: let A discover B and C, B discover D and E. 
// And B is discovered before C while D is discovered before E. 

// Stack when DFS reaches A: [A], when DFS reaches B and C: [B, C] and when DFS reaches D and E: [D, E]. 
// From [B, C]: pop C then pop B. From [D, E]: pop E then pop D. 

// Traversal order: [A, C, B, E, D] 

import java.util.ArrayDeque; 
import java.util.ArrayList; 
import java.util.Deque; 
import java.util.HashSet; 
import java.util.List; 
import java.util.Set; 
import java.util.Objects; 

public class DepthFirstSearch<T> {
  private final AdjacencyMatrixGraph<T> graph; 

  public DepthFirstSearch(AdjacencyMatrixGraph<T> graph) {
    this.graph = graph; 
  }

  public List<T> traverse(T start) {
    Objects.requireNonNull(start, "Start vertex cannot be null"); 

    if (!graph.containsVertex(start)) {
      throw new IllegalArgumentException("Vertex does not exist"); 
    }

    Deque<T> stack = new ArrayDeque<>(); 
    Set<T> visited = new HashSet<>(); 
    List traversalOrder = new ArrayList<>(); 

    stack.push(start); 
    visited.add(start); 

    while (!stack.isEmpty()) {
      T current = stack.pop(); 
      traversalOrder.add(current); 

      for (T neighbor : graph.neighborsOf(current)) {
        if (!visited.contains(neighbor)) {
          
          visited.add(neighbor); 
          stack.push(neighbor); 
        }
      }
    }

    return traversalOrder; 
  }
