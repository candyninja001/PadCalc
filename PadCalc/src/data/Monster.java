package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import data.skill.CardSkill;
import data.skill.leader.LeaderSkill;

public class Monster {
	public static ArrayList<Monster> monsters;
	
	public static JSONObject objMonsters;
	
	public final int id;
	public final String name;
	public final EnumAttribute attributePrimary;
	public final EnumAttribute attributeSub;
	public final EnumAwakening[] awokenSkills;
	public final CardSkill activeSkill;
	public final LeaderSkill leaderSkill;
	public final EnumType[] typing;
	public final int rarity;
	public final int sellValue;
	public final int teamCost;
	
	public final int levelMax;
	public final int xpCurve;
	
	public final int hpMax;
	public final int hpMin;
	public final double hpScale;
	
	public final int atkMax;
	public final int atkMin;
	public final double atkScale;
	
	public final int rcvMax;
	public final int rcvMin;
	public final double rcvScale;
	
	public Monster(int id, String name, EnumAttribute element1, EnumAttribute enumAttribute, Integer[] awakenings, String active, String leader, Integer[] typing2, int rare, int mp, int cost, int maxLevel, int curve, int hMax, int hMin, double hScale, int aMax, int aMin, double aScale, int rMax, int rMin, double rScale){
		this.id = id;
		this.name = name;
		attributePrimary = element1;
		attributeSub = enumAttribute;
		awokenSkills = EnumAwakening.getAwakenings(awakenings);
		activeSkill = CardSkill.getSkill(active);
		leaderSkill = LeaderSkill.getLeaderSkill(leader);
		typing = EnumType.getTyping(typing2);
		rarity = rare;
		sellValue = mp;
		teamCost = cost;
		levelMax = maxLevel;
		xpCurve = curve;
		
		hpMax = hMax;
		hpMin = hMin;
		hpScale = hScale;
		
		atkMax = aMax;
		atkMin = aMin;
		atkScale = aScale;
		
		rcvMax = rMax;
		rcvMin = rMin;
		rcvScale = rScale;
	}
	
	public int getLevelFromXP(int xp){
		int l = (int) Math.floor(98*Math.pow(xp/this.xpCurve, 1/2.5)+1);
		if(l<1)
			return 1;
		if(l>this.levelMax)
			return this.levelMax;
		return l;
	}
	
	public static void loadMonstersFromJSON(){
		monsters = new ArrayList<Monster>(4000);
		for(int x = 1; x < 4000; x++){
			monsters.add(null);
		}
		
		try {
			//objMats = new JSONObject(readStream(new FileInputStream("evolutions.json")));
			objMonsters = new JSONObject(readStream(new FileInputStream("monsters.json")));
			//objDungeons = new JSONObject(readStream(new FileInputStream("dungeons.json")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONArray monsterArray = objMonsters.getJSONArray("monsters");
		for (int i = 0; i < monsterArray.length(); i++)
		{
		    JSONObject objMonster = monsterArray.getJSONObject(i);
		    //public Monster(int id, String name, int element1, int element2, int[] awakenings, String active, String leader, int[] type, int rare, int mp, int cost, int maxLevel, int curve, int hMax, int hMin, int hScale, int aMax, int aMin, int aScale, int rMax, int rMin, int rScale)
		    //System.out.println("Loading Monster: " + objMonster.getInt("id"));
		    
		    Integer id = (int) getValue(objMonster, "id", 0);
			String name = (String) getValue(objMonster, "name", "");
			Integer attributePrimary = (int) getValue(objMonster, "element", -1);
			Integer attributeSub = (Integer) getValue(objMonster, "element2", -1);
			String activeSkill = (String) getValue(objMonster, "active_skill", "");
			String leaderSkill = (String) getValue(objMonster, "leader_skill", "");
			Integer[] typing  = new Integer[] {(Integer) getValue(objMonster, "type", -1), (Integer) getValue(objMonster, "type2", -1), (Integer) getValue(objMonster, "type3", -1)};
			Integer rarity = (int) getValue(objMonster, "rarity", 0);
			Integer sellValue = (int) getValue(objMonster, "monster_points", 0);
			Integer teamCost = (int) getValue(objMonster, "team_cost", 0);
			
			Integer levelMax = (int) getValue(objMonster, "max_level", 1);
			Integer xpCurve = (int) getValue(objMonster, "xp_curve", 100);
			
			Integer hpMax = (int) getValue(objMonster, "hp_max", 10);
			Integer hpMin = (int) getValue(objMonster, "hp_min", 10);
			double hpScale;
			if(getValue(objMonster, "hp_scale", 1.0).equals(1))
				hpScale = 1.0;
			else
				hpScale = (double) getValue(objMonster, "hp_scale", 1.0);
			
			Integer atkMax = (int) getValue(objMonster, "atk_max", 10);
			Integer atkMin = (int) getValue(objMonster, "atk_min", 10);
			double atkScale;
			if(getValue(objMonster, "atk_scale", 1.0).equals(1))
				atkScale = 1.0;
			else
				atkScale = (double) getValue(objMonster, "atk_scale", 1.0);
			
			Integer rcvMax = (int) getValue(objMonster, "rcv_max", 10);
			Integer rcvMin = (int) getValue(objMonster, "rcv_min", 10);
			double rcvScale;
			if(getValue(objMonster, "rcv_scale", 1.0).equals(1))
				rcvScale = 1.0;
			else
				rcvScale = (double) getValue(objMonster, "rcv_scale", 1.0);
			
			Integer[] awokenSkills = null;
			if(!objMonster.getJSONArray("awoken_skills").equals(null)){
				awokenSkills = new Integer[objMonster.getJSONArray("awoken_skills").length()];
				for(int f = 0; f < objMonster.getJSONArray("awoken_skills").length(); f++){
					awokenSkills[f] = objMonster.getJSONArray("awoken_skills").getInt(f);
				}
			}
		    
		    add(new Monster(id, 
		    		name, 
		    		EnumAttribute.fromInt(attributePrimary), 
		    		EnumAttribute.fromInt(attributeSub), 
		    		awokenSkills, 
		    		activeSkill, 
		    		leaderSkill, 
		    		typing, 
		    		rarity, 
		    		sellValue, 
		    		teamCost, 
		    		levelMax, 
		    		xpCurve, 
		    		hpMax, 
		    		hpMin, 
		    		hpScale, 
		    		atkMax, 
		    		atkMin, 
		    		atkScale, 
		    		rcvMax, 
		    		rcvMin, 
		    		rcvScale));
		}
	}
	
	private static Object getValue(JSONObject objMonster, String key, Object defaultValue) {
		if(!objMonster.has(key)){
			System.out.println("SPOOKS! Something broke, consult Monster.java");
			return defaultValue;
		}
		if(objMonster.get(key).equals(null))
			return defaultValue;
		return objMonster.get(key);
	}

	public static void add(Monster monster){
//		if(monsters.get(monster.id) != null){
//			System.out.println("Encountered a duplicate Monster ID: " + monster.id + ". Aborting second entry.");
//			return;
//		}
		//System.out.println(monsters.size());
		monsters.add(monster.id, monster);
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
	    String str = sb.toString();
	    return str;
	}
}
