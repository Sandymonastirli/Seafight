// Chrysanthi Monastirli

import java.util.ArrayList;
import java.util.Random;

public class RandomStrategy
 {
	private static final int BOARD_DIM = 10;
	private ArrayList<Vector2> targets;
	
	public RandomStrategy()
	{
		initTargets();
	}
	
	private void initTargets() 
	{
		targets = new ArrayList<Vector2>();
		for (int i = 0; i < BOARD_DIM; i++)
		{
			for (int j = 0; j < BOARD_DIM; j++)
			{
				targets.add(new Vector2(i,j));
			}
		}
	}
	
	public Vector2 nextStrike()
	{
		Random rand = new Random();
		int temp = rand.nextInt(targets.size());
		return (targets.get(temp));
	}
	
	public void update(Vector2 point)
	{
		targets.remove(point);
	}
}
