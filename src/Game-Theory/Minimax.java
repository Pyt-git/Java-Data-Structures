// For documentation details, visit folder Documentation in this repo. 

import java.util.Objects; 

public final class Minimax<T> {
  private final GameTree<T> gameTree; 

  public Minimax(GameTree<T> gameTree) {
    this.gameTree = gameTree; 
  }

  public int minimax(Node<T> node) {
    Objects.requireNonNull(node, "Node cannot be null"); 
  
    if (isLeaf(node)) {
      return node.getPayoff(); 
    }

    if (node.getPlayer() == Player.MAX) {
      int bestValue = Integer.MIN_VALUE;

      for (Node<T> child : node.getChildren()) {
        bestValue = Math.max(bestValue, minimax(child)); 
      }

      return bestValue; 
    }

    int bestValue = Integer.MAX_VALUE; 
    for (Node<T> child : node.getChildren()) {
      bestValue = Math.min(bestValue, minimax(child)); 
    }

    return bestValue; 
  }
}
