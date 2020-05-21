// Chrysanthi Monastirli

public class ComputerPlayer
{
	private String name;
	private ShipBoard shipBoard;
	private StrikeBoard strikeBoard;
	private ComputerStrategies computerStrategy;
	
	public ComputerPlayer()
	{
		name = "HAL";
		shipBoard = new ShipBoard();
		strikeBoard = new StrikeBoard();
		computerStrategy = new ComputerStrategies(strikeBoard);
		shipBoard.enterAllShipsRandomly();
	}
	
	public Vector2 nextStrike() 
	{
		return computerStrategy.nextStrike();
	}
	
	public void update(Vector2 point, boolean hit) 
	{
		strikeBoard.addStrike(point, hit);
		computerStrategy.update(point, hit);
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
	
	public void completeExploration()
	{
		computerStrategy.completeExploration();
	}
}
