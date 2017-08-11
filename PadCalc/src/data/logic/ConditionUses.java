package data.logic;

import data.Enemy;

public class ConditionUses extends Condition {
	public int maxUses;
	public int uses;
	
	public ConditionUses(){
		
	}
	
	public ConditionUses(int uses){
		maxUses = uses;
		this.uses = uses;
	}
	
	@Override
	public boolean isClear(Enemy enemy){
		return uses>1;
	}
	
	@Override
	public void success(){
		uses--;
	}
	
	@Override
	public void failure(){
	}
}
