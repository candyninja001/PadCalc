package data;

public class Floor {
	public Enemy[] enemies;
	public Floor(Enemy[] enemies){
		this.enemies = enemies;
	}
	
	public void onEnter(){
		for(int i = 0; i < enemies.length; i++){
			enemies[i].runPassive();
			enemies[i].runPreemptive();
		}
	}
	
	public void processTurn(){
		for(int i = 0; i < enemies.length; i++){
			enemies[i].runTurn();
		}
	}
	
	public boolean isClear(){
		for(int i = 0; i < enemies.length; i++){
			if(!enemies[i].isDead())
				return false;
		}
		return true;
	}
}
