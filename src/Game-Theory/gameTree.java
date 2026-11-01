// For documentation details, visit folder Documentation in this repo. 

import java.util.List; 
import java.util.ArrayList; 
import java.util.Objects; 

public final class GameTree<T> {
  public static final class Node<T> {

    private final T state; 
    private final Player player; 
    private final List<Node<T>> children; 
    private Integer payoff; 

    private Node(T state, Player player) {
      this.state = state; 
      this.player = player; 
      this.children = new ArrayList<>();
    }

    public Player getPlayer() {
      return player; 
    }

    public T getState() {
      return state; 
    }

    public List<Node<T>> getChildren() {
      return children; 
    }

    public Integer getPayoff() {
      return payoff;
    }
  }

  private final Node<T> root; 
  public GameTree(T initialState, Player player) {
    this.root = new Node<>(initialState, player); 
  }

  private Node<T> addChild(Node<T> parent, T childState, Player nextPlayer) {
    Objects.requireNonNull(parent, "Parent cannot be null"); 

    Node<T> child = new Node<>(childState, nextPlayer); 
    parent.children.add(child); 

    return child; 
  }

  private void setPayoff(Node<T> node, int payoff) {
    node.payoff = payoff; 
  }

  private boolean isLeaf(Node<T> node) {
    return node.children.isEmpty(); 
  }

  public T getRootState() {
    return root.state; 
  }
}
