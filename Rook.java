
/*
 * @author Harold Ainsworth
 * @version 1.0
 * @since 8-15-17
 */

public class Rook extends Piece{

	/*
	 * @param color the color of the Rook ("b" or "w")
	 * constructs a Rook object
	 */
	public Rook(String color){
		super(color, "r");
	}

	public boolean legalMovement(Movement m, boolean capturingMove){
		return m.isStraight();
	}

}
