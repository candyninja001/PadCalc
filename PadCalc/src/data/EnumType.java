package data;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gui.ImageLoader;

public enum EnumType {
	evomat, balanced, physical, healer, dragon, god, attacker, devil, machine, type9UnkownPleaseContactDev, type10UnkownPleaseContactDev, type11UnkownPleaseContactDev, awokenmat, protect, enhance, vender, type16UnkownPleaseContactDev, type17UnkownPleaseContactDev;
	
	public static ArrayList<BufferedImage> icons = new ArrayList<BufferedImage>();
	public static BufferedImage iconUnknown;
	
	public static BufferedImage getIcon(EnumType e){
		if(e == null)
			return icons.get(0);
		if(icons.size() > e.toInt())
			return icons.get(e.toInt());
		return iconUnknown;
	}
	
	public static void loadImages(){
		iconUnknown = ImageLoader.getImage("img/type/unknown.png");
		icons.add(ImageLoader.getImage("img/type/null.png"));
		for(int i = 1; i < 11; i++){
			icons.add(ImageLoader.getImage("img/type/"+i+".png"));
		}
	}
	
	public static EnumType fromInt(int i){
		switch(i){
			case 0: return evomat;
			case 1: return balanced;
			case 2: return physical;
			case 3: return healer;
			case 4: return dragon;
			case 5: return god;
			case 6: return attacker;
			case 7: return devil;
			case 8: return machine;
			case 9: return type9UnkownPleaseContactDev;
			case 10: return type10UnkownPleaseContactDev;
			case 11: return type11UnkownPleaseContactDev;
			case 12: return awokenmat;
			case 13: return protect;
			case 14: return enhance;
			case 15: return vender;
			case 16: return type16UnkownPleaseContactDev;
			case 17: return type17UnkownPleaseContactDev;
			default: return null;
		}
	}
	
	public static EnumType[] getTyping(Integer[] typing2){
		if(typing2.length == 0)
			return new EnumType[] {};
		EnumType[] typing = new EnumType[typing2.length];
		for(int i = 0; i < typing2.length; i++){
			typing[i] = fromInt(typing2[i]);
		}
		return typing;
	}
	
	public int toInt(){
		return toInt(this);
	}
	
    public static Integer toInt(EnumType attribute){
    	switch(attribute){
    		case evomat: return 0;
    		case balanced: return 1;
    		case physical: return 2;
    		case healer: return 3;
    		case dragon: return 4;
    		case god: return 5;
    		case attacker: return 6;
    		case devil: return 7;
    		case machine: return 8;
    		case awokenmat: return 12;
    		case protect: return 13;
    		case enhance: return 14;
    		case vender: return 15;
    		default: return null;
    	}
	}
}
