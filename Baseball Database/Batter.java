import java.text.*;

public class Batter {
   
    private String FirstName;
    private String LastName;
    private int AtBats;
    private int Hits;
    private int Doubles;
    private int Triples;
    private int HomeRuns;
    private int Singles;
    private StringBuilder lnfn;
    private StringBuilder printout;
    private StringBuilder fileprintout;
    
    public Batter(String fn, String ln)
    {
        FirstName = new String(fn);
        LastName = new String(ln);
    }
    
    public void setBats(int new_atbats)
    {
        AtBats = new_atbats;
    }
    
    public void setHits(int new_hits)
    {
        Hits = new_hits;
    }
    
    public void setDoubles(int new_doubles)
    {
        Doubles = new_doubles;
    }
    
    public void setTriples(int new_triples)
    {
        Triples = new_triples;
    }
    
    public void setHR(int new_homeruns)
    {
        HomeRuns = new_homeruns;
    }
    
    public double getAve()
    {
        double avg = (double)(Hits)/AtBats;
        return(avg);
    }
    
    public String getName()
    {
        lnfn = new StringBuilder(LastName.toLowerCase());
        lnfn.append(",");
        lnfn.append(FirstName.toLowerCase());
        return lnfn.toString();
        
    }
    
    public boolean equals(Batter B)
    {
        
        boolean samebatter = false;
        
        if(lnfn.equals(B.getName()))
            samebatter = true;
        
        return samebatter;
        
    }
    
    public String toString()
    {
        DecimalFormat df = new DecimalFormat("#.###");
        Singles = Hits - (Doubles + Triples + HomeRuns);
                
        printout = new StringBuilder();
        printout.append("\nPlayer: " + FirstName + " " + LastName );
        printout.append("\nAt Bats: " + AtBats);
        printout.append("\nHits: " + Hits);
        printout.append("\n\tSingles: " + Singles);
        printout.append("\n\tDoubles: " + Doubles);
        printout.append("\n\tTriples: " + Triples);
        printout.append("\n\tHR: " + HomeRuns);
        printout.append("\nAverage: " + df.format(getAve()));
        
       return printout.toString();
    }
    
        public String toStringFile()
    {
        fileprintout = new StringBuilder();
        fileprintout.append("\n" + FirstName);
        fileprintout.append("\n" + LastName);
        fileprintout.append("\n" + AtBats);
        fileprintout.append("\n" + Hits);
        fileprintout.append("\n" + Doubles);
        fileprintout.append("\n" +  Triples);
        fileprintout.append("\n" +  HomeRuns);
        
       return fileprintout.toString();
    }
    
    
}