abstract class Piece {
    protected String color;

    public Piece(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public abstract boolean isValidMove(int startX, int startY, int destX, int destY, Board board);

    @Override
    public String toString() {
        return (color.equals("white") ? "W" : "B") + getClass().getSimpleName().charAt(0);
    }
}

class King extends Piece {
    public King(String color) {
        super(color);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int destX, int destY, Board board) {
        return Math.abs(startX - destX) <= 1 && Math.abs(startY - destY) <= 1;
    }
}

class Queen extends Piece {
    public Queen(String color) {
        super(color);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int destX, int destY, Board board) {
        if (startX == destX || startY == destY || Math.abs(startX - destX) == Math.abs(startY - destY)) {
            return board.isPathClear(startX, startY, destX, destY);
        }
        return false;
    }
}

class Rook extends Piece {
    public Rook(String color) {
        super(color);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int destX, int destY, Board board) {
        if (startX == destX || startY == destY) {
            return board.isPathClear(startX, startY, destX, destY);
        }
        return false;
    }
}

class Bishop extends Piece {
    public Bishop(String color) {
        super(color);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int destX, int destY, Board board) {
        if (Math.abs(startX - destX) == Math.abs(startY - destY)) {
            return board.isPathClear(startX, startY, destX, destY);
        }
        return false;
    }
}

class Knight extends Piece {
    public Knight(String color) {
        super(color);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int destX, int destY, Board board) {
        return (Math.abs(startX - destX) == 2 && Math.abs(startY - destY) == 1) ||
               (Math.abs(startX - destX) == 1 && Math.abs(startY - destY) == 2);
    }
}

class Pawn extends Piece {
    private boolean hasMoved;

    public Pawn(String color) {
        super(color);
        this.hasMoved = false;
    }

    @Override
    public boolean isValidMove(int startX, int startY, int destX, int destY, Board board) {
        int direction = color.equals("white") ? -1 : 1;
        if (startX == destX && startY + direction == destY && board.getPiece(destX, destY) == null) {
            return true;
        }
        if (startX == destX && startY + 2 * direction == destY && !hasMoved && board.getPiece(destX, destY) == null) {
            return board.getPiece(startX, startY + direction) == null;
        }
        if (Math.abs(startX - destX) == 1 && startY + direction == destY && board.getPiece(destX, destY) != null) {
            return !board.getPiece(destX, destY).color.equals(this.color);
        }
        return false;
    }

    public void setHasMoved() {
        this.hasMoved = true;
    }
}
