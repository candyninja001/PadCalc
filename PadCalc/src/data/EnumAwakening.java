package data;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gui.ImageLoader;

public enum EnumAwakening {
	enhancedHP, enhancedAttack, enhancedRCV, reduceFire, reduceWater, reduceWood, reduceLight, reduceDark, autoHeal, resistBind, resistBlind, resistJammer, resistPoison, enhancedFireOrbs, enhancedWaterOrbs, enhancedWoodOrbs, enhancedLightOrbs, enhancedDarkOrbs, extendTime, recoverBind, skillBoost, enhancedFireRow, enhancedWaterRow, enhancedWoodRow, enhancedLightRow, enhancedDarkRow, twoProngedAttack, resistSkillBind, enhancedHeartOrbs, multiBoost, killerDragon, killerGod, killerDevil, killerMachine, killerAttacker, killerPhysical, killerHealer, killerBalanced, killerAwokenMat, killerEnhance, killerVender, killerEvoMat, comboEnhance, guardBreak, awakening45UnkownPleaseContactDev, awakening46UnkownPleaseContactDev;
	
	public static ArrayList<BufferedImage> icons = new ArrayList<BufferedImage>();
	public static BufferedImage iconUnknown;
	
	public static BufferedImage getIcon(EnumAwakening e){
		if(e == null)
			return icons.get(0);
		if(icons.size() > e.toInt())
			return icons.get(e.toInt());
		return iconUnknown;
	}
	
	public static void loadImages(){
		iconUnknown = ImageLoader.getImage("img/awakening/unknown.png");
		icons.add(ImageLoader.getImage("img/awakening/null.png"));
		for(int i = 1; i < 44; i++){
			icons.add(ImageLoader.getImage("img/awakening/"+i+".png"));
		}
	}
	
	public static int getAwakeningsForCard(EnumAwakening awakening, Card card){
		int count = 0;
		for(int a = 0; a < card.awakenings; a++){
			if(card.monster != null && card.monster.awokenSkills.length>a){
				if(card.monster.awokenSkills[a]==awakening)
					count++;
			}
		}
		return count;
	}
	
	public static int getRowAwakeningsForCard(EnumAwakening awakening, Card card){
		int count = 0;
		for(int a = 0; a < card.awakenings; a++){
			if(card.monster != null && card.monster.awokenSkills.length>a){
				if(card.monster.awokenSkills[a]==awakening)
					count++;
			}
		}
		return count;
	}
	
	public static int getEnhanceAwakeningsForCard(EnumAwakening awakening, Card card){
		int count = 0;
		for(int a = 0; a < card.awakenings; a++){
			if(card.monster != null && card.monster.awokenSkills.length>a){
				if(card.monster.awokenSkills[a]==awakening)
					count++;
			}
		}
		return count;
	}
	
	public static EnumAwakening fromInt(int i){
		switch(i){
			case 1: return enhancedHP;
			case 2: return enhancedAttack;
			case 3: return enhancedRCV;
			case 4: return reduceFire;
			case 5: return reduceWater;
			case 6: return reduceWood;
			case 7: return reduceLight;
			case 8: return reduceDark;
			case 9: return autoHeal;
			case 10: return resistBind;
			case 11: return resistBlind;
			case 12: return resistJammer;
			case 13: return resistPoison;
			case 14: return enhancedFireOrbs;
			case 15: return enhancedWaterOrbs;
			case 16: return enhancedWoodOrbs;
			case 17: return enhancedLightOrbs;
			case 18: return enhancedDarkOrbs;
			case 19: return extendTime;
			case 20: return recoverBind;
			case 21: return skillBoost;
			case 22: return enhancedFireRow;
			case 23: return enhancedWaterRow;
			case 24: return enhancedWoodRow;
			case 25: return enhancedLightRow;
			case 26: return enhancedDarkRow;
			case 27: return twoProngedAttack;
			case 28: return resistSkillBind;
			case 29: return enhancedHeartOrbs;
			case 30: return multiBoost;
			case 31: return killerDragon;
			case 32: return killerGod;
			case 33: return killerDevil;
			case 34: return killerMachine;
			case 35: return killerAttacker;
			case 36: return killerPhysical;
			case 37: return killerHealer;
			case 38: return killerBalanced;
			case 39: return killerAwokenMat;
			case 40: return killerEnhance;
			case 41: return killerVender;
			case 42: return killerEvoMat;
			case 43: return comboEnhance;
			case 44: return guardBreak;
			case 45: return awakening45UnkownPleaseContactDev;
			case 46: return awakening46UnkownPleaseContactDev;
			default: return null;
		}
	}
	
	public static EnumAwakening[] getAwakenings(Integer[] awakenings2){
		if(awakenings2.length == 0)
			return new EnumAwakening[] {};
		EnumAwakening[] awakenings = new EnumAwakening[awakenings2.length];
		for(int i = 0; i < awakenings2.length; i++){
			awakenings[i] = fromInt(awakenings2[i]);
		}
		return awakenings;
	}
	
	public static EnumAwakening getEnhanceAwakening(EnumOrbType orbType){
		if(orbType == EnumOrbType.fire)
    		return EnumAwakening.enhancedFireOrbs;
    	if(orbType == EnumOrbType.water)
    		return EnumAwakening.enhancedWaterOrbs;
    	if(orbType == EnumOrbType.wood)
    		return EnumAwakening.enhancedWoodOrbs;
    	if(orbType == EnumOrbType.light)
    		return EnumAwakening.enhancedLightOrbs;
    	if(orbType == EnumOrbType.dark)
    		return EnumAwakening.enhancedDarkOrbs;
    	if(orbType == EnumOrbType.heal)
    		return EnumAwakening.enhancedHeartOrbs;
    	return null;
	}
	
	public static EnumAwakening getRowAwakening(EnumOrbType orbType){
		if(orbType == EnumOrbType.fire)
    		return EnumAwakening.enhancedFireRow;
    	if(orbType == EnumOrbType.water)
    		return EnumAwakening.enhancedWaterRow;
    	if(orbType == EnumOrbType.wood)
    		return EnumAwakening.enhancedWoodRow;
    	if(orbType == EnumOrbType.light)
    		return EnumAwakening.enhancedLightRow;
    	if(orbType == EnumOrbType.dark)
    		return EnumAwakening.enhancedDarkRow;
    	return null;
	}
	
	public int toInt(){
		return toInt(this);
	}
	
    public static Integer toInt(EnumAwakening attribute){
    	switch(attribute){
    		case enhancedHP: return 1;
    		case enhancedAttack: return 2;
    		case enhancedRCV: return 3;
    		case reduceFire: return 4;
    		case reduceWater: return 5;
			case reduceWood: return 6;
			case reduceLight: return 7;
			case reduceDark: return 8;
			case autoHeal: return 9;
			case resistBind: return 10;
			case resistBlind: return 11;
			case resistJammer: return 12;
			case resistPoison: return 13;
			case enhancedFireOrbs: return 14;
			case enhancedWaterOrbs: return 15;
			case enhancedWoodOrbs: return 16;
			case enhancedLightOrbs: return 17;
			case enhancedDarkOrbs: return 18;
			case extendTime: return 19;
			case recoverBind: return 20;
			case skillBoost: return 21;
			case enhancedFireRow: return 22;
			case enhancedWaterRow: return 23;
			case enhancedWoodRow: return 24;
			case enhancedLightRow: return 25;
			case enhancedDarkRow: return 26;
			case twoProngedAttack: return 27;
			case resistSkillBind: return 28;
			case enhancedHeartOrbs: return 29;
			case multiBoost: return 30;
			case killerDragon: return 31;
			case killerGod: return 32;
			case killerDevil: return 33;
			case killerMachine: return 34;
			case killerAttacker: return 35;
			case killerPhysical: return 36;
			case killerHealer: return 37;
			case killerBalanced: return 38;
			case killerAwokenMat: return 39;
			case killerEnhance: return 40;
			case killerVender: return 41;
			case killerEvoMat: return 42;
			case comboEnhance: return 43;
			case guardBreak: return 44;
			case awakening45UnkownPleaseContactDev: return 45;
			case awakening46UnkownPleaseContactDev: return 46;
    		default: return null;
    	}
	}
    
    public String getImageName(){
    	return getImageName(this);
    }

	public String getImageName(EnumAwakening enumAwakening) {
		Integer i = enumAwakening.toInt();
		if (i == null)
			return "null";
		if (i > 43)
			return "unknown";
		return "" + i;
	}
}
