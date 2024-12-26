package com.example.chessgamel.project.mainChessApp;

import com.example.chessgamel.project.pieces.Piece;

import java.util.Iterator;

public class MoveValidator {

	private boolean isOneKingStalemate(Cell cell, Piece king, int type)
	{
		int nbPiece = 0;
		boolean stalemate = true;

		for (int y = 0; y < cell.getBoardHeight(); y++)
		{
			for (int x = 0; x < cell.getBoardWidth(); x++)
			{
				if (cell.getBoardPosition(x, y) == type)
					nbPiece++;
			}
		}
		if (nbPiece == 1)
		{
			for (int y = king.yPos - 1; y <= king.yPos + 1; y++)
			{
				for (int x = king.xPos - 1; x <= king.xPos + 1; x++)
				{
					if(y >= 0 && y < cell.getBoardHeight() && x >= 0 && x < cell.getBoardWidth() && cell.getBoardPosition(x, y) != type)
					{
						if (!isCheck(cell, x, y, type, true))
						{
							stalemate = false;
							break;
						}
					}
				}
				if (!stalemate)
					break;
			}
		}
		else
			stalemate = false;
		return (stalemate);
	}
	
	public boolean isLimitPieceStalemate(Cell cell)
	{

		if (cell.playerOneQueen != 0 || cell.playerTwoQueen != 0)
			return (false);
		else if (cell.playerOneRook != 0 || cell.playerTwoRook != 0)
			return (false);
		else if (cell.playerOneKnight > 1 || cell.playerTwoKnight > 1)
			return (false);
		else if (((cell.playerOneBishopDarkSquare != 0 || cell.playerOneBishopLightSquare != 0) && cell.playerOneKnight != 0) ||
				((cell.playerTwoBishopDarkSquare != 0 || cell.playerTwoBishopLightSquare != 0) && cell.playerTwoKnight != 0))
			return (false);
		else if ((cell.playerOneBishopDarkSquare != 0 && cell.playerOneBishopLightSquare != 0) || (cell.playerTwoBishopDarkSquare != 0 && cell.playerTwoBishopLightSquare != 0))
			return (false);
		else if (cell.playerOnePawn > 1 || cell.playerTwoPawn > 1)
			return (false);
		return (true);
	}
	
	public boolean isStalemate(Cell cell, Piece king, int type)
	{
		if (isOneKingStalemate(cell, king, type) || isLimitPieceStalemate(cell))
		{
			cell.stalemate = true;
			return (true);
		}
		return (false);
	}

	public boolean verticalProtection(Cell cell, int xPos, int yPos, int type)
	{
		int y = 0;
		int enemyType = 0;
		if (type == 1)
			enemyType = 2;
		else
			enemyType = 1;

		for (y = yPos - 1; y >= 0; y--)
		{
			if (cell.getBoardPosition(xPos, y) == type && cell.getPiece(xPos, y).name == "King")
			{
				for (y = yPos + 1; y < cell.getBoardHeight(); y++)
				{
					if (cell.getBoardPosition(xPos, y) == type)
						break;
					else if (cell.getBoardPosition(xPos, y) == enemyType)
					{
						if (cell.getPiece(xPos,  y).name == "Queen" || cell.getPiece(xPos, y).name == "Rook")
							return (true);
						else
							break;
					}
				}
				break;
			}
			else if (cell.getBoardPosition(xPos, y) != 0)
				break;
		}
		for (y = yPos + 1; y < cell.getBoardHeight(); y++)
		{
			if (cell.getBoardPosition(xPos, y) == type && cell.getPiece(xPos, y).name == "King")
			{
				for (y = yPos - 1; y >= 0; y--)
				{
					if (cell.getBoardPosition(xPos, y) == type)
						break;
					else if (cell.getBoardPosition(xPos,  y) == enemyType)
					{
						if (cell.getPiece(xPos, y).name == "Queen" || cell.getPiece(xPos, y).name == "Rook")
							return (true);
						else
							break;
					}
				}
				break;				
			}
			else if (cell.getBoardPosition(xPos, y) != 0)
				break;
		}
		return (false);
	}
	
	public boolean horizontalProtection(Cell cell, int xPos, int yPos, int type)
	{
		int x = 0;
		int enemyType = 0;
		if (type == 1)
			enemyType = 2;
		else
			enemyType = 1;

		for (x = xPos - 1; x >= 0; x--)
		{
			if (cell.getBoardPosition(x, yPos) == type && cell.getPiece(x, yPos).name == "King")
			{
				for (x = xPos + 1; x < cell.getBoardWidth(); x++)
				{
					if (cell.getBoardPosition(x, yPos) == type)
						break;
					else if (cell.getBoardPosition(x, yPos) == enemyType)
					{
						if (cell.getPiece(x,  yPos).name == "Queen" || cell.getPiece(x, yPos).name == "Rook")
							return (true);
						else
							break;
					}
				}
				break;
			}
			else if (cell.getBoardPosition(x, yPos) != 0)
				break;
		}
		for (x = xPos + 1; x < cell.getBoardWidth(); x++)
		{
			if (cell.getBoardPosition(x, yPos) == type && cell.getPiece(x, yPos).name == "King")
			{
				for (x = xPos - 1; x >= 0; x--)
				{
					if (cell.getBoardPosition(x, yPos) == type)
						break;
					else if (cell.getBoardPosition(x,  yPos) == enemyType)
					{
						if (cell.getPiece(x, yPos).name == "Queen" || cell.getPiece(x, yPos).name == "Rook")
							return (true);
						else
							break;
					}
				}
				break;				
			}
			else if (cell.getBoardPosition(x, yPos) != 0)
				break;
		}
		return (false);
	}
	
	public boolean slashDiagonalProtection(Cell cell, int xPos, int yPos, int type)
	{
		int enemyType = 0;
		if (type == 1)
			enemyType = 2;
		else
			enemyType = 1;

		int y = yPos - 1;
		for (int x = xPos + 1; x < cell.getBoardWidth() && y >= 0; x++, y--)
		{
			if (cell.getBoardPosition(x, y) == type && cell.getPiece(x, y).name == "King")
			{
				y = yPos + 1;
				for (x = xPos - 1; x >= 0 && y < cell.getBoardHeight(); x--, y++)
				{
					if (cell.getBoardPosition(x, y) == type)
						break;
					else if (cell.getBoardPosition(x, y) == enemyType)
					{
						if (cell.getPiece(x, y).name == "Queen" || cell.getPiece(x, y).name == "Bishop")
							return (true);
						else
							break;
					}
				}
				break;
			}
			else if (cell.getBoardPosition(x, y) != 0)
				break;
		}
		y = yPos + 1;
		for (int x = xPos - 1; x >= 0 && y < cell.getBoardHeight(); x--, y++)
		{
			if (cell.getBoardPosition(x, y) == type && cell.getPiece(x, y).name == "King")
			{
				y = yPos - 1;
				for (x = xPos + 1; x < cell.getBoardWidth() && y >= 0; x++, y--)
				{
					if (cell.getBoardPosition(x, y) == type)
						break;
					else if (cell.getBoardPosition(x, y) == enemyType)
					{
						if (cell.getPiece(x, y).name == "Queen" || cell.getPiece(x, y).name == "Bishop")
							return (true);
						else
							break;
					}
				}
				break;
			}
			else if (cell.getBoardPosition(x, y) != 0)
				break;			
		}
		return (false);
	}
	
	public boolean backslashDiagonalProtection(Cell cell, int xPos, int yPos, int type)
	{
		int enemyType = 0;
		if (type == 1)
			enemyType = 2;
		else
			enemyType = 1;

		int y = yPos - 1;
		for(int x = xPos - 1; x >= 0 && y >= 0; x--, y--)
		{
			if (cell.getBoardPosition(x, y) == type && cell.getPiece(x, y).name == "King")
			{
				y = yPos + 1;
				for(x = xPos + 1; x < cell.getBoardWidth() && y < cell.getBoardHeight(); x++, y++)
				{
					if (cell.getBoardPosition(x, y) == type)
						break;
					else if (cell.getBoardPosition(x, y) == enemyType)
					{
						if (cell.getPiece(x, y).name == "Queen" || cell.getPiece(x, y).name == "Bishop")
							return (true);
						else
							break;
					}
				}
				break;
			}
			else if (cell.getBoardPosition(x, y) != 0)
				break;
		}
		y = yPos + 1;
		for(int x = xPos + 1; x < cell.getBoardWidth() && y < cell.getBoardHeight(); x++, y++)
		{
			if (cell.getBoardPosition(x, y) == type && cell.getPiece(x, y).name == "King")
			{
				y = yPos - 1;
				for(x = xPos - 1; x >= 0 && y >= 0; x--, y--)
				{
					if (cell.getBoardPosition(x, y) == type)
						break;
					else if (cell.getBoardPosition(x, y) == enemyType)
					{
						if (cell.getPiece(x, y).name == "Queen" || cell.getPiece(x, y).name == "Bishop")
							return (true);
						else
							break;
					}
				}
				break;
			}
			else if (cell.getBoardPosition(x, y) != 0)
				break;
		}		
		return (false);
	}

	public boolean isCheck(Cell cell, int xPos, int yPos, int type, boolean kingCanCapture) {
		int y = 0;
		int x = 0;
		int enemyType = 0;
		if (type == 1)
			enemyType = 2;
		else
			enemyType = 1;

		for (x = xPos - 1; x >= 0; x--)
		{
			if (cell.getBoardPosition(x, yPos) == type && cell.getPiece(x, yPos).name != "King")
				break;
			else if (cell.getBoardPosition(x, yPos) == enemyType)
			{
				if (x == xPos - 1 && cell.getPiece(x, yPos) != null && kingCanCapture && cell.getPiece(x, yPos).name == "King")
					return (true);
				else if (cell.getPiece(x, yPos) != null && (cell.getPiece(x, yPos).name == "Queen" || cell.getPiece(x, yPos).name == "Rook"))
					return (true);
				else
					break;
			}
		}
		for (x = xPos + 1; x < cell.getBoardWidth(); x++)
		{
			if (cell.getBoardPosition(x, yPos) == type && cell.getPiece(x, yPos).name != "King")
				break;
			else if (cell.getBoardPosition(x, yPos) == enemyType)
			{
				if (x == xPos + 1 && cell.getPiece(x, yPos) != null && kingCanCapture && cell.getPiece(x, yPos).name == "King")
					return (true);
				else if (cell.getPiece(x, yPos) != null && (cell.getPiece(x, yPos).name == "Queen" || cell.getPiece(x, yPos).name == "Rook"))
					return (true);
				else
					break;
			}
		}
		for (y = yPos - 1; y >= 0; y--)
		{
			if (cell.getBoardPosition(xPos, y) == type && cell.getPiece(xPos, y).name != "King")
				break;
			else if (cell.getBoardPosition(xPos, y) == enemyType)
			{
				if (y == yPos - 1 && cell.getPiece(xPos, y) != null && kingCanCapture && cell.getPiece(xPos, y).name == "King")
					return (true);
				else if (cell.getPiece(xPos, y) != null && (cell.getPiece(xPos, y).name == "Queen" || cell.getPiece(xPos, y).name == "Rook"))
					return (true);
				else
					break;
			}
		}
		for (y = yPos + 1; y < cell.getBoardHeight(); y++)
		{
			if (cell.getBoardPosition(xPos, y) == type && cell.getPiece(xPos, y).name != "King")
				break;
			else if (cell.getBoardPosition(xPos, y) == enemyType)
			{
				if (y == yPos + 1 && cell.getPiece(xPos, y) != null && kingCanCapture && cell.getPiece(xPos, y).name == "King")
					return (true);
				else if (cell.getPiece(xPos, y) != null && (cell.getPiece(xPos, y).name == "Queen" || cell.getPiece(xPos, y).name == "Rook"))
					return (true);
				else
					break;
			}
		}
		for (y = yPos - 1, x = xPos - 1; y >= 0 && x >= 0; y--, x--)
		{
			if (cell.getBoardPosition(x, y) == type && cell.getPiece(x, y).name != "King")
				break;
			else if (cell.getBoardPosition(x, y) == enemyType)
			{
				if (y == yPos - 1 && cell.getBoardPosition(x, y) != 0 && cell.getPiece(x, y) != null && ((kingCanCapture && cell.getPiece(x, y).name == "King") || (type == 1 && cell.getPiece(x, y).name == "Pawn")))
					return (true);
				else if (cell.getBoardPosition(x, y) != 0 && cell.getPiece(x, y) != null && (cell.getPiece(x, y).name == "Queen" || cell.getPiece(x, y).name == "Bishop"))
					return (true);
				else
					break;
			}
		}
		for (y = yPos + 1, x = xPos + 1; y < cell.getBoardHeight() && x < cell.getBoardWidth(); y++, x++)
		{
			if (cell.getBoardPosition(x, y) == type && cell.getPiece(x, y).name != "King")
				break;
			else if (cell.getBoardPosition(x, y) == enemyType)
			{
				if (y == yPos + 1 && cell.getBoardPosition(x, y) != 0 && cell.getPiece(x, y) != null && ((kingCanCapture && cell.getPiece(x, y).name == "King") || (type == 2 && cell.getPiece(x, y).name == "Pawn")))
					return (true);
				else if (cell.getBoardPosition(x, y) != 0 && cell.getPiece(x, y) != null && (cell.getPiece(x, y).name == "Queen" || cell.getPiece(x, y).name == "Bishop"))
					return (true);
				else
					break;
			}
		}
		for (y = yPos - 1, x = xPos + 1; y >= 0 && x < cell.getBoardWidth(); y--, x++)
		{
			if (cell.getBoardPosition(x, y) == type && cell.getPiece(x, y).name != "King")
				break;
			else if (cell.getBoardPosition(x, y) == enemyType)
			{
				if (y == yPos - 1 && cell.getBoardPosition(x, y) != 0 && cell.getPiece(x, y) != null && ((kingCanCapture && cell.getPiece(x, y).name == "King") || (type == 1 && cell.getPiece(x, y).name == "Pawn")))
					return (true);
				else if (cell.getBoardPosition(x, y) != 0 && cell.getPiece(x, y) != null && (cell.getPiece(x, y).name == "Queen" || cell.getPiece(x, y).name == "Bishop"))
					return (true);
				else
					break;
			}
		}
		for (y = yPos + 1, x = xPos - 1; y < cell.getBoardHeight() && x >= 0; y++, x--)
		{
			if (cell.getBoardPosition(x, y) == type && cell.getPiece(x, y).name != "King")
				break;
			else if (cell.getBoardPosition(x, y) == enemyType)
			{
				if (y == yPos + 1 && cell.getBoardPosition(x, y) != 0 && cell.getPiece(x, y) != null && ((kingCanCapture && cell.getPiece(x, y).name == "King") || (type == 2 && cell.getPiece(x, y).name == "Pawn")))
					return (true);
				else if (cell.getBoardPosition(x, y) != 0 && (cell.getPiece(x, y).name == "Queen" || cell.getPiece(x, y).name == "Bishop"))
					return (true);
				else
					break;
			}
		}
		for (y = -2; y <= 2; y++)
		{
			if (y != 0)
			{
				x = y % 2 == 0 ? 1 : 2;
				if (yPos + y >= 0 && yPos + y < cell.getBoardHeight() && xPos - x >= 0 && xPos - x < cell.getBoardWidth() && cell.getBoardPosition(xPos - x, yPos + y) != type && cell.getBoardPosition(xPos - x, yPos + y) != 0)
				{
					if (cell.getPiece(xPos - x, yPos + y) != null && cell.getPiece(xPos - x, yPos + y).name == "Knight")
						return (true);
				}
				if (yPos + y >= 0 && yPos + y < cell.getBoardHeight() && xPos + x >= 0 && xPos + x < cell.getBoardWidth() && cell.getBoardPosition(xPos + x, yPos + y) != type && cell.getBoardPosition(xPos + x, yPos + y) != 0)
				{
					if (cell.getPiece(xPos + x, yPos + y) != null && cell.getPiece(xPos + x, yPos + y).name == "Knight")
						return (true);
				}
			}
		}
		return (false);
	}

	public void findAllCheckPieces(Cell cell, int xPos, int yPos, int type) {
		int y = 0;
		int x = 0;
		int enemyType = 0;
		if (type == 1)
			enemyType = 2;
		else
			enemyType = 1;

		for (x = xPos - 1; x >= 0; x--)
		{
			if (cell.getBoardPosition(x, yPos) == type)
				break;
			else if (cell.getBoardPosition(x, yPos) == enemyType)
			{
				if (cell.getPiece(x, yPos) != null && (cell.getPiece(x, yPos).name == "Queen" || cell.getPiece(x, yPos).name == "Rook"))
					cell.checkPieces.add(cell.getPiece(x, yPos));
				else
					break;
			}
		}
		for (x = xPos + 1; x < cell.getBoardWidth(); x++)
		{
			if (cell.getBoardPosition(x, yPos) == type)
				break;
			else if (cell.getBoardPosition(x, yPos) == enemyType)
			{
				if (cell.getPiece(x, yPos) != null && (cell.getPiece(x, yPos).name == "Queen" || cell.getPiece(x, yPos).name == "Rook"))
					cell.checkPieces.add(cell.getPiece(x, yPos));
				else
					break;
			}
		}
		for (y = yPos - 1; y >= 0; y--)
		{
			if (cell.getBoardPosition(xPos, y) == type)
				break;
			else if (cell.getBoardPosition(xPos, y) == enemyType)
			{
				if (cell.getPiece(xPos, y) != null && (cell.getPiece(xPos, y).name == "Queen" || cell.getPiece(xPos, y).name == "Rook"))
					cell.checkPieces.add(cell.getPiece(xPos, y));
				else
					break;
			}
		}
		for (y = yPos + 1; y < cell.getBoardHeight(); y++)
		{
			if (cell.getBoardPosition(xPos, y) == type)
				break;
			else if (cell.getBoardPosition(xPos, y) == enemyType)
			{
				if (cell.getPiece(xPos, y) != null && (cell.getPiece(xPos, y).name == "Queen" || cell.getPiece(xPos, y).name == "Rook"))
					cell.checkPieces.add(cell.getPiece(xPos, y));
				else
					break;
			}
		}
		for (y = yPos - 1, x = xPos - 1; y >= 0 && x >= 0; y--, x--)
		{
			if (cell.getBoardPosition(x, y) == type)
				break;
			else if (cell.getBoardPosition(x, y) == enemyType)
			{
				if (y == yPos - 1 && cell.getBoardPosition(x, y) != 0 && cell.getPiece(x, y) != null && (type == 1 && cell.getPiece(x, y).name == "Pawn"))
					cell.checkPieces.add(cell.getPiece(x, y));
				else if (cell.getBoardPosition(x, y) != 0 && cell.getPiece(x, y) != null && (cell.getPiece(x, y).name == "Queen" || cell.getPiece(x, y).name == "Bishop"))
					cell.checkPieces.add(cell.getPiece(x, y));
				else
					break;
			}
		}
		for (y = yPos + 1, x = xPos + 1; y < cell.getBoardHeight() && x < cell.getBoardWidth(); y++, x++)
		{
			if (cell.getBoardPosition(x, y) == type)
				break;
			else if (cell.getBoardPosition(x, y) == enemyType)
			{
				if (y == yPos + 1 && cell.getBoardPosition(x, y) != 0 && cell.getPiece(x, y) != null && (type == 2 && cell.getPiece(x, y).name == "Pawn"))
					cell.checkPieces.add(cell.getPiece(x, y));
				else if (cell.getBoardPosition(x, y) != 0 && cell.getPiece(x, y) != null && (cell.getPiece(x, y).name == "Queen" || cell.getPiece(x, y).name == "Bishop"))
					cell.checkPieces.add(cell.getPiece(x, y));
				else
					break;
			}
		}
		for (y = yPos - 1, x = xPos + 1; y >= 0 && x < cell.getBoardWidth(); y--, x++)
		{
			if (cell.getBoardPosition(x, y) == type)
				break;
			else if (cell.getBoardPosition(x, y) == enemyType)
			{
				if (y == yPos - 1 && cell.getBoardPosition(x, y) != 0 && cell.getPiece(x, y) != null && (type == 1 && cell.getPiece(x, y).name == "Pawn"))
					cell.checkPieces.add(cell.getPiece(x, y));
				else if (cell.getBoardPosition(x, y) != 0 && cell.getPiece(x, y) != null && (cell.getPiece(x, y).name == "Queen" || cell.getPiece(x, y).name == "Bishop"))
					cell.checkPieces.add(cell.getPiece(x, y));
				else
					break;
			}
		}
		for (y = yPos + 1, x = xPos - 1; y < cell.getBoardHeight() && x >= 0; y++, x--)
		{
			if (cell.getBoardPosition(x, y) == type)
				break;
			else if (cell.getBoardPosition(x, y) == enemyType)
			{
				if (y == yPos + 1 && cell.getBoardPosition(x, y) != 0 && cell.getPiece(x, y) != null && (type == 2 && cell.getPiece(x, y).name == "Pawn"))
					cell.checkPieces.add(cell.getPiece(x, y));
				if (cell.getBoardPosition(x, y) != 0 && (cell.getPiece(x, y).name == "Queen" || cell.getPiece(x, y).name == "Bishop"))
					cell.checkPieces.add(cell.getPiece(x, y));
				else
					break;
			}
		}
		for (y = -2; y <= 2; y++)
		{
			if (y != 0)
			{
				x = y % 2 == 0 ? 1 : 2;
				if (yPos + y >= 0 && yPos + y < cell.getBoardHeight() && xPos - x >= 0 && xPos - x < cell.getBoardWidth() && cell.getBoardPosition(xPos - x, yPos + y) != type && cell.getBoardPosition(xPos - x, yPos + y) != 0)
				{
					if (cell.getPiece(xPos - x, yPos + y) != null && cell.getPiece(xPos - x, yPos + y).name == "Knight")
						cell.checkPieces.add(cell.getPiece(xPos - x, yPos + y));
				}
				if (yPos + y >= 0 && yPos + y < cell.getBoardHeight() && xPos + x >= 0 && xPos + x < cell.getBoardWidth() && cell.getBoardPosition(xPos + x, yPos + y) != type && cell.getBoardPosition(xPos + x, yPos + y) != 0)
				{
					if (cell.getPiece(xPos + x, yPos + y) != null && cell.getPiece(xPos + x, yPos + y).name == "Knight")
						cell.checkPieces.add(cell.getPiece(xPos + x, yPos + y));
				}
			}
		}
	}

	public void findAllSaviorPieces(Cell cell, int xPos, int yPos, int type, boolean protect) {
		int y = 0;
		int x = 0;
		int enemyType = 0;
		if (type == 1)
			enemyType = 2;
		else
			enemyType = 1;

		for (x = xPos - 1; x >= 0; x--)
		{
			if (cell.getBoardPosition(x, yPos) == type)
				break;
			else if (cell.getBoardPosition(x, yPos) == enemyType)
			{
				if (cell.getPiece(x, yPos) != null && (cell.getPiece(x, yPos).name == "Queen" || cell.getPiece(x, yPos).name == "Rook"))
					cell.saviorPieces.add(cell.getPiece(x, yPos));
				else
					break;
			}
		}
		for (x = xPos + 1; x < cell.getBoardWidth(); x++)
		{
			if (cell.getBoardPosition(x, yPos) == type)
				break;
			else if (cell.getBoardPosition(x, yPos) == enemyType)
			{
				if (cell.getPiece(x, yPos) != null && (cell.getPiece(x, yPos).name == "Queen" || cell.getPiece(x, yPos).name == "Rook"))
					cell.saviorPieces.add(cell.getPiece(x, yPos));
				else
					break;
			}
		}
		for (y = yPos - 1; y >= 0; y--)
		{
			if (cell.getBoardPosition(xPos, y) == type)
				break;
			else if (cell.getBoardPosition(xPos, y) == enemyType)
			{
				if (enemyType == 2 && protect == true && y == yPos - 1 && cell.getPiece(xPos, y) != null && cell.getPiece(xPos, y).name == "Pawn")
					cell.saviorPieces.add(cell.getPiece(xPos, y));
				if (enemyType == 2 && protect == true && y == yPos - 2 && cell.getPiece(xPos, y) != null && cell.getPiece(xPos, y).name == "Pawn" && cell.getPiece(xPos, y).isFirstTime())
					cell.saviorPieces.add(cell.getPiece(xPos, y));
				if (cell.getPiece(xPos, y) != null && (cell.getPiece(xPos, y).name == "Queen" || cell.getPiece(xPos, y).name == "Rook"))
					cell.saviorPieces.add(cell.getPiece(xPos, y));
				else
					break;
			}
		}
		for (y = yPos + 1; y < cell.getBoardHeight(); y++)
		{
			if (cell.getBoardPosition(xPos, y) == type)
				break;
			else if (cell.getBoardPosition(xPos, y) == enemyType)
			{
				if (enemyType == 1 && protect == true && y == yPos + 1 && cell.getPiece(xPos, y) != null && cell.getPiece(xPos, y).name == "Pawn")
					cell.saviorPieces.add(cell.getPiece(xPos, y));
				if (enemyType == 1 && protect == true && y == yPos + 2 && cell.getPiece(xPos, y) != null && cell.getPiece(xPos, y).name == "Pawn" && cell.getPiece(xPos, y).isFirstTime())
					cell.saviorPieces.add(cell.getPiece(xPos, y));
				if (cell.getPiece(xPos, y) != null && (cell.getPiece(xPos, y).name == "Queen" || cell.getPiece(xPos, y).name == "Rook"))
					cell.saviorPieces.add(cell.getPiece(xPos, y));
				else
					break;
			}
		}
		for (y = yPos - 1, x = xPos - 1; y >= 0 && x >= 0; y--, x--)
		{
			if (cell.getBoardPosition(x, y) == type)
				break;
			else if (cell.getBoardPosition(x, y) == enemyType)
			{
				if (protect == false && y == yPos - 1 && cell.getBoardPosition(x, y) != 0 && cell.getPiece(x, y) != null && (type == 1 && cell.getPiece(x, y).name == "Pawn"))
					cell.saviorPieces.add(cell.getPiece(x, y));
				if (cell.getBoardPosition(x, y) != 0 && cell.getPiece(x, y) != null && (cell.getPiece(x, y).name == "Queen" || cell.getPiece(x, y).name == "Bishop"))
					cell.saviorPieces.add(cell.getPiece(x, y));
				else
					break;
			}
		}
		for (y = yPos + 1, x = xPos + 1; y < cell.getBoardHeight() && x < cell.getBoardWidth(); y++, x++)
		{
			if (cell.getBoardPosition(x, y) == type)
				break;
			else if (cell.getBoardPosition(x, y) == enemyType)
			{
				if (protect == false && y == yPos + 1 && cell.getBoardPosition(x, y) != 0 && cell.getPiece(x, y) != null && (type == 2 && cell.getPiece(x, y).name == "Pawn"))
					cell.saviorPieces.add(cell.getPiece(x, y));
				if (cell.getBoardPosition(x, y) != 0 && cell.getPiece(x, y) != null && (cell.getPiece(x, y).name == "Queen" || cell.getPiece(x, y).name == "Bishop"))
					cell.saviorPieces.add(cell.getPiece(x, y));
				else
					break;
			}
		}
		for (y = yPos - 1, x = xPos + 1; y >= 0 && x < cell.getBoardWidth(); y--, x++)
		{
			if (cell.getBoardPosition(x, y) == type)
				break;
			else if (cell.getBoardPosition(x, y) == enemyType)
			{
				if (protect == false && y == yPos - 1 && cell.getBoardPosition(x, y) != 0 && cell.getPiece(x, y) != null && (type == 1 && cell.getPiece(x, y).name == "Pawn"))
					cell.saviorPieces.add(cell.getPiece(x, y));
				if (cell.getBoardPosition(x, y) != 0 && cell.getPiece(x, y) != null && (cell.getPiece(x, y).name == "Queen" || cell.getPiece(x, y).name == "Bishop"))
					cell.saviorPieces.add(cell.getPiece(x, y));
				else
					break;
			}
		}
		for (y = yPos + 1, x = xPos - 1; y < cell.getBoardHeight() && x >= 0; y++, x--)
		{
			if (cell.getBoardPosition(x, y) == type)
				break;
			else if (cell.getBoardPosition(x, y) == enemyType)
			{
				if (protect == false && y == yPos + 1 && cell.getBoardPosition(x, y) != 0 && cell.getPiece(x, y) != null && (type == 2 && cell.getPiece(x, y).name == "Pawn"))
					cell.saviorPieces.add(cell.getPiece(x, y));
				if (cell.getBoardPosition(x, y) != 0 && (cell.getPiece(x, y).name == "Queen" || cell.getPiece(x, y).name == "Bishop"))
					cell.saviorPieces.add(cell.getPiece(x, y));
				else
					break;
			}
		}
		for (y = -2; y <= 2; y++)
		{
			if (y != 0)
			{
				x = y % 2 == 0 ? 1 : 2;
				if (yPos + y >= 0 && yPos + y < cell.getBoardHeight() && xPos - x >= 0 && xPos - x < cell.getBoardWidth() && cell.getBoardPosition(xPos - x, yPos + y) != type && cell.getBoardPosition(xPos - x, yPos + y) != 0)
				{
					if (cell.getPiece(xPos - x, yPos + y) != null && cell.getPiece(xPos - x, yPos + y).name == "Knight")
						cell.saviorPieces.add(cell.getPiece(xPos - x, yPos + y));
				}
				if (yPos + y >= 0 && yPos + y < cell.getBoardHeight() && xPos + x >= 0 && xPos + x < cell.getBoardWidth() && cell.getBoardPosition(xPos + x, yPos + y) != type && cell.getBoardPosition(xPos + x, yPos + y) != 0)
				{
					if (cell.getPiece(xPos + x, yPos + y) != null && cell.getPiece(xPos + x, yPos + y).name == "Knight")
						cell.saviorPieces.add(cell.getPiece(xPos + x, yPos + y));
				}
			}
		}
	}


	public boolean isCheckmate(Cell chessboard, int xPos, int yPos, int type)
	{
		boolean checkmate = true;
		int x = xPos;
		int y = yPos;
		for (y = yPos - 1; y <= yPos + 1; y++)
		{
			for (x = xPos - 1; x <= xPos + 1; x++)
			{
				if(y >= 0 && y < chessboard.getBoardHeight() && x >= 0 && x < chessboard.getBoardWidth() && chessboard.getBoardPosition(x, y) != type)
				{
					if (!isCheck(chessboard, x, y, type, true))
					{
						checkmate = false;
						break;
					}
				}
			}
			if (!checkmate)
				break;
		}
		if (chessboard.checkPieces.size() < 2)
		{
			Piece checkPiece = chessboard.checkPieces.get(0);
			canCapture(chessboard, checkPiece);
			canProtect(chessboard, xPos, yPos, type, checkPiece);
			if (!chessboard.saviorPieces.isEmpty())
			{
				for(Iterator<Piece> piece = chessboard.saviorPieces.iterator(); piece.hasNext(); )
				{
					Piece item = piece.next();
					item.isASavior = true;
					if (verticalProtection(chessboard, item.xPos, item.yPos, item.type) || horizontalProtection(chessboard, item.xPos, item.yPos, item.type) ||
				    	slashDiagonalProtection(chessboard, item.xPos, item.yPos, item.type) || backslashDiagonalProtection(chessboard, item.xPos, item.yPos, item.type))
				    {
				    	item.isASavior = false;
				    	piece.remove();
				    }
				}
			}
			if (!chessboard.saviorPieces.isEmpty())
				checkmate = false;
		}
		return (checkmate);
	}

	public void canCapture(Cell chessboard, Piece checkPiece)
	{
		findAllSaviorPieces(chessboard, checkPiece.xPos, checkPiece.yPos, checkPiece.type, false);
	}

	public void canProtect(Cell chessboard, int xPos, int yPos, int type, Piece checkPiece)
	{
		if (checkPiece.name == "Knight" || checkPiece.name == "Pawn")
			return;
		if (xPos == checkPiece.xPos && yPos > checkPiece.yPos)
			for (int y = checkPiece.yPos + 1; y < yPos; y++)
				findAllSaviorPieces(chessboard, checkPiece.xPos, y, checkPiece.type, true);
		if (xPos == checkPiece.xPos && yPos < checkPiece.yPos)
			for (int y = checkPiece.yPos - 1; y > yPos; y--)
				findAllSaviorPieces(chessboard, checkPiece.xPos, y, checkPiece.type, true);
		if (xPos > checkPiece.xPos && yPos == checkPiece.yPos)
			for (int x = checkPiece.xPos + 1; x < xPos; x++)
				findAllSaviorPieces(chessboard, x, checkPiece.yPos, checkPiece.type, true);
		if (xPos < checkPiece.xPos && yPos == checkPiece.yPos)
			for (int x = checkPiece.xPos - 1; x > xPos; x--)
				findAllSaviorPieces(chessboard, x, checkPiece.yPos, checkPiece.type, true);
		int y = checkPiece.yPos + 1;
		if (xPos > checkPiece.xPos && yPos > checkPiece.yPos)
			for (int x = checkPiece.xPos + 1; x < xPos && y < yPos; x++, y++)
				findAllSaviorPieces(chessboard, x, y, checkPiece.type, true);
		y = checkPiece.yPos - 1;
		if (xPos < checkPiece.xPos && yPos < checkPiece.yPos)
			for (int x = checkPiece.xPos - 1; x > xPos && y > yPos; x--, y--)
				findAllSaviorPieces(chessboard, x, y, checkPiece.type, true);
		y = checkPiece.yPos + 1;
		if (xPos < checkPiece.xPos && yPos > checkPiece.yPos)
			for (int x = checkPiece.xPos - 1; x > xPos && y < yPos; x--, y++)
				findAllSaviorPieces(chessboard, x, y, checkPiece.type, true);
		y = checkPiece.yPos - 1;
		if (xPos > checkPiece.xPos && yPos < checkPiece.yPos)
			for (int x = checkPiece.xPos + 1; x < xPos && y > yPos; x++, y--)
				findAllSaviorPieces(chessboard, x, y, checkPiece.type, true);
	}
	
	public boolean isThisProtecting(Cell chessboard, int xPos, int yPos, int type)
	{
		Piece checkPiece = chessboard.checkPieces.get(0);
		if (chessboard.getKing(type).xPos == checkPiece.xPos && chessboard.getKing(type).yPos > checkPiece.yPos)
			if (xPos == chessboard.getKing(type).xPos && yPos < chessboard.getKing(type).yPos && yPos > checkPiece.yPos)
				return (true);
		if (chessboard.getKing(type).xPos == checkPiece.xPos && chessboard.getKing(type).yPos < checkPiece.yPos)
			if (xPos == chessboard.getKing(type).xPos && yPos > chessboard.getKing(type).yPos && yPos < checkPiece.yPos)
				return (true);
		if (chessboard.getKing(type).xPos > checkPiece.xPos && chessboard.getKing(type).yPos == checkPiece.yPos)
			if (yPos == chessboard.getKing(type).yPos && xPos < chessboard.getKing(type).xPos && xPos > checkPiece.xPos)
				return (true);
		if (chessboard.getKing(type).xPos < checkPiece.xPos && chessboard.getKing(type).yPos == checkPiece.yPos)
			if (yPos == chessboard.getKing(type).yPos && xPos > chessboard.getKing(type).xPos && xPos < checkPiece.xPos)
				return (true);
		int y = checkPiece.yPos;
		if (chessboard.getKing(type).xPos > checkPiece.xPos && chessboard.getKing(type).yPos > checkPiece.yPos)
			for (int x = checkPiece.xPos; x < chessboard.getKing(type).xPos && y < chessboard.getKing(type).yPos; x++, y++)
				if (xPos == x && yPos == y)
					return (true);
		y = checkPiece.yPos;
		if (chessboard.getKing(type).xPos < checkPiece.xPos && chessboard.getKing(type).yPos < checkPiece.yPos)
			for (int x = checkPiece.xPos; x > chessboard.getKing(type).xPos && y > chessboard.getKing(type).yPos; x--, y--)
				if (xPos == x && yPos == y)
					return (true);
		y = checkPiece.yPos;
		if (chessboard.getKing(type).xPos < checkPiece.xPos && chessboard.getKing(type).yPos > checkPiece.yPos)
			for (int x = checkPiece.xPos; x > chessboard.getKing(type).xPos && y < chessboard.getKing(type).yPos; x--, y++)
				if (xPos == x && yPos == y)
					return (true);
		y = checkPiece.yPos;
		if (chessboard.getKing(type).xPos > checkPiece.xPos && chessboard.getKing(type).yPos < checkPiece.yPos)
			for (int x = checkPiece.xPos; x < chessboard.getKing(type).xPos && y > chessboard.getKing(type).yPos; x++, y--)
				if (xPos == x && yPos == y)
					return (true);
		return (false);
	}
}
