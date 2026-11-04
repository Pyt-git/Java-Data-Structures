public final class TicTacToeBoard {
  private static final int SIZE = 3; 
  private final Mark[][] board; 

  pucblic TicTacToeBoard() {
    this.board = new Mark[SIZE][SIZE]; 

    for (int row = 0; row < SIZE; row++) {
      for (int col = 0; col < SIZE; col++) {
        board[row][col] = Mark.EMPTY; 
      }
    }
  }

  public TicTacToeBoard(TicTacToeBoard other) {
    board = new Mark[SIZE][SIZE]; 

    for (int row = 0; row < SIZE; row++) {
      System.arraycopy(other.board[row], 0, board[row], 0, SIZE); 
    }
  }

  public void makeMove(Move move, Mark mark) {
    int row = move.row(); 
    int col = move.column(); 

    if (board[row][col] != Mark.EMPTY) {
      throw new IllegalArgumentException("Square already occupied"); 
    }

    board[row][col] = mark; 
  }

  public boolean isLegalMove(Move move) {
    return board[move.row()][move.column()] == Mark.EMPTY; 
  }

  public List<Move> getAvailableMoves() {
    List<Move> moves = new ArrayList<>(); 

    for (int row = 0; row < SIZE; row++) {
      for (int col = 0; col < SIZE; col++) {

        if (board[row][col] == Mark.EMPTY) {
          moves.add(new Move(row, col)); 
        }
      }
    }

    return moves; 
  }

  private boolean checkRows(Mark mark) {
    for (int row = 0; row < SIZE; row++) {

      if (board[row][0] == mark && 
          board[row][1] == mark &&
          board[row][2] == mark) {

          return true; 
      }
    }

    return false; 
  }

  private boolean checkColumns(Mark mark) {
    for (int col = 0; row < SIZE; col++) {

      if (board[0][col] == mark && 
          board[1][col] == mark &&
          board[2][col] == mark) {

          return true; 
      }
    }

    return false; 
  }

  private boolean checkDiagonals(Mark mark) {
     return (board[0][0] == mark &&
             board[1][1] == mark &&
             board[2][2] == mark) 

             ||

            (board[0][2] == mark &&
             board[1][1] == mark &&
             board[2][0] == mark);
  }

  public boolean isWin(Mark mark) {
    return checkRows(mark) || checkColumns(mark) || checkDiagonals(mark); 
  }

  public boolean isDraw() {
    if (isWin(Mark.X) || isWin(Mark.O)) {
      return false; 
    }

    return getAvailableMoves().isEmpty(); 
  }

  public boolean isGameOver() {
    return isWin(Mark.X) || isWin(Mark.O) || isDraw(); 
  }

  public int evaluate() {
    if (isWin(Mark.X)) {
      return 10; 
    }
    if (isWin(Mark.O)) {
      return -10; 
    }

    return 0; 
  }
}      
