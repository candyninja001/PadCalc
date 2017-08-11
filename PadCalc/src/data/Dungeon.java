package data;

public class Dungeon {
	public Board board;
	public Team team;
	public Floor[] floors;
	public int defaultBoardWidth = 6;
	public int defaultBoardHeight = 5;
	public double coinBoost = 1.0;
	public double expBoost = 1.0;
	public double eggBoost = 1.0;
	public boolean noAwakenings = false;
	public boolean fixedTime = false;
	public boolean noLeaderSkills = false;
	public boolean noDupes = false;
	public boolean allAttributes = false;
	public Monster cardRequirement = null;
	public boolean noContinues = false;
	public boolean roguelike = false;
	public boolean noInherits= false;
	public EnumOrbType[] defualtSkyfall = {EnumOrbType.fire, EnumOrbType.water, EnumOrbType.wood, EnumOrbType.light, EnumOrbType.dark, EnumOrbType.heal};
	
	public Dungeon(Board board, Team team, Floor[] floors){
		this.board = board;
		this.team = team;
		this.floors = floors;
	}
	
}
