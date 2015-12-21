/*
    Name:               Christian Boni
    Course:             CS 0401, T/R 1PM
    Project:            Assignment 4
    Due Date:           November 14, 2013
    
    Description:        Write a java graphic interface program that implements the game of tic tac toe.
    					The program should allow players to reset the board and start anew game at any time, undo each move,
    					and alternate between starting players after each game played. It should also calculate the winner,
    					or if there is a tie. 					
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Assig4 {

    int n;
    int n_plus;
    int n_minus;
    int Score [];
    int Score2 [];
    int Move_Count = 0;
    boolean togglePlayer = true;
    boolean toggleLetter = true;
    boolean resetGame = false;
    JFrame theWindow;
    JPanel topPanel;
    JPanel bottomPanel;
    JButton Reset;
    JButton Undo;
    JLabel TextBox;
    JButton Buttons[][];
    JButton Undo_Buttons[];
    ButtonListener[] Listeners;
    ButtonListener[] Listeners2;
    
    public Assig4(int dim)
            {
                n = dim;
                n_plus = (n+1);
                n_minus = (n-1);
                
                theWindow = new JFrame("Tic Tac Toe: v 1.0");
                topPanel = new JPanel();
                bottomPanel = new JPanel();
                
                theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                topPanel.setLayout(new GridLayout(n,n));
                bottomPanel.setLayout(new GridLayout(1,3));

                Reset = new JButton("New Game");
                Reset.addActionListener(new ResetListener());
                Undo = new JButton("Undo Move");
                Undo.addActionListener(new UndoListener());
                TextBox = new JLabel("X goes first");
                
                bottomPanel.add(Reset);
                bottomPanel.add(Undo);
                bottomPanel.add(TextBox);
                
                Buttons = new JButton[n][n];
                Listeners = new ButtonListener[n*n];
                Listeners2 = new ButtonListener[n*n];
                Score = new int[n*n];
                Score2 = new int[n*n];
                Undo_Buttons = new JButton[n*n];
                
                int k = 0;
                
                for(int i = 0; i < n; i++)
                {
                    for(int j = 0; j < n; j++)
                    {
                        Buttons[i][j] = new JButton("");
                        Buttons[i][j].setFont(new Font("TimesRoman", Font.BOLD, 50));
                        topPanel.add(Buttons[i][j]);
                        Buttons[i][j].addActionListener(Listeners[k] = new ButtonListener());
                        Undo_Buttons[k] = new JButton();
                        Listeners2[k] = new ButtonListener();
                        k++;
                    }
                }
                
                theWindow.add(topPanel, BorderLayout.CENTER);
                theWindow.add(bottomPanel, BorderLayout.SOUTH);
                theWindow.setMinimumSize(new Dimension(400,400));
                theWindow.pack();
                Undo.setEnabled(false);
				theWindow.setVisible(true);
            }
    
    private class ButtonListener implements ActionListener
    {
        
        boolean clicked = false;
        
        public void setClickState(boolean newState)
        {
            clicked = newState;
        }
        
        public boolean getClickState()
        {
            return clicked;
        }

        public void actionPerformed(ActionEvent e)
        {
            
            JButton b = (JButton) e.getSource();
            Listeners2[Move_Count] = this;
            Undo.setEnabled(true);
            
            if(clicked == false)
            {
                if (toggleLetter == true)
                {
                    b.setText("X");
                    Undo_Buttons[Move_Count] = b;
                    TextBox.setText("O goes next");
                    clicked = true;
                    toggleLetter = false;
                    Move_Count++;
                }

                else if (toggleLetter == false)
                {
                    b.setText("O");
                    Undo_Buttons[Move_Count] = b;
                    TextBox.setText("X goes next");
                    clicked = true;
                    toggleLetter = true;
                    Move_Count++;
                }
            }
            
            else if(clicked == true)
            {
                b.setText(b.getText());
            }
            
            int k = 0;
            
            for(int i = 0; i < (n); i++)
            {
                for(int j = 0; j < (n); j++)
                {
                    if(Buttons[i][j].getText().equals("X"))
                    {
                        Score[k] = 1;
                    }
                    else if(Buttons[i][j].getText().equals("O"))
                    {
                        Score[k] = 2;       
                    }
                    
                    k++;
                }
            }
            
            int l = 0;
            
            for(int i = 0; i < (n); i++)
            {
                for(int j = 0; j < (n); j++)
                {
                    if(Buttons[j][i].getText().equals("X"))
                    {
                        Score2[l] = 1;
                    }
                    else if(Buttons[j][i].getText().equals("O"))
                    {
                        Score2[l] = 2;       
                    }
                    
                    l++;
                }
            }
            
            int low = 0;
            int high = n;
            int x_count = 0;
            int o_count = 0;
            int x_count2 = 0;
            int o_count2 = 0;
            int x_count3 = 0;
            int o_count3 = 0;
            int x_count4 = 0;
            int o_count4 = 0;
            boolean check_winner1 = false;
            boolean check_winner2 = false;
            
            while(high <= (n*n))
            {
                
                for(int i = low; i < high; i++)
                {
                    if(Score[i] == 1)
                    {
                        x_count++;
                    }

                    else if(Score[i] == 2)
                    {
                        o_count++;
                    }
                }

                for(int i = low; i < high; i++)
                {
                    if(Score2[i] == 1)
                    {
                        x_count2++;
                    }

                    else if(Score2[i] == 2)
                    {
                        o_count2++;
                    }
                }

                    if(x_count == n || x_count2 == n)
                    {
                        TextBox.setText("X is the WINNER!");
                        for(int i = 0; i < (n); i++)
                        {
                            for(int j = 0; j < (n); j++)
                            {
                                Buttons[i][j].setEnabled(false);
                            }
                        }
                        Undo.setEnabled(false);
                        check_winner1 = true;
                        break;
                    }

                    else if(o_count == n || o_count2 == n)
                    {
                        TextBox.setText("O is the WINNER!");
                        for(int i = 0; i < (n); i++)
                        {
                            for(int j = 0; j < (n); j++)
                            {
                                Buttons[i][j].setEnabled(false);
                            }
                        }
                        Undo.setEnabled(false);
                        check_winner1 = true;
                        break;
                    }

                    else
                    {
                    x_count = 0;
                    o_count = 0;
                    x_count2 = 0;
                    o_count2 = 0;
                    low = high;
                    high = high + n;
                    }
            
            }
            
            if(check_winner1 == false)
            {
                for(int i = 0; i < n; i++)
                {
                    if(Score[i*n_plus] == 1)
                    {
                        x_count3++;
                    }

                    else if(Score[i*n_plus] == 2)
                    {
                        o_count3++;
                    }  
                }

                for(int i = 1; i <= n; i++)
                {
                    if(Score[i*n_minus] == 1)
                    {
                        x_count4++;
                    }

                    else if(Score[i*n_minus] == 2)
                    {
                        o_count4++;
                    }  
                }

                if(x_count3 == n || x_count4 == n)
                {
                TextBox.setText("X is the WINNER!");

                for(int i = 0; i < (n); i++)
                {
                    for(int j = 0; j < (n); j++)
                    {
                        Buttons[i][j].setEnabled(false);
                    }
                }
                Undo.setEnabled(false);
                check_winner2 = true;
                }

                else if(o_count3 == n || o_count4 == n)
                {
                TextBox.setText("O is the WINNER!");

                for(int i = 0; i < (n); i++)
                {
                    for(int j = 0; j < (n); j++)
                    {
                        Buttons[i][j].setEnabled(false);
                    }
                }
                Undo.setEnabled(false);
                check_winner2 = true;
                }
            
            }
            
            if(check_winner1 == false && check_winner2 == false)
            {

                if (Move_Count == (n*n))
                {
                    TextBox.setText("STALEMATE!");

                    for(int i = 0; i < (n); i++)
                    {
                        for(int j = 0; j < (n); j++)
                        {
                            Buttons[i][j].setEnabled(false);
                        }
                    } 
                    
                    Undo.setEnabled(false);

                }
            }
            
         }
        
    }
    
        private class ResetListener implements ActionListener
    {
            
        public void actionPerformed(ActionEvent e)
        {
            Move_Count = 0;
            
            int k = 0;
            
            for(int i = 0; i < n; i++)
            {
                for(int j = 0; j < n; j++)
                {
                    Buttons[i][j].setText(" ");
                    Buttons[i][j].setEnabled(true);
                    Listeners[k].setClickState(false);
                    Score[k] = 0;
                    Score2[k] = 0;
                    Listeners2[k] = null;
                    k++;
                }
            }    
            
            if(togglePlayer == true)
            {
                TextBox.setText("O goes first");
                togglePlayer = false;
                toggleLetter = false;
            }
            
            else if(togglePlayer == false)
            {
                TextBox.setText("X goes first");
                togglePlayer = true;
                toggleLetter = true;
            }
            
        }
        
    }
        
        private class UndoListener implements ActionListener
    {
        
        public void actionPerformed(ActionEvent e)
        {
            if(Move_Count > 0)
            {
                Undo_Buttons[Move_Count-1].setText("");
                Listeners2[Move_Count-1].setClickState(false);
                
                for(int i = 0; i < (n*n); i++)
                {
                Score[i] = 0;
                Score2[i] = 0;
                }
                
                if(toggleLetter == true)
                {
                    TextBox.setText("O goes next");
                    toggleLetter = false;
                }
                else if(toggleLetter == false)
                {
                    TextBox.setText("X goes next");
                    toggleLetter = true;
                }

                Move_Count--;
                
                if(Move_Count == 0)
                {
                    Undo.setEnabled(false);
                }
            }
        }
        
    }
    
    
    public static void main(String[] args) 
    {
        int N = Integer.parseInt(args[0]);
        Assig4 TicTacToe = new Assig4(N);
    }
}