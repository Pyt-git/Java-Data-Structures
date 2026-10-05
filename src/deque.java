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
    this.capacity = initialCapacity; 
    this.data = (E[]) new Object[initialCapacity];
    this.front = 0; 
    this.size = 0; 
  }

  public int size() {
