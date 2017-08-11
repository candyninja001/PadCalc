package data.skill.leader;

import data.Dungeon;
import data.EnumAttribute;

public class LeaderSkillEffect {
	public static String name = "ErrorLeaderSkill";
	public static String regex = "  No leader skill in puzzle and dragons would be so meta as to match this regex exactly  ";
	public static EnumAttribute[] allAttributes = {EnumAttribute.fire, EnumAttribute.water, EnumAttribute.wood, EnumAttribute.light, EnumAttribute.dark};
	public Boolean allowedIn3p = true;
	
	public LeaderSkillEffect(){
		
	}
	
	public String getSkillType(){
		return name;
	}
	
	public static LeaderSkillEffect[] readLeaderSkill(String desc){
		return null;
	}
	
	public double getAttackMultiplier(Dungeon dungeon){
		return 1.0;
	}
	
	public double getRecoveryMultiplier(Dungeon dungeon){
		return 1.0;
	}
	
	public double getShieldAmount(Dungeon dungeon){
		return 0.0;
	}
	
	public EnumAttribute[] getAffectedAttribute(){
		return allAttributes;
	}
	
	public EnumAttribute[] getShieldAttribute(){
		return allAttributes;
	}
	
	public double getMaxAttackMultiplier(){
		return 1.0;
	}
	
	public double getMaxRecoveryMultiplier(){
		return 1.0;
	}
	
	public double getMaxShieldAmount(){
		return 0.0;
	}
}
