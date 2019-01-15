package Ships;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Ships {
    public static int numRows = 10;
    public static int numCols = 10;
    public static int playerShips;
    public static int computerShips;
    public static String[][] grid = new String[numRows][numCols];
    public static int[][] missedGuesses = new int[numRows][numCols];
    public static Map map = new Map();
    public static Battle battle = new Battle();

    public static void main(String[] args) throws FileNotFoundException{
        
        System.out.println("Witamy w asystencie BattleShips! \n\n MENU");
        System.out.println(" 1 - Rejestracja ");
        System.out.println(" 2 - Logowanie ");
        System.out.println(" 3 - Nowa gra ");
        System.out.println(" 4 - Wróć do gry ");
        System.out.println(" 5 - Obejrzyj poprzednie gry ");
        System.out.println(" 6 - Profil uzytkownika ");
        
        int event,profile = 0;
        
        
        Scanner odczytWybor = new Scanner(System.in);
        System.out.println("\nWybierz opcję : ");
        event = odczytWybor.nextInt();
        
        switch(event){
            case 1 : // Rejestracja
                System.out.println("Rejestracja");
                
                Scanner inputLogin = new Scanner(System.in);
                System.out.println("Podaj login : ");
                String login = inputLogin.nextLine();
          
                Scanner inputPassword = new Scanner(System.in);
                System.out.println("Podaj Haslo : ");
                String password = inputPassword.nextLine();
          
                PrintWriter zapis = new PrintWriter("dane_logowania.txt");
                zapis.println(login);
                zapis.println(password);
                zapis.close();
                
                break;
            case 2 : 
                System.out.println("Logowanie");
                break;
            case 3 : 
                System.out.println("Nowa gra");
        
                map.createOceanMap();

                deployPlayerShips();

                deployComputerShips();

                // Battle
                do {
                    battle.Battle();
                }while(Ships.playerShips != 0 && Ships.computerShips != 0);

                battle.gameOver();
                
                break;
            case 4 : 
                System.out.println("Wroc do gry");
                break;
            case 5 : 
                System.out.println("Poprzednie gry");
                break;
            case 6 : 
                System.out.println("Profil uzytkownika");
                
                System.out.println("Zmiana danych logowania - wcisnij 7");
                System.out.println("Statystyki - wcisnij 8");
                
                Scanner readChoice = new Scanner(System.in);
                System.out.println("\nWybierz opcję : ");
                profile = readChoice.nextInt();
                
                switch(profile){
                    case 7 : 
                        Scanner inLogin = new Scanner(System.in);
                        System.out.println("Podaj login : ");
                        String loginChange = inLogin.nextLine();
          
                        Scanner inPassword = new Scanner(System.in);
                        System.out.println("Podaj Haslo : ");
                        String passwordChange = inPassword.nextLine();
          
                        PrintWriter save = new PrintWriter("dane_logowania.txt");
                        save.println(loginChange);
                        save.println(passwordChange);
                        save.close();
                        break;
                    case 8 : 
                        System.out.println("Statystyki : ");
                        break;
                    default:
                        throw new NoSuchMethodError("Nie ma takiej możliwości2");
                       }
                break;
                
            default:
                throw new NoSuchMethodError("Nie ma takiej możliwości");
        }  
      }
   
    public static void deployPlayerShips(){
        Scanner input = new Scanner(System.in);

        System.out.println("\nDeploy your ships:");
        //Deploying five ships for player
        Ships.playerShips = 10;
        for (int i = 1; i <= Ships.playerShips; ) {
            System.out.print("Enter X coordinate for your " + i + " ship: ");
            int x = input.nextInt();
            System.out.print("Enter Y coordinate for your " + i + " ship: ");
            int y = input.nextInt();

            if((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && (grid[x][y] == " "))
            {
                grid[x][y] =   "@";
                i++;
            }
            else if((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && grid[x][y] == "@")
                System.out.println("Nie można ulokować dwóch statków w jednej pozycji");
            else if((x < 0 || x >= numRows) || (y < 0 || y >= numCols))
                System.out.println("You can't place ships outside the " + numRows + " by " + numCols + " grid");
        }
        map.printOceanMap();
    }

    public static void deployComputerShips(){
        System.out.println("\nComputer is deploying ships");
        //Deploying five ships for computer
        Ships.computerShips = 10;
        for (int i = 1; i <= Ships.computerShips; ) {
            int x = (int)(Math.random() * 10);
            int y = (int)(Math.random() * 10);

            if((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && (grid[x][y] == " "))
            {
                grid[x][y] =   "x";
                System.out.println(i + ". ship DEPLOYED");
                i++;
            }
        }
        map.printOceanMap();
    }

    

    

   
}
