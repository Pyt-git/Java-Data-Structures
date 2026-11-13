// Weighted graph invariants: 
// 1. Each edge must store its destination vertex and its weight. 
// 2. Each cell of the matrix representation represent the weight of edges.
// 3. Every edge weight must be nonnegative and absence of an edge is null. 
// 4. Adjacency matrix is symmetric whenever an edge exists. 

// Worked example: 

import java.util.Map; 
import java.util.HasMap; 

public final class AdjacencyMatrixWeightedGraph<T> {
  private final Map<T, Integer> vertexToIndex; 
  private final Map<Integer, T> indexToVertex; 
  private Integer[][] adjacencyMatrix; 

  public AdjacencyMatrixWeightedGraph() {
    this.vertexToIndex = new HashMap<>(); 
    this.indexToVertex = new HashMap<>(); 
    this.adjacencyMatrix = new Integer[0][0]; 
  }

  public boolean addVertex(T vertex) {
    if (vertexToIndex.containsKey(vertex)) {
      return false; 
    }

    int newIndex = vertexToIndex.size(); 
    Integer[][] newMatrix = new Integer[newIndex + 1][newIndex + 1]; // expanding matrix size by 1

    for (int row = 0; row < adjacencyMatrix.length; row++) {
      System.arraycopy(adjacencyMatrix[row], 0, newMatrix[row], 0, adjacencyMatrix[row].length);
    }

    vertexToIndex.put(vertex, newIndex);
    indexToVertex.put(newIndex, vertex); 

    adjacencyMatrix = newMatrix; 

    return true; 
  }

  public boolean addEdge(T source, T destination, int weight) {
    if (weight < 0) {
      throw new IllegalArgumentException("Positive weight only");
    }

    Integer sourceIndex = vertexToIndex.get(source); 
    Integer destinationIndex = vertexToIndex.get(destination); 

    if (sourceIndex == null || destinationIndex == null) {
      throw new IllegalArgumentException("Both vertices do not exist"); 
    }

    if (adjacencyMatrix[sourceIndex][destinationIndex] != null) {
      return false; 
    }

    adjacencyMatrix[sourceIndex][destinationIndex] = weight; 
    adjacencyMatrix[destinationIndex][sourceIndex] = weight; // undirected graph preserves symmetry

    return true; 
  }

  public boolean removeEdge(T source, T destination) {
    Integer sourceIndex = vertexToIndex.get(source); 

    Integer destinationIndex = vertexToIndex.get(destination); 
    if (sourceIndex == null || destinationIndex == null) {
      return false; 
    }

    if (adjacencyMatrix[sourceIndex][destinationIndex] == null) {
      return false; 
    }

    if (adjacencyMatrix[destinationIndex][sourceIndex] == null) {
      return false; 
    }

    adjacencyMatrix[sourceIndex][destinationIndex] = null; 
    adjacencyMatrix[destinationIndex][sourceIndex] = null; 

    return true;
  }

  
