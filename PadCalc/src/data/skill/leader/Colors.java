package data.skill.leader;

import data.Dungeon;
import data.EnumAttribute;
import data.EnumOrbType;

public class Colors extends LeaderSkillEffect {
	public static String name = "Colors";
	public static String regex = " when attacking with | when reaching ";// The leader skills are worded in so many ways it's hard to put in one regex, these seem to be a common phrases they all have.
	public EnumOrbType[] colors = {};
	public EnumAttribute[] shieldAttributes = {EnumAttribute.fire, EnumAttribute.water, EnumAttribute.wood, EnumAttribute.light, EnumAttribute.dark};
	public int minimumColorCount = 1;
	public double minimumAttackMultiplier = 1.0;
	public double minimumRecoveryMultiplier = 1.0;
	public double minimumShield = 0.0;
	public int maximumColorCount = 1;
	public double maximumAttackMultiplier = 1.0;
	public double maximumRecoveryMultiplier = 1.0;
	public double maximumShield = 0.0;
	
	// eg 1 Non-Scaling (Haku)  "All attribute cards ATK x4.5 when attacking with 2 of following orb types: Fire, Water & Dark"
	// eg 2 Non-Scaling (Ra)  "All attribute cards ATK x5, RCV x2 when attacking with Fire, Water, Wood, Light & Dark orb types at the same time."
	// eg 3 Scaling (Ra Dragon)  "All attribute cards ATK x2 when attacking with 4 of following orb types: Fire, Water, Wood, Light, Dark & Heart. ATK x3 for each additional orb type, up to ATK x8 for all 6 matches."
	public static String regexRainbows = "(?:\\. )?(?<affectedElements>(?:All|(?:(?:Fire)?(?:, | & | and )?(?:Water)?(?:, | & | and )?(?:Wood)?(?:, | & | and )?(?:Light)?(?:, | & | and )?(?:Dark)?))) attribute cards "+
	                                       "(?:ATK x(?<minATK>[0-9.]+))?(?: |, )?(?:RCV x(?<minRCV>[0-9.]+))?(?: |, )?(?:(?<minReduction>\\d+)% all damage reduction)? when attacking with "+
	                                       "(?:(?:(?<minColors>\\d+) of following orb types: (?<colorGroup>(?:(?:Fire)?(?:, | & | and )?(?:Water)?(?:, | & | and )?(?:Wood)?(?:, | & | and )?(?:Light)?(?:, | & | and )?(?:Dark)?(?:, | & | and )?(?:Heart)?))\\."+
	                                       "(?: (?:ATK x(?<scaleATK>[0-9.]+))?(?: |, )?(?:RCV x(?<sacleRCV>[0-9.]+))?(?: |, )?(?:(?<scaleReduction>\\d+)% all damage reduction)? for each additional orb type, up to "+
	                                       "(?:ATK x(?<maxATK>[0-9.]+))?(?: |, )?(?:RCV x(?<maxRCV>[0-9.]+))?(?: |, )?(?:(?<maxReduction>\\d+)% all damage reduction)? for all (?<maxColors>\\d+) matches\\.)?)"+
	                                       "|(?:(?<colorsAll>(?:(?:Fire)?(?:, | & | and )?(?:Water)?(?:, | & | and )?(?:Wood)?(?:, | & | and )?(?:Light)?(?:, | & | and )?(?:Dark)?(?:, | & | and )?(?:Heart)?)) orb types at the same time\\.))";
	
	// eg 4 Non-Scaling (Dantalion)  "All attribute cards ATK x3, 25% all damage reduction when attacking with Heart and Heart combos at the same time."
	// eg 5 Non-Scaling (Ilmina)  "All attribute cards ATK x4.5, 25% all damage reduction when attacking with Light and Fire combos at the same time."
	// eg 6 Non-Scaling (Kenshin)  "All attribute cards ATK x2, 25% all damage reduction when attacking with Fire and Light combos at the same time."
	// Although the order is not consistent (see Ilmina and Kenshin) however there are only ever two orb types for this regex.
	public static String regexNonScalingDuo = "(?:\\. )?(?<affectedElements>(?:All|(?:(?:Fire)?(?:, | & | and )?(?:Water)?(?:, | & | and )?(?:Wood)?(?:, | & | and )?(?:Light)?(?:, | & | and )?(?:Dark)?))) attribute cards "+
                                              "(?:ATK x(?<atk>[0-9.]+))?(?: |, )?(?:RCV x(?<rcv>[0-9.]+))?(?: |, )?(?:(?<reduction>\\d+)% all damage reduction)? when attacking with "+
	                                          "(?:<colorA>(?:Fire|Water|Wood|Light|Dark|Heart)) and (?:<colorB>(?:Fire|Water|Wood|Light|Dark|Heart)) combos at the same time\\.";
	
	// eg 7 Non-Scaling (Lead Ilm)  "All attribute cards ATK x4 when reaching Light, Light & Fire or Light & Fire & Fire combos."
	// eg 8 Scaling (Sub Ilm)  "All attribute cards ATK x3.5 when reaching Light, Light & Fire or Light & Fire & Fire combos. ATK x2.5 for each additional combo, up to ATK x6 when reaching Light, Light, Fire & Fire 4 combos combination."
	// eg 9 Non-Scaling (Rozuel)  "All attribute cards ATK x3 when reaching Fire & Fire or Fire & Water combos."
	public static String regexColorCombos = "";
	
	// eg 10 Scaling (Awoken Ganesha)  "All attribute cards ATK x3 when reaching 3 set of Light combos. ATK x3 for each additional combo, up to ATK x9 when reaching Light, Light, Light & Light & Light 5 combos combination."
	// eg 11 Scaling (Gadius)  "All attribute cards ATK x3 when reaching 1 set of Heart combo. ATK x3.5 for each additional combo, up to ATK x6.5 when reaching 2 combos."
	// eg 12 Scaling (Awoken U&Y)  "All attribute cards ATK x3 when reaching 2 set of Water combos. ATK x2 for each additional combo, up to ATK x5 when reaching 3 combos."
	public static String regexSingularCombos = "";
	
	public Colors(EnumOrbType[] colors, EnumAttribute[] shieldAttributes, int minimumColorCount, double minimumAttackMultiplier, double minimumRecoveryMultiplier, double minimumShield, int maximumColorCount, double maximumAttackMultiplier, double maximumRecoveryMultiplier, double maximumShield){
		this.colors = colors;
		if(shieldAttributes != new EnumAttribute[]{})
			this.shieldAttributes = shieldAttributes;
		this.minimumColorCount = minimumColorCount;
		this.minimumAttackMultiplier = minimumAttackMultiplier;
		this.minimumRecoveryMultiplier = minimumRecoveryMultiplier;
		this.minimumShield = minimumShield;
		this.maximumColorCount = maximumColorCount;
		if(this.maximumColorCount < this.minimumColorCount)
			this.maximumColorCount = this.minimumColorCount;
		this.maximumAttackMultiplier = maximumAttackMultiplier;
		this.maximumRecoveryMultiplier = maximumRecoveryMultiplier;
		this.maximumShield = maximumShield;
	}
	
	public Colors(EnumOrbType[] colors, EnumAttribute[] shieldAttributes, int colorCount, double attackMultiplier, double recoveryMultiplier, double shield){
		this(colors, shieldAttributes, colorCount, attackMultiplier, recoveryMultiplier, shield, colorCount, attackMultiplier, recoveryMultiplier, shield);
	}
	
	public double getAttackMutliplier(Dungeon dungeon){
		dungeon.board.getCombos(dungeon);
		
		return 0.0;
	}
	
	public static LeaderSkillEffect[] readLeaderSkill(String desc){
		
		return null;
	}
}
