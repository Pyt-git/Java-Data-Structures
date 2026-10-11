// Binary heap invariants:
// Shape invariant: Heap is a complete binary tree - 
// Only the last level may not be full
// Last level is filled from left to right without arbitrary gaps in the tree

// Head-order invariant: 
// Min-heap - parent compares less than its children
// Max-heap - parent compares more more its children
// Heap does not guarantee sortedness after inorder traversal


import java.util.ArrayList; 
import java.util.Collection;
import java.util.Collections; 
import java.util.Comparator; 
import java.util.List; 
import java.util.NoSuchElementException; 
import java.util.Objects; 

public final class BinaryHeap<T> {
  private final ArrayList<T> elements; 
  private final Comparator<? super T> comparator;

  public BinaryHeap(Comparator<? super T> comparator) {
    this.comparator = Objects.requireNonNull(comparator, "Comparator cannot be null"); 
    this.elements = new ArrayList<>(); 
  }

  public BinaryHeap(int intialCapacity, Comparator<? super T> comparator) {
    if (initialCapacity < 0) {
      throw new IllegalArgumentException("initial capacity cannot be negative"); 
    }

    this.comparator = Objects.requireNonNull(comparator, "Comparator cannot be null");
    this.elements = new ArrayList<>(initialCapacity); 
  }

  public BinaryHeap(Collection<? extends T> values, Comparator<? super T> comparator) {
    Objects.requireNonNull(values, "Values cannot be null"); 

    this.comparator = Objects.requireNonNull(comparator, "Comparator cannot be null"); 
    this.elements = new ArrayList<>(values.size()); 

    for (T value : values) {
      this.elements.add(Objects.requireNonNull(value, "Heap values cannot be null")); 
    }

    heapify(); 
  }

  public void add(T value) {
     Objects.requireNonNull(value, "Value cannot be null"); 
     this.elements.add(value);
     siftUp(this.element.size() - 1); 
  }

  public boolean offer(T value) {
    add(value); 
    return true; 
  }

  public T peek() {
    if (this.elements.isEmpty()) {
      return null; 
    }

    return this.elements.get(0); 
  }

  public T element() {
    T value = peek(); 

    if (value == null) {
      throw new NoSuchElementException("heap is empty"); 
    }

    return value; 
  }

  public T poll() {
    if (this.elements.isEmpty()) {
      return null; 
    }

    return removeAt(0); 
  }

  public T remove() {
    T value = poll(); 

    if (value == null) {
      throw new NoSuchElementException("heap is empty"); 
    }

    return value; 
  }

  public boolean remove(T value) {
    Objects.requireNonNull(value, "Value cannot be null"); 

    int index = indexOf(value);
    if (index < 0) {
      return false; 
    } 

    removeAt(index); 
    return true; 
  }

  public boolean contains(T value) {
    Objects.requireNonNull(value, "Value cannot be null"); 

    return indexOf(value) >= 0; 
  }

  public int size() {
    return this.elements.size(); 
  }

  public boolean isEmpty() {
    return this.elements.size() == 0; 
  }

  public void clear() {
    this.elements.clear(); 
  }

  public Comparator<? super T> comparator() {
    return this.comparator; 
  }

  public List<T> snapshot() {
    return Collections.unmodifiableList(new ArratList<>(this.elements);
  }

  private void siftUp(int index) {
    T value = this.elements.get(index); 

    while (index > 0) {
      int parentIndex = parentIndex(index); 
      T parent = this.elements.get(parentIndex); 

      if (compare(value, parent) >= 0 {
        break; 
      }

      this.elements.set(index, parent);
      index = parentIndex; 
    }

    this.elements.set(index, value); 
  }

  private void siftDown(int index) {
    int size = this.element.size(); 

    T value = elements.get(index); 
    while (true) {
      int leftIndex = leftChildIndex(index); 

      if (leftIndex >= size) {
        break; 
      }
      int rightIndex = rightchildIndex(index); 
      int priorityChildIndex = leftIndex; 

      if (rightIndex < size && compare(this.elements.get(rightIndex), this.elements.get(leftIndex)) < 0) {
        priorityChildIndex = rightIndex; 
      }
      T priorityChild = this.elements.get(priorityChildIndex); 

      if (compare(value, priorityChild) <= 0) {
        break; 
      }
      this.elements.set(index, priorityChild); 
      index = priorityChildIndex; 
    }

    this.elements.set(index, value); 
  }

  private T removeAt(int index) {
    int lastIndex = this.elements.size() - 1; 
    T removed = this.elements.get(index); 
    T last = this.elements.remove(lastIndex); 

    if (index == lastIndex) {
      return removed; 
    }
    this.elements.set(index, last); 

    if (index > 0 && compare(last, this.elements.get(parentIndex(index))) < 0) {
      siftUp(index); 
    } else {
      siftDown(index); 
    }
    return removed; 
  }

  private void heapify() {
    if (this.elements.size() < 2) {
      return; 
    }

    int lastParent = parentIndex(this.elements.size() - 1); 
    for (int index = lastParent; index >= 0; index--) {
      siftDown(index); 
    }
  }

  private int indexOf(T. value) {
    for (int index = 0; index < this.elements.size(); index++) {
      if (Objects.equals(this.elements.get(index), value)) {
        return index;
      }
    }

    return -1; 
  }

  private int compare(T first, T second) {
    return this.comparator.compare(first, second); 
  }

  private static int parentIndex(int index) {
    return (index - 1) / 2; 
  }

  private static int leftChildIndex(int index) {
    return 2 * index + 1; 
  }

  private static int rightChildIndex(int index) {
    return 2 * index + 2; 
  }

  public boolean isValidHeap() {
    for (int parentIndex = 0; parentIndex < this.elements.size(); parentIndex++) {
      int leftIndex = leftChildIndex(parentIndex); 
      int rightIndex = rightChildIndex(parentIndex); 

      if (leftIndex < this.elements.size() && compare(this.elements.get(parentIndex), this.elements.get(leftIndex)) > 0) {
        return false; 
      }

      if (rightIndex < this.elements.size() && compare(this.elements.get(parentIndex), this.elements.get(rightIndex)) > 0) {
        return false; 
      }
    }

    return true; 
  }
}
