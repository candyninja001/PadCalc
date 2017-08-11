package data;

import java.util.ArrayList;
import java.util.Arrays;

import data.skill.leader.LeaderSkill;
import data.skill.leader.LeaderSkillEffect;
import data.skill.leader.MinimumOrbRequirement;

public class Team {
	public int skillBind;
	public int awokenBind;
	
	public void nextTurn(){
		
	}
	
	public boolean isMultiplayer(){
		return false;
	}
	
	public Card getLeader(){
		return null;
	}
	
	public Card getSub(int i){
		return null;
	}
	
	public Card getFriendLeader(){
		return null;
	}
	
	public Card[] getAllCards(){
		return null;
	}
	
	public Card[] getCurrentCards(){
		return null;
	}
	
	public int getTeamCost(int player){
    	return 0;
    }
    
    public int getTeamCostWithBadge(int player){
    	return 0;
    }

	public int getCurrentAwakenings(EnumAwakening awakening) {
		return 0;
	}
	
	public EnumOrbType[] getMatchedOrbs(Combo[] comboes){
		return null;
	}
	
	public boolean hasAttributeUnbound(){
		return false;
	}
	
	public boolean meetsDungeonConditions(){
		return true;
	}

	public void enterDungeon() {
		
	}
	
	public void resetDungeon() {
		
	}

	public void editTeam() {
		
	}

	public void copy(Team team) {
		
	}
	
	public LeaderSkillEffect[] getCombinedLeaderSkill(Dungeon dungeon){
		Card l = getLeader();
		Card f = getFriendLeader();
		ArrayList<LeaderSkillEffect> a = new ArrayList<LeaderSkillEffect>();
		LeaderSkillEffect[] e = new LeaderSkillEffect[]{};
		a.addAll(new ArrayList<>(Arrays.asList(l.getLeaderSkill(dungeon).getAllEffects())));
		a.addAll(new ArrayList<>(Arrays.asList(f.getLeaderSkill(dungeon).getAllEffects())));
		a.toArray(e);
		return e;
	}
	
	public int getMinOrbRequirement(Dungeon dungeon){
		LeaderSkillEffect[] ls = LeaderSkill.getLeaderSkillEffectsForName(getCombinedLeaderSkill(dungeon), "MinimumOrbRequirement");
		int min = 3;
		for(int i = 0; i < ls.length; i++){
			if(((MinimumOrbRequirement)ls[i]).getMin() > min)
				min = ((MinimumOrbRequirement)ls[i]).getMin();
		}
		return min;
	}

	public boolean canCardSwitch(int index) {
		return false;
	}
	
	public void switchCard(int index){
		
	}
	
}
