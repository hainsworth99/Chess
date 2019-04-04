
/*
 * @author Harold Ainsworth
 * @version 1.0
 * @since 8-15-17
 */

public class King extends Piece{

	/*
	 * @param color the color of the King ("b" or "w")
	 * constructs a King object
	 */
	public King(String color) {
		super(color, "K");
	}

	/*
	 * (non-Javadoc)
	 * @see Piece#legalMovement(Movement)
	 */
	public boolean legalMovement(Movement m, boolean capturingMove){
		return m.distance() == 1;
	}

}
