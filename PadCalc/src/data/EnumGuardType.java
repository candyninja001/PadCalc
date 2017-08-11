package data;

public enum EnumGuardType {
	statusSheild, damageReduction, damageAbsorb, damageVoid, comboAbsorb, attackBuff, fireReduction, waterReduction, woodReduction, lightReduction, darkReduction, fireAbsorb, waterAbsorb, woodAbsorb, lightAbsorb, darkAbsorb, resolve;
	
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
