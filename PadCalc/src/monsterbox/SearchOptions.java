package monsterbox;

import data.EnumAttribute;
import data.EnumAwakening;
import data.EnumType;
import data.Monster;

public class SearchOptions {
	//These variable are search parameters
	public EnumAwakening[] awakenings;
	public boolean anyAwakenings;
	public EnumType[] types;
	public boolean anyTypes;
	public EnumAttribute[] attributeFuzzy;
	public boolean anyAttributeFuzzy;
	public boolean farmable;
	
	public SearchOptions(){
		
	}
	
	public boolean isValid(Monster monster){
		
		return false;
	}
	
	private boolean matchesAwakenings(Monster monster){
		EnumAwakening[] a = monster.awokenSkills;
		
		return false;
	}
}
