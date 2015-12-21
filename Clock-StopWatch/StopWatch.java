

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.util.Date;

public class StopWatch extends JPanel
{
    
    private int size,prefWid,prefHt,D,halfD;
    static int tenths = 0 ,sec1 = 0, sec2 = 0, min1 = 0, min2 = 0, hour = 0;
    private int timingcount = 0, lapcount = 0;
    private boolean laps = false;
    private boolean stopped, initialize = true;
    private Date Date;
    private Dimension panelSize;
    private JPanel buttonPanel;
    private JButton start, stop, reset, lap;
    private buttonListener bListener;
    private clockListener cListener;
    private Timer T;
    private Digits tenthDigit, secDigit1, secDigit2, minDigit1, minDigit2,hourDigit;
    private Colons tenthColon, secColon, minColon;
    private PrintWriter P;
    private File F;
    
    public StopWatch(int s, String f)
    {
        size = s;
        D = (size/2);
        halfD = (D/2);
        
        prefWid = (8*size + 5*D);
        prefHt = (2*size + 110);
        
        panelSize = new Dimension(prefWid,prefHt);
        this.setPreferredSize(panelSize);
        
        Date = new Date();
        F = new File(f);
        
        try
        {
        P = new PrintWriter(new FileWriter(F));
        P.println("StopWatch Initialized on " + Date.toString());
        P.flush();
        }
        
        catch(FileNotFoundException ex)
        {}
        catch(IOException exx)
        {}
        
        tenthDigit = new Digits((size*8+D),55,size);
        tenthColon = new Colons((size*8+halfD),55,size);
        secDigit1 = new Digits((size*5+D)+(size+D),55,size);
        secDigit2 = new Digits((size*5+D),55,size);
        secColon = new Colons((size*5+halfD),55,size);
        minDigit1 = new Digits((size*2+D)+(size+D),55,size);
        minDigit2 = new Digits((size*2+D),55,size);
        minColon = new Colons((size*2+halfD),55,size);
        hourDigit = new Digits(size,55,size);
        
        buttonPanel = new JPanel(new GridLayout(1,4));
        bListener = new buttonListener();
        cListener = new clockListener();
        
        start = new JButton("Start Timer");
        stop = new JButton("Stop Timer");
        stop.setEnabled(false);
        reset = new JButton("Reset Timer");
        lap = new JButton("Show Lap");
        
        start.addActionListener(bListener);
        stop.addActionListener(bListener);
        reset.addActionListener(bListener);
        lap.addActionListener(bListener);
        
        buttonPanel.add(start);
        buttonPanel.add(stop);
        buttonPanel.add(reset);
        buttonPanel.add(lap);
        
        this.add(buttonPanel, BorderLayout.NORTH);
        this.setPreferredSize(panelSize);
        
        T = new Timer(100, cListener);

    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        tenthDigit.draw(g2);
        secDigit1.draw(g2);
        secDigit2.draw(g2);
        minDigit1.draw(g2);
        minDigit2.draw(g2);
        hourDigit.draw(g2);
        tenthColon.draw(g2);
        secColon.draw(g2);
        minColon.draw(g2);
        
    }
    
    private class buttonListener implements ActionListener
    {
        
        public void actionPerformed(ActionEvent e)
	{
            JButton bClicked = (JButton) e.getSource();
            
            if(bClicked == start)
            {

                T.start();
                stopped = false;
                start.setEnabled(false);
                stop.setEnabled(true);
                
                if(initialize == true)
                {
                timingcount++;
                P.println("Ready for Timing: " + timingcount);
                P.flush();
                initialize = false;
                }

            }
            
            else if(bClicked == reset)
            {
               tenths = 0;
               sec1 = 0;
               sec2 = 0;
               min1 = 0;
               min2 = 0;
               hour = 0;
                
               tenthDigit.reset();
               secDigit1.reset();
               secDigit2.reset();
               minDigit1.reset();
               minDigit2.reset();
               hourDigit.reset();
               
               timingcount++;
               P.println("Ready for Timing: " + timingcount);
               P.flush();
               
               if(laps == false)
               repaint();
               
               if(stopped == false)
               {
                T.restart();
                
                start.setEnabled(false);
                stop.setEnabled(true);
               }
               
               else if(stopped == true)
               {
                   start.setEnabled(true);
                   stop.setEnabled(false);
               }
               
            }
            
            else if(bClicked == stop)
            {  

               T.stop();
               
               stopped = true;
               start.setEnabled(true);
               stop.setEnabled(false);
            }
            
            else if(bClicked == lap)
            { 
                
                if(laps == false)
                {      
                lap.setText("Show Time");
                laps = true;
                lapcount++;
                
                P.println("Lap " + lapcount + ": " + hour + ":" + min2 + min1 + ":" + sec2 + sec1 + ":" + tenths);
                P.flush();

                }
                
                else if(laps == true)
                { 
                repaint();
                laps = false;
                lap.setText("Show Lap");
                }
                
            }
            

	}
        
    }
    
    
    private class clockListener implements ActionListener
    {
        
        public void actionPerformed(ActionEvent e)
	{
 
            tenthDigit.incrementTenths();
            if(laps == false)
            repaint();
            
            if(tenths == 0)
            {
                secDigit1.incrementSec();
                if(laps == false)
                repaint();

                if(sec1 == 0)
                {
                    secDigit2.incrementSec2();
                    if(laps == false)
                    repaint(); 

                    if(sec2 == 0)
                    {
                        minDigit1.incrementMin();
                        if(laps == false)
                        repaint();

                        if(min1 == 0)
                        {
                            minDigit2.incrementMin2();
                            if(laps == false)
                            repaint(); 

                            if(min1 == 0 && min2 == 0)
                            {
                            hourDigit.incrementHour();
                            if(laps == false)
                            repaint();
                            }
                        
                        }

                    }

                }
            
            }
                
	}
        
    }
    
}

class Digits extends LED
{
    int active;
    
    public Digits(int x, int y, int sz)
    {    
        super(x,y,sz);
        active = 0;
    }
    
    public void draw(Graphics2D g2)
    {
        g2.setColor(Color.blue);
        g2.setStroke(new BasicStroke(5));
        
        if(active == 0)
        {
            g2.draw(segments[0]);
            g2.draw(segments[1]);
            g2.draw(segments[2]);
            g2.draw(segments[3]);
            g2.draw(segments[4]);
            g2.draw(segments[5]);
        }
        
        else if(active == 1)
        {
            g2.draw(segments[1]);
            g2.draw(segments[2]);
        }
        
        else if(active == 2)
        {
            g2.draw(segments[0]);
            g2.draw(segments[1]);
            g2.draw(segments[3]);
            g2.draw(segments[4]);;
            g2.draw(segments[6]);
        }
        
        else if(active == 3)
        {
            g2.draw(segments[0]);
            g2.draw(segments[1]);
            g2.draw(segments[2]);
            g2.draw(segments[3]);
            g2.draw(segments[6]);
        }
        
        else if(active == 4)
        {
            g2.draw(segments[1]);
            g2.draw(segments[2]);
            g2.draw(segments[5]);
            g2.draw(segments[6]);
        }
        
        else if(active == 5)
        {
            g2.draw(segments[0]);
            g2.draw(segments[2]);
            g2.draw(segments[3]);
            g2.draw(segments[5]);
            g2.draw(segments[6]);
        }
        
        else if(active == 6)
        {
            g2.draw(segments[0]);
            g2.draw(segments[2]);
            g2.draw(segments[3]);
            g2.draw(segments[4]);
            g2.draw(segments[5]);
            g2.draw(segments[6]);
        }
        
        else if(active == 7)
        {
            g2.draw(segments[0]);
            g2.draw(segments[1]);
            g2.draw(segments[2]);
        }
        
        else if(active == 8)
        {
            g2.draw(segments[0]);
            g2.draw(segments[1]);
            g2.draw(segments[2]);
            g2.draw(segments[3]);
            g2.draw(segments[4]);
            g2.draw(segments[5]);
            g2.draw(segments[6]);
        }
        
        else if(active == 9)
        {
            g2.draw(segments[0]);
            g2.draw(segments[1]);
            g2.draw(segments[2]);
            g2.draw(segments[5]);
            g2.draw(segments[6]);
        }
        
        g2.setStroke(new BasicStroke());
    }
    
    public void incrementTenths()
    {
        if(active < 9)
            active++;
        else if(active == 9)
            active = 0;
        
        StopWatch.tenths = active;
    }
    
    public void incrementSec()
    {
        if(active < 9)
            active++;
        else if(active == 9)
            active = 0;
        
        StopWatch.sec1 = active;
    }
    
    public void incrementSec2()
    {
        if(active < 5)
            active++;
        else if(active == 5)
            active = 0;
        
        StopWatch.sec2 = active;
    }
    
    public void incrementMin()
    {
        if(active < 9)
            active++;
        else if(active == 9)
            active = 0;
        
        StopWatch.min1 = active;
    }
    
    public void incrementMin2()
    {
        if(active < 5)
            active++;
        else if(active == 5)
            active = 0;
        
        StopWatch.min2 = active;
    }

    public void incrementHour()
    {
        if(active < 9)
            active++;
        else if(active == 0)
            active = 0;
        
        StopWatch.hour = active;
    }
    
    public void reset()
    {
        active = 0;    
    }
}

class Colons extends LED
{
    
    Line2D dot1;
    Line2D dot2;
    
    public Colons(int x, int y, int sz)
    {
       super(x,y,sz);
       
        int startX = X, startY1 = (Y+10), startY2 = ((Y + 2*size)-12);
	int endX = X, endY1 = (Y+12), endY2 = ((Y + 2*size)-10);
        
        dot1 = new Line2D.Double(startX, startY1, endX, endY1);
        dot2 = new Line2D.Double(startX, startY2, endX, endY2);
    }
    
    public void draw(Graphics2D g2)
    {
        g2.setColor(Color.blue);
        g2.setStroke(new BasicStroke(8));
        g2.draw(dot1);
        g2.draw(dot2);
    }
    
}
