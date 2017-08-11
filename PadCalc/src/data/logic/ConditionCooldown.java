package data.logic;

import data.Enemy;

public class ConditionCooldown extends Condition {
	public int cooldown;
	public int count;
	
	public ConditionCooldown(){
		
	}
	
	public ConditionCooldown(int cooldown){
		count = 0;
		this.cooldown = cooldown;
	}
	
	@Override
	public boolean isClear(Enemy enemy){
		return count == 0;
	}
	
	@Override
	public void success(){
		count = cooldown - 1;
	}
	
	@Override
	public void failure(){ // This might only trigger if all other condiitons are valid, find out.
		count = count - 1;
		if(count<0)
			count = 0;
	}

}
