
// CS 0401 BatterDB class
// This class is a simple database of Batter objects.  Note the
// instance variables and methods and read the comments carefully.  You minimally
// must implement the methods shown.  You may also need to add some private methods.
// To get you started I have implemented the constructor and the addBatter method for you.

public class BatterDB
{
	private Batter [] theBatters; 	// Array of Batters to store the objects
	private int num;                // int to store logical size of DB
        private Batter [] temp;

	// Initialize this BatterDB
	public BatterDB(int size)
	{
		theBatters = new Batter[size];
		num = 0;
	}

	// Take already created Batter and add it to the DB.  This is simply putting
	// the new Batter at the end of the array, and incrementing the int to
	// indicate that a new movie has been added.  If no room is left in the 
	// array, resize to double the previous size, then add at the end.  Note that
	// the user never knows that resizing has even occurred, and the resize()
	// method is private so it cannot be called by the user.
	public void addBatter(Batter b)
	{
		if (num == theBatters.length)
			resize(2 * theBatters.length);
		theBatters[num] = b;
		num++;
	}
	
	// Remove and return the Batter that equals() the argument Batter, or
	// return null if the Batter is not found.  You should not leave a gap in
	// the array, so elements after the removed Batter should be shifted over.
	public Batter removeBatter(Batter b)
	{
            StringBuilder lnfn = new StringBuilder(b.getName());
            boolean found = false;
            int i = 0;
            do
            {
                if(theBatters[i].getName().equals(lnfn.toString()))
                {
                    found = true;
                }
            
                else
                i++;
            
            }while(i < num && found == false);
            
            if(found == true)
            {
                temp = new Batter[theBatters.length];
                Batter [] temp2 = theBatters;

                for(int j = 0; j < i ; j++)
                {
                    temp[j] = theBatters[j];
                }

                for(int j = i; j < (num-1); j++)
                {
                    temp[j] = theBatters[j+1];
                }

                num -= 1;
                theBatters = temp;
                
                return temp2[i];
            }
            
            return null;
            
	}
	
	// Return logical size of the DB
	public int numBatters()
	{
            return num;
	}
	
	// Resize the Batter array to that specified by the argument
	private void resize(int newsize)
	{
            temp = new Batter[newsize];
            
            for(int i = 0; i < num; i++)
            {
                temp[i] = theBatters[i];
            }
            
            theBatters = temp;
	}

	// Find and return the Batter in the DB matching the first and last
	// names provided.  Return null if not found.
	public Batter findBatter(String fName, String lName)
	{
            StringBuilder lnfn = new StringBuilder(lName.toLowerCase() + "," + fName.toLowerCase());
            boolean found = false;
            int i = 0;
            do
            {
                if(theBatters[i].getName().equals(lnfn.toString()))
                {
                    found = true;
                    return (theBatters[i]);
                }
            
            
            i++;
            
            }while(i < num && found == false);
            
            return null;
	}
	
	// Sort the DB alphabetically using the getName() method of Batters for
	// comparison
	public void sortName()
	{
            int MaxIndex, index, startIndex;
            Batter MinName;
           
            for(startIndex = 0; startIndex < (num - 1); startIndex++)
            {
                
                MaxIndex = startIndex;
                MinName = theBatters[startIndex];
                
                for(index = (startIndex + 1); index < num; index++)
                {
                    
                    if(theBatters[index].getName().charAt(0) < MinName.getName().charAt(0))
                    {
                        MinName = theBatters[index];
                        MaxIndex = index;
                    }
                    
                    else if(theBatters[index].getName().charAt(0) == MinName.getName().charAt(0))
                    {
                        boolean flag = false;
                        int i = 1;
                        
                        do
                        {
                        
                        if(theBatters[index].getName().charAt(i) < MinName.getName().charAt(i))
                        {
                            MinName = theBatters[index];
                            MaxIndex = index;
                            flag = true;
                        }
                        
                        else if (theBatters[index].getName().charAt(i) > MinName.getName().charAt(i))
                        {
                        	flag = true;
                        }
                        
                        else
                        i++;
                        
                        }while(flag == false);
                        
                    }
                    
                }
                
                theBatters[MaxIndex] = theBatters[startIndex];
                theBatters[startIndex] = MinName;
            }
            
	}
	
	// Sort the DB from high to low using the getAve() method of Batters for
	// comparison
	public void sortAve()
	{
            int MaxIndex, index, startIndex;
            Batter MaxAverage;
           
            for(startIndex = 0; startIndex < (num - 1); startIndex++)
            {
                
                MaxIndex = startIndex;
                MaxAverage = theBatters[startIndex];
                
                for(index = (startIndex + 1); index < num; index++)
                {
                    
                    if(theBatters[index].getAve() > MaxAverage.getAve())
                    {
                        MaxAverage = theBatters[index];
                        MaxIndex = index;
                    }
                    
                }
                
                theBatters[MaxIndex] = theBatters[startIndex];
                theBatters[startIndex] = MaxAverage;
                
            }
            
            
	}

	// Return a formatted string containing all of the Batters' info.  Note
	// that to do this you should call the toString() method for each Batter in
	// the DB.
	public String toString()
	{
            StringBuilder BatterList = new StringBuilder();
            
            for(int i = 0; i < num; i++)
            {
             BatterList.append(theBatters[i].toString());   
            }
            
            return BatterList.toString();
            
	}

	// Similar to the method above, but now we are not formatting the
	// string, so we can write the data to the file.
	public String toStringFile()
	{
            StringBuilder FilePrintOut = new StringBuilder();
            
            FilePrintOut.append(num);
            
            for(int i = 0; i < num; i++)
            {
             FilePrintOut.append(theBatters[i].toStringFile());   
            }
            
            FilePrintOut.append("\n");
            
            return FilePrintOut.toString();
	}

}