// Weighted graph invariants: 
// 1. Each edge must store its destination vertex and its weight. 
// 2. Each cell of the matrix representation represent the weight of edges.
// 3. Every edge weight must be nonnegative and absence of an edge is null. 
// 4. Adjacency matrix is symmetric whenever an edge exists. 

// Worked example: 

// Let adjacency matrix be: 
//   A  B  C  D
// A -  6  5  -
// B 6  -  0  -
// C 5  0  -  1
// D -  -  1  -

// Then, the graph represented is: 
// A <--6--> B
//   \      /
//    5    0
//     \  /
//      C
//      |
//      1
//      |
//      D

import java.util.Objects; 
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

  public T removeVertex(T vertex) {
    Objects.requireNonNull(vertex, "Vertex cannot be null"); 

    Integer removedIndex = vertexToIndex.get(vertex); 
    if (removedIndex == null) {
      return null; 
    }

    T removedVertex = vertex; 
    int oldSize = adjacencyMatrix.length; 

    Integer[][] newMatrix = new Integer[oldSize - 1][oldSize - 1]; // removing a vertex shrinks the dimensions by 1. 

    int newRow = 0; 
    for (int oldRow = 0; oldRow < oldSize; oldRow++) {

      if (oldRow == removedIndex) {
        continue; 
      }

      int newColumn = 0; 
      for (int oldColumn = 0; oldColumn < oldSize; oldColumn++) {

        if (oldColumn = removedIndex) {
          continue; 
        }

        newMatrix[newRow][newColumn] = adjacencyMatrix[oldRow][oldColumn]; 
        newColumn++;
      }

      newRow++; 
    }

    Map<T, Integer> newVertexToIndex = new HashMap<>(); 
    Map<T, Integer> newIndexToVertex = new HashMap<>(); 

    int newIndex = 0; 
    for (int oldIndex = 0; oldIndex < oldSize; oldIndex++) {
      if (oldIndex == removedIndex) {
        continue; 
      }

      T currentVertex = indexToVertex.get(oldIndex); 
      newVertexToIndex.put(newIndex, vertex); 
      newIndexToVertex.put(vertex, newIndex); 

      newIndex++; 
    }

    adjacencyMatrix = newMatrix; 
    
    vertexToIndex.clear(); 
    vertexToIndex.putAll(newVertexToIndex); 

    indexToVertex.clear(); 
    indexToVertex.putAll(newIndexToVertex); 

    return removedVertex; 
  }
  
  public boolean containsVertex(T vertex) {
    return vertexToIndex.containsKey(vertex); 
  }

  public boolean containsEdge(T source, T destination) {
    Integer sourceIndex = vertexToIndex.get(source); 
    Integer destinationIndex = vertexToIndex.get(destination); 

    if (sourceIndex == null || destinationIndex == null) {
      return false; 
    }

    return adjacencyMatric[sourceIndex][destinationIndex] != null; 
  }

  public Integer getWeight(T source, T destination) {
    Integer sourceIndex = vertexToIndex.get(source); 
    Integer destinationIndex = vertexToIndex.get(destination); 

    if (sourceIndex == null || destinationIndex == null) {
      return null; 
    }

    return adjacencyMatrix[sourceIndex][destinationIndex]; 
  }

  public Collection<Weighted<T>>
