package data;

import java.util.BitSet;

public class BinaryData {
	BitSet bits; // These are the true bits (1's) in order from the largest value.
	int length; // This is the specified size of the binary data.
	int readIndex; // This is the current position from which the reader will read from.
	
	public BinaryData(){
		readIndex = 0;
		length = 0;
		bits = new BitSet(0);
	}
	
	public BinaryData(int value, int size){
		readIndex = 0;
		length = size;
		bits = new BitSet(0);
		int index = 1;
		while (value != 0 && length >= index) {
			if (value % 2 != 0) {
				bits.set(length-index);
			}
			++index;
			value = value >>> 1;
		}
	}
	
	public BinaryData(byte[] bytes, int size){
		readIndex = 0;
		length = size;
		bits = new BitSet(0);
		for (int b = 0; b < bytes.length; b++){
			
			//for testing say,
			// bytes = {00101011,01101010,10001011}
			// b =      00000000 11111111 22222222
			// i =      01234567 01234567 01234567
			
			for (int i=0; i<8; i++) 
			{
			    if ((bytes[b] & (1 << 7-i)) > 0)
			    {
			        bits.set(b*8+i);
			    }
			}
		}
	}
	
	public BinaryData(BitSet bits, int size){
		readIndex = 0;
		length = size;
		this.bits = bits;
	}
	
	public void reset(){
		readIndex = 0;
	}
	
	public void skip(int amount){
		readIndex = readIndex + amount;
	}
	
	public boolean get(int index){
		return bits.get(index);
	}
	
	public void append(int value, int size){
		int index = 1;
		while (value != 0 && size >= index) {
			if (value % 2 != 0) {
				bits.set(length+size-index);
			}
			++index;
			value = value >>> 1;
		}
		length = length + size;
	}
	
	public void append(BinaryData data){
		for (int i = 0; i < data.length; ++i) {
			bits.set(length+i,data.get(i));
		}
		length = length + data.length;
	}
	
	public BinaryData getNext(int amount){
		readIndex = readIndex + amount;
		return new BinaryData(bits.get(readIndex-amount, readIndex), amount);
	}
	
	public int toInt(){
		int value = 0;
		int l = length;
		if (l > 16)
			l = 16;
		for (int i = 0; i < l; ++i) {
			value += bits.get(i) ? (1 << l-i-1) : 0;
		}
		return value;
	}
	
	public byte[] toByteArray(){
		byte[] bytes = new byte[(int) Math.ceil(length/8.0)];
		
		for (int b = 0; b < bytes.length; b++){
			
			for (int i=0; i<8; i++) 
			{
				if(get(b*8+i)){
					bytes[b] |= (128 >> i);
				}
			}
		}
		return bytes;
	}
	
	public String toString(){
		String s = "len "+length+":";
		for(int i = 0; i < length; i++){
			if (i % 8 == 0)
				s= s+" ";
			s= bits.get(i) ? s+"1" : s+"0";
		}
		return s;
	}
}
