package data.skill.leader;

public class Skyfall extends LeaderSkillEffect {
	public static String name = "skyfall";
	public static String regex = "No skyfall matches.";
	public boolean skyfall = true;
	
	public Skyfall(boolean state){
		skyfall = state;
	}
	
	public static LeaderSkillEffect[] readLeaderSkill(String desc){
		if(desc.contains("No skyfall matches."))
			return new LeaderSkillEffect[]{new Skyfall(false)};
		return new LeaderSkillEffect[]{};
	}
}
