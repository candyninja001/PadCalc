package data.skill.leader;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.Combo;
import data.Dungeon;
import data.EnumAttribute;
import data.EnumOrbType;

public class Combos extends LeaderSkillEffect {
	public static String name = "Combo";
	public static String regex = "combos";// The leader skills are worded in so many ways it's hard to put in one regex, these seem to be a common phrases they all have.
	public EnumAttribute[] shieldAttributes = {EnumAttribute.fire, EnumAttribute.water, EnumAttribute.wood, EnumAttribute.light, EnumAttribute.dark};
	public EnumAttribute[] affectedAttributes = {EnumAttribute.fire, EnumAttribute.water, EnumAttribute.wood, EnumAttribute.light, EnumAttribute.dark};
	public int minimumComboCount = 1;
	public double minimumAttackMultiplier = 1.0;
	public double minimumRecoveryMultiplier = 1.0;
	public double minimumShield = 0.0;
	public int maximumComboCount = 1;
	public double maximumAttackMultiplier = 1.0;
	public double maximumRecoveryMultiplier = 1.0;
	public double maximumShield = 0.0;
	
	
	// eg 1 Non-Scaling (Anubis)  "All attribute cards ATK x10 when reaching 10 combos or above."
	public static String regexNonScaling = "(?<affectedElements>(?:(?:Fire|Water|Wood|Light|Dark|All|&|and)(?: |, ))+)attribute cards (?:ATK x(?<atkMulti>[0-9.]+))?(?: |, )?(?:RCV x(?<rcvMulti>[0-9.]+))?(?: |, )?(?:(?<reductionAmount>\\d+)% all damage reduction)? when reaching (?<comboCount>\\d+) combos";
	
	// eg 2 Exactly (Hinomitsuha)  "All attribute cards ATK x4 when reaching exactly 7 combos."
	public static String regexExactly = "(?<affectedElements>(?:(?:Fire|Water|Wood|Light|Dark|All|&|and)(?: ))+)attribute cards (?:ATK x(?<atkMulti>[0-9.]+))?(?: |, )?(?:RCV x(?<rcvMulti>[0-9.]+))?(?: |, )?(?:(?<reductionAmount>\\d+)% all damage reduction)? when reaching exactly (?<comboCount>\\d+) combos";
	
	// eg 3 Scaling (Reincarnated Sakuya)  "ATK x1.5 at 5 combos. ATK x0.5 for each additional combo, up to ATK x4 at 10 combos."
	public static String regexScaling = "(?:ATK x(?<minATKMulti>[0-9.]+))?(?: |, )?(?:RCV x(?<minRCVMulti>[0-9.]+))?(?: |, )?(?:(?<minReductionAmount>\\d+)% all damage reduction)? (?:at|when reaching) (?<minComboCount>\\d+) combos\\. (?:ATK x(?<atkScale>[0-9.]+))?(?: |, )?(?:RCV x(?<rcvScale>[0-9.]+))?(?: |, )?(?:(?<reductionScale>\\d+)% all damage reduction)? for each additional combo, up to (?:ATK x(?<maxATKMulti>[0-9.]+))?(?: |, )?(?:RCV x(?<maxRCVMulti>[0-9.]+))?(?: |, )?(?:(?<maxReductionAmount>\\d+)% all damage reduction)? (?:at|when reaching) (?<maxComboCount>\\d+) combos";
	
	
	/**
	 * This creates a leader skill effect for multipliers scaling with the amount of combos made
	 * @param affectedAttributes
	 * @param minComboCount
	 * @param minAttackMultiplier
	 * @param minRecoveryMultiplier
	 * @param minShield
	 * @param maxComboCount
	 * @param maxAttackMultiplier
	 * @param maxRecoveryMultiplier
	 * @param maxShield
	 */
	public Combos(EnumAttribute[] affectedAttributes, int minComboCount, double minAttackMultiplier, double minRecoveryMultiplier, double minShield, int maxComboCount, double maxAttackMultiplier, double maxRecoveryMultiplier, double maxShield){ //TODO later add attribute specific shielding
		this.affectedAttributes = affectedAttributes;
		minimumComboCount = minComboCount;
		minimumAttackMultiplier = minAttackMultiplier;
		minimumRecoveryMultiplier = minRecoveryMultiplier;
		minimumShield = minShield;
		maximumComboCount = maxComboCount;
		if(this.maximumComboCount < this.minimumComboCount){
			System.out.println("[Combos.init] Something went wrong when making a leader skill; Max combo should never be less than min combo. Setting max combo to min combo+1. This will probably have unintended effects.");
			this.maximumComboCount = this.minimumComboCount;
		}
		maximumAttackMultiplier = maxAttackMultiplier;
		maximumRecoveryMultiplier = maxRecoveryMultiplier;
		maximumShield = maxShield;
	}
	
	/**
	 * This creates a leader skill effect for a non-scaling combo requirement
	 * @param affectedAttributes
	 * @param minComboCount
	 * @param minAttackMultiplier
	 * @param minRecoveryMultiplier
	 * @param minShield
	 */
	public Combos(EnumAttribute[] affectedAttributes, int minComboCount, double minAttackMultiplier, double minRecoveryMultiplier, double minShield){
		this(affectedAttributes, minComboCount, minAttackMultiplier, minRecoveryMultiplier, minShield, minComboCount, minAttackMultiplier, minRecoveryMultiplier, minShield);
	}

	/**
	 * This creates a leader skill effect for an exact amount of combos 
	 * @param minComboCount
	 * @param minAttackMultiplier
	 * @param minRecoveryMultiplier
	 * @param minShield
	 * @param extraArgument
	 */
	public Combos(EnumAttribute[] affectedAttributes, int minComboCount, double minAttackMultiplier, double minRecoveryMultiplier, double minShield, boolean extraArgument){ // Change if I ever figure out how
		this(affectedAttributes, minComboCount, minAttackMultiplier, minRecoveryMultiplier, minShield, minComboCount+1, 1.0, 1.0, 0.0);
	}
	
	public static LeaderSkillEffect[] readLeaderSkill(String desc){
		ArrayList<LeaderSkillEffect> collection = new ArrayList<LeaderSkillEffect>();
		LeaderSkillEffect[] result = new LeaderSkillEffect[]{};
		
		boolean errorflag = false;
		
		Matcher m1 = Pattern.compile(regexNonScaling).matcher(desc);
		while(m1.find()){ //This is the classic check; it finds when the leader skill is non-scaling, eg ATK x4 at 5+ combos
			String affectedElements = m1.group("affectedElements");
			String atkMulti = m1.group("atkMulti");
			String rcvMulti = m1.group("rcvMulti");
			String reductionAmount = m1.group("reductionAmount");
			String comboCount = m1.group("comboCount");
			//TODO add in the functionality for attribute specific reduction detection (not currently on any card in padx) via EnumAttribute.readAttributes(words)
			//String reductionElements = m1.group("comboCount");
			
			EnumAttribute[] e = allAttributes;
			int minComboCount = 1;
			double minAttackMultiplier = 1.0;
			double minRecoveryMultiplier = 1.0;
			double minShield = 0.0;
			//EnumAttributes[] attributes = shieldAttributes;
			
			e = EnumAttribute.readAttributes(affectedElements);
			if(e==new EnumAttribute[]{}){ // If no attributes were found
				System.out.println("[Combos.readLeaderSkill.m1] Regexes detected a non-scaling combo leader skill, but failed to find any attributes. Assuming all attributes, but this is incorrect.");
				errorflag = true;
				e = allAttributes;
			}
			
			if(atkMulti!=null)
				minAttackMultiplier = Double.parseDouble(atkMulti);
			if(rcvMulti!=null)
				minRecoveryMultiplier = Double.parseDouble(rcvMulti);
			if(reductionAmount!=null)
				minShield = Double.parseDouble(reductionAmount)/100.0;
			if(comboCount!=null)
				minComboCount = Integer.parseInt(comboCount);
			//if(reductionElements!=null)
			//	attributes = EnumAttributes.readAttributes(reductionElements);
			
			if(minShield<0.0)
				minShield = 0.0;
			if(minShield>1.0)
				minShield = 1.0;
				
			collection.add(new Combos(e, minComboCount, minAttackMultiplier, minRecoveryMultiplier, minShield));
		}
		
		Matcher m2 = Pattern.compile(regexExactly).matcher(desc);
		while(m2.find()){ 
			String affectedElements = m1.group("affectedElements");
			String atkMulti = m2.group("atkMulti");
			String rcvMulti = m2.group("rcvMulti");
			String reductionAmount = m2.group("reductionAmount");
			String comboCount = m2.group("comboCount");
			//TODO add in the functionality for attribute specific reduction detection (not currently on any card in padx) via EnumAttribute.readAttributes(words)
			//String reductionElements = m1.group("comboCount");
			
			EnumAttribute[] e = allAttributes;
			int minComboCount = 1;
			double minAttackMultiplier = 1.0;
			double minRecoveryMultiplier = 1.0;
			double minShield = 0.0;
			//EnumAttributes[] attributes = shieldAttributes;
			
			e = EnumAttribute.readAttributes(affectedElements);
			if(e==new EnumAttribute[]{}){ // If no attributes were found
				System.out.println("[Combos.readLeaderSkill.m2] Regexes detected an exact combo leader skill, but failed to find any attributes. Assuming all attributes, but this is incorrect.");
				errorflag = true;
				e = allAttributes;
			}
			
			if(atkMulti!=null)
				minAttackMultiplier = Double.parseDouble(atkMulti);
			if(rcvMulti!=null)
				minRecoveryMultiplier = Double.parseDouble(rcvMulti);
			if(reductionAmount!=null)
				minShield = Double.parseDouble(reductionAmount)/100.0;
			if(comboCount!=null)
				minComboCount = Integer.parseInt(comboCount);
			//if(reductionElements!=null)
			//	attributes = EnumAttributes.readAttributes(reductionElements);
			
			if(minShield<0.0)
				minShield = 0.0;
			if(minShield>1.0)
				minShield = 1.0;
				
			collection.add(new Combos(e, minComboCount, minAttackMultiplier, minRecoveryMultiplier, minShield, true));
		}
		
		Matcher m3 = Pattern.compile(regexScaling).matcher(desc);
		while(m3.find()){ 
			
			// (?:^| )(?:ATK x(?<minATKMulti>[0-9.]+))?(?: |, )?(?:RCV x(?<minRCVMulti>[0-9.]+))?(?: |, )?(?:(?<minReductionAmount>\\d+)% all damage reduction)? (?:at|when reaching) (?<minComboCount>\\d+) combos\\.
			// (?:ATK x(?<atkScale>[0-9.]+))?(?: |, )?(?:RCV x(?<rcvScale>[0-9.]+))?(?: |, )?(?:(?<reductionScale>\\d+)% all damage reduction)? for each additional combo,
			// up to (?:ATK x(?<maxATKMulti>[0-9.]+))?(?: |, )?(?:RCV x(?<maxRCVMulti>[0-9.]+))?(?: |, )?(?:(?<maxReductionAmount>\\d+)% all damage reduction)? (?:at|when reaching) (?<maxComboCount>\\d+) combos
			
			String sminATK = m3.group("minATKMulti");
			String sminRCV = m3.group("minRCVMulti");
			String sminReduction = m3.group("minReductionAmount");
			String sminCombo = m3.group("minComboCount");
			String sscaleATK = m3.group("atkScale");
			String sscaleRCV = m3.group("rcvScale");
			String sscaleReduction = m3.group("reductionScale");
			String smaxATK = m3.group("maxATKMulti");
			String smaxRCV = m3.group("maxRCVMulti");
			String smaxReduction = m3.group("maxReductionAmount");
			String smaxCombo = m3.group("maxComboCount");
			//TODO add in the functionality for attribute specific reduction detection (not currently on any card in padx) via EnumAttribute.readAttributes(words)
			//String reductionElements = m1.group("comboCount");
			
			double minATK = 1.0;
			double minRCV = 1.0;
			double minReduction = 0.0;
			int minCombo = 1;
			double scaleATK = 0.0;
			double scaleRCV = 0.0;
			double scaleReduction = 0.0;
			double maxATK = 1.0;
			double maxRCV = 1.0;
			double maxReduction = 0.0;
			int maxCombo = 1;
			
			//EnumAttributes[] attributes = shieldAttributes;
			
			if(sminATK!=null)
				minATK = Double.parseDouble(sminATK);
			if(sminRCV!=null)
				minRCV = Double.parseDouble(sminRCV);
			if(sminReduction!=null)
				minReduction = Double.parseDouble(sminReduction)/100.0;
			if(sminCombo!=null)
				minCombo = Integer.parseInt(sminCombo);
			if(sscaleATK!=null)
				scaleATK = Double.parseDouble(sscaleATK);
			if(sscaleRCV!=null)
				scaleRCV = Double.parseDouble(sscaleRCV);
			if(sscaleReduction!=null)
				scaleReduction = Double.parseDouble(sscaleReduction)/100.0;
			if(smaxATK!=null)
				maxATK = Double.parseDouble(smaxATK);
			if(smaxRCV!=null)
				maxRCV = Double.parseDouble(smaxRCV);
			if(smaxReduction!=null)
				maxReduction = Double.parseDouble(smaxReduction)/100.0;
			if(smaxCombo!=null)
				maxCombo = Integer.parseInt(smaxCombo);
			
			//if(reductionElements!=null)
			//	attributes = EnumAttributes.readAttributes(reductionElements);
			
			if(minReduction<0.0)
				minReduction = 0.0;
			if(minReduction>1.0)
				minReduction = 1.0;
			
			if(maxReduction<0.0)
				maxReduction = 0.0;
			if(maxReduction>1.0)
				maxReduction = 1.0;
			
			// This portion just reports errors in the description or the regex
			int diff = maxCombo - minCombo;
			
			if(minATK + (scaleATK * diff) != maxATK){
				System.out.println("[Combos.readLeaderSkill.m3] A scaling combo leader skill seems to be misbehaving, the math doesn't add up for ATK. Assuming the scaling was incorrect, following min and max ATK. Details on next line.\n[Combos.readLeaderSkill] " + minATK + "x ATK at " + minCombo + " + " + scaleATK + "x ATK per Combo up to " + maxATK + "x ATK at " + maxCombo + ".");
				errorflag = true;
			}
			if(minRCV + (scaleRCV * diff) != maxRCV){
				System.out.println("[Combos.readLeaderSkill.m3] A scaling combo leader skill seems to be misbehaving, the math doesn't add up for RCV. Assuming the scaling was incorrect, following min and max RCV. Details on next line.\n[Combos.readLeaderSkill] " + minRCV + "x RCV at " + minCombo + " + " + scaleRCV + "x RCV per Combo up to " + maxRCV + "x RCV at " + maxCombo + ".");
				errorflag = true;
			}
			if(minReduction + (scaleReduction * diff) != maxReduction){
				System.out.println("[Combos.readLeaderSkill.m3] A scaling combo leader skill seems to be misbehaving, the math doesn't add up for the damage reduction. Assuming the scaling was incorrect, following min and max damage reduction. Details on next line.\n[Combos.readLeaderSkill] " + (minReduction * 100.0) + "% damage reduction at " + minCombo + " + " + (scaleReduction * 100.0) + "% damage reduction per Combo up to " + (maxReduction * 100.0) + "% damage reduction at " + maxCombo + ".");
				errorflag = true;
			}
			
			collection.add(new Combos(allAttributes, minCombo, minATK, minRCV, minReduction, maxCombo, maxATK, maxRCV, maxReduction));
		}
		
		if(errorflag)
			System.out.println("[Combos.readLeaderSkill] Below is the entire leader skill description from the recent errors above:\n[Combos.readLeaderSkill] \""+ desc +"\"");
		
		collection.toArray(result);
		return result;
	}
	
	public double getAttackMultiplier(Dungeon dungeon){
		ArrayList<Combo> c = dungeon.board.getCombos(dungeon);
		int count = c.size();
		if(count >= maximumComboCount)
			return maximumAttackMultiplier;
		if(count == minimumComboCount)
			return minimumAttackMultiplier;
		if(count < maximumComboCount && count > minimumComboCount){
			int diff = maximumComboCount - minimumComboCount;
			double scale = (maximumAttackMultiplier - minimumAttackMultiplier) / diff;
			return minimumAttackMultiplier + (scale * (count - minimumComboCount));
		}
		return 1.0; //If this is somehow returned, congratulations! Now buy a new computer that does have malfunctioning logic.
	}
	
	public double getRecoveryMultiplier(Dungeon dungeon){
		ArrayList<Combo> c = dungeon.board.getCombos(dungeon);
		int count = c.size();
		if(count >= maximumComboCount)
			return maximumRecoveryMultiplier;
		if(count == minimumComboCount)
			return minimumRecoveryMultiplier;
		if(count < maximumComboCount && count > minimumComboCount){
			int diff = maximumComboCount - minimumComboCount;
			double scale = (maximumRecoveryMultiplier - minimumRecoveryMultiplier) / diff;
			return minimumRecoveryMultiplier + (scale * (count - minimumComboCount));
		}
		return 1.0; //If this is somehow returned, congratulations! Now buy a new computer that does have malfunctioning logic.
	}
	
	public double getShieldAmount(Dungeon dungeon){
		ArrayList<Combo> c = dungeon.board.getCombos(dungeon);
		int count = c.size();
		if(count >= maximumComboCount)
			return maximumShield;
		if(count == minimumComboCount)
			return minimumShield;
		if(count < maximumComboCount && count > minimumComboCount){
			int diff = maximumComboCount - minimumComboCount;
			double scale = (maximumShield - minimumShield) / diff;
			return minimumShield + (scale * (count - minimumComboCount));
		}
		return 0.0; //If this is somehow returned, congratulations! Now buy a new computer that does have malfunctioning logic.
	}
	
	public EnumAttribute[] getAffectedAttribute(){
		return affectedAttributes;
	}
	
	public EnumAttribute[] getShieldAttribute(){
		return shieldAttributes;
	}
	
	public double getMaxAttackMultiplier(){
		if(maximumAttackMultiplier>=minimumAttackMultiplier)
			return maximumAttackMultiplier;
		else
			return minimumAttackMultiplier;
	}
	
	public double getMaxRecoveryMultiplier(){
		if(maximumRecoveryMultiplier>=minimumRecoveryMultiplier)
			return maximumRecoveryMultiplier;
		else
			return minimumRecoveryMultiplier;
	}
	
	public double getMaxShieldAmount(){
		if(maximumShield>=minimumShield)
			return maximumShield;
		else
			return minimumShield;
	}
	
	public String toString(){
		if(minimumComboCount == maximumComboCount){ // Non-scaling
			String effect = defineEffect(maximumAttackMultiplier, maximumRecoveryMultiplier, maximumShield, shieldAttributes);
			return EnumAttribute.printAttributes(affectedAttributes)+ " attribute cards " + effect + " when reaching " + maximumComboCount + " combos.";
		}
		if(minimumComboCount + 1 == maximumComboCount){ // Exactly
			if(maximumAttackMultiplier == 1.0 && maximumRecoveryMultiplier == 1.0 && maximumShield == 0.0 && (minimumAttackMultiplier != 1.0 || minimumRecoveryMultiplier != 1.0 || minimumShield != 0.0)){
				String effect = defineEffect(minimumAttackMultiplier, minimumRecoveryMultiplier, minimumShield, shieldAttributes);
				return EnumAttribute.printAttributes(affectedAttributes)+ " attribute cards " + effect + " when reaching exactly " + maximumComboCount + " combos.";
			}
		}
		// Else scaling
		String minEffect = defineEffect(minimumAttackMultiplier, minimumRecoveryMultiplier, minimumShield, shieldAttributes);
		
		int diff = maximumComboCount - minimumComboCount;
		double scaleATK = (maximumAttackMultiplier - minimumAttackMultiplier) / diff;
		double scaleRCV = (maximumRecoveryMultiplier - minimumRecoveryMultiplier) / diff;
		double scaleShield = (maximumShield - minimumShield) / diff;
		String scaleEffect = "";
		if(scaleATK != 0.0)
			scaleEffect = "ATK x" + scaleATK;
		if(scaleRCV != 0.0){
			if(scaleEffect != "")
				scaleEffect = ", RCV x" + scaleRCV;
			else
				scaleEffect = "RCV x" + scaleRCV;
		}
		if(scaleShield != 0.0){
			if(scaleEffect != "")
				scaleEffect = ", " + (scaleShield * 100.0) + "% damage reduction";
			else
				scaleEffect = (scaleShield * 100.0) + "% damage reduction";
		}
		
		String maxEffect = defineEffect(maximumAttackMultiplier, maximumRecoveryMultiplier, maximumShield, shieldAttributes);
		
		return EnumAttribute.printAttributes(affectedAttributes)+ " attribute cards " + minEffect + " at " + minimumComboCount + " combos. " + scaleEffect + " for each additional combo, up to " + maxEffect + " at " + maximumComboCount + " combos.";
	}
	
	public static String defineEffect(double attack, double recovery, double shield, EnumAttribute[] shieldAtt){
		String effect = "";
		if(attack != 1.0)
			effect = "ATK x" + attack;
		if(recovery != 1.0){
			if(effect != "")
				effect = ", RCV x" + recovery;
			else
				effect = "RCV x" + recovery;
		}
		if(shield != 0.0){
			if(effect != "")
				effect = ", " + (shield * 100.0) + "% " + EnumAttribute.printAttributes(shieldAtt) + " damage reduction";
			else
				effect = (shield * 100.0) + "% " + EnumAttribute.printAttributes(shieldAtt) + " damage reduction";
		}
		return effect;
	}
	
}
