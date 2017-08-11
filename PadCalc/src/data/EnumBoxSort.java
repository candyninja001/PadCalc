package data;

import padherder.BoxEntry;

public enum EnumBoxSort {
	padherderID, monsterID, favorite, chronological, fuseCount, attack, hp, recovery, level, cost, rarity, attribute, type, pluses, subAttribute, assist, assisting, assisted, awakenings, monsterPoints, Skill, experienceValue, plusHP, plusATK, plusRCV, collab, evolvable, devolvable;	

	public EnumBoxSort[] getFollowingOrder(){
		switch(this){
			case favorite: return new EnumBoxSort[]{attribute, subAttribute, rarity, assisted, chronological};
			case chronological: return null;
			case fuseCount: return new EnumBoxSort[]{attribute, subAttribute, rarity, assisted, chronological};
			case attack: return new EnumBoxSort[]{attribute, subAttribute, rarity, assisted, chronological};
			case hp: return new EnumBoxSort[]{attribute, subAttribute, rarity, assisted, chronological};
			case recovery: return new EnumBoxSort[]{attribute, subAttribute, rarity, assisted, chronological};
			default: return null;
		}
	}
	
	public int scoreEntry(BoxEntry e){
		switch(this){
			case favorite: return 0;
			case chronological: return e.padID;
			case fuseCount: return 0;
			case attack: return e.card.getATK()
		}
		return 0;
	}
}
