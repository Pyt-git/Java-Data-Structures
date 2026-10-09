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

  private Node<T> deleteMinimum(Node<T> node) {
    if (node.left == null) {
      return node.right; 
    }

    node.left = deleteMinimum(node.left); 
    return rebalance(node); 
  }

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

  private int heightHelper(Node<T> node) { 
    return node == null ? 0 : node.height;
  }
  
  public int height() {
    return heightHelper(this.root); 
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

  private Node<T> rebalance(Node<T> node) {
    updateHeight(node); 

    int balance = balanceFactor(node); 
    if (balance > 1) {
      if (balanceFactor(node.left) < 0) {
        node.left = rotateLeft(node.left); 
      }

      return rotateRight(node); 
    }

    if (balance < -1) {
      if (balanceFactor(node.right) > 0) {
        node.right = rotateRight(node.right); 
      }

      return rotateLeft(node); 
    }

    return node; 
  }

  // clockwise rotation of tree components
  private Node<T> rotateRight(Node<T> y) {
    Node<T> x = y.left; 
    Node<T> transfer = x.right; 

    x.right = y; 
    y.left = transfer; 

    updateHeight(y); 
    updateHeight(x); 

    return x; 
  }

  // anticlockwise rotation of tree components
  private Node<T> rotateLeft(Node<T> x) {
    Node<T> y = x.right; 
    Node<T> transfer = y.left; 

    y.left = x; 
    x.right = transfer; 

    updateHeight(x); 
    updateHeight(y); 

    return y; 
  }

  private void updateHeight(Node<T> node) {
    node.height = 1 + Math.max(height(node.left), height(node.right)); 
  }

  public int balanceFactor(Node<T> node) {
    return node == null ? 0 : height(node.left) - height(node.right); 
  }

  private void ensureNotEmpty() {
    if (this.root == null) {
      throw new IllegalStateException("tree is empty"); 
    }
  }

  public boolean isValidAvlTree() { 
    validationResult validation = validate(this.root, null, null); 

    return validation.valid && validation.nodeCount == this.size; 
  }

  // check validity of a given AVL tree
  private ValidationResult validate(Node<T> node, T lowerExclusive, T upperExclusive) {
    if (node == null) {
      return ValidationResult.empty(); 
    }

    // check whether value is out of bounds
    if (lowerExclusive != null && this.comparator.compare(node.value, lowerExclusive) <= 0 {
      return ValidationResult.invalid(); 
    }

    if (upperExclusive != null && this.comparator.compare(node.value, upperExclusive) >= 0 {
      return ValidationResult.invalid(); 
    }

    ValidationResult left = validate(node.left, lowerExclusive, node.value); 
    if (!left.valid) {
      return ValidationResult.invalid(); 
    }

    ValidationResult right = validate(node.right, node.value, upperExclusive); 
    if (!right.valid) { 
      return ValidationResult.invalid(); 
    }

    int expectedHeight = 1 + 1 + Math.max(left.height, right.height); 
    boolean validHeight = node.height == expectedHeight; 
    boolean validBalance = Math.abs(left.height - right.height) <= 1; 

    return new ValidationResult(validHeight && validBalance, expectedHeight, 1 + left.nodeCount + right.nodeCount); 
  }

  public static final class ValidationResult {
    private final boolean valid; 
    private final int height; 
    private final int nodeCount; 

    private ValidateResult(boolean valid, int height, int nodeCount) {
      this.valid = valid; 
      this.height = height; 
      this.nodeCount = nodeCount; 
    }

    private static ValidationResult empty() {
      return new ValiationResult(true, 0, 0); 
    }

    private static ValidationResult invalid() {
      return new ValidationResult(false, 0, 0); 
    }
  }
}
