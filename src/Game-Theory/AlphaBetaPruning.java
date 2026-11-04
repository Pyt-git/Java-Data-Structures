// for documentation details, visit folder Documentation in this repo. 

import java.util.Objects;

public final class AlphaBetaPruning<T> {
  private final GameTree<T> gameTree; 

  public AlphaBetaPruning(GameTree<T> gameTree) {
    this.gameTree = gameTree; 
  }

  public int alphaBetaRoot(GameTree.Node<T> root) {
    Objects.requireNonNull(root, "Root cannot be null"); 

    return alphaBeta(root, Integer.MIN_VALUE, Integer.MAX_VALUE); 
  }

  private int alphaBeta(GameTree.Node<T> node, int alpha, int beta) {
    if (gameTree.isLeaf(node)) {

      return node.getPayoff(); 
    }

    if (node.getPlayer() == Player.MAX) {
      int value = Integer.MIN_VALUE; 

      for (GameTree.Node<T> child : node.getChildren()) {
        value = Math.max(value, alphaBeta(child, alpha, beta)); 

        alpha = Math.max(alpha, value); 
        if (alpha >= beta) {
          break; 
        }
      }

      return value; 
    }

    int value = Integer.MAX_VALUE; 
    for (GameTree.Node<T> child : node.getChildren()) {

      value = Math.min(value, alphaBeta(child, alpha, beta)); 
      beta = Math.min(beta, value); 

      if (alpha >= beta) {
        break; 
      }
    }

    return value; 
  }
}
