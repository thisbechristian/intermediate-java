/*
    Name:               Christian Boni
    Course:             CS 0401, T/R 1PM
    Project:            Assignment 5
    Due Date:           December 4, 2013
    
    Description:        Write a java graphic interface program that implements mimics the function of a simple stopwatch.
    					The program should allow users to start, pause, and reset the stopwatch freely. Furthermore
    					the stopwatch has the ability to keep track of laps each time the player presses the "Show Lap" Button.
    					Lastly, all the numbers mimic an led display and are drawn onto the JFrame.
 */
/* CS 0401 Fall 2013
   Assignment 5 Main Program Class
   You must use the program as is -- no changes!
*/

import java.awt.*;
import javax.swing.*;
	
public class Assig5
{	
	private StopWatch thePanel;  // StopWatch is a subclass of JPanel
	private JFrame theWindow;

	public Assig5(int sz, String fName)
	{
		theWindow = new JFrame("CS 401 Assignment 5");
	
		thePanel = new StopWatch(sz, fName);  // The arguments to this constructor
					// determines the size of the digits in the stopwatch and the
					// file to which lap times will be written

		Container c = theWindow.getContentPane();
		c.add(thePanel, BorderLayout.CENTER);

		theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theWindow.pack();
		theWindow.setVisible(true);
	}

	public static void main(String [] args)
	{
		int sz = Integer.parseInt(args[0]);
		String fName = new String(args[1]);
		new Assig5(sz, fName);
	}
}

