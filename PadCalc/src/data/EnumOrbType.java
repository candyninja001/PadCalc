package data;

public enum EnumOrbType {
	undefined, fire, water, wood, light, dark, heal, jammer, poison, mortal_poison, bomb, cleared;
	
	public static EnumOrbType fromChar(char c){
		switch(c){
			case 'u': return undefined;
			case 'r': return fire;
			case 'b': return water;
			case 'g': return wood;
			case 'l': return light;
			case 'd': return dark;
			case 'h': return heal;
			case 'j': return jammer;
			case 'p': return poison;
			case 'm': return mortal_poison;
			case 'o': return bomb;
			case 'c': return cleared;
			case 'U': return undefined;
			case 'R': return fire;
			case 'B': return water;
			case 'G': return wood;
			case 'L': return light;
			case 'D': return dark;
			case 'H': return heal;
			case 'J': return jammer;
			case 'P': return poison;
			case 'M': return mortal_poison;
			case 'O': return bomb;
			case 'C': return cleared;
			default: return undefined;
		}
	}
	
	public static EnumOrbType fromInt(int i){
		switch(i){
			case 0: return undefined;
			case 1: return fire;
			case 2: return water;
			case 3: return wood;
			case 4: return light;
			case 5: return dark;
			case 6: return heal;
			case 7: return jammer;
			case 8: return poison;
			case 9: return mortal_poison;
			case 10: return bomb;
			case 11: return cleared;
			default: return undefined;
		}
	}
	
	public int toInt(){
		return toInt(this);
	}
	
    public static Integer toInt(EnumOrbType type){
    	switch(type){
    		case undefined: return 0;
    		case fire: return 1;
    		case water: return 2;
    		case wood: return 3;
			case light: return 4;
			case dark: return 5;
			case heal: return 6;
			case jammer: return 7;
			case poison: return 8;
			case mortal_poison: return 9;
			case bomb: return 10;
			case cleared: return 11;
			default: return 0;
    	}
	}
    
    public String toString(){
		return toString(this);
	}
    
    public static String toString(EnumOrbType type){
    	switch(type){
    		case undefined: return "Undefined";
    		case fire: return "Fire";
    		case water: return "Water";
    		case wood: return "Wood";
			case light: return "Light";
			case dark: return "Dark";
			case heal: return "Heal";
			case jammer: return "Jammer";
			case poison: return "Poison";
			case mortal_poison: return "MortalPoison";
			case bomb: return "Bomb";
			case cleared: return "Cleared";
			default: return "Undefined";
    	}
	}
    
    public boolean canEnhance(){
		return canEnhance(this);
	}
    
    public boolean canEnhance(EnumOrbType type){
    	switch(this){
			case undefined: return true;
			case fire: return true;
			case water: return true;
			case wood: return true;
			case light: return true;
			case dark: return true;
			case heal: return true;
			case jammer: return false;
			case poison: return false;
			case mortal_poison: return false;
			case bomb: return false;
			case cleared: return false; // unsure of handling
		default: return true;
	}
    }

    public String toSymbol(){
		return toSymbol(this);
	}
    
	public String toSymbol(EnumOrbType type) {
		switch(type){
			case undefined: return "U";
			case fire: return "R";
			case water: return "B";
			case wood: return "G";
			case light: return "L";
			case dark: return "D";
			case heal: return "H";
			case jammer: return "J";
			case poison: return "P";
			case mortal_poison: return "M";
			case bomb: return "O";
			case cleared: return "C";
			default: return "U";
		}
	}
}
