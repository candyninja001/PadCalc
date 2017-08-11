package data.logic;

import data.skill.SkillSet;

public class EnemyLogicTest {
	public Condition[] conditions;
	public SkillSet skills;
	public EnemyLogicTest[] nested; //TODO create a better name
	
	public EnemyLogicTest(){
		
	}
	
	public boolean canUse(Enemy enemy){
		if(conditions.length==0)
			return true;
		for(int i = 0; i < condition.length; i++){
			if(!conditions[i].isClear(enemy))
				return false;
		}
		return false;
	}
	
	public void use(){
		
	}
}
