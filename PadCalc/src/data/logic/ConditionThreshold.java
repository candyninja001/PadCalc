package data.logic;

import data.Enemy;

public class ConditionThreshold extends Condition{
	public double threshold;
	
	public ConditionThreshold(){
	}
	
	public ConditionThreshold(int threshold){
		this.threshold = (double) threshold;
	}
	
	@Override
	public boolean isClear(Enemy enemy){
		return enemy.getPercentHP()<(threshold/100.0);
	}
	
	@Override
	public void success(){
	}
	
	@Override
	public void failure(){
	}
}
