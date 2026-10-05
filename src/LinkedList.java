public class MyLinkedList<E> {
  private static class Node<E> {
    E data; 
    Node<E> next; 
    Node<E> prev; 

    Node(E data) {
      this.data = data; 
    }
  }

  private Node<E> head; 
  private Node<E> tail; 
  private int size; 

  public MyLinkedList() {
    head = null; 
    tail = null;
    size = 0; 
  }

  public void addFirst(E element) {
    Node<E> newNode = new Node<>(element); 

    if (head == null) {
      head = tail = newNode; 
    } else {
      newNode.next = head; 
      head.prev = newNode; 
      head = newNode; 
    }

    size++; 
  }

  public void addLast(E element) {
    Node<E> newNode = new Node<>(element); 

    if (tail == null) {
      head = tail = newNode; 
    } else {
      tail.next = newNode; 
      newNode.prev = tail; 
      tail = newNode; 
    }

    size++; 
  }

  public E get(int index) {
    checkIndex(index); 
    Node<E> current = head; 

    for (int i = 0; i < index; i++) {
      current = current.next; 
    }

    return current.data; 
  }

  public E removeFirst() {
    if (head == null) {
      throw new IllegalStateException("List is empty"); 
    }

    E value = head.data; 

    head = head.next; 
    if (head != null) {
      head.prev = null; 
    } else {
      tail = null; 
    }

    size--;
    return value; 
  }

  public E removeLast() {
    if (tail == null) {
      throw new IllegalStateException("List is empty"); 
    }

    E value = tail.data; 

    tail = tail.prev; 
    if (tail != null) {
      tail.next = null; 
    } else {
      head = null; 
    }

    size--;
    return value; 
  }

  public E removeWithIndex(int index) {
    checkIndex(index); 

    if (index == 0) return removeFIrst(); 
    if (index == size - 1) return removeLast(); 

    Node<E> current = head;
    for (int i = 0; i < index; i++) {
      current = current.next; 
    }

    E value = current.data; 

    current.prev.next = current.next; 
    current.next.prev = current.prev; 

    size--;
    return value; 
  }

  private void checkIndex(int index) { 
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size); 
    }
  }
}
