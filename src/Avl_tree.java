// AVL tree invariants: 
// Every value in the left subtree compares below the node.
// Every value in the right subtree compares above the node. 
// BST inorder traversal and element sortedness must be preserved. 

// AVL rotational image: 
// A left AVL rotation can be viewed as the anticlockwise sense movement of the tree's components. 
// A right AVL rotation can be viewed as the clockwise sense movement of the tree's components.

import java.util.ArrayDeque; 
import java.util.ArrayList; 
import java.util.Collections; 
import java.util.Comparator; 
import java.util.Deque;
import java.util.List; 
import java.util.Objects; 

public final class AvlTree<T> {

  private static final class Node<T> {
    private T value; 
    private Node<T> left; 
    private Node<T> right; 
    private int height; 

    private Node(T value) {
      this.value = value; 
      this.height = 1; 
    }
  }

  private static final class MutationResult {
    private boolean modified; 
  }

  private final Comparator<? super T> comparator; 
  private Node<T> root; 
  private int size; 

  public AvlTree(Comparator<? super T> comparator) {
    this.comparator = Objects.requireNonNull(comparator, "Comparator cannot be null"); 
  }

  public boolean add(T value) {
    Objects.requireNonNull(value, "Value cannot be null");

    MutationResult result = new MutationResult();
    this.root = insert(this.root, value, result); 

    if (result.modified) {
      this.size++; 
    }
    return result.modified; 
  }

  private Node<T> insert(Node<T> node, T value, MutationResult result) {
    if (node == null) {
      result.modified == true; 
      return new Node<> (value); 
    }

    int comparison = this.comparator.compare(value, node.value); 
    if (comparison < 0) {
      node.left = insert(node.left, value, result); 
    } else if (comparison > 0) {
      node.right = insert(node.right, value, result); 
    } else {
      return node; 
    }

    return rebalance(node); 
  }

  public boolean remove(T value) {
    Objects.requireNonNull(value, "Value cannot be null");
    
    MutationResult result = new MutationResult();
    this.root = delete(this.root, value, result); 

    if (result.modified) {
      this.size--; 
    }
    return result.modified;
  }

  private Node<T> delete(Node<T> node, T value, MutationResult result) {
    if (node == null) {
      return null; 
    }
    
    int comparison = this.comparator.compare(value, node.value); 
    if (comparison < 0) {
      node.left = delete(node.left, value, result);
    } else if (comparison > 0) {
      node.right = delete(node.right, value, result);
    } else {
      result.modified = true; 
    
    if (node.left == null) {
      return node.right;
    }
    if (node.right == null) {
      return node.left; 
    }

    Node<T> successor = minimumNode(node.right); 
    node.value = successor.value; 
    node.right = deleteMinimum(node.right); 
    }

   return rebalance(node); 
  }

  private Node<T> minimumNode(Node<T>, node) {
    Node<T> current = node; 

    while (current.left != null) {
      current = current.left; 
    }

    return current; 
  }

  // deleteMinimum 

  public boolean contains(T value) {
    Objects.requireNonNull(value, "Value cannot be null");

    Node<T> current = this.root; 
    while (current != null) {
      int comparison = this.comparator.compare(value, node.value); 

      if (comparison < 0 {
          current = current.left; 
      } else if (comparison > 0) {
        current = current.right; 
      } else {
        return true; 
      }
    }

    return false; 
  }

  public T minimum() {
    ensureNotEmpty(); 
    return minimumNode(this.root).value;
  }

  public T maximum() {
    ensureNotEmpty(); 

    Node<T> current = this.root; 
    while (current.right != null) {
      current = current.right; 
    }

    return current.value; 

  }

  public int size() {
    return this.size; 
  }

  public boolean isEmpty() { 
    return this.size == 0; 
  }

  private int heightHelper(Node
  public int height() {
    return height(this.root); 
  }

  
      
    
