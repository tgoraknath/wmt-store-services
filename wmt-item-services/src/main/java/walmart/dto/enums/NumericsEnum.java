package walmart.dto.enums;

import java.util.HashMap;
import java.util.Map;

public enum NumericsEnum {
	Zero('0'), One('1'), Two('2'), Three('3'),
	Four('4'), Five('5'), Six('6'), Seven('7'),
	Eight('8'), Nine('9');
	private static Map<Character, NumericsEnum> MAP = null;
	
	static {
		MAP = new HashMap<>();
		for(NumericsEnum im : NumericsEnum.values()) {
			MAP.put(im.c, im);
		}
	}
	private char c;
	NumericsEnum(char c) {
	 this.c = c;	
	}

	public static NumericsEnum find(char c) {
		return MAP.get(c);
		
	}
	public static NumericsEnum[] find(String query) {
		int len = query.length();
		NumericsEnum[] retArray = new NumericsEnum[len];
		for(int i = 0 ; i < len ; i++) {
			retArray[i] = find(query.charAt(i));
		}
		return retArray;
	}
}
