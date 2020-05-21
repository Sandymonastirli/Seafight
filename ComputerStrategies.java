// Chrysanthi Monastirli 

public class ComputerStrategies 
{
	private RandomStrategy randomStrategy;
	private ExplorationStrategy explorationStrategy;
	private boolean isExploring;

	
	public ComputerStrategies(StrikeBoard sb)
	{
		randomStrategy = new RandomStrategy();
		explorationStrategy = new ExplorationStrategy(sb);
		isExploring = false;
	}
	
	public Vector2 nextStrike() 
	{
		if (isExploring) 
		{
			if (explorationStrategy.hasNextCandidate())
			{	
				return explorationStrategy.nextStrike();
			}else {
				isExploring = false;
				return randomStrategy.nextStrike();
			}
		}else{
			return randomStrategy.nextStrike();
		}
	}

	public void update(Vector2 point, boolean hit)
	{
		randomStrategy.update(point);
		if (hit)
		{
			if (isExploring)
			{
				explorationStrategy.update(point);
			}else{
				explorationStrategy.initialize(point);
				isExploring= true;
			}
		}
	}
	
	public void completeExploration()
	{
		isExploring = false;
	}	
}


