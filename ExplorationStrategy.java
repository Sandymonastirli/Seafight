// Chrysanthi Monastirli

import java.util.Stack;

public class ExplorationStrategy 
{
	private StrikeBoard strikeBoard;
	private Stack<Vector2> targets;
	private Vector2 startingPoint;

	public ExplorationStrategy(StrikeBoard sb)
	{
		strikeBoard = sb;
		targets = new Stack<Vector2>();
		startingPoint = new Vector2();
	}
	
	public void initialize(Vector2 point) 
	{
		while (!targets.empty())
		{
			targets.pop();
		}
		startingPoint.setX(point.getX());
		startingPoint.setY(point.getY());
		if (strikeBoard.isValidCandidate(new Vector2(point.getX()+1, point.getY()))) targets.push(new Vector2(point.getX()+1, point.getY()));
		if (strikeBoard.isValidCandidate(new Vector2(point.getX()-1, point.getY()))) targets.push(new Vector2(point.getX()-1, point.getY()));
		if (strikeBoard.isValidCandidate(new Vector2(point.getX(), point.getY()-1))) targets.push(new Vector2(point.getX(), point.getY()-1));
		if (strikeBoard.isValidCandidate(new Vector2(point.getX(), point.getY()+1))) targets.push(new Vector2(point.getX(), point.getY()+1));
	}
	
	public Vector2 nextStrike() 
	{
		return targets.pop();
	}
	
	public boolean hasNextCandidate() 
	{
		return (!targets.empty());
	}
	
	public void update(Vector2 point)
	{
		if (point.getX() == startingPoint.getX()) 
		{
			// if point is on the left side of starting, push next left point.
			if (point.getY()<startingPoint.getY()) 
			{
				if (strikeBoard.isValidCandidate(new Vector2(point.getX(), point.getY()-1))) targets.push(new Vector2(point.getX(), point.getY()-1));
			}
			// if point is on the right side of starting, push next right point.
			else {
				if (strikeBoard.isValidCandidate(new Vector2(point.getX(), point.getY()+1)))
					targets.push(new Vector2(point.getX(), point.getY()+1));
			}
		}
		else {
			// if point is upwards of starting, push next upper point.
			if (point.getX()<startingPoint.getX()) 
			{
				if (strikeBoard.isValidCandidate(new Vector2(point.getX()-1, point.getY()))) targets.push(new Vector2(point.getX()-1, point.getY()));
			} 
			// if point is downwards of starting, push next downward point.
			else {
				if (strikeBoard.isValidCandidate(new Vector2(point.getX()+1, point.getY()))) targets.push(new Vector2(point.getX()+1, point.getY()));
			}
		}
	}
}

	