import java.util.Scanner;
public class dieGame
{
    private int replay = 0;
    private int moneyWager = 100;
    private boolean gameStatus = true;
    private int wager = 0;
    private int total = 0;

    public void printInstructions()
    {
        // Instructions and rules
        System.out.println("Welcome to Craps Jr!");
        System.out.print("Here are the rules: You place a bet and hope that the die rolls a 7 or 11. ");
        System.out.print("if the die rolls a 2,3, or 12, then you lose the wagered amount...TIMES TWO!");
        System.out.print(" If you do end up winning, the amount you wagered is doubled!");
        System.out.println(" If it lands on any other number, then you lose your amount "
                + "wagered and are prompted to continue playing, goodluck!");
    }

    public boolean checkWin(int moneyWager, int wager)
    {
        int roll1, roll2;
        Die d1 = new Die();
        roll1 = d1.roll();
        Die d2 = new Die();
        roll2 = d2.roll();
        total = roll1 + roll2;
        System.out.println("You have rolled a " + total);
        if (moneyWager < 1)
        {
            System.out.println("You have run out of money! Sorry!");
            gameStatus = false;
        }
        if (total == 7 || total == 11)
        {
            // If it is a winning roll than it doubles the amount wagered
            moneyWager = moneyWager + (moneyWager * 2);
            System.out.println("Current balance: " + moneyWager);
            // Prompts the user if they want to continue playing
            System.out.println("You have won! Would you like to continue? (yes/no)");
            Scanner contW = new Scanner(System.in);
            String contWin = contW.nextLine();
            if (contWin.equals("no"))
            {
                System.out.println("Rounds played: " + replay + " Final Balance: " + moneyWager);
                gameStatus = false;
            }
        }
        else if (total == 2 || total == 3 || total == 12)
        {
            // If the user rolls a losing number, the amount wagered * 2 is lost
            moneyWager -= (wager * 2);
            System.out.println("Current balance: " + moneyWager);
            System.out.println("Oh no! you've landed on a losing number, better luck next time! Continue? (yes/no)");
            Scanner contL = new Scanner(System.in);
            // Prompts the user if they want to continue playing
            String contLose = contL.nextLine();
            if (contLose.equals("no"))
            {
                System.out.println("Rounds played: " + replay + " Final Balance: " + moneyWager);
                gameStatus =  false;
            }
        }
        else
        {
            // If the dice rolls a neutral number, the amount wagered is lost
            moneyWager -= wager;
            System.out.println("Current balance: " + moneyWager);
        }
        // If the player loses all their money, the game ends
        if (moneyWager < 1)
        {
            System.out.println("You have run out of money! Sorry!");
            gameStatus = false;
        }
        return true;
    }

    public void playGame()
    {
        Scanner inputScanner = new Scanner(System.in);

        while (gameStatus == true)
        {
            // Variables for each round
            int roll1, roll2, total;

            // Displays stats at the beginning of each round
            System.out.println("Current amount available to wager: " + moneyWager);
            System.out.print("Enter your wager: ");

            // Takes user input and reprompts user if they try to bet more than they have
            wager = inputScanner.nextInt();
            while (wager > moneyWager)
            {
                System.out.print("Your wager can't be greater than the amount of money you have! Enter your wager: ");
                wager = inputScanner.nextInt();
            }

            // Rolls dice and checks if it is a winning or losing roll
            total = 0;
            gameStatus = checkWin(moneyWager, wager);
            replay += 1;
        }
    }




    public static void main(String[] args)
    {
        dieGame game = new dieGame();
        game.printInstructions();
        game.playGame();
    }
}