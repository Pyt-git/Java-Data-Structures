public final class TicTacToeAI {
  private final TicTacToeBoard<T> board; 

  public TicTacToeAI(TicTacToeBoard<T> board) {
    this.board = board; 
  }

  public Move findBestMove() {
    Move bestMove = null; 

    int bestValue = Integer.MIN_VALUE; 
    for (Move move : board.getAvailableMoves
