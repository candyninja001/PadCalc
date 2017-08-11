package data;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.BitSet;

import org.json.JSONObject;

import data.skill.CardSkill;
import data.skill.leader.LeaderSkill;
import gui.ImageLoader;

public class Card{
    public final Monster monster;
    public Card inherit;
    public int level;
    public int plusHP;
    public int plusATK;
    public int plusRCV;
    public int skillLevel;
    public int awakenings;
    public EnumLatentAwakening[] latents = new EnumLatentAwakening[6];
    
    public int bind;
    public int swapCooldown;
    public EnumAttribute skillAttribute;
    public int skillAttributeCooldown;
    
    // Used in rouge dungeons for calculating levels
    public int currentXP;
	public BufferedImage image;
    
    public Card(Monster monster){
        this.monster = monster;
        inherit = null;
        level = 1;
        plusHP = 0;
        plusATK = 0;
        plusRCV = 0;
        skillLevel = 1;
        awakenings = 0;
        bind = 0;
        swapCooldown = 0;
        skillAttribute = null;
        skillAttributeCooldown = 0;
    }
    
    public Card(BinaryData data){
    	data.reset();
    	int id = data.getNext(13).toInt();
    	System.out.println("Id: "+id);
    	if(id<0 || id > Monster.monsters.size() || Monster.monsters.get(id)==null)
    		monster = null;
    	else
    		monster = Monster.monsters.get(id);
    	setAssumed();
    	int skill = data.getNext(6).toInt();
    	if(skill<=this.getActive().getMaxSkillLevel()){
    		if(skill>1)
    			this.skillLevel = skill;
    		else
    			this.skillLevel = 1;
    	}
    	this.plusHP = data.getNext(7).toInt();
    	if(plusHP>99)
    		this.plusHP = 99;
    	if(plusHP<0)
    		this.plusHP = 0;
    	this.plusATK = data.getNext(7).toInt();
    	if(plusATK>99)
    		this.plusATK = 99;
    	if(plusATK<0)
    		this.plusATK = 0;
    	this.plusRCV = data.getNext(7).toInt();
    	if(plusRCV>99)
    		this.plusRCV = 99;
    	if(plusRCV<0)
    		this.plusRCV = 0;
    	
    	int idI = data.getNext(13).toInt();
    	if(idI<0 || idI > Monster.monsters.size() || Monster.monsters.get(idI)==null)
    		this.inherit = new Card((Monster)null);
    	else
    		this.inherit = new Card(Monster.monsters.get(idI));
    	this.inherit.setAssumed();
    	int skillI = data.getNext(6).toInt();
    	if(skillI<=this.inherit.getActive().getMaxSkillLevel()){
    		if(skillI>1)
    			this.inherit.skillLevel = skillI;
    		else
    			this.inherit.skillLevel = 1;	
    	}
    	if(data.getNext(1).toInt()==1){
    		this.inherit.plusHP=99;
    		this.inherit.plusATK=99;
    		this.inherit.plusRCV=99;
    	}
    	this.latents = new EnumLatentAwakening[6];
    	latents[0] = EnumLatentAwakening.fromInt(data.getNext(6).toInt());
    	latents[1] = EnumLatentAwakening.fromInt(data.getNext(6).toInt());
    	latents[2] = EnumLatentAwakening.fromInt(data.getNext(6).toInt());
    	latents[3] = EnumLatentAwakening.fromInt(data.getNext(6).toInt());
    	latents[4] = EnumLatentAwakening.fromInt(data.getNext(6).toInt());
    	latents[5] = EnumLatentAwakening.fromInt(data.getNext(6).toInt());
    	bind = 0;
        swapCooldown = 0;
        skillAttribute = null;
        skillAttributeCooldown = 0;
    }
    
    public Card copy(){
    	Card copy = new Card(this.monster);
    	copy.level = this.level;
    	copy.plusHP = this.plusHP;
    	
    	copy.plusATK = this.plusATK;
    	copy.plusRCV = this.plusRCV;
    	copy.skillLevel = this.skillLevel;
        copy.awakenings = this.awakenings;
        copy.latents = this.latents;
        
        copy.bind = this.bind;
        copy.swapCooldown = this.swapCooldown;
        copy.skillAttribute = this.skillAttribute;
        copy.skillAttributeCooldown = this.skillAttributeCooldown;
        
        copy.currentXP = this.currentXP;
        
        if(this.inherit!=null && this.inherit.monster!=null)
	        this.inherit = this.inherit.copy();
        
        return copy;
    }
    
    public void resetXP(){
    	this.currentXP = 0;
    	this.level = 1;
    }
    
    public void setXP(int xp){
    	this.currentXP = xp;
    	this.level = this.monster.getLevelFromXP(currentXP);
    }
    
    public void addXP(int xp){
    	this.setXP(this.currentXP + xp);
    }
    
    public int getID(){
    	if(monster == null)
    		return 0;
    	return monster.id;
    }
    
    public BufferedImage getIcon(){
    	int num = getID();
    	if(image == null){
    		if (num == 0)
    			image = ImageLoader.getImage("img/book/0.png");// 00?
    		else
    			image = ImageLoader.getImage("img/book/"+ getID() +".png");
    	}
    	return image;
    }
    
    public int getMaxLevel(){
    	if(monster == null)
    		return 1;
    	return monster.levelMax;
    }
    
    public EnumType[] getTyping(){
    	if(monster == null)
    		return new EnumType[] {};
    	return monster.typing;
    }
    
    public String getName() {
    	if(monster == null)
    		return "";
    	return monster.name;
	}
    
    public CardSkill getActive() {
    	if(monster == null)
    		return CardSkill.noSkill;
    	return monster.activeSkill;
	}
    
    public EnumAwakening[] getAwakenings(){
    	if(monster == null)
    		return new EnumAwakening[] {};
    	return monster.awokenSkills;
    }
    
    public int getHP(boolean plus, boolean awakenings, boolean latents, boolean inherit){
    	if(monster == null)
    		return 0;
    	int p = plus ? 10*plusHP : 0;
    	int a = awakenings ? 200*getAwakenings(EnumAwakening.enhancedHP) : 0;
    	int l = latents ? (int) Math.round(getHP(false, false, false, false)*getLatents(EnumLatentAwakening.hp)*0.015) : 0;
    	int i = 0;
        if(this.inherit != null && inherit){
        	if(this.inherit.monster.attributePrimary==this.monster.attributePrimary){
        		if(this.inherit.plusHP==99&&this.inherit.plusATK==99&&this.inherit.plusRCV==99){
        			i = (int) Math.round(this.inherit.getHP(true, false, false, false)*0.1);
        		}else{
        			i = (int) Math.round(this.inherit.getHP(false, false, false, false)*0.1);
        		}
        	}
        }
        int b = (int) Math.round(monster.hpMin + ((int) ((double)(monster.hpMax - monster.hpMin)) * Math.pow((((double)level) - 1.0) / (((double)monster.levelMax) - 1.0), monster.hpScale)));
        return b+a+l+i+p;
    }
    
    public int getHPByState(Team team, Dungeon dungeon){
		if(this.bind>0 || team.awokenBind>0 || dungeon.noAwakenings){
			return this.getHP(true, false, false, !dungeon.noInherits);
		}else{
			return this.getHP(true, true, true, !dungeon.noInherits);
		}
    }
    
    public int getATKByState(Team team, Dungeon dungeon){
		if(this.bind>0 || team.awokenBind>0 || dungeon.noAwakenings){
			return this.getATK(true, false, false, !dungeon.noInherits);
		}else{
			return this.getATK(true, true, true, !dungeon.noInherits);
		}
    }
    
    public int getRCVByState(Team team, Dungeon dungeon){
		if(this.bind>0 || team.awokenBind>0 || dungeon.noAwakenings){
			return this.getRCV(true, false, false, !dungeon.noInherits);
		}else{
			return this.getRCV(true, true, true, !dungeon.noInherits);
		}
    }
    
    public int getATK(boolean plus, boolean awakenings, boolean latents, boolean inherit){
    	if(monster == null)
    		return 0;
    	int p = plus ? 5*plusATK : 0;
    	int a = awakenings ? 100*getAwakenings(EnumAwakening.enhancedAttack) : 0;
    	int l = latents ? (int) Math.round(getATK(false, false, false, false)*getLatents(EnumLatentAwakening.atk)*0.01) : 0;
    	int i = 0;
        if(this.inherit != null && inherit){
        	if(this.inherit.monster.attributePrimary==this.monster.attributePrimary){
        		if(this.inherit.plusHP==99&&this.inherit.plusATK==99&&this.inherit.plusRCV==99){
        			i = (int) Math.round(this.inherit.getATK(true, false, false, false)*0.05);
        		}else{
        			i = (int) Math.round(this.inherit.getATK(false, false, false, false)*0.05);
        		}
        	}
        }
        int b = (int) Math.round(monster.atkMin + ((int) ((double)(monster.atkMax - monster.atkMin)) * Math.pow((((double)level) - 1.0) / (((double)monster.levelMax) - 1.0), monster.atkScale)));
        return b+a+l+i+p;
    }
    
    public int getRCV(boolean plus, boolean awakenings, boolean latents, boolean inherit){
    	if(monster == null)
    		return 0;
    	int p = plus ? 1*plusRCV : 0;
    	int a = awakenings ? 50*getAwakenings(EnumAwakening.enhancedRCV) : 0;
    	int l = latents ? (int) Math.round(getRCV(false, false, false, false)*getLatents(EnumLatentAwakening.rcv)*0.05) : 0;
    	int i = 0;
        if(this.inherit != null && inherit){
        	if(this.inherit.monster.attributePrimary==this.monster.attributePrimary){
        		if(this.inherit.plusHP==99&&this.inherit.plusATK==99&&this.inherit.plusRCV==99){
        			i = (int) Math.round(this.inherit.getRCV(true, false, false, false)*0.15);
        		}else{
        			i = (int) Math.round(this.inherit.getRCV(false, false, false, false)*0.15);
        		}
        	}
        }
        int b = (int) Math.round(monster.rcvMin + ((int) ((double)(monster.rcvMax - monster.rcvMin)) * Math.pow((((double)level) - 1.0) / (((double)monster.levelMax) - 1.0), monster.rcvScale)));
        return b+a+l+i+p;
    }
    
    /**
     * Sets the level and awakenings to max
     */
    public void setAssumed(){
    	if(monster != null){
		    level = monster.levelMax;
		    skillLevel = monster.activeSkill.getMaxSkillLevel();
		    awakenings = monster.awokenSkills.length;
    	}else{
    		level = 1;
    		skillLevel = 1;
    		awakenings = 0;
    	}
    	plusHP = 0;
	    plusATK = 0;
	    plusRCV = 0;
    }

	public static Card loadCard(JSONObject objCard) {
		Integer id = objCard.getInt("id");
		Card card = new Card((Monster)null);
		if(id != null && id > 0 && id < Monster.monsters.size() && Monster.monsters.get(id) != null && Monster.monsters.get(id) != null){
			if(Monster.monsters.get(id) != null){
				card = new Card(Monster.monsters.get(id));
				card.setAssumed();
				if(objCard.has("inherit"))
					card.inherit = Card.loadCard(objCard.getJSONObject("inherit"));
				card.level = objCard.getInt("level");
				card.plusHP = objCard.getInt("plus_hp");
				card.plusATK = objCard.getInt("plus_atk");
				card.plusRCV = objCard.getInt("plus_rcv");
				card.skillLevel = objCard.getInt("skill_level");
				card.awakenings = objCard.getInt("awakenings");
				// card.latents = ????
			}
		}
		return card;
	}
	
	public JSONObject saveCard(){
		JSONObject objCard = new JSONObject();
		objCard.put("id", getID());
		if(inherit!=null)
			objCard.put("inherit", inherit.saveCard());
		objCard.put("level", level);
		objCard.put("plus_hp", plusHP);
		objCard.put("plus_atk", plusATK);
		objCard.put("plus_rcv", plusRCV);
		objCard.put("skill_level", skillLevel);
		objCard.put("awakenings", awakenings);
		// objCard.put(????, latents); ????
		return objCard;
	}

	public BinaryData getCardData() {
		BinaryData data = new BinaryData();
		data.append(this.getID(), 13);
		data.append(this.skillLevel, 6);
		data.append(this.plusHP, 7);
		data.append(this.plusATK, 7);
		data.append(this.plusRCV, 7);
		if(this.inherit!=null){
			data.append(this.inherit.getID(), 13);
			data.append(this.inherit.skillLevel, 6);
		}else{
			data.append(0, 13);
			data.append(1, 6);
		}
		data.append(EnumLatentAwakening.toInt(getLatent(0)), 6);
		data.append(EnumLatentAwakening.toInt(getLatent(1)), 6);
		data.append(EnumLatentAwakening.toInt(getLatent(2)), 6);
		data.append(EnumLatentAwakening.toInt(getLatent(3)), 6);
		data.append(EnumLatentAwakening.toInt(getLatent(4)), 6);
		data.append(EnumLatentAwakening.toInt(getLatent(5)), 6);
		return data;
	}
	
	public EnumLatentAwakening getLatent(int index){
		if(latents==null)
			return null;
		if(index<latents.length)
			return latents[index];
		return null;
	}
	
	public String toShortString(){
		String s = "" + this.getID();
		if(inherit!=null)
			s = s + "(" + this.inherit.getID() + ")";
		return s;
	}
	
	public String toString(){
		String s = "" + this.getID() + "@" + this.skillLevel;
		if(inherit!=null)
			s = s + "(" + this.inherit.getID() + "@" + this.inherit.skillLevel + ")";
		if(this.plusHP == 99 && this.plusATK == 99 && this.plusRCV ==99){
			s = s + "+297";
		}else{
			s = s + "+" + this.plusHP + "+" + this.plusATK + "+" + this.plusRCV;
		}
		return s;
	}
	
	public int getAwakenings(EnumAwakening awakening){
		int count = 0;
		for(int a = 0; a < awakenings; a++){
			if(monster != null && monster.awokenSkills.length>a){
				if(monster.awokenSkills[a]==awakening)
					count++;
			}
		}
		return count;
	}
	
	public int getLatents(EnumLatentAwakening latent){
		int count = 0;
		for(int a = 0; a < latents.length; a++){
			if(latents[a]==latent)
				count++;
		}
		return count;
	}
	
	public int calcPrimaryAttack(ArrayList<Combo> comboes, Team team){
		if(monster==null || monster.attributePrimary == null)
			return 0;
		double comboMultiplier = 0.25*((double)(comboes.size()+3));
		double multiplayerMultiplier = 1.0;
		if(team.isMultiplayer() && getAwakenings(EnumAwakening.multiBoost)>0)
			multiplayerMultiplier = 1.5;
		double baseAttack = 0;
		double enhanceMultiplier;
		double orbMultiplier;
		double tpaMultiplier;
		double comboEnhanceMultiplier;
		double rowMultiplier;
		int rowCount = 0;
		for (int i = 0; i < comboes.size(); i++){
			enhanceMultiplier = 1.0;
			orbMultiplier = 1.0;
			tpaMultiplier = 1.0;
			comboEnhanceMultiplier = 1.0;
			if(EnumAttribute.matchesOrbType(monster.attributePrimary, comboes.get(i).orbType)){
				if(comboes.get(i).enhanceCount>0){
					enhanceMultiplier=(1.0+(0.06*comboes.get(i).enhanceCount))*(1.0+(0.05*team.getCurrentAwakenings(EnumAwakening.getEnhanceAwakening(comboes.get(i).orbType))));
					enhanceMultiplier=Math.floor(enhanceMultiplier*100.0)/100.0;
				}
				if(comboes.get(i).orbCount==4){
					tpaMultiplier = Math.pow(1.5, getAwakenings(EnumAwakening.twoProngedAttack));
				}
				if(comboes.get(i).isRow()){
					rowCount++;
				}
				if(comboes.size()>6){
					comboEnhanceMultiplier = Math.pow(2, getAwakenings(EnumAwakening.comboEnhance));
				}
				orbMultiplier = 0.25*((double)(comboes.get(i).orbCount+1));
				//TODO take into account guard break awakenings at some point
				baseAttack = baseAttack + (this.getATK(true, true, true, true)*enhanceMultiplier*tpaMultiplier*comboEnhanceMultiplier*orbMultiplier);
			}
		}
		rowMultiplier = 1+((0.1*team.getCurrentAwakenings(EnumAwakening.getRowAwakening(monster.attributePrimary.getOrbType()))*rowCount));
		return (int) Math.round(baseAttack*comboMultiplier*multiplayerMultiplier*rowMultiplier);
	}
	
	public int calcSubAttack(ArrayList<Combo> comboes, Team team, Dungeon dungeon){
		if(monster==null || monster.attributeSub == null)
			return 0;
		double comboMultiplier = 0.25*((double)(comboes.size()+3));
		double multiplayerMultiplier = 1.0;
		if(team.isMultiplayer() && getAwakenings(EnumAwakening.multiBoost)>0)
			multiplayerMultiplier = 1.5;
		double baseAttack = 0;
		double enhanceMultiplier;
		double orbMultiplier;
		double tpaMultiplier;
		double comboEnhanceMultiplier;
		double rowMultiplier;
		int rowCount = 0;
		for (int i = 0; i < comboes.size(); i++){
			enhanceMultiplier = 1.0;
			tpaMultiplier = 1.0;
			orbMultiplier = 1.0;
			comboEnhanceMultiplier = 1.0;
			if(EnumAttribute.matchesOrbType(monster.attributePrimary, comboes.get(i).orbType)){
				if(comboes.get(i).enhanceCount>0){
					enhanceMultiplier=(1.0+(0.06*comboes.get(i).enhanceCount))*(1.0+(0.05*team.getCurrentAwakenings(EnumAwakening.getEnhanceAwakening(comboes.get(i).orbType))));
					enhanceMultiplier=Math.floor(enhanceMultiplier*100.0)/100.0;
				}
				if(comboes.get(i).orbCount==4){
					tpaMultiplier = Math.pow(1.5, getAwakenings(EnumAwakening.twoProngedAttack));
				}
				if(comboes.get(i).isRow()){
					rowCount++;
				}
				if(comboes.size()>6){
					comboEnhanceMultiplier = Math.pow(2, getAwakenings(EnumAwakening.comboEnhance));
				}
				orbMultiplier = 0.25*((double)(comboes.get(i).orbCount+1));
				//TODO take into account guard break awakenings at some point
				baseAttack = baseAttack + (this.getATKByState(team, dungeon)*enhanceMultiplier*tpaMultiplier*comboEnhanceMultiplier*orbMultiplier);
			}
		}
		rowMultiplier = 1+((0.1*team.getCurrentAwakenings(EnumAwakening.getRowAwakening(monster.attributePrimary.getOrbType()))*rowCount));
		if(monster.attributePrimary == monster.attributeSub)
			return (int) Math.round(0.1*baseAttack*comboMultiplier*multiplayerMultiplier*rowMultiplier);
		return (int) Math.round(0.3*baseAttack*comboMultiplier*multiplayerMultiplier*rowMultiplier);
	}

	public LeaderSkill getLeaderSkill(Dungeon dungeon) {
		if(dungeon.noLeaderSkills || bind>0)
			return LeaderSkill.noLeaderSkill;
		return monster.leaderSkill;
	}
}

