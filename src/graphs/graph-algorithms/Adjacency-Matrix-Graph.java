// Adjacency matrix invariants: 
// Each vertex corresponds to one index in matrix[i][j]
// Adjacency matrix must be a square matrix
// In adjacency matrix, from row-vertex to column-vertex
// For an undirected graph, adding an edge preserves matrix symmetry
// For a directed graph, adding an edge affects the specified direction only
// Adjacency matrix may represent a cyclic graph

// Example with undirected triangular graph A-B-C-A: 
// One's are in cells represented by: row A column B, row B column A, 
// row B column C, row C column B, row C column A and row A column C. 

import java.util.ArrayDeque; 
import java.util.ArrayList; 
import java.util.Deque; 
import java.util.HashMap; 
import java.util.LinkedHashMap; 
import java.util.List; 
import java.util.Objects; 
import java.util.Map; 
import java.util.Set; 

public class AdjacencyMatrixGraph<T> {
  private static final int DEFAULT_CAPACITY = 8; 
  
  // Stores vertex's index in matrix
  private final Map<T, Integer> indices;
  
  private final List<T> vertices; 
  private final Deque<Integer> freeIndices; 
  private boolean[][] matrix; 
  private final boolean directed; 
  private int vertexCount; 
  private int edgeCount; 

  public AdjacencyMatrixGraph(boolean directed) {
    this(directed, DEFAULT_CAPACITY); 
  }

  public AdjacencyMatrixGraph(boolean directed, int initialCapacity) {
    if (initialCapacity < 0) {
      throw new IllegalArgumentException("Capacity cannot be negative"); 
    }

    this.directed = directed; 
    this.indices = new HashMap<>(); 
    this.vertices = new ArrayList<>(); 
    this.freeIndices = new ArrayDeque<>(); 
    this.matrix = new boolean[initialCapacity][initialCapacity]; 
    this.vertexCount = 0; 
    this.edgeCount = 0; 
  }

  public boolean addVertex(T vertex) {
    Objects.requireNonNull(vertex, "Vertex cannot be null"); 
    
    if (indices.containsKey(vertex)) {
      return false; 
    }

    final int index; 
    if (!freeIndices.isEmpty()) {
      index = freeIndices.removeFirst(); 
      vertices.set(index, vertex); 
    } else {
      index = vertices.size(); 
      ensureCapacity(index + 1); 
      vertices.add(vertex); 
    }

    indices.put(vertex, index); 
    vertexCount++; 

    return true; 
  }

  // Removing vertices through scan of matrix row
  public boolean removeVertex(T vertex) {
    Integer index = indices.get(vertex); 

    if (index == null) {
      return false; 
    }

    if (directed) {
      for (int i = 0; i < matrix.length; i++) {
        if (matrix[index][i]) {
          edgeCount--; 
        }

        if (i != index && matrix[i][index]) {
          edgeCount--;
        }
      }
    } else {
      for (int i = 0; i < matrix.length; i++) {
        if (matrix[index][i]) {
          edgeCount--; 
        }
      }
    }

    for (int i = 0; i < matrix.length; i++) {
      matrix[index][i] = false; 
      matrix[i][index] = false; 
    }

    indices.remove(vertex);
    vertices.set(index, null); 
    freeIndices.addLast(index); 

    vertexCount--;
    return true; 
  }

  public boolean addEdge(T _from, T _to) {
    int fromIndex = requireIndex(_from); 
    int toIndex = requireIndex(_to); 

    if (matrix[fromIndex][toIndex]) { // if edge already exists, no need for add operation
      return false; 
    }

    matrix[fromIndex][toIndex] = true; // directed graph add unidirectional edge

    if (!directed) {
      matrix[toIndex][fromIndex] = true; // undirected graph add other direction
    }

    edgeCount++; 
    return true; 
  }

  public boolean removeEdge(T _from, T _to) {
    int fromIndex = requireIndex(_from); 
    int toIndex = requireIndex(_to); 

    if (!matrix[fromIndex][toIndex]) { // if edge does not exist, no need for remove operation
      return false; 
    }

    matrix[fromIndex][toIndex] = false; 

    edgeCount--; 
    return true; 
  }

  public boolean containsVertex(T vertex) {
    return indices.containsKey(vertex);
  }

  public boolean containsEdge(T _from, T _to) {
    Integer fromIndex = indices.get(_from); 
    Integer toIndex = indices.get(_to); 

    if (fromIndex == null || toIndex == null) {
      return false; 
    }

    return matrix[fromIndex][toIndex]; 
  }

  public Set<T> neighborsOf(T vertex) {
    int index = requireIndex(vertex); 

    Set<T> neighbors = new LinkedHashSet<>(); 
    for (int i = 0; i < vertices.size(); i++) {
      if (matrix[index][i]) {
        T neighbor = vertices.get(i); 

        if (neighbor != null) {
          neighbors.add(neighbor); 
        }
      }
    }

    return neighbors; 
  }

  public Set<T> vertices() {
    Set<T> result = new LinkedHashSet<>(); 

    for (T vertex : vertices) {
      if (vertex != null) {
        result.add(vertex);
      }
    }

    return result; 
  }

  public int edgeCount() {
    return this.edgeCount; 
  }

  public boolean isEmpty() {
    return this.vertexCount == 0; 
  }

  public boolean isDirected() {
    return this.directed; 
  }

  public int capacity() {
    return matrix.length; 
  }

  public void clear() {
    indices.clear(); 
    vertices.clear(); 
    freeIndices.clear(); 

    matrix = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY]; 

    vertexCount = 0; 
    edgeCount = 0; 
  }

  private int requireIndex(T vertex) {
    Objects.requireNonNull(vertex, "Vertex cannot be null"); 

    Integer index = indices.get(vertex); 
    if (index == null) {
      throw new IllegalArgumentException("Vertex does not exist"); 
    }

    return index; 
  }

  private void ensureCapacity(int requiredCapacity) {
    if (requiredCapacity <= matrix.length) {
      return; 
    }

    int newCapacity = Math.max(requiredCapacity, Math.max(1, matrix.length * 2)); // avoid matrix of length 0

    boolean[][] expanded = new boolean[newCapacity][newCapacity]; 
    for (int row = 0; row < matrix.length; row++) {
      System.arraycopy(matrix[row], 0, expanded[row], 0, matrix[row].length); 
    }

    matrix = expanded; 
  }
}
