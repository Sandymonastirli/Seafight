// Chrysanthi Monastirli A.M. 1716

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

public class ShipBoard
{
	private static final int SHIPS_NO = 5, BOARD_DIM = 10;	// initializers
	private static final int[] shipLen = {5, 4, 3, 3, 2};  // length of each ship
	Scanner scan;
	private int[][] board;
	private boolean[][] hasBeenHit;
	private boolean lastStrikeSank;
	private int[] hitsLeft;            // how many hits are left for each ship
	private int shipsLeft;
	
	// constructor
	public ShipBoard() 
	{
		board = new int[BOARD_DIM][BOARD_DIM];
		hasBeenHit = new boolean[BOARD_DIM][BOARD_DIM];
		for (int i = 0; i < BOARD_DIM; i++)
		{
			for (int j = 0; j < BOARD_DIM; j++)
			{
				board[i][j] = 0;
				hasBeenHit[i][j] = false;
			}
		}
		
		hitsLeft = new int[SHIPS_NO];
		for (int i = 0; i < SHIPS_NO; i++)
		{
			hitsLeft[i] = shipLen[i];
		}
		
		lastStrikeSank = false;
		shipsLeft = SHIPS_NO;
	}
	
	public boolean lastStrikeSankShip() 
	{
		return lastStrikeSank;
	}
	
	public boolean allShipsSank() 
	{
		return (shipsLeft == 0);
	}
	
	public boolean getStrike(Vector2 point) 
	{
		lastStrikeSank = false;
		if (hasBeenHit[point.getX()][point.getY()]) 
		{
			System.out.println(point + ": Already hit. Pay attention next time!");
			return false;
		}
		hasBeenHit[point.getX()][point.getY()] = true;
		if (board[point.getX()][point.getY()] == 0)
		{
			System.out.println(point + ": Miss!"); 
			return false;
		}
		if (--hitsLeft[board[point.getX()][point.getY()]-1] == 0) 
		{
			shipsLeft--; 
			lastStrikeSank = true;
		}
		System.out.println(point + ": Hit!");
		return true;
	}
	
	public void enterAllShipsRandomly()
	{
		for (int i = 0; i < SHIPS_NO; i++)
		{
			enterShipRandomly(i+1);
		}
		System.out.println("All "+ SHIPS_NO +" ships has been set:");
		printBoard();
	}
	
	public void enterShipRandomly(int id)
	{
		Random random = new Random();
		int vertOrHor, randX, randY;
		boolean hor;
		while (true) 
		{
			vertOrHor = random.nextInt(2);
			if (vertOrHor == 0)
			{
				hor = false;
				randX = random.nextInt(BOARD_DIM-shipLen[id-1]+1);
				randY = random.nextInt(10);
			}else{
				hor = true;
				randX = random.nextInt(10);
				randY = random.nextInt(BOARD_DIM-shipLen[id-1]+1);
			}
			Vector2 temp = new Vector2(randX, randY);
			if (checkValid(id, false, temp))
			{
				placeShip(id, hor, temp);
				return;
			}
		}
	}
	
	// this was created just for testing
	public void enterAllShipsFromFile(File f)
	{
		try 
		{
			scan = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (int i=0; i<SHIPS_NO;i++) 
		{
			enterShipManually(i+1);
		}
	}
	
	public void enterAllShipsManually() 
	{
		scan = new Scanner(System.in);
		for (int i = 0; i < SHIPS_NO; i++)
		{
			enterShipManually(i+1);
		}
		System.out.println("All " + SHIPS_NO + " ships has been set:");
		printBoard();
	}
	
	private void enterShipManually(int id) 
	{
		boolean horizontal;
		Vector2 point;
		while (true) 
		{
			horizontal = readDirection(id);
			point = readStartingPoint(id);
			if (checkValid(id, horizontal, point)) break;
			else System.out.println("Ship " + id + ": Wrong position. Try again!");
		}
		placeShip(id, horizontal, point);
	}
	
	public void printBoard() 
	{
		for (int i = 0; i < BOARD_DIM; i++) 
		{
			for (int j = 0; j < BOARD_DIM; j++)
			{
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	private void placeShip(int id, boolean hor, Vector2 point)
	{
		int len = shipLen[id-1];
		if (hor) 
		{
			for (int i = 0; i < len; i++)
			{
				board[point.getX()][point.getY()+i]= id;
			}
		} else {
			for (int i = 0; i < len; i++)
			{
				board[point.getX()+i][point.getY()] = id;
			}
		}
	}
	
	private boolean checkValid (int id, boolean hor, Vector2 point)
	{
		int len = shipLen[id-1];
		if (point.getX() < 0 || (!hor) && point.getX() + len>BOARD_DIM || point.getX() >= BOARD_DIM) return false;
		if (point.getY() < 0 || hor && point.getY() + len>BOARD_DIM || point.getY() >= BOARD_DIM) return false;
		if (hor)
		{
			for (int i = 0; i < len; i++) 
			{
				if (board[point.getX()][point.getY()+i] != 0) return false;
			}
		} else {
			for (int i = 0; i < len; i++)
			{
				if (board[point.getX()+i][point.getY()] != 0) return false;
			}
		}
		return true;
	}
	
	private boolean readDirection(int id) {
		boolean horizontal;
		String temp;
		System.out.println("Give direction of the ship "+ id + ": [ h | v ]");
		do {
			temp = scan.nextLine();
			if (temp.equals("h") || temp.equals("H")) horizontal = true;
			else horizontal = false;
		} while (!(temp.equals("v") || temp.equals("h") || temp.equals("V") || temp.equals("H")));
		if (horizontal) System.out.println("Direction of Ship " + id + " has been set to Horizontal.");
		else System.out.println("Direction of Ship "+ id + " has been set to Vertical.");
		return horizontal;
	}
	
	private Vector2 readStartingPoint(int id) 
	{
		Vector2 point;
		String[] temp2;
		
		System.out.println("Give the coordinates in which Ship " + id + " starts: [ x,y ]");
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
			break;
		}
		System.out.println("Ship "+ id + " starting point has been set to "+point+".");
		return point;
	}

	public static void main(String[] args)
	{
		ShipBoard s = new ShipBoard();
		
		// automatically enter the Ship positionings reading the file ShipBoardInput instead of reading from keyboard
		File file = new File("shipBoardInput");
		s.enterAllShipsFromFile(file);
		
		// make a random strike (that actually hits) ("hit" is printed)
		s.getStrike(new Vector2(1,1));
		
		// second one in the same point shouldn't print the "hit" label
		s.getStrike(new Vector2(1,1));
		
		// strike more random points
		s.getStrike(new Vector2(2,2));
		s.getStrike(new Vector2(3,3));
		s.getStrike(new Vector2(4,5));
		s.getStrike(new Vector2(5,5));
		
		if (s.allShipsSank()) System.out.println("1: all ships sank!"); // nope
		
		// let's sink a ship..
		s.getStrike(new Vector2(6,5));
		s.getStrike(new Vector2(7,5));
		s.getStrike(new Vector2(8,5));
		
		if (s.lastStrikeSankShip()) System.out.println("1: last strike sank a ship!"); // nope
		
		s.getStrike(new Vector2(9,5)); // that sinks ship with id 1
		
		if (s.lastStrikeSankShip()) System.out.println("2: last strike sank a ship!"); // should print the label
		
		//now let's sink all the rest of ships!!
		s.getStrike(new Vector2(6,6));
		s.getStrike(new Vector2(6,7));
		s.getStrike(new Vector2(6,8));
		s.getStrike(new Vector2(6,9)); // ship no 2 goes boom
		s.getStrike(new Vector2(0,0));
		s.getStrike(new Vector2(0,1));
		s.getStrike(new Vector2(0,2)); // goodbye ship no 4
		s.getStrike(new Vector2(1,1));
		s.getStrike(new Vector2(2,1));
		s.getStrike(new Vector2(3,1)); // ship no 3 no more
		
		if (s.allShipsSank()) System.out.println("2: all ships have sunk!"); // nope, one is up
		
		s.getStrike(new Vector2(2,3));
		s.getStrike(new Vector2(3,3)); // last one gone
		
		if (s.allShipsSank()) System.out.println("3: all ships have sunk!"); // should print the label
		
		// looks good!
		
		
		
		
		// part 2:
		System.out.println("Part2!");
		
		// make a new board and place boats randomly.
		ShipBoard s2 = new ShipBoard();
		s2.enterAllShipsRandomly();
		
		
			ExplorationStrategy e = new ExplorationStrategy(new StrikeBoard());
			e.initialize(new Vector2(1,1));
	}

}


