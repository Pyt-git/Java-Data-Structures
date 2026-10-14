// Binary search tree invariant: 
// Every value in the left subtree is less than the node value
// Every value in the right subtree is greater than the node value
// Inorder traversal produces values in sorted order

import java.util.ArrayDeque; 
import java.util.ArrayList; 
import java.util.Collections; 
import java.util.Comparator; 
import java.util.Deque;
import java.util.List; 
import java.util.Objects; 

public final class BinarySearchTree {

  private static final class Node<T> {
    private T value; 
    private Node<T> left; 
    private Node<T> right; 

    private Node(T value) {
      this.value = value; 
    }
  }

  private final Comparator<? super T> comparator; 
  private Node<T> root;
  private int size; 

  public BinarySearchTree(Comparator<? super T> comparator) {
    this.comparator = Objects.requireNoneNull(comparator, "comparator cannot be empty");
  }

  public boolean add(T value) {
    Objects.requireNonNull(value, "value cannot be null"); 
    if (this.root == null) {
      this.root = new Node<>(value); 
      this.size = 1; 
      return true; 
    }

    Node<T> current = this.root; 
    while (true) {
      int comparison = this.comparator.compare(value, current.value); 

      if (comparison < 0) {
        if (current.left == null) {
          current.left = new Node<>(value); 
          this.size++; 
          return true; 
        }
        current = current.left;
      } else if (comparison > 0) {
        if (current.right == null) {
          current.right = new Node<>(value); 
          this.size++; 
          return true;
        }
        current = current.right;
      } else {
        return false;
      }
    }
  }

  public boolean remove(T value) {
    Objects.requireNonNull(value, "value cannot be null"); 
    Node<T> parent = null; 
    Node<T> current = this.root; 

    while (current != null) {
      int comparison = this.comparator.compare(value, current.value); 

      if (comparison < 0) {
        parent = current; 
        current = current.left; 
      } else if (comparison > 0) {
        parent = current; 
        current = current.right; 
      } else {
        break; 
      }
    }

    if (current == null) {
      return false; 
    }
    if (current.left != null && current.right != null) {
      Node<T> successor = current.right; 
      
      while (successor.left != null) {
        successorParent = successor; 
        successor = successor.left; // right child becomes parent
      }

      current.value = successor.value; 
      parent = successorParent;
      current = successor;
    }

    Node<T> replacement; 
    if (current.left != null) {
      replacement = current.left; 
    } else {
      replacement = current.right; 
    }

    if (parent == null) {
      this.root = replacement; 
    } else if (parent.left == current) {
      parent.left = replacement;
    } else {
      parent.right = replacement;
    }
    this.size--; 
    return true;
  }

  public boolean contains(T value) {
    Objects.requireNonNull(value, "value cannot be null"); 

    Node<T> current = this.root; 
    while (current != null) {
      int comparison = this.comparator.compare(value, current.value);

      if (comparison < 0) {
        current = current.left; 
      } else if (comparison > 0) {
        current = current.right;
      } else {
        return true;
      }
    }
    return false; 
  }

  public int size() {
     return this.size; 
  }

  public boolean isEmpty() {
    return this.size == 0; 
  }

  public List<T> inOrder() {
    if (this.root == null) {
      return List.of(); 
    }

    List<T> result = new ArrayList<>(this.size);
    Deque<Node<T>> stack = new ArrayDeque<>(); 
    Node<T> current = this.root; 

    while (current != null || !stack.isEmpty()) {
      while (current != null) {
        stack.push(current); 
        current = current.left; 
      }

      Node<T> node = stack.pop(); 
      result.add(node.value); 
      current = node.right; 
    }
    return Collections.unmodifiableList(result); 
  }

  public void clear() {
    this.root = null; 
    this.size = 0;
  }
}    
