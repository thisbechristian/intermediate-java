/*
    Name:               Christian Boni
    Course:             CS 0401, T/R 1PM
    Project:            Assignment 3
    Due Date:           October 31, 2013
    
    Description:        Write a program that acts as a database that can store multiple batters.
    					The program should also all be able to add/remove batters, sort the batters both
    					alphabetically and by descending batting average, mutate/access the batters stats and
    					print all of the batters and their stats out to the screen, and finally saving all this raw data to a file. 
    					
 */
 
import java.util.*;
import java.io.*;

// CS 0401 Fall 2013.  Assignment 3 main program class.  Note how this class is set up
// with instance variables and methods.  The idea is that the main program is itself an
// object and the methods being called are parts of the object that implement the various
// requirements.  I have implemented the initial reading of the data from the file to
// get you started.  You must add the menu and all of its required functionality.

// Note that this program WILL NOT COMPILE until you have completed the Batter and BatterDB
// classes to some extent.  They do not have to be totally working but all of the methods
// used here must be implemented in order for this code to compile.

public class Assig3
{
	private BatterDB theDB;
	private Batter currBatter;
	private Scanner inScan;
	private String fName;

	// Note how this method is working.  It first reads the number of Batters from the
	// file, then for each Batter it gets the names, creates the object, and mutates it
	// with the instance methods shown.  Finally, it adds the new object to the BatterDB
	// object.
	public void getBatters(String fName) throws IOException
	{
		Batter currB;
		File inFile = new File(fName);
		Scanner inScan = new Scanner(inFile);
		int numBatters = inScan.nextInt();
		inScan.nextLine();
		for (int i = 0; i < numBatters; i++)
		{
			String first = inScan.nextLine();
			String last = inScan.nextLine();
			currB = new Batter(first, last);

			int ab, h, d, t, hr;	
			ab = inScan.nextInt();  inScan.nextLine();
			currB.setBats(ab);
			h = inScan.nextInt();	inScan.nextLine();
			currB.setHits(h);
			d = inScan.nextInt();	inScan.nextLine();
			currB.setDoubles(d);
			t = inScan.nextInt();	inScan.nextLine();
			currB.setTriples(t);
			hr = inScan.nextInt();	inScan.nextLine();
			currB.setHR(hr);
			theDB.addBatter(currB);
		}
	}
	
	// Constructor is really where the execution begins.  Initialize the
	// database (done for you) and then go into the main interactive loop (you
	// must add this code).
	public Assig3(String fstring) throws IOException
	{

		fName = fstring;
		theDB = new BatterDB(2);
		getBatters(fName);
                
                System.out.println("Welcome to the Bat-Stat Baseball Program");
                
                int choice;
                
                do
                {
                System.out.println("\nPlease choose one of the options below:");
                System.out.println("\t1) Show the list of players");
                System.out.println("\t2) Add a new player");
                System.out.println("\t3) Search for a player");
                System.out.println("\t4) Remove a player");
                System.out.println("\t5) Upate a player");
                System.out.println("\t6) Sort the list alphabetically");
                System.out.println("\t7) Sort the list by batting average");
                System.out.println("\t8) Quit the program [list will be saved]");
                
                Scanner S = new Scanner(System.in);
                choice = S.nextInt();
                
                if (choice == 1)
                {
                    System.out.println("Batter List: ");
                    System.out.println("");
                    System.out.println(theDB.toString());
                    
                }
                
                if (choice == 2)
                {
                    String fn, ln;
                    int ab, h, d, t, hr;
                    System.out.println("Enter data for the Batter");
                    System.out.println();
                    S.nextLine();
                    
                    System.out.println("First Name? ");
                    fn = S.nextLine();
                    System.out.println("Last Name? ");
                    ln = S.nextLine();
                    
                    currBatter = new Batter(fn, ln);
                    
                    System.out.println("At Bats? ");
                    ab = S.nextInt();
                    
                    currBatter.setBats(ab);
                    
                    do
                    {
                    System.out.println("Hits (<=" + ab + ")? ");
                    h = S.nextInt();
                    }while(h > ab);
                    
                    currBatter.setHits(h);
                    
                    do
                    {
                    System.out.println("Doubles (<=" + h + ")? ");
                    d = S.nextInt();
                    }while(d > h);
                    
                    currBatter.setDoubles(d);
                    
                    do
                    {
                    System.out.println("Triples (<=" + h + ")? ");
                    t = S.nextInt();
                    }while(t > h);
                    
                    currBatter.setTriples(t);
                    
                    do
                    {
                    System.out.println("HR (<=" + h + ")? ");
                    hr = S.nextInt();
                    }while(hr > h);
                    
                    currBatter.setHR(hr);
                    
                    theDB.addBatter(currBatter);
                    
                }
                
                if (choice == 3 || choice == 4 || choice == 5)
                {
                    String fn, ln;
                    int ab, h, d, t, hr;
                    S.nextLine();
                    
                    System.out.println("Enter the player's first name: ");
                    fn = S.nextLine();
                    System.out.println("Enter the player's last name: ");
                    ln = S.nextLine();
                    
                    if(theDB.findBatter(fn, ln) != null)
                    {
                        currBatter = theDB.findBatter(fn, ln);
                        
                        if(choice == 3)
                        {
                        System.out.println("\nPlayer Found:");
                        System.out.println(currBatter.toString());
                        }
                        
                        else if(choice == 4)
                        {
                        currBatter = theDB.removeBatter(currBatter);
                        System.out.println("\nPlayer Removed:");
                        System.out.println(currBatter.toString());
                        }
                        
                        else if(choice == 5)
                        {
                        System.out.println("\nPlayer Found:");
                        System.out.println(currBatter.toString());
                        
                        System.out.println("At Bats? ");
                        ab = S.nextInt();

                        currBatter.setBats(ab);

                        do
                        {
                        System.out.println("Hits (<=" + ab + ")? ");
                        h = S.nextInt();
                        }while(h > ab);

                        currBatter.setHits(h);

                        do
                        {
                        System.out.println("Doubles (<=" + h + ")? ");
                        d = S.nextInt();
                        }while(d > h);

                        currBatter.setDoubles(d);

                        do
                        {
                        System.out.println("Triples (<=" + h + ")? ");
                        t = S.nextInt();
                        }while(t > h);

                        currBatter.setTriples(t);

                        do
                        {
                        System.out.println("HR (<=" + h + ")? ");
                        hr = S.nextInt();
                        }while(hr > h);

                        currBatter.setHR(hr);
                        }
                        
                    }
                    
                    else
                        System.out.println(fn + " " + ln + " was not found." );
                    
                    
                }
                
                if (choice == 6)
                {
                    System.out.println("List is now sorted alphabetically.");
                    theDB.sortName();
                }
                
                if (choice == 7)
                {
                    System.out.println("List is now sorted by batting average.");
                    theDB.sortAve();
                }
                
                if (choice == 8)
                {
                    PrintWriter pw = new PrintWriter("batters.txt");
                    pw.print(theDB.toStringFile());
                    pw.close();
                    
                }
                
                
                if (choice > 8 || choice < 1)
                {
                    System.out.println("Invalid Input: Please Try Again.");
                }
                
                }while(choice != 8);
	
                
	}	
	
	// Note that the main method here is simply creating an Assig3 object.  The
	// rest of the execution is done via the constructor and other instance methods
	// in the Assig3 class.  Note also that this is using a command line argument for
	// the name of the file.  All of our programs so far have had the "String [] args"
	// list in the header -- we are finally using it here to read the file name from the
	// command line.  That name is then passed into the Assig3 constructor.
	public static void main(String [] args) throws IOException
	{
		Assig3 A3 = new Assig3(args[0]);
	}
}