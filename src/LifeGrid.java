/*The Class LifeGrid draws on the JPanel to show what is happening in the Life simulation
 * Date: 11/7/17
 * Author: Grace Hunter
 */
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class LifeGrid extends JPanel{
	boolean[][] squares;
	double width;
	double height;
	
	public LifeGrid(boolean[][] in) {
		squares = in; //receive and store squares array from Life
	}
	public void newSquares(boolean[][] in) {
		squares = in; //reset squares
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g); //overwrite parent's paintComponent
		width = this.getWidth(); //width of grid
		height = this.getHeight(); //height of grid
		
		g.setColor(new Color (0, 124, 16)); //make squares green
		for (int x = 0; x < squares.length; x++) {
			for (int y = 0; y < squares[0].length; y++) {
				if (squares[x][y]) { //draw a square for each alive square
					g.fillRect((int)(Math.round(x*(width/squares.length))), (int)(Math.round(y*(height/squares[0].length))), 
							(int)(Math.round(width/squares.length)+0.5), (int)(Math.round(height/squares[0].length)+0.5));
				}
			}
		}
		
		g.setColor(new Color(178,178,178)); //make the color grey
		for (int x = 0; x < squares.length; x++) { //draw horizontal lines
			g.drawLine(0, (int)Math.round(x*(height/squares.length)), (int)Math.round(width), (int)Math.round(x*(height/squares.length)));
		}
		
		for (int y = 0; y < squares[0].length; y++) { //draw vertical lines
			g.drawLine((int)Math.round(y*(width/squares.length)), 0, (int)Math.round(y*(width/squares.length)), (int)Math.round(height));
		}
	}
}
