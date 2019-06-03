/*This file contains the class Life() which runs a simulation of Conway's Game of Life, which LifeGrid prints out
 * Date: 11/7/17
 * Author: Grace Hunter
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Life implements MouseListener, ActionListener, Runnable {
	
	boolean squares[][] = new boolean[50][50]; //array that stores whether the squares are alive (true) or dead (false)
	JFrame frame = new JFrame("Life"); //JFrame frame
	LifeGrid canvas = new LifeGrid(squares); //LifeGrid canvas
	//three buttons for start, stop, play
	JButton stepButton = new JButton("STEP");
	JButton playButton = new JButton("PLAY");
	JButton stopButton = new JButton("STOP");
	Container southContainer = new Container(); //south container to hold buttons
	boolean isPlaying = false; //boolean will track when play button is pressed
	
	public Life() {
		
		//format frame and add canvas to center
		frame.setSize(900,950); 
		frame.setLayout(new BorderLayout());
		frame.add(canvas, BorderLayout.CENTER);
				
		//format south Container
		southContainer.setLayout(new GridLayout(1, 3));
		southContainer.add(stepButton);
		stepButton.addActionListener(this);
		southContainer.add(playButton);
		playButton.addActionListener(this);
		southContainer.add(stopButton);
		stopButton.addActionListener(this);
		
		//add south container to south of borderLayout and set default close operation and make frame visible
		frame.add(southContainer, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		canvas.addMouseListener(this); //add mouse listener to canvas
	}
	
	public static void main(String[] args) {
		new Life();
	}
	
	public boolean[][] step() { //does one step (evaluate whether squares are dead or alive) and returns an updated array
		boolean[][] nextRound = new boolean[squares.length][squares[0].length]; //stores values for the next round
		int neighbors = 0; //track number of neighbors for each square, is set to 0 every time a new square is evaluated
		
		//leftmost column (excluding corners)
		for (int r = 1; r < squares[0].length - 1; r++) {
				int c = 0;
				neighbors = 0;
				if (squares[c][r-1]) { //up
					neighbors ++;
				}
				if (squares[c+1][r-1]) { //up right
					neighbors ++;
				}
				if (squares[c+1][r]) { //right
					neighbors ++;
				}
				if (squares[c+1][r+1]) { //down right
					neighbors ++;
				}
				if (squares[c][r+1]) { //down
					neighbors ++;
				}
				
				//evaluate whether the number of neighbors makes the square dead or alive
				if (neighbors == 3) {
					nextRound[c][r] = true;
				}
				else if ((neighbors == 2) && squares[c][r]) {
					nextRound[c][r] = true;
				}
				else {
					nextRound[c][r] = false;
				}
		}
		
		//rightmost column (excluding corners)
		for (int r = 1; r < squares[0].length - 1; r++) {
			int c = squares[0].length - 1;
			neighbors = 0;
			if (squares[c][r-1]) { //up
				neighbors ++;
			}
			if (squares[c-1][r-1]) { //up left
				neighbors ++;
			}
			if (squares[c-1][r]) { //left
				neighbors ++;
			}
			if (squares[c-1][r+1]) { //down right
				neighbors ++;
			}
			if (squares[c][r+1]) { //down
				neighbors ++;
			}
			
			//evaluate whether the number of neighbors makes the square dead or alive
			if (neighbors == 3) {
				nextRound[c][r] = true;
			}
			else if ((neighbors == 2) && squares[c][r]) {
				nextRound[c][r] = true;
			}
			else {
				nextRound[c][r] = false;
			}
		}
		
		//top row (excluding corners)
		for (int c = 1; c < squares.length - 1; c++) {
			int r = 0;
			neighbors = 0;
			if (squares[c+1][r]) { //right
				neighbors ++;
			}
			if (squares[c+1][r+1]) { //down right
				neighbors ++;
			}
			if (squares[c][r+1]) { //down
				neighbors ++;
			}
			if (squares[c-1][r+1]) { //down left
				neighbors ++;
			}
			if (squares[c-1][r]) { //left
				neighbors ++;
			}
			
			//evaluate whether the number of neighbors makes the square dead or alive
			if (neighbors == 3) {
				nextRound[c][r] = true;
			}
			else if ((neighbors == 2) && squares[c][r]) {
				nextRound[c][r] = true;
			}
			else {
				nextRound[c][r] = false;
			}
		}
		
		//bottom row (excluding corners)
		for (int c = 1; c < squares.length - 1; c++) {
			int r = squares.length - 1;
			neighbors = 0;
			if (squares[c+1][r]) { //right
				neighbors ++;
			}
			if (squares[c+1][r-1]) { //up right
				neighbors ++;
			}
			if (squares[c][r-1]) { //up
				neighbors ++;
			}
			if (squares[c-1][r-1]) { //up left
				neighbors ++;
			}
			if (squares[c-1][r]) { //left
				neighbors ++;
			}
			
			//evaluate whether the number of neighbors makes the square dead or alive
			if (neighbors == 3) {
				nextRound[c][r] = true;
			}
			else if ((neighbors == 2) && squares[c][r]) {
				nextRound[c][r] = true;
			}
			else {
				nextRound[c][r] = false;
			}
		}
		
		//middle
		for (int c = 1; c < squares.length - 1; c++) {
			for (int r = 1; r < squares[0].length - 1; r++) {
				neighbors = 0;
				if (squares[c-1][r-1]){ //up left
					neighbors++;
				}
				if (squares[c][r-1]) { //up
					neighbors++;
				}
				if (squares[c+1][r-1]) { //up right
					neighbors++;
				}
				if (squares[c+1][r]) { //right
					neighbors++;
				}
				if (squares[c+1][r+1]) { //down right
					neighbors++;
				}
				if (squares[c][r+1]) { //down
					neighbors++;
				}
				if (squares[c-1][r+1]) { //down left
					neighbors++;
				}
				if (squares[c-1][r]) { //left
					neighbors++;
				}
				
				//evaluate whether the number of neighbors makes the square dead or alive
				if (neighbors == 3) { //if the cell has three neighbors, it is alive, regardless of its previous state
					nextRound[c][r] = true;
				}
				else if ((neighbors == 2) && squares[c][r]) { //if the cell has two neighbors and is already alive it can stay alive
					nextRound[c][r] = true;
				}
				else { //if none of the previous conditions are true the square is dead
					nextRound[c][r] = false;
				}
			}
		}
		
		//corners
		//upper left
		neighbors = 0;
		if (squares[1][0]) { //right
			neighbors++;
		}
		if (squares[1][1]) { //down right
			neighbors++;
		}
		if (squares[0][1]) { //down
			neighbors++;
		}
		//evaluate whether the number of neighbors makes the square dead or alive
		if (neighbors == 3) { //if the cell has three neighbors, it is alive, regardless of its previous state
			nextRound[0][0] = true;
		}
		else if ((neighbors == 2) && squares[0][0]) { //if the cell has two neighbors and is already alive it can stay alive
			nextRound[0][0] = true;
		}
		else { //if none of the previous conditions are true the square is dead
			nextRound[0][0] = false;
		}
		
		//upper right
		neighbors = 0;
		if (squares[squares.length-2][0]) { //left
			neighbors++;
		}
		if (squares[squares.length-2][1]) { //down left
			neighbors++;
		}
		if (squares[squares.length - 1][1]) { //left
			neighbors++;
		}
		//evaluate whether the number of neighbors makes the square dead or alive
		if (neighbors == 3) { //if the cell has three neighbors, it is alive, regardless of its previous state
			nextRound[squares.length - 1][0] = true;
		}
		else if ((neighbors == 2) && squares[squares.length - 1][0]) { //if the cell has two neighbors and is already alive it can stay alive
			nextRound[squares.length - 1][0] = true;
		}
		else { //if none of the previous conditions are true the square is dead
			nextRound[squares.length - 1][0] = false;
		}
		
		//down right
		neighbors = 0;
		if (squares[squares.length - 1][squares[0].length - 2]) { //up
			neighbors++;
		}
		if (squares[squares.length - 2][squares[0].length - 2]) { //up left
			neighbors++;
		}
		if (squares[squares.length - 2][squares[0].length - 1]) { //left
			neighbors++;
		}
		
		//evaluate whether the number of neighbors makes the square dead or alive
		if (neighbors == 3) { //if the cell has three neighbors, it is alive, regardless of its previous state
			nextRound[squares.length - 1][squares[0].length - 1] = true;
		}
		else if ((neighbors == 2) && squares[squares.length - 1][squares[0].length - 1]) { //if the cell has two neighbors and is already alive it can stay alive
			nextRound[squares.length - 1][squares[0].length - 1] = true;
		}
		else { //if none of the previous conditions are true the square is dead
			nextRound[squares.length  - 1][squares[0].length - 1] = false;
		}		
		
		//down left
		neighbors = 0;
		if (squares[0][squares[0].length - 2]) { //up
			neighbors++;
		}
		if (squares[1][squares[0].length - 2]) { //up right
			neighbors++;
		}
		if (squares[1][squares[0].length - 1]) { //right
			neighbors++;
		}
		
		//evaluate whether the number of neighbors makes the square dead or alive
		if (neighbors == 3) { //if the cell has three neighbors, it is alive, regardless of its previous state
			nextRound[1][squares[0].length - 1] = true;
		}
		else if ((neighbors == 2) && squares[1][squares[0].length - 1]) { //if the cell has two neighbors and is already alive it can stay alive
			nextRound[1][squares[0].length - 1] = true;
		}
		else { //if none of the previous conditions are true the square is dead
			nextRound[1][squares[0].length - 1] = false;
		}		

		//update squares
		return nextRound;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {	
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent event) {
		double widthSquare = canvas.getWidth()/(double)(squares.length); //find the width of one square
		double heightSquare = canvas.getHeight()/(double)squares[0].length; //find the height of one square
		int column = Math.min((int)(event.getX()/widthSquare), squares.length - 1); //find which column it is in
		int row = Math.min((int)(event.getY()/heightSquare), squares[0].length -1); //find which row it is in
		squares[column][row] = !squares[column][row]; //change dead/alive to opposite
		frame.repaint(); //calls all paint components
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(stepButton)) { //if STEP button clicked:
			squares = step(); //determine who is dead/alive
			canvas.newSquares(squares); //reset squares for LifeGrid
			frame.repaint(); //redraw grid
		}
		if (event.getSource().equals(playButton)) { //if PLAY button clicked
				if (isPlaying == false) { //if it isn't already playing
					isPlaying = true; //set isPlaying to true so the while loop in run() will run
					playButton.setEnabled(false); //disable the play button
					Thread thread = new Thread(this); //create a new thread of execution
					thread.start(); //start the thread of execution
				}
		}
		if (event.getSource().equals(stopButton)) { //if STOP button clicked
			playButton.setEnabled(true); //enable the play button
			isPlaying = false; //stop the while loop in run()
		}
	}

	@Override
	public void run() {
		while (isPlaying == true) {
			squares = step(); //determine who is dead/alive
			canvas.newSquares(squares); //reset squares for LifeGrid
			frame.repaint(); //redraw grid
			try { //try to sleep for 1/4 sec to slow it down
				Thread.sleep(250);
			}
			catch (InterruptedException ex){ //error
				ex.printStackTrace();
			}
		}
	}
}
