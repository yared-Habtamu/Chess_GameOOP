package com.example.chessgamel.project.pieces;

import com.example.chessgamel.project.mainChessApp.Cell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Pawn extends Piece {

	private Image image;
	
	public Pawn(int type, int xPos, int yPos) {
		super(type, xPos, yPos);
		name = "Pawn";

		if(type==1){
			image = new Image("file:src/main/java/com/ChessPiece/White_Pawn.png");
			imageView.setImage(image);
			imageView.fitHeightProperty();
			imageView.fitWidthProperty();
	        imageView.setPreserveRatio(true);
	        imageView.setSmooth(true);
	        imageView.setCache(true);
		}else{
			image = new Image("file:src/main/java/com/ChessPiece/Black_Pawn.png");
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
		if (gameLogic.horizontalProtection(cell, this.xPos, this.yPos, this.type))
			return;
		if (this.type == 1)
		{
			if (!gameLogic.slashDiagonalProtection(cell, this.xPos, this.yPos, this.type) && !gameLogic.backslashDiagonalProtection(cell, this.xPos, this.yPos, this.type))
			{
				if (this.yPos - 1 >= 0 && cell.getBoardPosition(this.xPos, this.yPos - 1) == 0)
				{
					if (cell.checkState)
					{
						if (gameLogic.isThisProtecting(cell, this.xPos, this.yPos - 1, this.type))
							cell.colorSquare(this.xPos, this.yPos - 1, false);
					}
					else
						cell.colorSquare(this.xPos, this.yPos - 1, false);
				}
				if (this.isFirstTime == true && cell.getBoardPosition(this.xPos, this.yPos - 2) == 0)
				{
					if (cell.checkState)
					{
						if (gameLogic.isThisProtecting(cell, this.xPos, this.yPos - 2, this.type))
							cell.colorSquare(this.xPos, this.yPos - 2, false);
					}
					else
						cell.colorSquare(this.xPos, this.yPos - 2, false);
				}
			}
			if (!gameLogic.verticalProtection(cell, this.xPos, this.yPos, this.type))
			{
				if (!gameLogic.slashDiagonalProtection(cell, this.xPos, this.yPos, this.type))
				{
					if (this.yPos - 1 >= 0 && this.xPos - 1 >= 0 && cell.getBoardPosition(this.xPos - 1, this.yPos - 1) != this.type && cell.getBoardPosition(this.xPos - 1, this.yPos - 1) != 0)
					{
						if (cell.checkState)
						{
							if (gameLogic.isThisProtecting(cell, this.xPos - 1, this.yPos - 1, this.type))
								cell.colorSquare(this.xPos - 1, this.yPos - 1, false);
						}
						else
							cell.colorSquare(this.xPos - 1, this.yPos - 1, false);
					}
				}
				if (!gameLogic.backslashDiagonalProtection(cell, this.xPos, this.yPos, this.type))
				{
					if (this.yPos - 1 >= 0 && this.xPos + 1 < cell.getBoardWidth() && cell.getBoardPosition(this.xPos + 1, this.yPos - 1) != this.type && cell.getBoardPosition(this.xPos + 1, this.yPos - 1) != 0)
					{
						if (cell.checkState)
						{
							if (gameLogic.isThisProtecting(cell, this.xPos + 1, this.yPos - 1, this.type))
								cell.colorSquare(this.xPos + 1, this.yPos - 1, false);
						}
						else
							cell.colorSquare(this.xPos + 1, this.yPos - 1, false);
					}
				}
			}
		}
		else if (this.type == 2)
		{
			if (!gameLogic.slashDiagonalProtection(cell, this.xPos, this.yPos, this.type) && !gameLogic.backslashDiagonalProtection(cell, this.xPos, this.yPos, this.type))
			{
				if (this.yPos + 1 < cell.getBoardHeight() && cell.getBoardPosition(this.xPos, this.yPos + 1) == 0)
				{
					if (cell.checkState)
					{
						if (gameLogic.isThisProtecting(cell, this.xPos, this.yPos + 1, this.type))
							cell.colorSquare(this.xPos, this.yPos + 1, false);
					}
					else
						cell.colorSquare(this.xPos, this.yPos + 1, false);
				}
				if (this.isFirstTime == true && cell.getBoardPosition(this.xPos, this.yPos + 2) == 0)
				{
					if (cell.checkState)
					{
						if (gameLogic.isThisProtecting(cell, this.xPos, this.yPos + 2, this.type))
							cell.colorSquare(this.xPos, this.yPos + 2, false);
					}
					else
						cell.colorSquare(this.xPos, this.yPos + 2, false);
				}
			}
			if (!gameLogic.verticalProtection(cell, this.xPos, this.yPos, this.type))
			{
				if (!gameLogic.backslashDiagonalProtection(cell, this.xPos, this.yPos, this.type))
				{
					if (this.yPos + 1 < cell.getBoardHeight() && this.xPos - 1 >= 0 && cell.getBoardPosition(this.xPos - 1, this.yPos + 1) != this.type && cell.getBoardPosition(this.xPos - 1, this.yPos + 1) != 0)
					{
						if (cell.checkState)
						{
							if (gameLogic.isThisProtecting(cell, this.xPos - 1, this.yPos + 1, this.type))
								cell.colorSquare(this.xPos - 1, this.yPos + 1, false);
						}
						else
							cell.colorSquare(this.xPos - 1, this.yPos + 1, false);
					}
				}
				if (!gameLogic.slashDiagonalProtection(cell, this.xPos, this.yPos, this.type))
				{
					if (this.yPos + 1 < cell.getBoardHeight() && this.xPos + 1 < cell.getBoardWidth() && cell.getBoardPosition(this.xPos + 1, this.yPos + 1) != this.type && cell.getBoardPosition(this.xPos + 1, this.yPos + 1) != 0)
					{
						if (cell.checkState)
						{
							if (gameLogic.isThisProtecting(cell, this.xPos + 1, this.yPos + 1, this.type))
								cell.colorSquare(this.xPos + 1, this.yPos + 1, false);
						}
						else
							cell.colorSquare(this.xPos + 1, this.yPos + 1, false);
					}
				}
			}
		}
	}
}
