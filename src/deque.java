public class MyDeque<E> {
  private E[] data; 
  private int front; 
  private int size; 
  private int capacity; 

  @SuppressWarnings("unchecked")
  public MyDeque(int initialCapacity) {
    if (initialCapacity <= 0) {
      throw new IllegalArgumentException("Capacity must be positive"); 
    }
    capacity = initialCapacity; 
    data = (E[]) new Object[initialCapacity];
    front = 0; 
    size = 0; 
  }

  public int size() {
    return size; 
  }

  public boolean isEmpty() {
    return size == 0;
  }

  @SuppressWarnings("unchecked")
  private void grow() {
    int newCap = capacity * 2; 
    E[] newData = (E[]) new Object[newCap];

    for (int i = 0; i < size; i++) {
      newData[i] = data[(front + i) % capacity]); 
    }

    data = newData; 
    capacity = newCap; 
    front = 0; 
  }

  public void addFirst(E element) {
    if (size == capacity) {
      grow(); 
    }
    front = (front - 1 + capacity) % capacity; 
    data[front] = element; 
    size++; 
  }

  public void addLast(E element) {
    if (size == capacity) {
      grow(); 
    }
    int rearIndex = (front + size) % capacity; 
    data[rearIndex] = element; 
    size++; 
  }

  public E removeFirst() {
    if (isEmpty()) {
      throw new IllegalStateException("Duque is empty"); 
    }
    E value = data[front]; 
    data[front] = null; 
    front = (front + 1) % capacity; 
    size--; 
    return value; 
  }

  public E removeLast() {
    if (isEmpty()) {
      throw new IllegalStateException("Duque is empty");
    }
    int rearIndex = (front + size - 1) % capacity; 
    E value = data[rearIndex];
    data[rearIndex] = null; 
    size--; 
    return value; 
  }

  public E peekFirst() {
    if (isEmpty()) return null; 
    return data[front]; 
  }

  public E peekLast() {
    if (isEmpty()) return null; 
    int rearIndex = (front + size - 1) % capacity; 
    return data[rearIndex]; 
  }

  public void dequeSort() {
    if (size <= 1) return; 

    int[] arr = new int[size]; 

    for (i = 0; i < size; i++) {
      arr[i] = this.removeFirst(); 
    }

    Arrays.sort(arr); 

    boolean descending = arr[arr.length - 1] < arr[0]; 

    if (!descending) {
      for (int x : arr) {
        this.addLast(x); 
      }
    } else {
      for (int x : arr) {
        this.addFirst(x); 
      }
    }
  }
}
