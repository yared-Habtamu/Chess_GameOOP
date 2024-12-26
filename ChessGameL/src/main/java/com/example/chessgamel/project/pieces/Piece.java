package com.example.chessgamel.project.pieces;

import com.example.chessgamel.project.mainChessApp.Cell;
import com.example.chessgamel.project.mainChessApp.MoveValidator;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Translate;

public abstract class Piece extends Group{
	public int type;
	public int xPos;
	public int yPos;
	public String name;
	protected ImageView imageView = new ImageView();

	protected Translate pos;
	protected MoveValidator gameLogic = new MoveValidator();
	public boolean isFirstTime;
	public boolean isASavior = false;
	
	public Piece(int type, int xPos, int yPos) {
		this.type = type;
		this.xPos = xPos;
		this.yPos = yPos;
		isFirstTime = true;
	}

	public void SelectPiece(Cell board) {
	}

	public void MovePiece(Cell cell, int x, int y) {
		cell.setBoard(this.xPos, this.yPos, 0);
		cell.setPiece(this.xPos, this.yPos, null);
		if (!cell.checkState && this.canCastle(cell)!=0){
			if(this.canCastle(cell)==1){
				cell.setBoard(x-1, y, this.type);
				cell.setPiece(x-1, y, this);
				this.xPos = x - 1;
				cell.setBoard(5, y, cell.getPiece(7, y).type);
				cell.setPiece(5, y, cell.getPiece(7, y));
				cell.getPiece(7, y).xPos = 7;
				cell.setBoard(7, y, 0);
				cell.setPiece(7, y, null);
			}
			if(this.canCastle(cell)==2){
				cell.setBoard(x+2, y, this.type);
				cell.setPiece(x+2, y, this);
				this.xPos = x + 2;
				cell.setBoard(3, y, cell.getPiece(0, y).type);
				cell.setPiece(3, y, cell.getPiece(0, y));
				cell.getPiece(3, y).xPos = 3;
				cell.setBoard(0, y, 0);
				cell.setPiece(0, y, null);
			}
			if(this.canCastle(cell)==3){
				cell.setBoard(x-1, y, this.type);
				cell.setPiece(x-1, y, this);
				this.xPos = x - 1;
				cell.setBoard(5, y, cell.getPiece(7, y).type);
				cell.setPiece(5, y, cell.getPiece(7, y));
				cell.getPiece(5, y).xPos = 5;
				cell.setBoard(7, y, 0);
				cell.setPiece(7, y, null);
			}
			if(this.canCastle(cell)==4){
				cell.setBoard(x+2, y, this.type);
				cell.setPiece(x+2, y, this);
				this.xPos = x + 2;
				cell.setBoard(3, y, cell.getPiece(0, y).type);
				cell.setPiece(3, y, cell.getPiece(0, y));
				cell.getPiece(3, y).xPos = 3;
				cell.setBoard(0, y, 0);
				cell.setPiece(0, y, null);
			}
		}
		else{
			this.xPos = x;
			this.yPos = y;
			if (cell.getPiece(x, y) != null)
				cell.getPiece(x, y).capture(cell);
			cell.setBoard(x, y, this.type);
			cell.setPiece(x, y, this);
			if (this.name == "Pawn" && ((this.type == 1 && this.yPos == 0) || (this.type == 2 && this.yPos == 7)))
			{
				cell.createPromotePiece(this);
				if (this.type == 1)
					cell.playerOnePawn--;
				else
					cell.playerTwoPawn--;
			}
		}
	}
	public ImageView getImage() {
		return (imageView);
	}
	
	public void centerImage() {
        Image img = imageView.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = imageView.getFitWidth() / img.getWidth();
            double ratioY = imageView.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            imageView.setX((imageView.getFitWidth() - w) / 2);
            imageView.setY((imageView.getFitHeight() - h) / 2);

        }
    }
	
	// Capture method: When a piece is captured by another one
	public void capture(Cell cell) {
		if (this.type == 1)
		{
			if (this.name == "Rook")
				cell.playerOneRook--;
			else if (this.name == "Knight")
				cell.playerOneKnight--;
			else if (this.name == "Queen")
				cell.playerOneQueen--;
			else if (this.name == "Pawn")
				cell.playerOnePawn--;
			else if (this.name == "Bishop" && (this.xPos + this.yPos) % 2 != 0)
				cell.playerOneBishopDarkSquare--;
			else if (this.name == "Bishop" && (this.xPos + this.yPos) % 2 == 0)
				cell.playerOneBishopLightSquare--;
		}
		else
		{
			if (this.name == "Rook")
				cell.playerTwoRook--;
			else if (this.name == "Knight")
				cell.playerTwoKnight--;
			else if (this.name == "Queen")
				cell.playerTwoQueen--;
			else if (this.name == "Pawn")
				cell.playerTwoPawn--;
			else if (this.name == "Bishop" && (this.xPos + this.yPos) % 2 == 0)
				cell.playerTwoBishopLightSquare--;
			else if (this.name == "Bishop" && (this.xPos + this.yPos) % 2 != 0)
				cell.playerTwoBishopDarkSquare--;
		}
		cell.getChildren().remove(this.getImage());
	}
	
	public int canCastle(Cell cell){
		return 0;
	}

	public void resize(double width, double height) {
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
	}

	// overridden version of the relocate method
	public void relocate(double x, double y) {
		imageView.setTranslateX(x);
		imageView.setTranslateY(y);	
		centerImage();
	}
	
	public void resetPiece()
	{
		this.isFirstTime = true;
		this.isASavior = false;
	}
	public boolean isFirstTime() {
		return isFirstTime;
	}
	public void setFirstTime(boolean isFirstTime) {
		this.isFirstTime = isFirstTime;
	}
	public int getX(){
		return this.xPos;
	}
	public int getY(){
		return this.yPos;
	}
}
