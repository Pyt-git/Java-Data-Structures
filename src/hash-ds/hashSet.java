public class MyHashSet<K> {
  private static final Object PRESENT = new Object(); 
  private final MyHashMap<K, Object> map = new HashMap<>(); 

  public boolean add(K key) {
    return map.put(key, PRESENT) == null; // check necessity of add execution
  }

  public boolean remove(K key) {
    return map.remove(key) != null; // check necessity of remove execution
  }

  public boolean contains(K key) {
    return map.containsKey(key); 
  }

  public int size() {
    return map.size(); 
  }

  public boolean isEmpty() {
    return size == 0;
  }
}
