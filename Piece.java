
/*
 * @author Harold Ainsworth
 * @version 1.0
 * @since 8-15-17
 */

public abstract class Piece {
	
	protected String color;
	protected String rank;
	protected boolean moved;
	
	/*
	 * constructs a Piece object
	 * @param color the color of the piece ("b", "w")
	 * @param rank the rank of the piece ("k", "q", "b", "n", "r", "p")
	 */
	public Piece (String color , String rank){
		this.color = color;
		this.rank = rank;
		moved = false;
	}
	
	/*
	 * changes moved to true, showing that the piece has moved
	 */
	public void movePiece(){
		moved = true;
	}
	
	/*
	 * @return moved
	 */
	public boolean hasMoved(){
		return moved;
	}
	
	/*
	 * @param m the movement made
	 * @return true if the movement path is legal for this piece
	 */
	public abstract boolean legalMovement(Movement m, boolean capturingMove);
	
	/*
	 * @return true if the piece's color is white; false otherwise
	 */
	public boolean isWhite(){
		return color.equals("w");
	}
	
	/*
	 * @return rank
	 */
	public String getRank(){
		return rank;
	}
	
	/*
	 * @return the image file name associated with this piece
	 */
	public String getFileName(){
		return "./src/images/" + color + rank + ".png";
	}
	
}
