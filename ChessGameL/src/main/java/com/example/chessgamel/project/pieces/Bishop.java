package com.example.chessgamel.project.pieces;

import com.example.chessgamel.project.mainChessApp.Cell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bishop extends Piece {
	private Image image;
	public Bishop(int type, int xPos, int yPos) {
		super(type, xPos, yPos);
		name = "Bishop";
		if(type==1){
			image = new Image("file:src/main/java/com/ChessPiece/White_Bishop.png");
			imageView.setImage(image);
			imageView.fitHeightProperty();
			imageView.fitWidthProperty();
	        imageView.setPreserveRatio(true);
	        imageView.setSmooth(true);
	        imageView.setCache(true);
		}
		else{
			image = new Image("file:src/main/java/com/ChessPiece/Black_Bishop.png");
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
		int y = this.yPos + 1;
		cell.colorSquare(this.xPos, this.yPos, true);
		if (cell.checkState && !this.isASavior)
			return;
		if (gameLogic.horizontalProtection(cell, this.xPos, this.yPos, this.type) || gameLogic.verticalProtection(cell, this.xPos, this.yPos, this.type))
			return;
		if (!gameLogic.slashDiagonalProtection(cell, this.xPos, this.yPos, this.type))
		{
			for(int x = this.xPos + 1; x < cell.getBoardWidth() && y < cell.getBoardHeight(); x++, y++)
			{
				if (cell.getBoardPosition(x, y) == 0)
				{
					if (cell.checkState)
					{
						if (gameLogic.isThisProtecting(cell, x, y, this.type))
							cell.colorSquare(x, y, false);
					}
					else
						cell.colorSquare(x, y, false);
				}
				else if (cell.getBoardPosition(x, y) == this.type)
					break;
				else
				{
					if (cell.checkState)
					{
						if (gameLogic.isThisProtecting(cell, x, y, this.type))
							cell.colorSquare(x, y, false);
					}
					else
						cell.colorSquare(x, y, false);
					break;
				}
			}
			y = this.yPos - 1;
			for(int x = this.xPos - 1; x >= 0 && y >= 0; x--, y--)
			{
				if (cell.getBoardPosition(x, y) == 0)
				{
					if (cell.checkState)
					{
						if (gameLogic.isThisProtecting(cell, x, y, this.type))
							cell.colorSquare(x, y, false);
					}
					else
						cell.colorSquare(x, y, false);
				}
				else if (cell.getBoardPosition(x, y) == this.type)
					break;
				else
				{
					if (cell.checkState)
					{
						if (gameLogic.isThisProtecting(cell, x, y, this.type))
							cell.colorSquare(x, y, false);
					}
					else
						cell.colorSquare(x, y, false);
					break;
				}
			}
		}
		if (!gameLogic.backslashDiagonalProtection(cell, this.xPos, this.yPos, this.type))
		{
			y = this.yPos + 1;
			for (int x = this.xPos - 1; x >= 0 && y < cell.getBoardHeight(); x--, y++)
			{
				if (cell.getBoardPosition(x, y) == 0)
				{
					if (cell.checkState)
					{
						if (gameLogic.isThisProtecting(cell, x, y, this.type))
							cell.colorSquare(x, y, false);
					}
					else
						cell.colorSquare(x, y, false);
				}
				else if (cell.getBoardPosition(x, y) == this.type)
					break;
				else
				{
					if (cell.checkState)
					{
						if (gameLogic.isThisProtecting(cell, x, y, this.type))
							cell.colorSquare(x, y, false);
					}
					else
						cell.colorSquare(x, y, false);
					break;
				}
			}
			y = this.yPos - 1;
			for (int x = this.xPos + 1; x < cell.getBoardWidth() && y >= 0; x++, y--)
			{
				if (cell.getBoardPosition(x, y) == 0)
				{
					if (cell.checkState)
					{
						if (gameLogic.isThisProtecting(cell, x, y, this.type))
							cell.colorSquare(x, y, false);
					}
					else
						cell.colorSquare(x, y, false);
				}
				else if (cell.getBoardPosition(x, y) == this.type)
					break;
				else
				{
					if (cell.checkState)
					{
						if (gameLogic.isThisProtecting(cell, x, y, this.type))
							cell.colorSquare(x, y, false);
					}
					else
						cell.colorSquare(x, y, false);
					break;
				}
			}
		}
	}
}
