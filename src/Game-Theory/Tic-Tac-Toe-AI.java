public final class TicTacToeAI { 

  public Move findBestMove(TicTacToeBoard) {
    Move bestMove = null; 

    int bestValue = Integer.MIN_VALUE; 
    for (Move move : board.getAvailableMoves()) {
      
      TicTacToeBoard<T> child = new TicTacToeBoard(board); 
      child.makeMove(move, Mark.X); 

      int value = alphaBeta(child, false, Integer.MIN_VALUE, Integer.MAX_VALUE); 
      if (value > bestValue) {

        bestValue = value; 
        bestMove = move; 
      }
    }

    return bestMove; 
  }

  private int alphaBeta(
    
