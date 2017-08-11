package data;

public class Attack {
	public EnumAttackForm form;
	public EnumAttribute attribute;
	public boolean defenseBreak = false;
	public int attack;
	
	public Attack(EnumAttackForm form, EnumAttribute attribute,boolean defenseBreak, int attack){
		this.form = form;
		this.attribute = attribute;
		this.defenseBreak = defenseBreak;
		this.attack = attack;
	}
}
