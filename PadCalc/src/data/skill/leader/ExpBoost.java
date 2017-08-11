package data.skill.leader;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpBoost extends LeaderSkillEffect {
	public static String name = "ExperienceBoost";
	public static String regex = "Get x(?<expBoost>[0-9.]+) experience after a battle.";
	public boolean allowedIn3p = true;
	
	public double multiplier = 1.0;
	
	public ExpBoost(double amount){
		multiplier = amount;
		if(amount!=1.0)
			allowedIn3p = false;
	}
	
	public static LeaderSkillEffect[] readLeaderSkill(String desc){
		Matcher m1 = Pattern.compile(regex).matcher(desc);
		while(m1.find()){
			String expBoost = m1.group("expBoost");
			
			double b = 1.0;
			
			if(expBoost!=null)
				b = Double.parseDouble(expBoost);
			
			if(b < 0.0){
				System.out.println("[ExpBoost.readLeaderSkill.m1] Regexes detected a negative coin boost leader skill (" + b + "). Setting boost to 1.0.");
				b = 1.0;
				System.out.println("[ExpBoost.readLeaderSkill] Below is the entire leader skill description from the recent errors above:\n[ExpBoost.readLeaderSkill] \""+ desc +"\"");
			}
				
			return new LeaderSkillEffect[]{new CoinBoost(b)};
		}
		return new LeaderSkillEffect[]{};
	}
	
	public String toString(){
		return "Get x" + multiplier + " experience after a battle when this card enters the dungeon as a leader.";
	}
}
