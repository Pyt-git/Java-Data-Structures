// Directed cycle detection graph algorithm invariants:

// 1. Every discovered vertex is marked as visited. 
// 2. recursionStack contains every active DFS call. 
// 3. A directed cycle exists if DFS reaches a neighbor currently in the stack. 

// Example: 

// Suppose graph is: 
//   A---B
//   |   |
//   |   |
//   D---C

// First call: dfs(A). A is visited and A is actively explored: 
// visited = {A}, recursionStack = {A}. 

// Second call: dfs(B). B is visited and both A and B are actively explored: 
// visited = {A, B}, recursionStack = {A, B}. 

// Third call: dfs(C). C is visited and A, B and C are actively explored:
// visited = {A, B, C}, recursionStack = {A, B, C}. 

// Fourth call: dfs(D). D is visited and all vertices are actively explored: 
// visited = {A, B, C, D}, recursionStack = {A, B, C, D}. 

// Fifth call: dfs(A). A is visited again and in the stack:
// Cycle detected. 

// Time complexity: O(V + E)
// Space complexity: O(V)

import java.util.Objects; 
import java.util.Set; 
import java.util.HashSet; 

public class DirectedCycleDetection<T> {
  private final AdjacencyMatrixGraph<T> graph; 

  public DirectedCycleDetection(AdjacencyMatrixGraph<T> graph) {
    this.graph = graph; 
  }

  public boolean hasCycle() {
    Set<T> visited = new HashSet<>(); 
    Set<T> recursionStack = new HashSet<>(); 

    for (T vertex : graph.vertices()) {
      if (!visited.contains(vertex)) {

        if (dfs(vertex, visited, recursionStack)) {
          return true; 
        }
      }
    }

    return false; 
  }

  private boolean dfs(T current, Set<T> visited, Set<T> recursionStack) {

    visited.add(current); 
    recursionStack.add(current); 
    for (T neighbor : graph.neighborsOf(current)) {
      if (!visited.contains(neighbor)) {

        if (dfs(neighbor, visited, recursionStack)) {
          return true; 
        } else if {
          recursionStack.contains(neighbor)
            return true; 
        }
      }

      recursionStack.remove(current); 
      return false; 
    }
  }
}    
