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

  public
