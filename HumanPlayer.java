// Chrysanthi Monastirli 

import java.util.Scanner;

public class HumanPlayer
 {
	private static final int BOARD_DIM = 10;
	private String name;
	private ShipBoard shipBoard;
	private StrikeBoard strikeBoard;
	Scanner scan;
	
	public HumanPlayer(String name)
	{
		this.name = name;
		shipBoard = new ShipBoard();
		strikeBoard = new StrikeBoard();
		System.out.println(name + " place your Ships!");
		shipBoard.enterAllShipsManually();
		System.out.println();
		scan = new Scanner(System.in);
	}
	
	public Vector2 nextStrike()
	{
		Vector2 point;
		String[] temp2;
		
		System.out.println("Give the coordinates of the point you want to strike: [ x,y ]");
		while (true)
		{
			temp2 = scan.nextLine().split(",");
			if (temp2.length != 2) continue;
			try 
			{
				point = new Vector2(Integer.parseInt(temp2[0]), Integer.parseInt(temp2[1]));
			} catch(Exception e) {
				continue;
			}
			if (point.getX() < 0 || point.getX() >= BOARD_DIM || point.getY() < 0 || point.getY() >= BOARD_DIM)
			{
				System.out.println("Invalid point. Try again!");
				continue;
			}
			break;
		}
		return point;
	}
	
	public void update(Vector2 point, boolean hit) 
	{
		strikeBoard.addStrike(point, hit);
	}
	
	public boolean getStrike(Vector2 point)
	{
		return shipBoard.getStrike(point);
	}
	
	public boolean allShipsSank() 
	{
		return shipBoard.allShipsSank();
	}
	
	public boolean lastStrikeSankShip() 
	{
		return shipBoard.lastStrikeSankShip();
	}
	
	public String toString() 
	{
		return name;
	}
	
	public void printShipBoard() 
	{
		System.out.println("ShipBoard:");
		shipBoard.printBoard();
	}
	
	public void printStrikeBoard() 
	{
		System.out.println("StrikeBoard:");
		strikeBoard.printBoard();
	}
	
}
