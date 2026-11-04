import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TicTacToeAITest {
  private TicTacToeAI ai; 
  private TicTacToeBoard board;

  @BeforeEach
  void setUp() {
    ai = new TicTacToeAI(); 
    board = new TicTacToeBoard(); 
  }

  private void play(int... rowColPairs) {}

  @Test
  void emptyBoardReturnsLegalMove() {
    Move best = ai.findBestMove(board); 
    assertNotNull(best); 
    assertTrue(board.isLegalMove(best)); 
  }

  
    
