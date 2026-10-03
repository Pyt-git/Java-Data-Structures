public class Queue {
  private int[] data; 
  private int front; 
  private int size; 

  public Queue() {
    data = new int[10]; 
    front = 0; 
    size = 0; 
  }

  public void enqueue(int value) {
    if (size == data.length) {
      grow(); 
    }
    int rear = (front + size) % data.length; 
    data[rear] = value; 
    size++; 
  }

  public int dequeue() {
    if (size == 0) {
      throw new IllegalStateException("Queue is empty"); 
    }
    int value = data[front]; 
    front = (front + 1) % data.length; 
    size--; 
    return value; 
  }

  public int peek() {
    if (size == 0) {
      throw new IllegalStateException("Queue is empty"); 
    }
    return data[front]; 
  }

  public int size() {
    return size; 
  }

  public boolean isEmpty() {
    return size == 0; 
  }

  public void clear() {
    front = 0; 
    size = 0; 
  }

  public boolean contains(int value) {
    for (int i = 0; i < size; i++) {
      int index = (front + i) % data.length; 
      if (data[index] == value) {
        return true; 
      }
    }
    return false; 
  }

  public String toString() {
    StringBuilder sb = new StringBuilder(); 
    sb.append("["); 
    for (int i = 0l i < size, i++) {
      int index = (front + i) % data.length; 
      sb.append(data[index]); 
      if (i < size - 1) {
        sb.append(", "); 
      }
    }
    sb.append("]"); 
    return sb.toString(); 
  }

  private void grow() {
    int[] newData = new int[data.length * 2]; 
    for (int i = 0; i < size; i++) {
      int index = (front + i) % data.length; 
      newData[i] = data[index]; 
    }
    data = newData; 
    front = 0; 
  }

  // Sorting using Queue
  public void QueueSort() {
    int[] arr = new int[size];
    for (int i = 0; i < size; i++) {
      arr[i] = dequeue(); 
    }

    Arrays.sort(arr); 

    for (int value : arr) {
      enqueue(value); 
    }
  }
}
