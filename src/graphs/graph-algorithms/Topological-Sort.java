// Topological sort invariants: 
// 1. All discovered vertices are marked as visited. 
// 2. A vertex is pushed onto the stack only after all its descendants finish. 
// 3. For every directed edge U -> V, U must come before V. 
// 4. A valid topological ordering only exists for a directed acyclic graph. 

// Worked example: 
// suppose graph is:

// A --> B --> D
// |
// |
// v
// C

// Then, DFS discovers A, B and D -> visited = {A, B, D}. 
// D finishes before B -> stack = [D, B]. 
// DFS then returns to A to discover C -> visited = {A, B, D, C}
// C is finished -> stack = [D, B, C]
// A is finished last -> stack = [D, B, C, A]. 

// Apply LIFO mechanism during stack popping:
// Final topological sort ordering is: "A, C, B, D". 

// Time complexity: O(V + E). 
// Space complexity: O(V). 

import java.util.List; 
import java.util.ArrayList; 
import java.util.Set; 
import java.util.HashSet; 
import java.util.Deque; 
import java.util.ArrayDeque;

public class TopologicalSort<T> {
  private final AdjacencyMatrixGraph<T> graph; 

  public TopologicalSort(AdjacencyMatrixGraph<T> graph) {
    this.graph = graph; 
  }

  public List<T> sort() {
    DirectedCycleDetection<T> detector = new DirectedCycleDetection<>(graph); 

    if (detector.hasCycle()) {
      throw new IllegalStateException("Graph is not a DAG"); 
    }

    Set<T> visited = new HashSet<>(); 
    Deque<T> ordering = new ArrayDeque<>(); 

    for (T vertex : graph.vertices()) {
      if (!visited.contains(vertex)) {

        dfs(vertex, visited, ordering); 
      }
    }

    List<T> result = new ArrayList<>(); 
    while (!ordering.isEmpty()) {

      result.add(ordering.pop()); 
    }

    return result; 
  }

  private void dfs(T current, Set<T> visited, Deque<T> ordering) {
    visited.add(current); 

    for (T neighbor : graph.neighborsOf(current)) {
      if (!visited.contains(neighbor)) {

        dfs(neighbor, visited, ordering); 
      }
    }

    ordering.push(current); 
  }
}
