import java.util.Scanner;

public class Game {
    private Board board;
    private boolean isGameOver;
    private String currentPlayer;

    public Game() {
        board = new Board();
        isGameOver = false;
        currentPlayer = "white";
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        while (!isGameOver) {
            board.printBoard();
            System.out.println(currentPlayer + "'s turn. Enter move (e.g., e2 e4):");

            String start = scanner.next();
            String dest = scanner.next();

            int[] startCoords = board.convertChessNotation(start);
            int[] destCoords = board.convertChessNotation(dest);

            Piece piece = board.getPiece(startCoords[0], startCoords[1]);

            if (piece == null || !piece.getColor().equals(currentPlayer)) {
                System.out.println("Invalid move. It's " + currentPlayer + "'s turn. Try again.");
                continue;
            }

            if (!board.movePiece(startCoords[0], startCoords[1], destCoords[0], destCoords[1])) {
                System.out.println("Invalid move. Try again.");
            } else {
                currentPlayer = currentPlayer.equals("white") ? "black" : "white";
            }

            isGameOver = checkGameOver();
        }

        scanner.close();
    }

    private boolean checkGameOver() {
        return false;
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
