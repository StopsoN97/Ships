package Ships;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    public static void main(String[] args) throws FileNotFoundException, IOException{
        
        int event,profile = 0;
        boolean isEqualLogin = false, isEqualPass = false;
        Scanner LogIn,Password,readChoice,inLogin,inPassword,read;
        String loggingLogin="",loggingPass="",registrationLogin="",registrationPass="",loginChange,passwordChange;
        PrintWriter save;
        File readFile;
        
        System.out.println("Witamy w asystencie BattleShips! \n\n MENU");
        System.out.println(" 1 - Rejestracja ");
        System.out.println(" 2 - Logowanie ");
        System.out.println(" 3 - Nowa gra ");
        System.out.println(" 4 - Wróć do gry ");
        System.out.println(" 5 - Obejrzyj poprzednie gry ");
        System.out.println(" 6 - Profil uzytkownika ");
        
        
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
            case 2 : //Logowanie
                System.out.println("Logowanie");
                
                //Pobranie danych do logowania
                LogIn = new Scanner(System.in);
                Password = new Scanner(System.in);
                System.out.println("Podaj login i haslo aby sie zalogować : ");
                loggingLogin = LogIn.nextLine();
                loggingPass = LogIn.nextLine();
                
                //Odczytanie danych z pliku logowania
                
                readFile = new File("dane_logowania.txt");
                read = new Scanner(readFile);
                while(read.hasNextLine())
                {
                registrationLogin+=read.nextLine();
                registrationPass+=read.nextLine();
                }
                
                     //TESTY
                
                /*System.out.println("Login logowania : " + loggingLogin);
                System.out.println("Haslo logowania : " + loggingPass);
                System.out.println("Login z rejestracji : " + registrationLogin);     
                System.out.println("Haslo z rejestracji : " + registrationPass);*/
                
                //Porównanie wprowadzonych danych do logowania
                
                
                
                isEqualLogin = loggingLogin.equals(registrationLogin);
                isEqualPass = loggingPass.equals(registrationPass);
                
                //System.out.println(isEqualLogin);
                //System.out.println(isEqualPass);
                
                if(isEqualLogin == true && isEqualPass == true)
                {
                    System.out.println("Zalogowanie przebiegło pomyślnie !");
                    
                }
                else    
                {
                    System.out.println("Niepoprawny login lub hasło ! Wprowadź poprawne dane");
                    
                }
                
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
                
                readChoice = new Scanner(System.in);
                System.out.println("\nWybierz opcję : ");
                profile = readChoice.nextInt();
                
                switch(profile){
                    case 7 : 
                        inLogin = new Scanner(System.in);
                        System.out.println("Podaj login : ");
                        loginChange = inLogin.nextLine();
          
                        inPassword = new Scanner(System.in);
                        System.out.println("Podaj Haslo : ");
                        passwordChange = inPassword.nextLine();
          
                        save = new PrintWriter("dane_logowania.txt");
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
