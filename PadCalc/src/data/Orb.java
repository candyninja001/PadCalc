package data;

import java.util.ArrayList;

public class Orb {
	public EnumOrbType type;
	public ArrayList<EnumOrbType> possibleTypes; // used for undefined
	public boolean enhanced;
	public boolean locked;
	public boolean blinded;
	
	public Orb(EnumOrbType type, ArrayList<EnumOrbType> possiblities, boolean enhance, boolean lock, boolean blind){
		this.type = type;
		if(possiblities==null){
			possibleTypes = new ArrayList<EnumOrbType>();
			if(type==EnumOrbType.undefined){
				possibleTypes.add(EnumOrbType.fire);
				possibleTypes.add(EnumOrbType.water);
				possibleTypes.add(EnumOrbType.wood);
				possibleTypes.add(EnumOrbType.light);
				possibleTypes.add(EnumOrbType.dark);
				possibleTypes.add(EnumOrbType.heal);
			}else{
				possibleTypes.add(type);
			}
		}else{
			possibleTypes = possiblities;
		}
		enhanced = false;
		if(this.type.canEnhance())
			enhanced = enhance;
		locked = lock;
		blinded = blind;
	}
	
	public Orb(Orb o) {
		this.type = o.type;
		this.possibleTypes = o.possibleTypes;
		this.enhanced = o.enhanced;
		this.locked = o.locked;
		this.blinded = o.blinded;
	}
	
	public Orb copy(){
		return new Orb(this);
	}
	
	public EnumOrbType getType(){
		return type;
	}
	
	public void change(EnumOrbType oldType, EnumOrbType transformType){
		if(!locked){
			if(type == EnumOrbType.undefined){
				if(possibleTypes.contains(oldType)){
					if(possibleTypes.contains(transformType)){
						possibleTypes.remove(oldType);
					}else{
						possibleTypes.remove(oldType);
						possibleTypes.add(transformType);
					}
				}
				if(possibleTypes.size()==1){
					type = possibleTypes.get(0);
					if(!type.canEnhance())// remove enhance if the new orb type cannot be enhanced;
						enhanced = false;
				}
			}else if(type == oldType){
				type = transformType;
				if(!type.canEnhance())// remove enhance if the new orb type cannot be enhanced;
					enhanced = false;
			}
		}
	}
	
	public void enhance(){
		if(type.canEnhance())
			enhanced = true;
	}
}
