package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class SkillOld {
	public static Map<String, Skill> skills = new HashMap<String, Skill>();
	public static final Skill noSkill = new Skill("None", "", 0, 0);
	
	public static JSONObject objSkills;
	
	public final String name;
	public final String effect;
	public final int minCooldown;
	public final int maxCooldown;
	
	public SkillOld(String name, String effect, int min, int max){
		this.name = name;
		this.effect = effect;
		minCooldown = min;
		maxCooldown = max;
	}
	
	public static void loadSkillsFromJSON(){
		try {
			//objMats = new JSONObject(readStream(new FileInputStream("evolutions.json")));
			objSkills = new JSONObject(readStream(new FileInputStream("active_skills.json")));
			//objDungeons = new JSONObject(readStream(new FileInputStream("dungeons.json")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONArray skillArray = objSkills.getJSONArray("skills");
		for (int i = 0; i < skillArray.length(); i++)
		{
		    JSONObject objMonster = skillArray.getJSONObject(i);
		    //public Monster(int id, String name, int element1, int element2, int[] awakenings, String active, String leader, int[] type, int rare, int mp, int cost, int maxLevel, int curve, int hMax, int hMin, int hScale, int aMax, int aMin, int aScale, int rMax, int rMin, int rScale)
		    System.out.println("Loading Skill: " + objMonster.getString("name"));
		    
		    add(new Skill(objMonster.getString("name"), objMonster.getString("effect"), objMonster.getInt("min_cooldown"), objMonster.getInt("max_cooldown")));
		}
	}

	public static void add(Skill skill){
//		if(monsters.get(monster.id) != null){
//			System.out.println("Encountered a duplicate Monster ID: " + monster.id + ". Aborting second entry.");
//			return;
//		}
		//System.out.println(monsters.size());
		skills.put(skill.name, skill);
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

	public static Skill getSkill(String active) {
		if(skills.containsKey(active))
			return skills.get(active);
		return noSkill;
	}
	
	public int getMaxSkillLevel(){
		return maxCooldown - minCooldown + 1;
	}

	public int getCooldown(int skillLevel) {
		return maxCooldown - skillLevel + 1;
	}
}
