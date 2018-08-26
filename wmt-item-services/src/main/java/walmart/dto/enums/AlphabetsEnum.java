package walmart.dto.enums;

import java.util.HashMap;
import java.util.Map;

public enum AlphabetsEnum {
	// alphabets 
	A('A', 'a'), B('B', 'b'), C('C', 'c'), D('D', 'd'), E('E', 'e'), F('F', 'F'),
	G('G', 'g'), H('H', 'h'), I('I', 'i'), J('J', 'j'), K('K', 'k'),  L('L', 'l'),
	M('M', 'm'), N('N', 'n'), O('O', 'o'), P('P', 'p'), Q('Q', 'q'), R('R', 'r'),
	S('S', 's'), T('T', 't'), U('U', 'u'), V('V', 'v'), W('W', 'w'), X('X', 'X'),
	Y('Y', 'y'), Z('Z', 'z');
	private char u, l;
	private static Map<Character, AlphabetsEnum> MAP = null;
	
	static {
		MAP = new HashMap<>();
		for(AlphabetsEnum cm : AlphabetsEnum.values()) {
			MAP.put(cm.u, cm);
			MAP.put(cm.l, cm);
		}
	}
	AlphabetsEnum(char u, char l) {
		this.u = u;
		this.l = l;
	}
	public static AlphabetsEnum find(char c) {
		return MAP.get(c);
		
	}
	public static AlphabetsEnum[] find(String query) {
		int len = query.length();
		AlphabetsEnum[] retArray = new AlphabetsEnum[len];
		for(int i = 0 ; i < len ; i++) {
			retArray[i] = find(query.charAt(i));
		}
		return retArray;
	}

}
