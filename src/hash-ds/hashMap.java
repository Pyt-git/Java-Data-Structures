public class MyHashMap<K, V> {

  private static class Node<K, V> {
    final K key; 
    V value;
    Node<K, V> next; 

    Node(K key, V value) {
      this.key = key; 
      this.value = value; 
    }
  }

  private Node<K, V>[] buckets; 
  private int size; 
  private int capacity; 
  private static final double LOAD_FACTOR = 0.75; 

  public MyHashMap(int initialCapacity) {
    capacity = initialCapacity; 
    buckets = (Node<K,V>[]) new Node[capacity];
    size = 0; 
  }

  private int hash(K key) {
    if (key == null) return 0; 
    return Math.abs(key.hashCode() % capacity); 
  }

  public void put(K key, V value) {
    int index = hash(key); 
    Node<K, V> head = buckets[index]; 

    while (head != null) {
      if (head.key.equals(key)) {
        head.value = value; 
        return;
      }
      head = head.next; 
    }

    Node<K, V> newNode = new Node<>(key, value); 
    newNode.next = buckets[index]; 
    buckets[index] = newNode; 
    size++;

    if ((double) size / capacity > LOAD_FACTOR) {
      resize(); 
    }
  }

  public V get(K key) {
    int index = hash(key); 
    Node<K, V> head = buckets[index]; 

    while (head != null) {
      if (head.key.equals(key)) {
        return head.value; 
      }
      head = head.next; 
    }
    return null; 
  }

  public V remove(K key) {
    int index = hash(key); 
    Node<K, V> head = buckets[index]; 
    Node<K, V> prev = null; 

    while (head != null) { 
      if (head.key.equals(key)) {
        if (prev == null) {
          buckets[index] = head.next; 
        } else {
          prev.next = head.next; 
        }
        size--; 
        return head.value; 
      }
      prev = head; 
      head = head.next; 
    }
    return null;
  }

  private void resize() {
    int oldCapacity = capacity; 
    capacity = capacity * 2; 

    Node<K, V>[] oldBuckets = buckets; 
    buckets = (Node<K, V>[]) new Node[capacity]; 
    size = 0; 

    for (int i = 0, i < oldCapacity; i++) {
      Node<K, V> head = oldBuckets[i]; 
      while (head != null) {
        put(head.key, head.value); 
        head = head.next; 
      }
    }
  }

  public int size() {
    return size; 
  }

  public boolean containsKey(K key) {
    int index = hash(key); 
    Node<K, V> current = buckets[index]; 

    while (current != null) {
      if (current.key.equals(key)) {
        return true; 
      }
      return false;
    }
  }
}
