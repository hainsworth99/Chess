
/*
 * @author Harold Ainsworth
 * @version 1.0
 * @since 8-15-17
 */

public class Chess {

	public Piece[][] pieces;
	private ChessDisplay display;
	private boolean whitesTurn = true;

	public static void main (String[] args){
		new Chess();
	}

	/*
	 * constructs a new Chess object that keeps track of a chess match played by users
	 */
	public Chess (){
		display = new ChessDisplay(this);
		pieces = new Piece[8][8];
		resetBoard();
	}

	/*
	 * resets the board, putting the pieces in their starting positions
	 */
	public void resetBoard(){
		for (int row = 0 ; row < 8 ; row++){
			for (int  col = 0 ; col < 8 ; col++){
				if (row == 1 )
					pieces[row][col] = new Pawn("b");
				else if (row == 6)
					pieces[row][col] = new Pawn("w");
				else if (row == 0){ // black pieces
					switch (col){
					case 0: 
					case 7: 
						pieces[row][col] = new Rook("b");
						break;
					case 1:
					case 6:
						pieces[row][col] = new Knight("b");
						break;
					case 2: 
					case 5:
						pieces[row][col] = new Bishop("b");
						break;
					case 3:
						pieces [row][col] = new Queen("b");
						break;
					case 4:
						pieces[row][col] = new King("b");
						break;
					default:
						pieces[row][col] = null;
					}
				}
				else if (row == 7){ // white pieces 
					switch (col){
					case 0: 
					case 7: 
						pieces[row][col] = new Rook("w");
						break;
					case 1:
					case 6:
						pieces[row][col] = new Knight("w");
						break;
					case 2: 
					case 5:
						pieces[row][col] = new Bishop("w");
						break;
					case 3:
						pieces [row][col] = new Queen("w");
						break;
					case 4:
						pieces[row][col] = new King("w");
						break;
					default:
						pieces[row][col] = null;
					}
				}
				else pieces[row][col] = null;
			}
		}
	}

	/*
	 * changes whose turn it is
	 */
	public void changeTurn(){
		whitesTurn = !whitesTurn; 
	}

	/*
	 * @param row the row of the piece 
	 * @param col the column of the piece
	 * @return true if the tile clicked is a piece controlled by the person whose turn it is, false otherwise
	 */
	public boolean ownPiece(int row, int col){
		if (pieces[row][col] == null)
			return false;
		return whitesTurn == pieces[row][col].isWhite();
	}

	/*
	 * @param row the row of the piece 
	 * @param col the column of the piece
	 * handles logic for when tiles are clicked
	 */
	public void tileClicked(int row, int col){
		if (!display.tileSelected()){ // if no other tile has been selected
			if (ownPiece(row, col))
				display.selectTile(row, col);
		}
		else{ // if another tile has already been selected
			Movement m = new Movement (display.getSelectedTile(), new Position(col, row));
			boolean pathClear = true;
			if (m.isStraight())
				pathClear = clearStraight(m);
			else if (m.isDiagonal())
				pathClear = clearDiagonal(m);
			if (pathClear && destinationClear(m) && pieces[m.getStart().getY()][m.getStart().getX()].legalMovement(m, capturingMove(m))){
				pieces[row][col] = pieces[m.getStart().getY()][m.getStart().getX()];
				pieces[m.getStart().getY()][m.getStart().getX()] = null;
				pieces[row][col].movePiece();
				System.out.printf("Piece moved to x: %d y: %d\n", m.getEnd().getX(), m.getEnd().getY());
				changeTurn();
			}
			display.unselect();
		}
	}

	/*
	 * @param m the movement taken by the piece
	 * @return true if the move would result in a captured piece
	 */
	public boolean capturingMove(Movement m){
		if (destinationClear(m) && pieces[m.getEnd().getY()][m.getEnd().getX()] != null)
			return true;
		else return false;
	}

	/*
	 * @param m the movement the piece is taking
	 * @return true if the diagonal path is clear for the piece to make the attempted move, false otherwise
	 */
	public boolean clearDiagonal(Movement m){
		//TODO
		return true;
	}

	/*
	 * @param m the movement the piece is taking
	 * @return true if the straight path is clear for the piece to make the attempted move, false otherwise
	 */
	public boolean clearStraight(Movement m){
		if (m.isStraight()){
			if(m.distance() <= 1){
				return true;
			}
			else if(m.isVertical()){
				int y = m.getStart().getY();
				int xStart = m.getStart().getX();
				int xEnd = m.getEnd().getX();
				if (xStart>xEnd){
					int temp = xStart;
					xStart = xEnd;
					xEnd = temp;
				}
				boolean clear = true;
				for (int i = xStart + 1 ; i < xEnd ; i++){
					if (pieces[y][i] != null)
						clear = false;
				}
				return clear;
			}
			else{
				int x = m.getStart().getX();
				int yStart = m.getStart().getY();
				int yEnd = m.getEnd().getY();
				if (yStart>yEnd){
					int temp = yStart;
					yStart = yEnd;
					yEnd = temp;
				}
				boolean clear = true;
				for (int i = yStart + 1 ; i < yEnd ; i++){
					if (pieces[i][x] != null)
						clear = false;
				}
				return clear;
			}
		}
		else return false;
	}

	/*
	 * @param m the movement the piece is taking
	 * @return true if the tile the piece is moving to is either empty or contains an opponents piece, false otherwise
	 */
	public boolean destinationClear(Movement m){
		if (pieces[m.getEnd().getY()][m.getEnd().getX()] == null)
			return true;
		else{
			return pieces[m.getEnd().getY()][m.getEnd().getX()].isWhite() != pieces[m.getStart().getY()][m.getStart().getX()].isWhite();
		}
	}


}
