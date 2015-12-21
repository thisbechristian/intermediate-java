import java.util.*;
import java.io.*;

public class Results
{
    File F;
    Scanner fileReader;
    int rounds;
    int wins;
    int loses;
    String output;
    PrintWriter fileOut;
    PrintWriter fileOutReset;
    
    public Results(String file)
    {
        F = new File(file);
        
        try
        {
        fileOutReset = new PrintWriter(new FileWriter(F));	// Resets the results in the 'results.txt' file to all zeros
        fileOutReset.println(0);
        fileOutReset.println(0);
        fileOutReset.println(0);
        fileOutReset.close();
        }
        
        catch(IOException e)
        {}
        
        try
        {
        fileReader = new Scanner(F);						// Initializes each variable through scanning in values from the 'results.txt' file
        
        rounds = fileReader.nextInt();
        wins = fileReader.nextInt();
        loses = fileReader.nextInt();
        
        }
        
        catch (FileNotFoundException e)
        {}
        
        
    }
    
    public void won()
    {
        wins++;	// Mutator that increments wins when called
    }
    
    public void lost()
    {  
        loses++; // Mutator that increments losses when called
    }
    
    public void save()
    {
        rounds = wins + loses; // Sums all of the rounds played
        
        try
        {
            fileOut = new PrintWriter(new FileWriter(F)); // Outputs the results to the same 'results.txt' file
            fileOut.println(rounds);
            fileOut.println(wins);
            fileOut.println(loses);
            fileOut.close();
        }
        
        catch(IOException e)
        {}
        
    }
    
    public String toString()		// Accessor that compiles all of the results in a String when called
    {
     output = ("\nHere are the results: \n\t Rounds Tried: " + rounds + "\n\t Rounds Won: " + wins + "\n\t Rounds Lost: " + loses + "\n\n");
     return(output);
    }
    
}