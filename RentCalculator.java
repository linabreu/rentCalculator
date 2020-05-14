/*
 Lina Breunlin
CIS 1633 AA Java
November 4, 2019
Program calculates rent for a specific user inquiry and also displays a monthly rent schedule
Test Data

Input (rooms, bedrooms)             Output (monthly rent)                    Passed?
0 ,0                                    700                                     Y
2, 5                                    1250                                    Y
3, 4                                    1350                                    Y
6 , NA                                  Error Mess , displays sched             Y
5, purple                               Error Mess , displays sched             Y
-7,NA                                   Error Mess , displays sched             Y
 */

package rentcalculator;
import java.util.Scanner;

public class RentCalculator {

    
   public static void main(String[] args) {
    
    //declare variables
    int numRooms = 0;
    
    int numFloor = 0;
    
    int monthlyRent = 0;

    int [] floorPrices = new int [6];
    
    floorPrices [0] = 700; 
    
    int [] [] rentAmounts = new int [6] [4];

    int row;
    
    int col;
    
    int price = 0;
    
    //declare constants
    final int BASE = 150;
   
    final int FLOOR_JUMP = 50;
   
    final int ROOM_JUMP = 150;
    
     final int MAX_ROWS = 6;
    
    final int MAX_COLS = 4;

   // print header to make it look nice
    System.out.println();
   
    System.out.println("--------------------------------------------------------------------------------------------------------------------------------");

    System.out.println("                                     --| Artichoke Towers Monthly Rent Calculator|--                       ");
   
    System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
    
    Scanner in = new Scanner(System.in);
  
    numRooms = getNumRooms (); //call method to get the desired number of bedrooms
    
    if (numRooms >=0 && numRooms <=3)
        
    {
        numFloor = getNumFloor (); //if numRooms returns valid prompt for the desired floor
        
            if (numFloor >=0 && numFloor <=5) //if both numRooms and numFloor are valid display the rent
            {
              monthlyRent = calculateRent (numRooms, numFloor);  
              System.out.println ();  
              System.out.println("The monthly rent for this apartment is:" + " " + "$" + monthlyRent + ".00");
            }
    }

    /*
        outer loop
        row = LCV
        MAX_ROWS = SV
        initialize row to 0 and compare to MAX_ROWS, increment by 1
    */
      for (row =0; row < MAX_ROWS; row++)
        
    {
      price = BASE; //BASE = 150 bc the base price for 1 additional bedroom is 150 extra
 
      /*
        outer loop
        col = LCV
        MAX_COLS = SV
        initialize col to 0 and compare to MAX_ROWS, increment by 1
      */
        for (col = 0; col < MAX_COLS; col++)
            
        {
          rentAmounts [row][col] = floorPrices [row] + price;
          
           //add 150 for each iteration of the loop to represent the price for an additional bedroom
          price= price + ROOM_JUMP;

        }
        
        if (row < MAX_ROWS - 1)
        {
           // 50 extra for each additional floor
          floorPrices [row + 1] = floorPrices [row] + FLOOR_JUMP;
        }
    }

    //call method to display rent schedule
    displayRentSched (rentAmounts, floorPrices);
    
}


 /**
     get the desired number of bedrooms from the user
     @return number of bedrooms
     */

public static int getNumRooms()
    { 
    //declare variables
    int numBedrooms = 0;
        
    Scanner in = new Scanner (System.in);
    
    System.out.println("Please enter the desired number of bedrooms as 0 for studio, 1 for one bedroom, 2 for two bedroom or 3 for three bedrooms:");
    
     //make sure that user input is valid
    if (in.hasNextInt())
        
        {
          numBedrooms = in.nextInt(); 
          
            if (numBedrooms <0 || numBedrooms >3)
                {
                    System.out.println ("Error. Number of rooms must be between 0 and 3. Please see rent schedule for plans and pricing.");
                }
        }
    else
        {
            System.out.println ("Error. Number of rooms must be a number.Please see rent schedule for plans and pricing"); 
            
            numBedrooms = -1;
 
        }
     return numBedrooms;
    }

/**
 get the desired floor for the user
 @return the floor number
 */
public static int getNumFloor()
    { 
    //declare variables
    int floorNum = 0;
        
    Scanner in = new Scanner (System.in);
    
    System.out.println("Please enter the desired floor as 0 for ground, 1 for first, 2 for second, 3 for third, 4 for fourth or 5 for fifth:");

         //make sure that user input is valid
        if (in.hasNextInt())

            {
              floorNum = in.nextInt(); 

                if (floorNum <0 || floorNum >5)
                    {
                      System.out.println ("Error. Floor number must be between 0 and 5. Please see rent schedule for plans and pricing");  
                    }
            }
        else
        {
          System.out.println ("Error. Floor number must be a number.Please see rent schedule for plans and pricing");
          floorNum = -1;
        }
    return floorNum;
    }
  
    /**
     determine how what floor the user wants to live on
     @param numRooms = user input for desired number of bedrooms
     @param numFloor = user input for desired floor
    @return rentPrice =  the monthly rent based on the user input
     */
   public static int calculateRent (int numRooms, int numFloor) 
   {  
       //declare variables
       int rentPrice = 0;
       
       //declare constants
       final int BASE_PRICE = 700;
       
       final int  ADD_ROOM = 150;
       
       final int ADD_FLOOR = 50;
       
       rentPrice = BASE_PRICE + (numRooms * ADD_ROOM) + (numFloor * ADD_FLOOR);
       
       return rentPrice;   
   }

   /**
     determine how what floor the user wants to live on
     @param amounts = 2D array of rent prices for the different bedrooms
     @param prices =  1D array of prices for the different floors
     */
   public static void displayRentSched (int [][] amounts, int [] prices)
   {
       
       //declare variables
       final int MAX_ROOMS = 3;
       
       String[] floor = {"Floor 0", "Floor 1", "Floor 2", "Floor 3", "Floor 4", "Floor 5"}; // this array holds the floor names
       
       
         // print out the rent schedule header to make it look nice
         System.out.println ();
    
         System.out.println("--------------------------------------------------------------------------------");

         System.out.println("                 --| Artichoke Towers Monthly Rent Schedule|--                       ");
    
         System.out.println();
    
         System.out.println("           Studio         1-Bedroom        2-Bedroom       3-Bedroom");
   
         System.out.println("--------------------------------------------------------------------------------");
         
         
            //loop through rows in 2D array to display results
            for (int row = 0; row < prices.length; row++)
                
                {
                    //display the parallel arry that has the floor names
                    System.out.print (floor[row]);
                    
                    System.out.printf ("%5s", "$");
                    
                    //display the base price for the floor
                    System.out.printf ("%1d", prices [row] );
                    
                    System.out.printf (".00");
                    
                   //loop through columns in 2D array to display prices for different room options
                    for (int col = 0; col < MAX_ROOMS; col ++)
                        
                    {
                       System.out.printf ("%10s", "$"); 
                        
                       System.out.printf ("%1d", amounts[row][col]);
                       
                       System.out.printf (".00");
                    }
                    
                     //add a new line to space things out
                    System.out.println ();
                    
                     //add a new line to space things out
                    System.out.println ();
                }
            
         System.out.println("--------------------------------------------------------------------------------");
         
         System.out.println("Thank you for your interest in Artichoke Towers!");
   }

}