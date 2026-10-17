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
    
