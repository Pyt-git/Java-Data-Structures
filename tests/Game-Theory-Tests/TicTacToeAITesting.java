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

  @Test
  void takesTheWin() {
    play(0, 0, 1, 0, 0, 1, 1, 1); 
    assertEquals(new Move(0, 2), ai.findBestMove(board)); 
  }

  @Test 
  void blocksTheThreat() {
    play(1, 1, 0, 0, 2, 2, 0, 1); 
    assertEquals(new Move(0, 2), ai.findBestMove(board)); 
  }

  @Test 
  void neverLosesAgainstAnyOpponentLine() {
    int[] oWins = {0};
    playOut(new TicTacToeBoard(), oWins); 
    assertEquals(0, oWins[0]); 
  }
}
