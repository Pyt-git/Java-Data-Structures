// helpers for tic-tac-toe AI. 

public enum Mark {
  X,
  O, 
  EMPTY
}

public record Move(
  int row, 
  int column) {}
