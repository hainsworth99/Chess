
/*
 * @author Harold Ainsworth
 * @version 1.0
 * @since 8-15-17
 */

public class Bishop extends Piece{

	/*
	 * @param color the color of the Bishop ("b" or "w")
	 * constructs a Bishop object
	 */
	public Bishop(String color){
		super(color, "b");
	}

	/*
	 * (non-Javadoc)
	 * @see Piece#legalMovement(Movement)
	 */
	public boolean legalMovement(Movement m, boolean capturingMove){
		return m.isDiagonal();
	}

}
