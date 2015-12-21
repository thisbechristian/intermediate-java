/*
    Name:               Christian Boni
    Course:             CS 0401, T/R 1PM
    Project:            Assignment 2
    Due Date:           Octoberer 8, 2013
    
    Description:        Write a program to create a word scrambler game using several different classes.
 */

import java.util.*;

public class Assig2 {


    public static void main(String[] args)
    {
        
        String name;
        String rw;
        String sw;
        String uw;
        boolean correct;
        int g;
        String run = "y";
        
        Scanner inScan = new Scanner(System.in);
        
        System.out.println("\nWelcome to The Scrambler!");
        System.out.println("Please enter your name: ");
        name = inScan.next();
        
        System.out.println("\n" + name + ", you have 3 guesses to get the Scramble");
        System.out.println("Goodluck!");
        
        Results R = new Results("results.txt");		// Creates a new Results object, with the results file as an argument
        Scramble S = new Scramble("words.txt");		// Creates a new Scrambler object, with the word file as an argument
        
        do
        {
            
            rw = S.getRealWord();	// Uses the Accessor 'getRealWord' to get the unscrambled word
            
            if(rw != null)			// Checks for special case when 'getRealWord' returns null
            {
                correct = false;
                g = 3;				// Initializes number of guesses to 3

                do
                {

                sw = S.getScrambledWord(); 		// Uses the Accessor 'getScrambledWord' to get the scrambled word

                System.out.println("\nScrambler: " + sw);
                System.out.print("Your Guess: ");
                uw = inScan.next().toLowerCase();		// Reads in users guess for the word

                if(uw.equals(rw))						// If statement to check to see if word entered is correct
                {
                    System.out.println("Great Job " + name + "!  You got it!");
                    R.won();							// Uses the Mutator 'won' to increment the number of wins
                    correct = true;
                }

                else
                {
                correct = false;
                g--;									// Number of guesses is de-incremented
                System.out.println("Sorry, " + name + " That isn't correct");
                System.out.println("You have " + g + " tries remaining");
                }

                }while(g > 0 && correct != true);		// Do-While Loop that runs until the correct answer is given or number of guesses runs out
                
                if(correct == false)
                {
                System.out.println("\nRound over!  Better luck next time " + name);
                System.out.println("The actual word is " + rw);
                R.lost();							// Uses the Mutator 'lost' to increment the number of losses
                }
                
                do
                {
                System.out.println("\nWould you like to play another round (Y/N)?");
                run = inScan.next().toLowerCase();
                }while(!(run.equals("y") || run.equals("n"))); // Do-While Loop that will run until the user answers only a 'y' or an 'n'
            }
            
            else	// Special case where all the words in the text file have been read-in allowing the game to quit itself
            {
                System.out.println("\nSorry you have run out of words! Thanks for playing!");
                run = "n";
            }
            
        }while(run.equals("y")); 			// Do-While Loop that runs the program until the user quits or runs out of words
        
        R.save();							// Uses the Mutator 'save' to calculate the number of rounds played and save the rounds play, won, and lost to a file 
        System.out.print(R.toString());		// Uses the Accessor 'toString' to print out the results to the screen
    }
}