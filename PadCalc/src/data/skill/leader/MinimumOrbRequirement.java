package data.skill.leader;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MinimumOrbRequirement extends LeaderSkillEffect {
	public static String name = "MinimumOrbRequirement";
	public static String regex = "Can no longer clear (?<restriction>\\d+)(?: | or less )connected orbs";
	public int minimum = 3;
	
	public MinimumOrbRequirement(int min){
		minimum = min;
	}
	
	public static LeaderSkillEffect[] readLeaderSkill(String desc){
		ArrayList<LeaderSkillEffect> collection = new ArrayList<LeaderSkillEffect>();
		LeaderSkillEffect[] result = new LeaderSkillEffect[]{};
		
		boolean errorflag = false;
		
		Matcher m1 = Pattern.compile(regex).matcher(desc);
		while(m1.find()){
			String restriction = m1.group("restriction");
			
			int min = 3;
			
			if(restriction!=null)
				min = Integer.parseInt(restriction) + 1;
			
			if(min<3){
				System.out.println("[MinimumComboRequirement.readLeaderSkill.m1] Regexes detected minimum combo requirement of " + min + " but this can't be right. Setting min to 3.");
				min = 3;
			}
			
			collection.add(new MinimumOrbRequirement(min));
		}
		
		if(errorflag)
			System.out.println("[MinimumComboRequirement.readLeaderSkill] Below is the entire leader skill description from the recent errors above:\n[MinimumComboRequirement.readLeaderSkill] \""+ desc +"\"");
		
		collection.toArray(result);
		return result;
	}
	
	public int getMin(){
		return minimum;
	}
	
	public String toString(){
		return "Can no longer clear " + (minimum - 1) + " or less connected orbs.";
	}
}
