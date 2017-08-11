package data;

public class Guard {
	public final EnumGuardType guardType;
	public final double amount;
	public final int duration;
	
	public Guard(EnumGuardType type, double value, int length){
		guardType = type;
		amount = value;
		duration = length;
	}
}
