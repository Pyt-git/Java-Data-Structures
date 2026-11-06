import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.Test; 
import static org.junit.api.Assertions.*;

class MyArrayListTest {
  private MyArrayList<Integer> list; 

  @BeforeEach
  void setUp() {
    list = new MyArrayList<>(); 
  }

  @Test
  void addingPreservesOrder() {
    list.add(10); 
    list.add(20); 
    list.add(30); 

    assertEquals(3, list.size()); 
    assertEquals(Integer.valueOf(20), list.get(1)); 
  }

  void emptyArrayList() {
    assertEquals(0, list.size()); 
    assertTrue(list.isEmpty()); 
  }

  void removingPreservesOrder() {
    
