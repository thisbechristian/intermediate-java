import java.awt.*;
import javax.swing.*;

// This program and MyPanel.java should give you a lot of help with Assignment
// 5.  Read over the code and commends very carefully.
public class A5Help
{	
	private MyPanel thePanel;  // MyPanel is a subclass of JPanel
	private JFrame theWindow;

	public A5Help(int sz, int rate)
	{
		theWindow = new JFrame("CS 0401 A5 Help Program");
	
		thePanel = new MyPanel(sz, rate);  // The argument to this constructor
		// determines the size of the digits in the stopwatch

		theWindow.add(thePanel, BorderLayout.CENTER);

		theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theWindow.pack();
		theWindow.setVisible(true);
	}

	public static void main(String [] args)
	{
		int sz = Integer.parseInt(args[0]);
		int rate = Integer.parseInt(args[1]);
		new A5Help(sz, rate);
	}
}