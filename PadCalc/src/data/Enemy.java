package data;

import java.util.ArrayList;

public class Enemy {
    public final Monster monster;
    public final int def;
    public final int maxHP;
    public final int atk;
    
    public int hp;
    public int turn; // Turn is the turn counter for the monster
    
    public ArrayList<Guard> guards = new ArrayList<Guard>();
    
    public Enemy(Monster monster, int def, int hp, int atk){
        this.monster = monster;
        this.def = def;
        this.maxHP = hp;
        this.hp = hp;
        this.atk = atk;
    }
    
    public Enemy(Monster monster, int def, int hp, int atk, int startHP){
        this.monster = monster;
        this.def = def;
        this.maxHP = hp;
        this.hp = startHP;
        this.atk = atk;
    }
    
    public double getPercentHP(){
        return ((double) hp)/((double) maxHP);
    }

	public void runPassive() {
		
	}

	public boolean runPreemptive() { // returns true if preemptive does something
		return false;
	}

	public void runTurn() {
		
	}
	
	public boolean runDeath() {
		return false;
	}

	public boolean isDead() {
		return this.hp==0;
	}
}
