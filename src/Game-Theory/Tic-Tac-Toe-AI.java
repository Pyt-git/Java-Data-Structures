public final class TicTacToeAI { 

  public Move findBestMove(TicTacToeBoard board) {
    Move bestMove = null; 

    int bestValue = Integer.MIN_VALUE; 
    for (Move move : board.getAvailableMoves()) {
      
      TicTacToeBoard child = new TicTacToeBoard(board); 
      child.makeMove(move, Mark.X); 

      int value = alphaBeta(child, false, Integer.MIN_VALUE, Integer.MAX_VALUE); 
      if (value > bestValue) {

        bestValue = value; 
        bestMove = move; 
      }
    }

    return bestMove; 
  }

  private int alphaBeta(TicTacToeBoard board, boolean maximizingPlayer, int alpha, int beta) {
    if (board.isGameOver()) {
      return board.evaluate(); 
    }

    if (maximizingPlayer) {
      int value = Integer.MIN_VALUE; 

      for (Move move : board.getAvailableMoves()) {
        TicTacToeBoard child = new TicTacToeBoard(board); 

        child.makeMove(move, Mark.X); 
        value = Math.max(value, alphaBeta(child, false, alpha, beta)); 

        alpha = Math.max(alpha, value); 
        if (alpha >= beta) {
          break; 
        }
      }

      return value; 
    }

    int value = Integer.MAX_VALUE; 
    for (Move move : board.getAvailableMoves()) {

      TicTacToeBoard child = new TicTacToeBoard(board); 
      child.makeMove(move, Mark.O); 

      value = Math.min(value, alphaBeta(child, true, alpha, beta)); 
      beta = Math.min(beta, value); 

      if (alpha >= beta) {
        break; 
      }
    }

    return value;
  }
}

// Example game with AI: 

public static void Main(String[] args) {
  TicTacToeBoard board = new TicTacToeBoard(); 
  TicTacToeAI ai = new TicTacToeAI(); 

  board.makeMove(new Move(1, 1), Mark.O); 
  Move bestMove = ai.findBestMove(board); 

  board.makeMove(bestMove, Mark.X); 
  System.out.println(board); 
}
