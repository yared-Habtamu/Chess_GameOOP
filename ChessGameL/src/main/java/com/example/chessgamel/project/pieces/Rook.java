package com.example.chessgamel.project.pieces;

import com.example.chessgamel.project.mainChessApp.Cell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Rook extends Piece {
	private Image image;

	public Rook(int type, int xPos, int yPos) {
		super(type, xPos, yPos);
		name = "Rook";
		if(type==1){
			image = new Image("file:src/main/java/com/ChessPiece/White_Rook.png");
			imageView.setImage(image);
			imageView.fitHeightProperty();
			imageView.fitWidthProperty();
	        imageView.setPreserveRatio(true);
	        imageView.setSmooth(true);
	        imageView.setCache(true);
		}else{
			image = new Image("file:src/main/java/com/ChessPiece/Black_Rook.png");
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
		cell.colorSquare(this.xPos, this.yPos, true);
		if (cell.checkState && !this.isASavior)
			return;
		if (gameLogic.slashDiagonalProtection(cell, this.xPos, this.yPos, this.type) || gameLogic.backslashDiagonalProtection(cell, this.xPos, this.yPos, this.type))
			return;
		if (!gameLogic.horizontalProtection(cell, this.xPos, this.yPos, this.type))
		{
			for (int y = this.yPos - 1; y >= 0; y--)
			{
				if (cell.getBoardPosition(this.xPos, y) == 0)
				{
					if (cell.checkState)
					{
						if (gameLogic.isThisProtecting(cell, this.xPos, y, this.type))
							cell.colorSquare(this.xPos, y, false);
					}
					else
						cell.colorSquare(this.xPos, y, false);
				}
				else if (cell.getBoardPosition(this.xPos, y) == this.type)
					break;
				else
				{
					if (cell.checkState)
					{
						if (gameLogic.isThisProtecting(cell, this.xPos, y, this.type))
							cell.colorSquare(this.xPos, y, false);
					}
					else
						cell.colorSquare(this.xPos, y, false);
					break;
				}
			}
			for (int y = this.yPos + 1; y < cell.getBoardHeight(); y++)
			{
				if (cell.getBoardPosition(this.xPos, y) == 0)
				{
					if (cell.checkState)
					{
						if (gameLogic.isThisProtecting(cell, this.xPos, y, this.type))
							cell.colorSquare(this.xPos, y, false);
					}
					else
						cell.colorSquare(this.xPos, y, false);
				}
				else if (cell.getBoardPosition(this.xPos, y) == this.type)
					break;
				else
				{
					if (cell.checkState)
					{
						if (gameLogic.isThisProtecting(cell, this.xPos, y, this.type))
							cell.colorSquare(this.xPos, y, false);
					}
					else
						cell.colorSquare(this.xPos, y, false);
					break;
				}
			}
		}
		if (!gameLogic.verticalProtection(cell, this.xPos, this.yPos, this.type))
		{
			for (int x = this.xPos - 1; x >= 0; x--)
			{
				if (cell.getBoardPosition(x, this.yPos) == 0)
				{
					if (cell.checkState)
					{
						if (gameLogic.isThisProtecting(cell, x, this.yPos, this.type))
							cell.colorSquare(x, this.yPos, false);
					}
					else
						cell.colorSquare(x, this.yPos, false);
				}
				else if (cell.getBoardPosition(x, this.yPos) == this.type)
					break;
				else
				{
					if (cell.checkState)
					{
						if (gameLogic.isThisProtecting(cell, x, this.yPos, this.type))
							cell.colorSquare(x, this.yPos, false);
					}
					else
						cell.colorSquare(x, this.yPos, false);
					break;
				}
			}
			for (int x = this.xPos + 1; x < cell.getBoardWidth(); x++)
			{
				if (cell.getBoardPosition(x, this.yPos) == 0)
				{
					if (cell.checkState)
					{
						if (gameLogic.isThisProtecting(cell, x, this.yPos, this.type))
							cell.colorSquare(x, this.yPos, false);
					}
					else
						cell.colorSquare(x, this.yPos, false);
				}
				else if (cell.getBoardPosition(x, this.yPos) == this.type)
					break;
				else
				{
					if (cell.checkState)
					{
						if (gameLogic.isThisProtecting(cell, x, this.yPos, this.type))
							cell.colorSquare(x, this.yPos, false);
					}
					else
						cell.colorSquare(x, this.yPos, false);
					break;
				}
			}
		}
	}	
}