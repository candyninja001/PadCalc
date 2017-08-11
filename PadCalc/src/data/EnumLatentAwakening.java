package data;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gui.ImageLoader;

public enum EnumLatentAwakening {
	hp, atk, rcv, autoHeal, extendTime, resistFire, resistWater, resistWood, resistLight, resistDark, resistSkillDelay, allStats, allStats2, killerGod, killerGod2, killerDragon, killerDragon2, killerDevil, killerDevil2, killerMachine, killerMachine2, killerBalanced, killerBalanced2, killerAttacker, killerAttacker2, killerPhysical, killerPhysical2, killerHealer, killerHealer2;
	
	public static ArrayList<BufferedImage> icons = new ArrayList<BufferedImage>();
	public static BufferedImage iconUnknown;
	
	public static BufferedImage getIcon(EnumLatentAwakening e){
		if(e == null)
			return icons.get(0);
		if(icons.size() > e.toInt())
			return icons.get(e.toInt());
		return iconUnknown;
	}
	
	public static void loadImages(){
		iconUnknown = ImageLoader.getImage("img/latent/unknown.png");
		icons.add(ImageLoader.getImage("img/latent/null.png"));
		for(int i = 1; i < 11; i++){
			icons.add(ImageLoader.getImage("img/latent/"+i+".png"));
		}
	}
	
	public static EnumLatentAwakening fromInt(int i){
		switch(i){
			case 1: return hp;
			case 2: return atk;
			case 3: return rcv;
			case 4: return autoHeal;
			case 5: return extendTime;
			case 6: return resistFire;
			case 7: return resistWater;
			case 8: return resistWood;
			case 9: return resistLight;
			case 10: return resistDark;
			case 11: return resistSkillDelay;
			case 12: return allStats;
			case 13: return allStats2;
			case 14: return killerGod;
			case 15: return killerGod2;
			case 16: return killerDragon;
			case 17: return killerDragon2;
			case 18: return killerDevil;
			case 19: return killerDevil2;
			case 20: return killerMachine;
			case 21: return killerMachine2;
			case 22: return killerBalanced;
			case 23: return killerBalanced2;
			case 24: return killerAttacker;
			case 25: return killerAttacker2;
			case 26: return killerPhysical;
			case 27: return killerPhysical2;
			case 28: return killerHealer;
			case 29: return killerHealer2;
			
			default: return null;
		}
	}
	
	public static EnumLatentAwakening[] getLatents(int[] l){
		if(l.length == 0)
			return new EnumLatentAwakening[] {};
		EnumLatentAwakening[] latents = new EnumLatentAwakening[l.length];
		for(int i = 0; i < l.length; i++){
			latents[i] = fromInt(l[i]);
		}
		return latents;
	}
	
	public int toInt(){
		return toInt(this);
	}
	
    public static Integer toInt(EnumLatentAwakening latent){
    	if(latent==null)
    		return 0;
    	switch(latent){
    		case hp: return 1;
    		case atk: return 2;
    		case rcv: return 3;
    		case autoHeal: return 4;
    		case extendTime: return 5;
    		case resistFire: return 6;
    		case resistWater: return 7;
    		case resistWood: return 8;
    		case resistLight: return 9;
    		case resistDark: return 10;
    		case resistSkillDelay: return 11;
    		case allStats: return 12;
    		case allStats2: return 13;
    		case killerGod: return 14;
    		case killerGod2: return 15;
    		case killerDragon: return 16;
    		case killerDragon2: return 17;
    		case killerDevil: return 18;
    		case killerDevil2: return 19;
    		case killerMachine: return 20;
    		case killerMachine2: return 21;
    		case killerBalanced: return 22;
    		case killerBalanced2: return 23;
    		case killerAttacker: return 24;
    		case killerAttacker2: return 25;
    		case killerPhysical: return 26;
    		case killerPhysical2: return 27;
    		case killerHealer: return 28;
    		case killerHealer2: return 29;
    		
    		default: return 0;
    	}
	}
}
