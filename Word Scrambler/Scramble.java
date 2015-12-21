import java.util.*;
import java.io.*;
import java.lang.*;

public class Scramble
{
    
    String realword;
    StringBuilder scrambledword;
    File F;
    Scanner fileReader;
    Random R = new Random();
    boolean scrambled = true;

    
    public Scramble(String file)
    {
        F = new File(file);
        
        try
        {
        fileReader = new Scanner(F);
        }
        
        catch (FileNotFoundException e)
        {}
        
    }
    
    public String getRealWord()
            {
               
                if(fileReader.hasNextLine() && scrambled == true)	// Checks to see that getScrambledWord has been called and that there is another line in file
                {
                realword = fileReader.next();						// Reads in the next unscrambled word
                scrambled = false;
                return realword;									// Returns the unscrambled word when called
                }
                
                if(scrambled == false)								// If this method is called again without calling getScrambledWord, the same unscrambled word will be returned
                return realword;
                
                else
                return null;										// If this method is called after getScrambledWord has been called and there is no words left in the file, null will be returned               
            }
    
    public String getScrambledWord()
            {
                
                int n = realword.length();
                int r;
                char temp;
                
                if (scrambled == false)			// Checks to see if getScrambledWord has already been called
                {
                    
                scrambled = true;
                scrambledword = new StringBuilder(realword);
                
                for(int i = (n-1); i > 0; i--)
                {
                    r = (R.nextInt(i+1));		// Sets r to a random integer that is >= 0 AND < (i+1) 
                    
                    temp = scrambledword.charAt(r);
                    scrambledword.setCharAt(r, scrambledword.charAt(i));
                    scrambledword.setCharAt(i, temp);
                }
                
                }
                
                return scrambledword.toString();
                
            }
}