
/*
 * @author Harold Ainsworth
 * @version 1.0
 * @since 8-15-17
 */

public class Queen extends Piece{

	/*
	 * @param color the color of the Queen ("b" or "w")
	 * constructs a Queen object
	 */
	public Queen(String color){
		super(color, "q");
	}

	/*
	 * (non-Javadoc)
	 * @see Piece#legalMovement(Movement)
	 */
	public boolean legalMovement(Movement m, boolean capturingMove){
		return m.isDiagonal() || m.isStraight();
	}

}
