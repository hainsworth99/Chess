
/*
 * @author Harold Ainsworth
 * @version 1.0
 * @since 8-15-17
 */

public class Movement {

	private Position start;
	private Position end;
	
	/*
	 * @param start the starting Position of the Movement
	 * @param end the finishing Position of the Movement 
	 * construct a Movement object
	 */
	public Movement (Position start, Position end){
		this.start = start;
		this.end = end;
	}
	
	/*
	 * @return true if the path of the movement is diagonal, false otherwise
	 */
	public boolean isDiagonal(){
		return didTravel() && Math.abs(xDif()) == Math.abs(yDif());
	}
	
	/*
	 * @return true if the path o the movement is straight, and goes across rather than up and down
	 */
	public boolean isVertical(){
		return this.isStraight() && this.xDif()!=0 && this.yDif() == 0;
	}
	
	/*
	 * @return true if the path of the movement is straight, false otherwise
	 */
	public boolean isStraight(){
		return didTravel() && (xDif() == 0 || yDif() == 0);
	}
	
	/*
	 * @return true if there is a difference between the two positions, false otherwise
	 */
	public boolean didTravel(){
		return xDif() != 0 || yDif() != 0;
	}
	
	/*
	 * @return the distance of the Movement
	 */
	public int distance(){
		if (isDiagonal()){
			return Math.abs(xDif());
		}
		else if (isStraight()){
			if(xDif() == 0)
				return Math.abs(yDif());
			else return Math.abs(xDif());
		}
		else if (!didTravel())
			return 0;
		else return -1;
	}
	
	/*
	 * @return the difference between the y's of the start and end Positions
	 */
	public int yDif(){	
		return start.getY() - end.getY();
	}
	
	/*
	 * @return the difference between the x's of the start and end Positions
	 */
	public int xDif(){
		return start.getX() - end.getX();
	}
	
	/*
	 * @return start
	 */
	public Position getStart(){
		return start;
	}
	
	/*
	 * @return end
	 */
	public Position getEnd(){
		return end;
	}
}
