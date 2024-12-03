class Board {
    private Piece[][] board;

    public Board() {
        board = new Piece[8][8];
        setupBoard();
    }

    private void setupBoard() {
        board[0][0] = new Rook("black");
        board[0][1] = new Knight("black");
        board[0][2] = new Bishop("black");
        board[0][3] = new Queen("black");
        board[0][4] = new King("black");
        board[0][5] = new Bishop("black");
        board[0][6] = new Knight("black");
        board[0][7] = new Rook("black");
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn("black");
        }
        board[7][0] = new Rook("white");
        board[7][1] = new Knight("white");
        board[7][2] = new Bishop("white");
        board[7][3] = new Queen("white");
        board[7][4] = new King("white");
        board[7][5] = new Bishop("white");
        board[7][6] = new Knight("white");
        board[7][7] = new Rook("white");
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn("white");
        }
    }

    public Piece getPiece(int x, int y) {
        return board[y][x];
    }

    public boolean movePiece(int startX, int startY, int destX, int destY) {
        Piece piece = board[startY][startX];
        if (piece == null || !piece.isValidMove(startX, startY, destX, destY, this)) {
            return false;
        }
        Piece targetPiece = board[destY][destX];
        if (targetPiece != null && targetPiece.color.equals(piece.color)) {
            return false;
        }
        board[destY][destX] = piece;
        board[startY][startX] = null;
        if (piece instanceof Pawn) {
            ((Pawn) piece).setHasMoved();
        }
        return true;
    }

    public boolean isPathClear(int startX, int startY, int destX, int destY) {
        int xDirection = Integer.compare(destX, startX);
        int yDirection = Integer.compare(destY, startY);
        int currentX = startX + xDirection;
        int currentY = startY + yDirection;
        while (currentX != destX || currentY != destY) {
            if (board[currentY][currentX] != null) {
                return false;
            }
            currentX += xDirection;
            currentY += yDirection;
        }
        return true;
    }

    public void printBoard() {
        System.out.println("    a  b  c  d  e  f  g  h");
        System.out.println(" +-----------------------+");
        for (int y = 0; y < 8; y++) {
            System.out.print((8 - y) + "| ");
            for (int x = 0; x < 8; x++) {
                if (board[y][x] != null) {
                    System.out.print(board[y][x].toString() + " ");
                } else {
                    System.out.print(".  ");
                }
            }
            System.out.println("| " + (8 - y));
        }
        System.out.println(" +----------------------+");
        System.out.println("    a  b  c  d  e  f  g  h");
    }

    public int[] convertChessNotation(String notation) {
        int x = notation.charAt(0) - 'a';
        int y = 8 - Character.getNumericValue(notation.charAt(1));
        return new int[]{x, y};
    }
}
