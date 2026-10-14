// Adjacency list invariants: 
// Every vertex corresponds to exactly one key in the adjacency list. 
// Every neighbor must be connected via an edge. 
// Undirected graph: both A -> B and B -> A paths are possible.
// Directed graphs: pathing direction must respect the edge orientation.
// Removing an vertex causes the removal of all edges connected to it. 

import java.util.Collections; 
import java.util.LinkedHashMap; 
import java.util.LinkedHashSet; 
import java.util.Map; 
import java.util.Objects; 
import java.util.Set; 

public final class AdjacencyListGraph<T> {
  private final Map<T, Set<T>> adjacency; 
  private final boolean directed; 
  private int edgeCount; 

  public AdjacencyListGraph(boolean directed) {
    this.adjacency = new LinkedHashMap<>(); 
    this.directed = directed; 
  }

  public boolean addVertex(T vertex) {
    requireVertex(vertex); 

    if (this.adjacency.containsKey(vertex)) {
      return false; 
    }
    this.adjacency.put(vertex, new LinkedHashMap<>()); 

    return true; 
  }

  public boolean removeVertex(T vertex) {
    requireVertex(vertex); 

    Set<T> outgoing = this.adjacency.get(vertex);
    if (outgoing == null) {
      return false; 
    } 

    if (this.di, rected) {
      removeDirectedVertex(vertex, outgoing); 
    }
    this.adjacency.remove(vertex); 

    return true; 
  }

  public boolean addEdge(T _from, T _to) {
    requireExistingVertex(_from); 
    requireExistingVertex(_to); 

    Set<T> neighbors = this.adjacency.get(_from); 
    if (!neighbors.add(_to)) {
      return false; 
    } 
    if (!this.directed) {
      this.adjacency.get(_to).add(_from); 
    }

    this.edgeCount++; 
    return true; 
  }

  public boolean removeEdge(T _from, T _to) {
    requireExistingVertex(_from); 
    requireExistingVertex(_to);

    boolean removed = this.adjacency.get(_from).remove(_to); 
    if (!removed) {
      return false; 
    } 
    if (!this.directed) {
      this.adjacency .get(_to).remove(_from); 
    }
    this.edgeCount--; 
    return true; 
  }

  public boolean containsVertex(T vertex) {
    requireVertex(vertex); 
    return this.adjacency.containsKey(vertex); 
  }

  public boolean containsEdge(T _from, T _to) {
    requireExistingVertex(_from); 
    requireExistingVertex(_to);

    return this.adjacency.get(_from).contains(_to); 
  }

  public Set<T> neighborsOf(T vertex) {
    requireExistingVertex(vertex); 

    return Collections.unmodifiableSet(new LinkedHashMap<>(this.adjacency.get(vertex))); 
  }

  public Set<T> vertices() {
    return Collections.unmodifiableSet(new LinkedHashMap<>(this.adjacency.keySet())); 
  }

  public int vertexCount() {
    return this.adjacency.size(); 
  }

  public int edgeCount() {
    return this.edgeCount; 
  }

  public boolean isEmpty() {
    return this.adjacency.isEmpty(); 
  }

  public void clear() {
    this.adjacency.clear(); 
    this.edgeCount = 0; 
  }

  private void removeUndirectedVertex(T vertex, Set<T> neighbors) {
    Set<T> copy = new LinkedHashSet<>(neighbors); 

    for (T neighbor : copy) {
      this.adjacency.get(neighbor).remove(vertex); 
      this.edgeCount--; 
    }
  }

  private void removeDirectedVertex(T vertex, Set<T> outgoing) {
    this.edgeCount -= outgoing.size(); 

    for (Map.Entry<T, Set<T>> entry : this.adjacency.entrySet()) {
      if (! Objects.equals(entry.getKey(), vertex) && entry.getValue().remove(vertex)) {
        this.edgeCount--; 
      }
    }
  }

  private void requireExistingVertex(T vertex) {
    requireVertex(vertex); 

    if (!this.adjacency.containsKey(vertex)) {
      throw new IllegalArgumentException("vertex does not exist");
    }
  }

  private static <T> void requireVertex(T vertex) {
    Objects.requireNonNull(vertex, "Vertex cannot be null"); 
  }
}
