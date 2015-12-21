// CS 0401 Fall 2013
// This handout should help you with Assignment 5. See more
// comments below.

import java.awt.*;
import java.util.Random;
import javax.swing.*;
import java.awt.event.*;

// CrazyLED is a subclass of LED.  It is primitive -- simply displaying one segment
// at a time.  However, you can get some hints for your Digit class from this.  In
// your Digit class you should display the correct segments to represent each of the
// possible digit values.
class CrazyLED extends LED
{
	private int active;

	public CrazyLED(int X, int Y, int sz)
	{
		super(X, Y, sz);
		active = 0;
	}

	// This method is abstract in LED, so it must be implemented
	// here.  Note what is being done.
	public void draw(Graphics2D g2)
	{
		// I am increasing the stroke width so the LED looks better
		g2.setStroke(new BasicStroke(8));
		g2.setColor(Color.blue);
		g2.draw(segments[active]);
		// Setting the stroke back to the default here -- see what happens
		// if you don't do this.
		g2.setStroke(new BasicStroke());
	}

	// Simple mutator to change the segment that is being
	// displayed
	public void mutate()
	{
		active = (active + 1) % segments.length;
	}
}

// Note that MyPanel extends JPanel.  This means that it has all of
// the functionality of a JPanel.  It implements ActionListener, which
// means it implements the method actionPerformed.  Your StopWatch class
// can use this class as a starting point.
public class MyPanel extends JPanel implements ActionListener
{
	// Declare instance variables as shown.
	private CrazyLED theLED;  // Object to be drawn graphically
	private JButton start, stop, move;  // Some buttons
	private JPanel buttonPanel;	 // Another panel to hold the buttons
	private ButtonListener bListener;  // listener for the buttons
	private Timer T;  // Timer -- see the API in javax.swing.Timer
	private int size;
	private int prefWid, prefHt;  // see details on these below

	public MyPanel(int sz, int rate)
	{
		size = sz;
		// Set the preferred width and height of the panel based on the
		// size argument that is passed in.  See the getPreferredSize
		// method below for more information on this.
		prefWid = 3 * size;
		prefHt = 4 * size;

		// Randomly place the CrazyLED in the panel
		Random R = new Random();
		int x = R.nextInt(prefWid - size);
		int y = R.nextInt(prefHt - 2*size);
		theLED = new CrazyLED(x, y, size);

		// Make some buttons and put them in a new subpanel.  We separate
		// the panel with the buttons from the main panel so that the
		// graphics don't interfere with the rendering of the components.
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 2));
		bListener = new ButtonListener();
		start = new JButton("Go");
		start.addActionListener(bListener);
		buttonPanel.add(start);
		stop = new JButton("Stop");
		stop.addActionListener(bListener);
		stop.setEnabled(false);
		buttonPanel.add(stop);
		this.add(buttonPanel, BorderLayout.NORTH);

		// Create a new Timer, setting the delay to rate milliseconds.
		// The second argument to the constructor must be an
		// ActionListener.  Since the current object (a MyPanel object)
		// implements ActionListener, we can pass it to the Timer constructor
		// using the "this" keyword.
		T = new Timer(rate, this);
		
	}

	// When a panel is added to a JFrame, the JFrame needs to determine how
	// much space to give it.  The getPreferredSize method allows the panel to
	// "tell" the JFrame that it wants the specified amount of space.  However,
	// it is only preferred -- the JFrame is not obliged to give it this much
	// space.
	public Dimension getPreferredSize()
	{
		return new Dimension(prefWid, prefHt);
	}

	// Handle the buttons but starting and stopping the timer.
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == start)
			{
				T.start();
				start.setEnabled(false);
				stop.setEnabled(true);
			}
			else if (e.getSource() == stop)
			{
				T.stop();
				stop.setEnabled(false);
				start.setEnabled(true);
			}
		}
	}
	
	// To draw graphics within a JPanel in Java, we override the
	// paintComponent() method.  Experiment with this method by putting
	// other graphical drawing code in here.
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);	// Make sure you call the super version
									// before anything elses
		Graphics2D g2 = (Graphics2D) g;		// The Graphics2D class is a
					// subclass of Graphics with added functionality.  By
					// casting we get access to this functionality, including
					// the ability to draw simple objects such as
					// Rectangle2D, Ellipse2D and Line2D
		if (theLED != null)	// It's a good idea to check for null here
			theLED.draw(g2);
	}

	// The ActionListener that is fired by the Timer
	public void actionPerformed(ActionEvent e)
	{
		theLED.mutate();
		repaint();  // This method call requests that the graphics be redrawn
					// See what happens if it is not done.
	}
}