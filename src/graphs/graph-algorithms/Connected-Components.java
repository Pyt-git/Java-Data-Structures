// Connected components invariants: 
// 1. Every vertex belonging to an already discovered graph component is marked visited. 

// Suppose there are three graph components: 
// A---B---C, D---E and F. 
// After first DFS run: visited = {A, B, C}.
// After second DFS run: visited = {A, B, C, D, E}. 
// After third DFS run: visited = {A, B, C, D, E, F}.

// 2. Since every vertex reachable from start vertex has been marked as visited, 
//    One call of helper dfs(start, visited) generates one graph component. 

// Taking example graph from the first invariant, final visited = {A, B, C, D, E, F};
// componentCount = 3. 

import java.util.ArrayDeque; 
import java.util.Deque; 
import java.util.HashSet; 
import java.util.Set; 
import java.util.Objects; 

public class ConnectedComponents<T> {
  private final AdjacencyMatrixGraph<T> graph; 

  public ConnectedComponents(AdjacencyMatrixGraph<T> graph) {
    this.graph = graph; 
  }

  public int countComponents() {
    Set<T> visited = new HashSet<>(); 

    int componentCount = 0; 
    for (T vertex : graph.vertices()) {

      if (!visited.contains(vertex)) {
        dfs(vertex, visited); 

        componentCount++; 
      }
    }

    return componentCount; 
  }

  private void dfs(T start, Set<T> visited) {
    Objects.requireNonNull(start, "Starting vertex cannot be null"); 
    
    Deque<T> = stack = new ArrayDeque<>(); 
    stack.push(start); 
    visited.add(start); 

    while (!stack.isEmpty()) {
      T current = stack.pop(); 
 
      for (T neighbor : graph.neighborsOf(current)) {
        if (!visited.contains(neighbor)) {

          visited.add(neighbor); 
          stack.push(neighbor); 
        }
      }
    }
  }
}
