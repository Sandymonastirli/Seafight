// Chrysanthi Monastirli 
import java.util.Scanner;

public class BattleShip
{

	private static Scanner scan;

	public static void main(String[] args)
	{
		String temp;
		scan = new Scanner(System.in);
		System.out.println("Choose type of game between vsHuman or vsComputer: [ c | h ]");
		do 
		{
			temp = scan.nextLine();
		} while (!(temp.equals("c") || temp.equals("C") || temp.equals("h") || temp.equals("H")));
		
		if (temp.equals("c") || temp.equals("C"))
		{
			humanVsComputer();
		}
		else humanVsHuman();
	}
	
	private static void humanVsComputer() 
	{
		HumanPlayer a = new HumanPlayer("Sandy");
		ComputerPlayer b = new ComputerPlayer();
		Vector2 strike;
		while (true)
		{
			
			// player a turn
			System.out.println(a+" it's your turn!");
			strike = a.nextStrike();
			a.update(strike, b.getStrike(strike));
			a.printStrikeBoard();
			if (b.allShipsSank()) {System.out.println(a+" wins!!"); break;}
			else if (b.lastStrikeSankShip()) System.out.println("Boom! A ship is dead!");
			System.out.println();

			
			// computer turn
			System.out.println(b+" it's your turn!");
			strike = b.nextStrike();
			b.update(strike, a.getStrike(strike));
			b.printStrikeBoard();
			if (a.allShipsSank()) {System.out.println(b+" wins!!"); break;}
			else if (a.lastStrikeSankShip()) {System.out.println("Boom! A ship is dead!"); b.completeExploration();}
			System.out.println();
		}
		
	}

	private static void humanVsHuman()
	{
		String p1 = "Sandy", p2 = "Mary";
		HumanPlayer a, b;
		a = new HumanPlayer(p1);
		b = new HumanPlayer(p2);
		Vector2 strike;
		while (true)
		{		
			// player a turn
			System.out.println(a+" it's your turn!");
			strike = a.nextStrike();
			a.update(strike, b.getStrike(strike));
			a.printStrikeBoard();
			if (b.allShipsSank()) {System.out.println(a+" wins!!"); break;}
			else if (b.lastStrikeSankShip()) System.out.println("Boom! A ship is dead!");
			System.out.println();
			
			// player b turn
			System.out.println(b+" it's your turn!");
			strike = b.nextStrike();
			b.update(strike, a.getStrike(strike));
			b.printStrikeBoard();
			if (a.allShipsSank()) {System.out.println(b+" wins!!"); break;}
			else if (a.lastStrikeSankShip()) System.out.println("Boom! A ship is dead!");
			System.out.println();
		}
		System.out.println("Game Over!");
	}

}
