// Chrysanthi Monastirli

public class StrikeBoard 
{
	private static final int BOARD_DIM = 10;
	private int[][] board;
	
	public StrikeBoard() 
	{
		board = new int[BOARD_DIM][BOARD_DIM];
		for (int i = 0; i < BOARD_DIM;i++)
			{
			for (int j = 0; j < BOARD_DIM; j++) 
			{
				board[i][j] = 0;
			}
		}
	}
	
	public void addStrike(Vector2 point, boolean hit) 
	{
		if (hit) board[point.getX()][point.getY()] = 1;
		else board[point.getX()][point.getY()] = -1;
	}
	
	public void printBoard()
	{
		for (int i = 0; i < BOARD_DIM; i++)
		{
			for (int j = 0; j < BOARD_DIM; j++)
			{
				if (board[i][j] != -1) System.out.print(" ");
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public boolean isValidCandidate(Vector2 point)
	{
		if (point.getX() < 0 || point.getX() >= BOARD_DIM || point.getY() < 0 || point.getY() >= BOARD_DIM) return false;
		if (board[point.getX()][point.getY()] != 0) return false;
		return true;
	}	
}
