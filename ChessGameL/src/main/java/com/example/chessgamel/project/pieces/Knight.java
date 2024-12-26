package com.example.chessgamel.project.pieces;

import com.example.chessgamel.project.mainChessApp.Cell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Knight extends Piece {
	private Image image;
	
	public Knight(int type, int xPos, int yPos) {
		super(type, xPos, yPos);
		name = "Knight";
		if(type==1){
			image = new Image("file:src/main/java/com/ChessPiece/White_Knight.png");
			imageView.setImage(image);
			imageView.fitHeightProperty();
			imageView.fitWidthProperty();
	        imageView.setPreserveRatio(true);
	        imageView.setSmooth(true);
	        imageView.setCache(true);
		}else{
			image = new Image("file:src/main/java/com/ChessPiece/Black_Knight.png");
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
		int x = 0;
		cell.colorSquare(this.xPos, this.yPos, true);
		if (cell.checkState && !this.isASavior)
			return;
		if (gameLogic.verticalProtection(cell, this.xPos, this.yPos, this.type) || gameLogic.horizontalProtection(cell, this.xPos, this.yPos, this.type) ||
			gameLogic.slashDiagonalProtection(cell, this.xPos, this.yPos, this.type) || gameLogic.backslashDiagonalProtection(cell, this.xPos, this.yPos, this.type))
			return;
		for (int y = -2; y <= 2; y++)
		{
			if (y != 0)
			{
				x = y % 2 == 0 ? 1 : 2;
				if (this.yPos + y >= 0 && this.yPos + y < cell.getBoardHeight() && this.xPos - x >= 0 && this.xPos - x < cell.getBoardWidth() && cell.getBoardPosition(this.xPos - x, this.yPos + y) != this.type)
				{
					if (cell.checkState)
					{
						if (gameLogic.isThisProtecting(cell, this.xPos - x, this.yPos + y, this.type))
							cell.colorSquare(this.xPos - x, this.yPos + y, false);
					}
					else
						cell.colorSquare(this.xPos - x, this.yPos + y, false);
				}
				if (this.yPos + y >= 0 && this.yPos + y < cell.getBoardHeight() && this.xPos + x >= 0 && this.xPos + x < cell.getBoardWidth() && cell.getBoardPosition(this.xPos + x, this.yPos + y) != this.type)
				{
					if (cell.checkState)
					{
						if (gameLogic.isThisProtecting(cell, this.xPos + x, this.yPos + y, this.type))
							cell.colorSquare(this.xPos + x, this.yPos + y, false);
					}
					else
						cell.colorSquare(this.xPos + x, this.yPos + y, false);
				}
			}
		}
	}	
}
