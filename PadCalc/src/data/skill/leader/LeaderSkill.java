package data.skill.leader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

import data.EnumOrbType;

public class LeaderSkill {
	public static ArrayList<LeaderSkillEffect> registry = new ArrayList<LeaderSkillEffect>();
	// Old ideas for registering LeaderSkillEffects
	//public static ArrayList<String, String> regexes = new ArrayList<String, String>();
	//public static Map<String, LeaderSkill> regexes = new HashMap<String, LeaderSkill>();
	public static Map<String, LeaderSkill> leaderSkills = new HashMap<String, LeaderSkill>();
	public static final LeaderSkill noLeaderSkill = new LeaderSkill("None", "");
	
	public static JSONObject objLeaderSkills;
	
	public LeaderSkillEffect[] skills;
	
	public final String leaderSkillName;
	public final String effect;
	
	public LeaderSkill(String name, String effect){
		this.leaderSkillName = name;
		this.effect = effect;
		readLeaderSkill(effect);
	}
	
	public static void loadLeaderSkillsFromJSON(){
		registerAllLeaderSkillEffects();
		try {
			//objMats = new JSONObject(readStream(new FileInputStream("evolutions.json")));
			objLeaderSkills = new JSONObject(readStream(new FileInputStream("leader_skills.json")));
			//objDungeons = new JSONObject(readStream(new FileInputStream("dungeons.json")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONArray skillArray = objLeaderSkills.getJSONArray("leader_skills");
		for (int i = 0; i < skillArray.length(); i++)
		{
		    JSONObject objMonster = skillArray.getJSONObject(i);
		    //public Monster(int id, String name, int element1, int element2, int[] awakenings, String active, String leader, int[] type, int rare, int mp, int cost, int maxLevel, int curve, int hMax, int hMin, int hScale, int aMax, int aMin, int aScale, int rMax, int rMin, int rScale)
		    System.out.println("Loading Leader Skill: " + objMonster.getString("name"));
		    
		    LeaderSkill ls = new LeaderSkill(objMonster.getString("name"), objMonster.getString("effect"));
		    add(ls);
		}
	}
	
	public static void add(LeaderSkill leaderSkill){
		leaderSkills.put(leaderSkill.leaderSkillName, leaderSkill);
	}
	
	public static String readStream(InputStream is) {
	    StringBuilder sb = new StringBuilder(512);
	    try {
	        Reader r = new InputStreamReader(is, "UTF-8");
	        int c = 0;
	        while ((c = r.read()) != -1) {
	            sb.append((char) c);
	        }
	    } catch (IOException e) {
	    	System.out.println("Failed to load a JSON.");
	        throw new RuntimeException(e);
	    }
	    return sb.toString();
	}
	
	public void readLeaderSkill(String desc){
		ArrayList<LeaderSkillEffect[]> lse =  new ArrayList<LeaderSkillEffect[]>();
		ArrayList<LeaderSkillEffect> e = new ArrayList<LeaderSkillEffect>();
		LeaderSkillEffect[] skills = new LeaderSkillEffect[]{};
		
//		//I understand that there are to static references here, I could probably fix this by removing the static modifier, but it should work
//		for(int i = 0; i < registry.size(); i++){
//			String r = registry.get(i).regex;
//			Matcher m1 = Pattern.compile(r).matcher(desc);
//			LeaderSkillEffect[] s = new LeaderSkillEffect[]{};
//			if(m1.find())
//				s = registry.get(i).readLeaderSkill(desc);
//			e.addAll(Arrays.asList(s));
//		}
		
		lse.add(BoardSize.readLeaderSkill(desc));
		lse.add(CoinBoost.readLeaderSkill(desc));
		lse.add(Colors.readLeaderSkill(desc));
		lse.add(Combos.readLeaderSkill(desc));
		//lse.add(CrossColors.readLeaderSkill(desc)); - not set up
		//lse.add(CrossHeart.readLeaderSkill(desc)); - not set up
		lse.add(ExpBoost.readLeaderSkill(desc));
		lse.add(MinimumOrbRequirement.readLeaderSkill(desc));
		lse.add(Resolve.readLeaderSkill(desc));
		//lse.add(SkillUsage.readLeaderSkill(desc)); - not set up
		lse.add(Skyfall.readLeaderSkill(desc));
		
		//lse.add(.readLeaderSkill(desc));
		
		System.out.println("ArrayList size: " + lse.size());
		for (int i = 0; i < lse.size(); i++){
			LeaderSkillEffect[] ls = lse.get(i);
			if(ls != null && ls.length > 0){
				for (int j = 0; j < ls.length; j++){
					e.add(ls[j]);
				}
			}
		}
		
		e.toArray(skills);
		this.skills = skills;
	}
	
	public LeaderSkillEffect[] getAllEffects(){
		return skills;
	}
	
	public static LeaderSkillEffect[] getLeaderSkillEffectsForName(LeaderSkillEffect[] skills, String name){
		ArrayList<LeaderSkillEffect> a = new ArrayList<LeaderSkillEffect>();
		LeaderSkillEffect[] e = new LeaderSkillEffect[]{};
		
		for(int i = 0; skills.length < i; i++){
			if(skills[i].getSkillType() == name)
				a.add(skills[i]);
		}
		
		a.toArray(e);
		return e;
	}
	
	public LeaderSkillEffect[] getLeaderSkillEffectsForName(String name){
		return getLeaderSkillEffectsForName(getAllEffects(), name);
	}
	
	public static int getMinimumOrbRequirement(LeaderSkillEffect[] effects){
		int min = 3;
		for(int i = 0; effects.length < i; i++){
			if(effects[i].getSkillType() == "MinimumOrbRequirement"){
				int m = ((MinimumOrbRequirement)effects[i]).getMin();
				if (m > min)
					min = m;
			}
		}
		return min;
	}
	
	public static void registerAllLeaderSkillEffects(){
		registerLeaderSkillEffect(new BoardSize(6, 5));
		registerLeaderSkillEffect(new CoinBoost(1.0));
		registerLeaderSkillEffect(new Colors(new EnumOrbType[]{EnumOrbType.fire, EnumOrbType.water, EnumOrbType.wood, EnumOrbType.light, EnumOrbType.dark}, LeaderSkillEffect.allAttributes, 5, 1.0, 1.0, 0.0));
		registerLeaderSkillEffect(new Combos(LeaderSkillEffect.allAttributes, 10, 1.0, 1.0, 0.0));
		registerLeaderSkillEffect(new MinimumOrbRequirement(3));
	}
	
	public static void registerLeaderSkillEffect(LeaderSkillEffect e){
		registry.add(e);
	}

	public static LeaderSkill getLeaderSkill(String leaderSkill) {
		if(!leaderSkills.containsKey(leaderSkill))
			return noLeaderSkill;
		return leaderSkills.get(leaderSkill);
	}
	
}
