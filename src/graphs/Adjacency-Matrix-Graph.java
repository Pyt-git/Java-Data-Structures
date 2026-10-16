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

