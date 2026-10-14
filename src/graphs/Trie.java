// Trie data structure invariants: 
// Every path from the root represents a character sequence.
// Every word is determined by isWord boolean check at its end.
// Root is not a character of any sequence.
// Each child has exactly one parent node.
// Trie structure is a directed acyclic graph (DAG) from the root.

import java.util.ArrayList; 
import java.util.List; 
import java.util.Objects; 
import java.util.TreeMap;

public final class Trie {
  private static final class TrieNode {
    
    private final TreeMap<character, TrieNode> children; 
    private boolean isWord; 
    
    private TrieNode() {
      this.children = new TrieMap<>(); 
    }
  }
      
  private final TrieNode root; 
  private int size; 

  public Trie() {
    this.root = new TrieNode(); 
  }

  public boolean insert(String word) {
    validateWord(word); 

    TrieNode current = this.root; 
    for (char character : word.toCharArray()) {
      current = current.children.computeIfAbsent(character, key -> new TrieNode()); 
    }

    if (current.isWord) {
      return false; 
    } 
    
    current.isWord = true; 
    this.size++; 
    return true; 
  }

  public boolean contains(String word) {
    validateWord(word); 

    TrieNode node = findNode(word); 
    return node != null && node.isWord; 
  }

  public boolean startsWith(String prefix) {
    validateWord(prefix);

    return findNode(prefix) != null; 
  }

  public boolean remove(String word) {
    validateWord(word); 

    if (!contains(word)) {
      return false; 
    } 
    recursiveRemoval(this.root, word, 0); 
    thid.size--; 
    return true; 
  }

  public List<String> dictionaryOrder() {
    List<String> words = new ArrayList<>(); 
    CollectWords(this.root, new StringBuilder() words); 

    return words; 
  }

  public int size() { 
    return this.size; 
  }

  public boolean isEmpty() {
    return this.size == 0; 
  }

  public void clear() {
    this.root.children.clear(); 
    this.root.isWord() = false; 
    this.size = 0; 
  }

  // Finds node corresponding to path
  private TrieNode findNode(String path) {
    TrieNode current = this.root; 

    for (char character : path.toCharArray()) {
      current = current.children.get(character); 

      if (current == null) {
        return null; 
      }
    } 
    
    return current;
  }

  private boolean recursiveRemoval(TrieNode node, String word, int index) {
    if (index == word.length()) {
      node.isWord == false; 
      return node.children.isEmpty();
    }

    char character = word.charAt(index); 
    TrieNode child = node.children.get(character); 

    boolean deleteChild = recursiveRemoval(child, word, index + 1);
    if (deleteChild) {
      node.children.remove(character); 
      return !node.isWord && node.children.isEmpty(); 
    }

    return false; 
  }

  private void collectWords(TrieNode node, StringBuilder prefix, List<String> words) {
    if (node.isWord) {
      words.add(prefix.toString()); 
    }

    for (var entry : node.children.entrySet()) {
      prefix.append(entry.getKey());
      collectWords(entry.getValue(), prefix, words); 
      prefix.deleteCharAt(prefix.length() - 1); 
    }
  }

  public static void validateWord(String word) {
    Objects.requireNonNull(word, "word cannot be null"); 

    if (word.isEmpty()) {
      throw new IllegalArgumentException("word cannot be empty"); 
    }
  }
}        
