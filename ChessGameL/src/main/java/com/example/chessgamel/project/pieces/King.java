package com.example.chessgamel.project.pieces;

import com.example.chessgamel.project.mainChessApp.Cell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class King extends Piece {
	private Image image;
	
	public King(int type, int xPos, int yPos) {
		super(type, xPos, yPos);
		name = "King";
		if(type==1){
			image = new Image("file:src/main/java/com/ChessPiece/White_King.png");
			imageView.setImage(image);
			imageView.fitHeightProperty();
			imageView.fitWidthProperty();
	        imageView.setPreserveRatio(true);
	        imageView.setSmooth(true);
	        imageView.setCache(true);
		}
		else{
			image = new Image("file:src/main/java/com/ChessPiece/Black_King.png");
			imageView.setImage(image);
			imageView.fitHeightProperty();
			imageView.fitWidthProperty();
	        imageView.setPreserveRatio(true);
	        imageView.setSmooth(true);
	        imageView.setCache(true);
		}
	}
	
	@Override
	public ImageView getImage() {
		return (imageView);
	}
	
	@Override
	public void SelectPiece(Cell cell) {
		int x = this.xPos;
		int y = this.yPos;
		cell.colorSquare(this.xPos, this.yPos, true);
		for (y = this.yPos - 1; y <= this.yPos + 1; y++)
		{
			for (x = this.xPos - 1; x <= this.xPos + 1; x++)
			{
				if(y >= 0 && y < cell.getBoardHeight() && x >= 0 && x < cell.getBoardWidth() && cell.getBoardPosition(x, y) != this.type)
				{
					if (!cell.checkState)
						this.canCastle(cell);
					if (!gameLogic.isCheck(cell, x, y, this.type, true))
						cell.colorSquare(x, y, false);
				}
			}
		}


		
	}
	public int canCastle(Cell cell){
		int canCastle =0;


		if(type==2 && this.isFirstTime && cell.getBoardPosition(5, 0) == 0 && cell.getBoardPosition(6, 0) == 0 && cell.getPiece(7, 0) != null && cell.getPiece(7, 0).isFirstTime){
			canCastle = 1;
			cell.colorSquare(7, 0, false);
		}
		if(type==2 && this.isFirstTime && cell.getBoardPosition(1, 0) == 0 && cell.getBoardPosition(2, 0) == 0 && cell.getBoardPosition(3, 0) == 0 && cell.getPiece(0, 0) != null && cell.getPiece(0, 0).isFirstTime){
			canCastle = 2;
			cell.colorSquare(0, 0, false);
		}

		if(type==1 && this.isFirstTime && cell.getBoardPosition(5, 7) == 0 && cell.getBoardPosition(6, 7) == 0 && cell.getPiece(7, 7) != null && cell.getPiece(7, 7).isFirstTime){
			canCastle = 3;
			cell.colorSquare(7, 7, false);
		}
		if(type==1 && this.isFirstTime && cell.getBoardPosition(1, 7) == 0 && cell.getBoardPosition(2, 7) == 0 && cell.getBoardPosition(3, 7) == 0 && cell.getPiece(0, 7) != null && cell.getPiece(0, 7).isFirstTime){
			canCastle = 4;
			cell.colorSquare(0, 7, false);
		}
		return canCastle; 
	}
}
