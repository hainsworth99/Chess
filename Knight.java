
/*
 * @author Harold Ainsworth
 * @version 1.0
 * @since 8-15-17
 */

public class Knight extends Piece{

	/*
	 * @param color the color of the Knight ("b" or "w")
	 * constucts a Knight object
	 */
	public Knight(String color){
		super(color, "n");
	}
	
	/*
	 * (non-Javadoc)
	 * @see Piece#legalMovement(Movement)
	 */
	public boolean legalMovement(Movement m, boolean capturingMove){
		return (Math.abs(m.xDif()) == 2 && Math.abs(m.yDif()) == 1) || (Math.abs(m.xDif()) == 1 && Math.abs(m.yDif()) == 2);
	}
	
}
