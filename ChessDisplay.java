/*
 * @author Harold Ainsworth
 * @version 1.0
 * @since 8-15-17
 */

import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;

public class ChessDisplay extends JComponent implements MouseListener{

	private static final int SPACING = 25;
	private static final int TILE_WIDTH = 75;

	private JFrame frame;
	private Chess game;
	private int selectedCol = -1;
	private int selectedRow = -1;

	/*
	 * constructs a ChessDisplay object
	 * @param game the game linked to this display
	 */
	public ChessDisplay (Chess game){
		this.game = game; 

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);

		this.setPreferredSize(new Dimension(SPACING * 2 + TILE_WIDTH * 8 , SPACING * 2 + TILE_WIDTH * 8));
		this.addMouseListener(this);

		frame.pack();
		frame.setVisible(true);
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g){

		//background
		g.setColor(Color.blue);
		g.fillRect(0, 0, getWidth(), getHeight());

		//board
		Image board = new ImageIcon("./src/images/board.gif").getImage();
		g.drawImage(board, SPACING, SPACING, TILE_WIDTH * 8 , TILE_WIDTH * 8, null);

		//pieces 
		for (int row = 0 ; row < 8 ; row ++)
			for (int col = 0 ; col < 8 ; col++){
				if (game.pieces[row][col] != null)
					drawPiece(g, game.pieces[row][col], col * TILE_WIDTH + SPACING, row * TILE_WIDTH + SPACING);
				if (selectedCol == col && selectedRow == row && game.pieces[row][col] != null)
					drawBorder(g, col * TILE_WIDTH + SPACING, row * TILE_WIDTH + SPACING);
			}

	}

	/*
	 * @param g the Graphics object used to draw the border
	 * @param x the x coordinate to start the border
	 * @param y the y coordinate to start the border
	 * draws a border around the specified area
	 */
	public void drawBorder(Graphics g, int x, int y){
		g.setColor(Color.YELLOW);
		g.drawRect(x, y, TILE_WIDTH, TILE_WIDTH);
		g.drawRect(x + 1, y + 1, TILE_WIDTH - 2, TILE_WIDTH - 2);
		g.drawRect(x + 2 , y + 2, TILE_WIDTH - 4, TILE_WIDTH - 4);
	}

	/*
	 * @param g the Graphics object used to draw the border
	 * @param p the piece to be drawn
	 * @param x the x coordinate to start the piece
	 * @param y the y coordinate to start the piece
	 * draws the specified piece
	 */
	public void drawPiece(Graphics g, Piece p, int x, int y){
		if (p == null)
		{
			g.setColor(Color.BLACK);
			g.drawRect(x, y, TILE_WIDTH, TILE_WIDTH);
		}
		else
		{
			String fileName = p.getFileName();
			if (!new File(fileName).exists())
				throw new IllegalArgumentException("bad file name:  " + fileName);
			Image image = new ImageIcon(fileName).getImage();
			g.drawImage(image, x, y, TILE_WIDTH, TILE_WIDTH, null);
		}
	}
	
	/* 
	 * (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	public void mouseClicked(MouseEvent e){
		if (e.getX() > SPACING && e.getY() > SPACING && e.getX() <= SPACING + TILE_WIDTH * 8 && e.getY() <= SPACING + TILE_WIDTH * 8){
			int col = (e.getX() - SPACING) / TILE_WIDTH;
			int row = (e.getY() - SPACING) / TILE_WIDTH;
			System.out.println("row " + row + ", col " + col + " clicked");
			game.tileClicked(row, col);
		}
		else 
			unselect();
		repaint();
	}
	
	/*
	 * unselects if a tile is selected
	 */
	public void unselect(){
		selectedRow = -1;
		selectedCol = -1;
	}

	/*
	 * selects tile at the specified coordinates
	 */
	public void selectTile(int row, int col){
		selectedCol = col;
		selectedRow = row;
	}
	
	/*
	 * @return if there is a tile selected
	 */
	public boolean tileSelected(){
		return selectedCol >= 0 && selectedCol < 8 && selectedRow >= 0 && selectedRow < 8;
	}
	
	/*
	 * @return the Position of the selected tile
	 */
	public Position getSelectedTile(){
		return new Position(selectedCol, selectedRow);
	}
	
	public void mousePressed(MouseEvent e){

	}

	public void mouseReleased(MouseEvent e){

	}

	public void mouseEntered(MouseEvent e){

	}

	public void mouseExited(MouseEvent e){

	}

}
