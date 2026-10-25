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

