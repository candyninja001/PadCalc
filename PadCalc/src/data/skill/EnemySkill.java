package data.skill;

import java.util.HashMap;

import org.json.JSONObject;

public class EnemySkill extends Skill {
	public static HashMap<String, Class<? extends Skill>> skillClasses;
	public double damage = 0.0;
	
	public EnemySkill(){
		this.damage = 0.0;
	}
	
	public EnemySkill(double damage){
		this.damage = damage;
	}
	
	public static Skill getSkillText(JSONObject json){
		try {
			return (Skill) skillClasses.get(json.getString("effect")).getMethod("readJSON", JSONObject.class).invoke(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return readJSON(json);
	}
	
	public static Skill readJSON(JSONObject json){
		return new Skill();
	}
	
	public static void registerSkills(){
		skillClasses.put("awokenBind", AwokenBind.class);
		skillClasses.put("enemyAttack", EnemyAttack.class);
	}
	
}
