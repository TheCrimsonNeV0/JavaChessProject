// Written by: Nevzat Umut Demirseren

public class Pawn extends Piece {
    boolean hasMoved;
    boolean color;

    public Pawn(boolean color) {
        hasMoved = false;
        this.color = color;
    }

    void move(int from_x, int from_y, int to_x, int to_y) throws InvalidMoveException {
        if (isPathAvailable(from_x, from_y, to_x, to_y)) {
            Board.chessBoardArray[from_x][from_y] = null;
            Board.chessBoardArray[to_x][to_y] = this;
        }
        else throw new InvalidMoveException();
        this.hasMoved = true;
    }

    @Override
    boolean isPathAvailable(int from_x, int from_y, int to_x, int to_y) {
        boolean isAvailable = false;
        if (from_x == to_x) {
            if (color == Piece.WHITE && from_y == to_y + 1) isAvailable = true;
            else if (color == Piece.WHITE && from_y == to_y + 2 && !hasMoved) isAvailable = true;
            else if (color == Piece.BLACK && from_y == to_y - 1) isAvailable = true;
            else if (color == Piece.BLACK && from_y == to_y - 2 && !hasMoved) isAvailable = true;
        } else if (Math.abs(from_x - to_x) == 1) {
            if (color == Piece.WHITE && from_y == to_y + 1 && Board.chessBoardArray[to_x][to_y] != null && Board.chessBoardArray[to_x][to_y].color == Piece.BLACK) isAvailable = true;
            else if (color == Piece.BLACK && from_y == to_y - 1 && Board.chessBoardArray[to_x][to_y] != null && Board.chessBoardArray[to_x][to_y].color == Piece.WHITE) isAvailable = true;
        } else {
            return false;
        }

        if (color == Piece.WHITE) { //White
            for (int i = to_y; i <= from_y; i++) {
                if (Board.chessBoardArray[to_x][i] == null) {
                    isAvailable = false;
                    break;
                }
            }
        }
        else { //Black
            for (int i = from_y; i <= to_y; i++) {
                if (Board.chessBoardArray[to_x][i] == null) {
                    isAvailable = false;
                    break;
                }
            }
        }
        return isAvailable;
    }
}