// Dijkstra invariants:
// 1. Every recorded distance is the best one discovered so far. 
// 2. The priority queue contains candidate vertices ordered 
//    by their current known best distance. 
// 3. Dijkstra selects the cheapest frontier node. 
// 4. A finalized vertex has a distance (cost) that is correct forever. 

// Worked example: 
// Suppose weighted graph is triangular with components: 
// A --4--> B, A --1--> C and C --2--> B. 
// Initializing: A = 0, B and C are unknown. 
// Exploring neighbors of A: B has distance 4 and C has distance 1. 
// Update: A = 0, B = 4, C = 1. 
// Choosing shortest path (lowest cost): 
// A --> C (distance = 1) and C --> B (distance = 2)
// Update B: distance(B) = 3 and 3 < 4. 
// Final path: A --> C --> B. 

import java.util.List; 
import java.util.HashMap; 
import java.util.ArrayList; 
import java.util.Map; 
import java.util.PriorityQueue; 
import java.util.Comparator; 
import java.util.LinkedList; 
import java.util.Collections; 

public final class Dijkstra<T> {

  private final class WeightedEdge<T> {
    private final T destination; 
    private final int weight; 

    public WeightedEdge(T destination, int weight) {
      this.destination = destination; 
      this.weight = weight; 
    }

    public T getDestination() {
      return destination; 
    }

    public int getWeight() {
      return weight; 
    }
  }

  private final class NodeDistance<T> {
    private final T vertex; 
    private final int distance; 

    public NodeDistance(T vertex, int distance) {
      this.vertex = vertex; 
      this.distance = distance; 
    }

    public T getVertex() {
      return vertex; 
    }

    public int getDistance() {
      return distance; 
    }
  }

  public static final class Result<T> {
    private final Map<T, Integer> distances; 
    private final Map<T, T> previous; 

    public Result(Map<T, Integer> distances, Map<T, T> previous) {
      this.distances = distances; 
      this.previous = previous; 
    }

      public Map<T, Integer> getDistances() {
        return distances; 
      }

      public Map<T, T> getPrevious() {
        return previous; 
      }
   }

   private final Map<T, List<WeightedEdge<T>>> graph; 

   public Dijkstra() {
     this.graph = new HashMap<>(); 
   }

   public void addVertex(T vertex) {
     graph.putIfAbsent(vertex, new ArrayList<>()); 
   }

   public void addEdge(T source, T destination, int weight) {
     if (weight < 0) {
       throw new IllegalArgumentException("Dijkstra requires non-negative weights"); 
     }

     addVertex(source); 
     addVertex(destination); 

     graph.get(source).add(new WeightedEdge<>(destination, weight)); 
   }

   public Result<T> shortestPaths(T source) {
     Map<T, Integer> distances = new HashMap<>(); 
     Map<T, T> previous = new HashMap<>(); 

     for (T vertex : graph.keySet()) {
       distances.put(vertex, Integer.MAX_VALUE); 
     }

     distances.put(source, 0); 
     PriorityQueue<NodeDistance<T>> pq = new PriorityQueue<>(Comparator.comparingInt(NodeDistance::getDistance)); 

     pq.offer(new NodeDistance<>(source, 0)); 
     while (!pq.isEmpty()) {

       NodeDistance<T> current = pq.poll(); 
       T currentVertex = current.getVertex(); 
       int currentDistance = current.getDistance(); 

       if (currentDistance > distances.get(currentVertex)) { 
         continue; 
       }

       for (WeightedEdge<T> edge : graph.get(currentVertex)) {
         T neighbor = edge.getDestination(); 

         int newDistance = currentDistance + edge.getWeight(); 
         if (newDistance < distances.get(neighbor)) {

           distances.put(neighbor, newDistance); 
           previous.put(neighbor, currentVertex); 

           pq.offer(new NodeDistance<>(neighbor, newDistance)); 
         }
       }
     }

     return new Result<>(distances, previous); 
   }

   public List<T> getPath(T source, T destination, Result<T> result) {
     LinkedList<T> path = new LinkedList<>(); 

     T current = destination; 
     while (current != null) {

       path.addFirst(current); 
       if (current.equals(source)) {
         break; 
       }

       current = result.getPrevious().get(current);
     }

     if (!path.getFirst().equals(source)) {
       return Collections.emptyList(); 
     }

     return path; 
   }
}
