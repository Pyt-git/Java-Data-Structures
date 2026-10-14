public class Stack {
  private int[] data; 
  private int size; 

  public Stack() {
    data = new int[10]; 
    size = 0; 
  }

  public void push(int value) {
    if (size == data.length) {
      grow(); 
    }
    data[size] = value; 
    size++; 
  }

  public int pop() {
    if (size == 0) {
      throw new IllegalStateException("Stack is empty"); 
    }
    size--; 
  return data[size]; 
  }

  public int peek() {
    if (size == 0) {
      throw new IllegalStateException("Stack is empty"); 
    }
    return data[size - 1]; // LIFO without popping. 
  }

  public int size() {
    return size; 
  }

  public boolean isEmpty() {
    return size == 0; 
  }

  public void clear() {
    size = 0; 
  }

  public boolean contains(int value) {
    for (int i = 0; i < size; i++) {
      if (data[i] == value) {
        return true; 
      }
    }
    return false; 
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(); 
    sb.append("["); 

    for (int i = 0; i < size; i++) {
      ab.append(data[i]);
      if (i < size - 1) {
        sb.append(", ");
      }
    }
    sb.append("]");
    return sb.toString(); 
  }
  
  // grow() creates new copy of existing Stack. 
  private void grow() { 
    int[] newData = new int[data.length * 2]; 
    for (int i = 0; i < data.length; i++) {
      newData[i] = data[i]; 
    }
    data = newData; 
  }
}
        
