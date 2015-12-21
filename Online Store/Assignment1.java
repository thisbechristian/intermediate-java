/*
    Name:               Christian Boni
    Course:             CS 0401, T/R 1PM
    Project:            Assignment 1
    Due Date:           September 24, 2013
    
    Description:        Write a program that represents a small online store which sells a few items.
                        Furthermore, allow several different shipping options and even a speical discount question
                        selected at random to allow for a 10% discount. Put this program in a loop allowing the customer
                        to run it as many times as they want and to quit after each order.
 */

import java.util.*;
import java.io.*;

public class Assignment1 {

    public static void main(String[] args )
    {
        
    Scanner inScan = new Scanner(System.in);
    
    int run = 0;
    
    System.out.println("Welcome to Buy Stuff (or Buy it Not)");
    System.out.println("This IS the store you are looking for...");
   
    while(run != 1 && run != 2)     //while loop to ensure that only a '1' or a '2' is entered  
    {
        System.out.println("Is there a customer in line? (1 = yes) (2 = no)");
        run = inScan.nextInt();
    }
    
        while (run == 1)            //while loop that allows the program to re-run as long as run is equal to 1 
        {
            
            System.out.println("\nWelcome New Customer!");
            
            int choice;
            int shipping;
            int a = 0;
            int m = 0;
            int s = 0;
            int temp;
            
            do                      // do while loop that allows the user to update the number of items until they are satisfied
            {                       
                System.out.println("\nPlease update your order:");
                System.out.println("\t1) Yoda Action Figure: $10.00 each");
                System.out.println("\t2) Star Wars Movie Blue Ray: $15.00 each");
                System.out.println("\t3) Death Star Lego Set: $350.00 each");
                System.out.println("Or any other number to checkout");
                choice = inScan.nextInt();

                if (choice == 1)
                {
                    System.out.println("\nCurrent order: " + a + " Yoda Action Figure at $10.00 each ");
                    System.out.println("Please enter your new order (negative number to leave unchanged) ");
                    temp = inScan.nextInt();

                    if (temp >= 0)               // only assigns the new item count if the temporary value is >= 0 
                    {                            // therefore if a negative value is entered, item count remains unchanged
                        a = temp;
                    }

                    System.out.println("Order updated to " + a + " Yoda Action Figures");

                }

                else if (choice == 2)
                {
                    System.out.println("\nCurrent order: " + m + " Star Wars Blue Rays at $15.00 each ");
                    System.out.println("Please enter your new order (negative number to leave unchanged) ");
                    temp = inScan.nextInt();

                    if (temp >= 0)
                    {
                        m = temp;
                    }

                    System.out.println("Order updated to " + m + " Star Wars Blue Rays");
                }

                else if (choice == 3)
                {
                    System.out.println("\nCurrent order: " + s + " Death Star Lego Set at $350.00 each ");
                    System.out.println("Please enter your new order (negative number to leave unchanged) ");
                    temp = inScan.nextInt();

                    if (temp >= 0)
                    {
                        s = temp;
                    }

                    System.out.println("Order updated to " + s + " Death Star Sets");
                }

            }while(choice >= 1 && choice <= 3);
            
            int ques;
            int ans;
            int n;
            int discount = 0;
            String question = "";
            String answer = "";
            String uanswer = "";
            
            try
            {
                Scanner fileScanner = new Scanner(new BufferedReader(new FileReader("qanda.txt"))); // New Scanner which reads in the text file with the discount questions and answers
                n = fileScanner.nextInt();          // Reads in "n" the number of questions and answers
                                                    
                ques = (int)(Math.random()*n + 2);  // Randomly selects an integer number >= 2 and < (n+2)
                boolean even = (ques%2 == 0);       // Boolean statement to see if ques is even or odd
                
                if(even == true)                    // Questions are only on EVEN lines in the text file, and Answers on ODD lines
                {                                   // If ques is even then the answer will be on the following line
                    ans = ques + 1;
                }
                else
                {                                   // If ques is odd then that value becomes the answer 
                    ans = ques;                     // Therefore, the questions is on the previous line
                    ques = ques - 1;
                }
                
                for(int j = 1; j < ques; j++)       // For statment that will skip every line preceding the line with the question
                {
                    fileScanner.nextLine();
                }
                
                question = fileScanner.nextLine();  // Scanner reads in the line with the question and then the line with the answer
                answer = fileScanner.nextLine();
                
                System.out.println("\nAnswer the Star Wars Trivia Question to receive a 10% discounton your order (each answer is one word only): ");
                System.out.println(question);
                uanswer = inScan.next().toLowerCase();
                
                if (uanswer.equals(answer))
                {
                    discount = 1;
                }
                
            }
            
            catch (FileNotFoundException e)
            {

            }

            if (discount == 1)
            {
                System.out.println("You are correct! You get a 10% discount!!");
            }
            
            else
            {
                System.out.println("Sorry, You are incorrect... You DO NOT get a 10% discount..");
            }
                  
            do
            {
               System.out.println("\nPlease choose your shipping option: ");
               System.out.println("\t1) Regular shipping [$5.00 per $50.00 of merchandise (rounded up)]");
               System.out.println("\t2) Express shipping [$10.00 per $50.00 of merchandise (rounded up)]");
               System.out.println("\t3) Super saver shipping [free]");
               shipping = inScan.nextInt();
               
            }while(shipping < 1 || shipping > 3);       //do while loop to ensure that only a '1' or a '2' or '3' is entered
            
            double atotal = 0;
            double mtotal = 0;
            double stotal = 0;
            double subtotal = 0;
            double shippingvalue = 0;
            double discountvalue;
            int creditcard;
            
            atotal = (a*10.00);
            mtotal = (m*15.00);
            stotal = (s*350.00);
            subtotal = (atotal + mtotal + stotal);
            
            System.out.println("\nHere are your order details:");
            
            if (a > 0)
            {
                System.out.printf("\n\t%d Yodas at $10.00 each: \t\t\t\t$%.2f",a,atotal);
            }
            
            if (m > 0)
            {
                System.out.printf("\n\t%d Star Wars BlueRays at $15.00 each: \t\t\t$%.2f",m,mtotal);
            }
            
            if (s > 0)
            {
                System.out.printf("\n\t%d Death Star Lego Sets at $350.00 each: \t\t$%.2f",s,stotal);
            }
            
            System.out.printf("\n\t\t\t\t\t\t\t\t--------");
            System.out.printf("\n\tSubtotal: \t\t\t\t\t\t$%.2f",subtotal);
            
            if (discount == 1)
            {
                discountvalue = (subtotal*0.10);
                subtotal -= discountvalue;
                System.out.printf("\n\t10%% discount: \t\t\t\t\t\t($%.2f)",discountvalue);
            }
            
            int flag = 1;
            int i = 1;
            
            if(subtotal != 0)
            {
                switch (shipping)
                {
                    case 1:
                    {
                        shippingvalue = 5.0;

                        while (flag == 1)                // while statement that will perform this incrementing process stated below
                        {                                
                            if(subtotal > 50*i)          // if statement that will increment the shipping total by $5
                            {                            // as the subtotal exceeds each extra $50 ordered
                            i++;
                            shippingvalue = 5.0*i;
                            }

                            else
                            {
                               flag = 0;     
                            }

                        }
                        System.out.printf("\n\tRegular Shipping: \t\t\t\t\t$%.2f",shippingvalue);
                        break;
                    }

                    case 2:
                    {
                        shippingvalue = 10.0;

                        while (flag == 1)               
                        {
                            if(subtotal > 50*i)
                            {
                            i++;
                            shippingvalue = 10.0*i;
                            }

                            else
                            {
                               flag = 0;     
                            }
                        }

                        System.out.printf("\n\tExpress Shipping: \t\t\t\t\t$%.2f",shippingvalue);
                        break;
                    }

                    case 3:
                    {
                        shippingvalue = 0;
                        System.out.printf("\n\tSuper Saver Shipping: \t\t\t\t\t$%.2f",shippingvalue);
                        break;
                    }

                }
            }
            
            else
            {
                shippingvalue = 0;              // Sets shipping to 0 if subtotal = 0
            }
             
            subtotal += shippingvalue;          // Calulates final total
            
            System.out.printf("\n\t\t\t\t\t\t\t\t--------");
            System.out.printf("\n\tTotal: \t\t\t\t\t\t\t$%.2f",subtotal);
            

            System.out.println("\n\nPlease enter your credit card number: ");
            creditcard = inScan.nextInt();
            
            System.out.println("\nThank you for your order! Please come again!");
            
            System.out.println("\nWelcome to Buy Stuff (or Buy it Not)");
            System.out.println("This IS the store you are looking for...");
            
            run = 0;
            
            while(run != 1 && run != 2)
            {
                System.out.println("Is there a customer in line? (1 = yes) (2 = no)");
                run = inScan.nextInt();
            }
        }
        
    }
}