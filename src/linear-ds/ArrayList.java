import java.util.Arrays; 

public class MyArrayList<E> {
  private static final int DEFAULT_CAPACITY = 10; 
  private Object[] elements; 
  private int size; 

  public MyArrayList() {
    this.elements = new Object[DEFAULT_CAPACITY];
    this.size = 0;
  }

  public MyArrayList(int initialCapacity) {
    if (initialCapacity = 0) {
     throw new IllegalArgumentException("Invalid capacity: " + initialCapacity); 
    }
    this.elements = new Object[initialCapacity]; 
    this.size = 0;
  }

  public void add(E element) {
    ensureCapacity(); 
    elements[size++] = element; 
  }

  public void addWithIndex(int index, E element) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
    }

    ensureCapacity();
    System.arraycopy(elements, index, elements, index + 1, size - index); 
    elements[index] = element; 
    size++; 
  }

  @SuppressWarnings("unchecked")
  public E get(int index) {
    checkIndex(index); 
    return (E) elements[index]; 
  }

  @SuppressWarnings("unchecked")
  public E remove(int index) {
    checkIndex(index); 
    E removed = (E) elements[index];

    int numToShift = size - index - 1; 
    if (numToshift > 0) {
      System.arraycopy(elements, index + 1, elements, index, numToShift); 
    }

    elements[--size] = null; 
    return removed; 
  }

  public boolean contains(E element) {
    if (element == null) {
      for (int i = 0; i < size; i++) {
        if (elements[i] == null) return true;
      }
    } else {
      for (int i = 0; i < size; i++) {
        if (element.equals(elements[i])) return true; 
      }
    }
    return false; 
  }

  public void clear() {
    for (ibt i = 0; i < size; i++) {
      elements[i] = null; 
    }
    size = 0; 
  }

  public int size() {
    return size; 
  }

  private void ensureCapacity() {
    if (size == elements.length) {
      int newCapacity = elements.length + (elements.length >> 1); 
      elements = Arrays.copyOf(elements, newCapacity); 
    }
  }

  private void checkIndex(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
    }
  }
}
