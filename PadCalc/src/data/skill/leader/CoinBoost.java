package data.skill.leader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CoinBoost extends LeaderSkillEffect {
	public static String name = "CoinBoost";
	public static String regex = "Get x(?<coinBoost>[0-9.]+) coins after a battle.";
	public boolean allowedIn3p = false;
	
	public double multiplier = 1.0;
	
	public CoinBoost(double amount){
		multiplier = amount;
		if(amount!=1.0)
			allowedIn3p = false;
	}
	
	public static LeaderSkillEffect[] readLeaderSkill(String desc){
		Matcher m1 = Pattern.compile(regex).matcher(desc);
		while(m1.find()){
			String coinBoost = m1.group("coinBoost");
			
			double b = 3;
			
			if(coinBoost!=null)
				b = Double.parseDouble(coinBoost);
			
			if(b < 0.0){
				System.out.println("[CoinBoost.readLeaderSkill.m1] Regexes detected a negative coin boost leader skill (" + b + "). Setting boost to 1.0.");
				b = 1.0;
				System.out.println("[CoinBoost.readLeaderSkill] Below is the entire leader skill description from the recent errors above:\n[CoinBoost.readLeaderSkill] \""+ desc +"\"");
			}
				
			return new LeaderSkillEffect[]{new CoinBoost(b)};
		}
		return new LeaderSkillEffect[]{};
	}
	
	public String toString(){
		return "Get x" + multiplier + " coins after a battle when this card enters the dungeon as a leader.";
	}
}
