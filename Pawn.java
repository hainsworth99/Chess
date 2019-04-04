/*
 * @author Harold Ainsworth
 * @version 1.0
 * @since 8-15-17
 */
public class Pawn extends Piece{

	private boolean isQueen;

	/*
	 * @param color the color of the Pawn ("b" or "w")
	 * constructs a Pawn object
	 */
	public Pawn(String color){
		super(color, "p");
		isQueen = false;
	}

	/*
	 * (non-Javadoc)
	 * @see Piece#legalMovement(Movement)
	 */
	public boolean legalMovement(Movement m, boolean capturingMove){
		if (isQueen)
			return m.isDiagonal() || m.isStraight();
		else if (hasMoved()){
			if (capturingMove){
				if(isWhite())
					return m.isDiagonal() && m.yDif() == 1;
				else return m.isDiagonal() && m.yDif() == -1; 
			}
			else{
				if(isWhite())
					return m.isStraight() && m.yDif() == 1;
				else return m.isStraight() && m.yDif() == -1; 
			}
		}
		else{
			if (capturingMove){
				if(isWhite())
					return m.isDiagonal() && m.yDif() == 1;
				else return m.isDiagonal() && m.yDif() == -1; 
			}
			else{
				if(isWhite())
					return (m.isStraight() && m.yDif() == 1) || (m.isStraight() && m.yDif() == 2);
				else return (m.isStraight() && m.yDif() == -1) || (m.isStraight() && m.yDif() == -2); 
			}
		}

	}

	/*
	 * turns pawn into queen
	 */
	public void makeQueen(){
		isQueen = true;
	}

	/*
	 * (non-Javadoc)
	 * @see Piece#getFileName()
	 */
	public String getFileName(){
		if (isQueen)
			return "./src/images/" + color + "q.png";
		else 
			return super.getFileName();
	}

}
