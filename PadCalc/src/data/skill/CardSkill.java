package data.skill;

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

public class CardSkill {
	public static String[] regexes = {};
	public static Map<String, CardSkill> cardSkills = new HashMap<String, CardSkill>();
	public static final CardSkill noSkill = new CardSkill("None", "", 0, 0);
	
	public static JSONObject objSkills;
	
	public final String skillName;
	public final String effect;
	public final int minCooldown;
	public final int maxCooldown;
	
	public CardSkill(String name, String effect, int min, int max){
		this.skillName = name;
		this.effect = effect;
		minCooldown = min;
		maxCooldown = max;
		readCardSkill(effect);
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
		    System.out.println("Loading Card Skill: " + objMonster.getString("name"));
		    
		    CardSkill cs = new CardSkill(objMonster.getString("name"), objMonster.getString("effect"), objMonster.getInt("min_cooldown"), objMonster.getInt("max_cooldown"));
		    add(cs);
		}
	}

	private void readCardSkill(String effect) {
		ArrayList<CardSkillEffect> skills = new ArrayList<CardSkillEffect>();
		if (effect.contains("Switch places with leader card. Use again to switch back."))
			skills.add(new SwitchLead());
	}

	public static void add(CardSkill cardSkill){
//		if(monsters.get(monster.id) != null){
//			System.out.println("Encountered a duplicate Monster ID: " + monster.id + ". Aborting second entry.");
//			return;
//		}
		//System.out.println(monsters.size());
		cardSkills.put(cardSkill.skillName, cardSkill);
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

	public static CardSkill getSkill(String active) {
		if(cardSkills.containsKey(active))
			return cardSkills.get(active);
		return noSkill;
	}
	
	public int getMaxSkillLevel(){
		return maxCooldown - minCooldown + 1;
	}

	public int getCooldown(int skillLevel) {
		return maxCooldown - skillLevel + 1;
	}
}
