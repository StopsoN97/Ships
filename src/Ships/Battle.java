package Ships;

import static Ships.Ships.grid;
import static Ships.Ships.missedGuesses;
import static Ships.Ships.numCols;
import static Ships.Ships.numRows;
import java.util.Scanner;

public class Battle {
    
    public static Map map = new Map();
    
    public void Battle(){
        playerTurn();
        computerTurn();

        map.printOceanMap();

        System.out.println();
        System.out.println("Twoje statki: " + Ships.playerShips + " | statki komputera: " + Ships.computerShips);
        System.out.println();
    }
    
    
    public static void playerTurn(){
        System.out.println("\nTwoj ruch");
        int x = -1, y = -1;
        do {
            Scanner input = new Scanner(System.in);
            System.out.print("Wprowadz koordynat X: ");
            x = input.nextInt();
            System.out.print("Wprowadz koordynat Y ");
            y = input.nextInt();

            if ((x >= 0 && x < numRows) && (y >= 0 && y < numCols)) //valid guess
            {
                if (grid[x][y] == "x") //if computer ship is already there; computer loses ship
                {
                    System.out.println("Zatopiles statek!");
                    grid[x][y] = "!"; //Hit mark
                    --Ships.computerShips;
                }
                else if (grid[x][y] == "@") {
                    System.out.println("Zatopiles swoj wlasny statek :(");
                    grid[x][y] = "x";
                    --Ships.playerShips;
                    ++Ships.computerShips;
                }
                else if (grid[x][y] == " ") {
                    System.out.println("Pudło!");
                    grid[x][y] = "-";
                }
            }
            else if ((x < 0 || x >= numRows) || (y < 0 || y >= numCols))  //invalid guess
                System.out.println("You can't place ships outside the " + numRows + " by " + numCols + " grid");
        }while((x < 0 || x >= numRows) || (y < 0 || y >= numCols));  //keep re-prompting till valid guess
    }
    
    
     public static void computerTurn(){
        System.out.println("\nRuch komputera");
        //Guess co-ordinates
        int x = -1, y = -1;
        do {
            x = (int)(Math.random() * 10);
            y = (int)(Math.random() * 10);

            if ((x >= 0 && x < numRows) && (y >= 0 && y < numCols)) //valid guess
            {
                if (grid[x][y] == "@") //if player ship is already there; player loses ship
                {
                    System.out.println("Komputer zatopił jeden z Twoich statków !");
                    grid[x][y] = "x";
                    --Ships.playerShips;
                    ++Ships.computerShips;
                }
                else if (grid[x][y] == "x") {
                    System.out.println("Komputer zatopił jeden ze swoich statków !");
                    grid[x][y] = "!";
                }
                else if (grid[x][y] == " ") {
                    System.out.println("Komputer spudłował");
                    //Saving missed guesses for computer
                    if(missedGuesses[x][y] != 1)
                        missedGuesses[x][y] = 1;
                }
            }
        }while((x < 0 || x >= numRows) || (y < 0 || y >= numCols));  //keep re-prompting till valid guess
    }
    
    
    public void gameOver(){
        System.out.println("Twoje statki: " + Ships.playerShips + " | statki komputera: " + Ships.computerShips);
        if(Ships.playerShips > 0 && Ships.computerShips <= 0)
            System.out.println("Brawo! Wygrales bitwe :)");
        else
            System.out.println("Przegrałeś bitwe !");
        System.out.println();
    }
    
}
