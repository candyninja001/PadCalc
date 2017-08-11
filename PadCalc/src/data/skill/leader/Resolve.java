package data.skill.leader;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Resolve extends LeaderSkillEffect {
	public static String name = "Resolve";
	public static String regex = "While your HP is (?<threshold>\\d+)% or above, a single hit that normally kills you will instead leave you with 1 HP.";
	
	public double percentage = 1.1; //1.1 because default should not let you survive single hits at full (1.0) hp
	
	public Resolve(double percentage){
		this.percentage = percentage;
	}
	
	public static LeaderSkillEffect[] readLeaderSkill(String desc){
		ArrayList<LeaderSkillEffect> collection = new ArrayList<LeaderSkillEffect>();
		LeaderSkillEffect[] result = new LeaderSkillEffect[]{};
		
		Matcher m = Pattern.compile(regex).matcher(desc);
		while(m.find()){
			String s = m.group("threshold");
			Double t = 1.1;
			if(s!=null){
				t = Double.parseDouble(s)/100.0;
				if(t<0.0)
					t = 0.0;
				if(t>1.0)
					t = 1.0;
			}
			collection.add(new Resolve(Double.parseDouble(s)/100));
		}
		
		collection.toArray(result);
		return result;
	}
}
