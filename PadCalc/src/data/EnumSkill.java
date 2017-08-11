package data;

public enum EnumSkill {
	enemyAttack, // {amount}
	enemyMultiattack, // {hits, amount}
	statusSheild, // {duration}
	enemyDamageReduction, // {value, duration}
	enemyElementReduction, // {value, duration}
	enemyDamageAbsorb, // {value, duration}
	enemyDamageVoid, // {value, duration}
	changeTime, // {value, isPercent, duration} note: for debuff, negative time; for percent, use double, eg 75% reduction is 0.25
	skillDelay, // {minAmount, maxAmount} //Unsure if there are dungeons with skill delay to only specific types or attributes
	enemyAttackBuff, // {amount, duration} note: amount is percentage multiplier (2.0 is 200%), duration of -1 will assume only next attack
	purge, 
	cardBind, // {{boolean x6}, chance, type, color, durationMin, durationMax} TL;DR: which cards can be bound, the chance, type and color if applicable, and duration
	clearBind, // {amount}
	skillBind, // {duration}
	awokenBind, // {duration}
	clearAwokenBind, // {amount}
	enemyElementAbsorb, // {color, duration}
	comboAbsorb, // {maxAbsorb, duration} note: maxAbsorb is the max combo count that will be absorbed, eg 5 requires 6+ combos.
	orbChange, // {[startOrbColorRange}, {changeOrbColorRange}, amount, minimum} if amount is 0, will change all orbs of specified type
	rowChange, // {rowIndex, {changeOrbColorRange}} 1 is top row, -1 is bottom row
	columnChange, // {columnIndex, {changeOrbColorRange}} 1 is left column, -1 is right column
	lockOrbs, // {{OrbColorRange}, amount}
	lockRow, // {rowIndex}
	lockColumn, // {columnIndex}
	scrollRow, // {rowIndex, duration}
	scrollColumn, // {columnIndex, duration}
	gravityTeam, // {percent}
	orbSkyfall, // {{OrbColorRange}, duration}
	hazardSkyfall, // {{OrbColorRange}, duration}
	healEnemy, // {percentage}
	healTeamSet, // {amount}
	healTeam, // {percentage}
	resurrect, // {percentage}
	fixedStart, 
	blindBoard, 
	blindOrbs, // {{{x,y} x30}, duration}
	skip, 
	suicide,
	haste, // {amount}
	singleNuke, // {value, element, target}
	massNuke, // {value, element}
	enhance, //	{{orbTypes}} CAREFUL OF DOUBLE ARRAY
	boostRCV, // {multiplier, RCV, duration}
	recoverRCV, // {factor}
	boostATK, // {{attributes}, {cardTypes}, multiplier, duration}
	boostATKAwakening, // {multiplier, {awakenings}, duration}
	posion, // {amount}
	gravityEnemy, // {percent}
	gravityTrue, // {percent}
	delay, // {value}
	teamDamageShield, // {percent, duration} percent is percent reduced, eg 30% shield is 0.30
	reduceDefense, // {percent, duration} percent is percent reduced, eg 100% reduced is 1.00
	teamVoidDamage, // {orbType}
	massAttack, // {duration}
	counterAttack, // {orbType, multiplier, duration}
	freezeTime, // {duration}
	removeHP, // brings hp to 1
	grudge, // TODO Set this up, its the atk based off hp
	clearBoard,
	voidDamageAbsorb, // {duration}
	switchLead,
	randomLead; //{duration} 
	
	public static EnumGuardType fromInt(int i){
		switch(i){
			case 1: return statusSheild;
			case 2: return damageReduction;
			case 3: return damageAbsorb;
			case 4: return damageVoid;
			case 5: return comboAbsorb;
			case 6: return attackBuff;
			case 7: return fireReduction;
			case 8: return waterReduction;
			case 9: return woodReduction;
			case 10: return lightReduction;
			case 11: return darkReduction;
			case 12: return fireAbsorb;
			case 13: return waterAbsorb;
			case 14: return woodAbsorb;
			case 15: return lightAbsorb;
			case 16: return darkAbsorb;
			case 17: return resolve;
			
			default: return null;
		}
	}
	
	public int toInt(){
		return toInt(this);
	}
	
    public static Integer toInt(EnumGuardType guardType){
    	switch(guardType){
			case statusSheild: return 1;
			case damageReduction: return 2;
			case damageAbsorb: return 3;
			case damageVoid: return 4;
			case comboAbsorb: return 5;
			case attackBuff: return 6;
			case fireReduction: return 7;
			case waterReduction: return 8;
			case woodReduction: return 9;
			case lightReduction: return 10;
			case darkReduction: return 11;
			case fireAbsorb: return 12;
			case waterAbsorb: return 13;
			case woodAbsorb: return 14;
			case lightAbsorb: return 15;
			case darkAbsorb: return 16;
			case resolve: return 17;
			default: return 0;
    	}
	}
}
