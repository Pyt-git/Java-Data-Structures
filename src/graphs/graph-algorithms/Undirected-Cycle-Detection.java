// Cycle detection for undirected graphs. 

// Suppose graph is:
//   A---B
//   |   |
//   |   |
//   D---C

// Cycle is detected when DFS reaches a neighbor that has been visited, but is not the parent of current vertex.
// parent(vertex) is the neighboring vertex from which DFS gets to current vertex. 

// Suppose DFS starts at vertex B, with traversal order {B, C, D, A}. 
// Then, once DFS reaches A, neighbor(A) = {D, B}. 
// Parent(A) = D, B == visited && B != parent(A). 
// Therefore, cycle detected at edge A---B. 

// Time complexity: O(V + E) 
// Space complexity: O(V) - Each vertex is visited exactly once. 

import java.util.Set; 
import java.util.HashSet; 

public class UndirectedCycleDetection<T> {
  private final AdjacencyMatrixGraph<T> graph; 

  public UndirectedCycleDetection(AdjacencyMatrixGraph<T> graph) {
    this.graph = graph; 
  }

  public boolean hasCycle() { 
    Set<T> visited = new HashSet<>();

    for (T vertex : graph.vertices()) {
      if (!visited.contains(vertex)) {

        if (dfs(vertex, null, visited)) {
          return true; 
        }
      }
    }

    return false; 
  }

  private boolean dfs(T current, T parent, Set<T> visited) {
    visited.add(current); 

    for (T neignbor : graph.neighborsOf(current)) {
      if (!visited.contains(neighbor)) {

        if (dfs(neighbor, current, visited)) {
          return true; 
        } 
      } else if (!neighbor.equals(parent)) {
        return true;
      }
    }
       
    return false;
    }
  }
