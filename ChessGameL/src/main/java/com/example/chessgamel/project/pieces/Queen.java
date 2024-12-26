package com.example.chessgamel.project.pieces;

import com.example.chessgamel.project.mainChessApp.Cell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Queen extends Piece {
	private Image image;
	
	public Queen(int type, int xPos, int yPos) {
		super(type, xPos, yPos);
		name = "Queen";
		if(type==1){
			image = new Image("file:src/main/java/com/ChessPiece/White_Queen.png");
			imageView.setImage(image);
			imageView.fitHeightProperty();
			imageView.fitWidthProperty();
	        imageView.setPreserveRatio(true);
	        imageView.setSmooth(true);
	        imageView.setCache(true);
		}else{
			image = new Image("file:src/main/java/com/ChessPiece/Black_Queen.png");
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
		int y = this.yPos + 1;
		if (cell.checkState && !this.isASavior)
			return;
		if (!gameLogic.slashDiagonalProtection(cell, this.xPos, this.yPos, this.type) && !gameLogic.verticalProtection(cell, this.xPos, this.yPos, this.type) && !gameLogic.horizontalProtection(cell, this.xPos, this.yPos, this.type))
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
		if (!gameLogic.backslashDiagonalProtection(cell, this.xPos, this.yPos, this.type) && !gameLogic.verticalProtection(cell, this.xPos, this.yPos, this.type) && !gameLogic.horizontalProtection(cell, this.xPos, this.yPos, this.type))
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
		if (!gameLogic.horizontalProtection(cell, this.xPos, this.yPos, this.type) && !gameLogic.slashDiagonalProtection(cell, this.xPos, this.yPos, this.type) && !gameLogic.backslashDiagonalProtection(cell, this.xPos, this.yPos, this.type))
		{
			for (y = this.yPos - 1; y >= 0; y--)
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
			for (y = this.yPos + 1; y < cell.getBoardHeight(); y++)
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
		if (!gameLogic.verticalProtection(cell, this.xPos, this.yPos, this.type) && !gameLogic.slashDiagonalProtection(cell, this.xPos, this.yPos, this.type) && !gameLogic.backslashDiagonalProtection(cell, this.xPos, this.yPos, this.type))
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
