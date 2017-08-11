package data;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum EnumAttribute {
	fire, water, wood, light, dark, poison, lazer, extra, followUp; //posion and lazer are for calculating damage from skills
	
	public static EnumAttribute fromInt(int i){
		switch(i){
			case 0: return fire;
			case 1: return water;
			case 2: return wood;
			case 3: return light;
			case 4: return dark;
			case 5: return poison;
			case 6: return lazer;
			case 7: return extra;
			case 8: return followUp;
			default: return null;
		}
	}
	
	public String toString(){
		switch(this){
			case fire: return "Fire";
			case water: return "Water";
			case wood: return "Wood";
			case light: return "Light";
			case dark: return "Dark";
			case poison: return "Poison";
			case lazer: return "True Damage"; //TODO change to "lazer" or something later maybe if needed
			case extra: return "After Attack";
			case followUp: return "Follow-Up Attack";
			default: return null;
		}
	}
	
	public static EnumAttribute[] readAttributes(String input){
		if(input==null)
			return new EnumAttribute[]{};
		ArrayList<EnumAttribute> a = new ArrayList<EnumAttribute>();
		EnumAttribute[] e = new EnumAttribute[]{};
		
		Matcher fr = Pattern.compile("Fire").matcher(input);
		Matcher wt = Pattern.compile("Water").matcher(input);
		Matcher wd = Pattern.compile("Wood").matcher(input);
		Matcher lt = Pattern.compile("Light").matcher(input);
		Matcher dk = Pattern.compile("Dark").matcher(input);
		Matcher all = Pattern.compile("All").matcher(input);
		//TODO maybe enter poison lazer and extra? I don't think it would be needed or used.
		while(fr.find())
			a.add(fire);
		while(wt.find())
			a.add(water);
		while(wd.find())
			a.add(wood);
		while(lt.find())
			a.add(light);
		while(dk.find())
			a.add(dark);
		while(all.find()){
			a.add(fire);
			a.add(water);
			a.add(wood);
			a.add(light);
			a.add(dark);
		}
		
		a.toArray(e);
		return e;
	}
	
	public static String printAttributes(EnumAttribute[] attributes){
		int fr = 0;
		int wt = 0;
		int wd = 0;
		int lt = 0;
		int dk = 0;
		
		for(int i = 0; i < attributes.length; i++){
			if(attributes[i] == fire)
				fr++;
			if(attributes[i] == water)
				wt++;
			if(attributes[i] == wood)
				wd++;
			if(attributes[i] == light)
				lt++;
			if(attributes[i] == dark)
				dk++;
		}
		
		if(fr==1&&wt==1&&wd==1&&lt==1&&dk==1)
			return "All";
		
		ArrayList<EnumAttribute> att = new ArrayList<EnumAttribute>();
		for(int f = 0; f < fr; f++)
			att.add(fire);
		for(int w = 0; w < wt; w++)
			att.add(water);
		for(int ww = 0; ww < wd; ww++)
			att.add(wood);
		for(int l = 0; l < lt; l++)
			att.add(light);
		for(int d = 0; d < dk; d++)
			att.add(dark);
		
		if(att.size()==0){
			System.out.println("[EnumAttribute.printAttributes] Attempted to print attributes but found none. Declaring \"All\" instead."); 
			return "All";
		}
		if(att.size()==1){
			return att.get(0).toString();
		}
		if(att.size()==2){
			return att.get(0).toString() + " & " + att.get(1).toString();
		}
		String s = att.get(0).toString();
		for(int i = 1; i < att.size() - 1; i++){
			s += ", " + att.get(i);
		}
		s += " & " + att.get(att.size() - 1);
		return s;
	}
	
	public boolean matchesOrbType(EnumOrbType orbType){
		return matchesOrbType(this, orbType);
	}
	
	public static boolean matchesOrbType(EnumAttribute attribute, EnumOrbType orbType){
    	if(attribute == EnumAttribute.fire && orbType == EnumOrbType.fire)
    		return true;
    	if(attribute == EnumAttribute.water && orbType == EnumOrbType.water)
    		return true;
    	if(attribute == EnumAttribute.wood && orbType == EnumOrbType.wood)
    		return true;
    	if(attribute == EnumAttribute.light && orbType == EnumOrbType.light)
    		return true;
    	if(attribute == EnumAttribute.dark && orbType == EnumOrbType.dark)
    		return true;
    	return false;
	}
	
	public EnumOrbType getOrbType(){
		return getOrbType(this);
	}
	
	public static EnumOrbType getOrbType(EnumAttribute attribute){
    	if(attribute == EnumAttribute.fire)
    		return EnumOrbType.fire;
    	if(attribute == EnumAttribute.water)
    		return EnumOrbType.water;
    	if(attribute == EnumAttribute.wood)
    		return EnumOrbType.wood;
    	if(attribute == EnumAttribute.light)
    		return EnumOrbType.light;
    	if(attribute == EnumAttribute.dark)
    		return EnumOrbType.dark;
    	return null;
	}
	
	public int toInt(){
		return toInt(this);
	}
	
    public static Integer toInt(EnumAttribute attribute){
    	switch(attribute){
			case fire: return 0;
			case water: return 1;
			case wood: return 2;
			case light: return 3;
			case dark: return 4;
			case poison: return 5;
			case lazer: return 6;
			case extra: return 7;
			default: return -1;
    	}
	}
}
