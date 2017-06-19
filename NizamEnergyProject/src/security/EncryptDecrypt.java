package security;

import java.util.HashMap;

public class EncryptDecrypt {

	public static String encrypt(String s) {
		HashMap<String, String> map =new HashMap<String, String>();
		map.put("a", "z");
		map.put("b", "y");
		map.put("c", "x");
		map.put("d", "w");
		map.put("e", "v");
		map.put("f", "u");
		map.put("g", "t");
		map.put("h", "s");
		map.put("i", "r");
		map.put("j", "q");
		map.put("k", "p");
		map.put("l", "o");
		map.put("m", "n");
		map.put("n", "m");
		map.put("o", "l");
		map.put("p", "k");
		map.put("q", "j");
		map.put("r", "i");
		map.put("s", "h");
		map.put("t", "g");
		map.put("u", "f");
		map.put("v", "e");
		map.put("w", "d");
		map.put("x", "c");
		map.put("y", "b");
		map.put("z", "a");
		
		map.put("A", "Z");
		map.put("B", "Y");
		map.put("C", "X");
		map.put("D", "W");
		map.put("E", "V");
		map.put("F", "U");
		map.put("G", "T");
		map.put("H", "S");
		map.put("I", "R");
		map.put("J", "Q");
		map.put("K", "P");
		map.put("L", "O");
		map.put("M", "N");
		map.put("N", "M");
		map.put("O", "L");
		map.put("P", "K");
		map.put("Q", "J");
		map.put("R", "I");
		map.put("S", "H");
		map.put("T", "G");
		map.put("U", "F");
		map.put("V", "E");
		map.put("W", "D");
		map.put("X", "C");
		map.put("Y", "B");
		map.put("Z", "A");
		
		map.put("0", "9");
		map.put("1", "8");
		map.put("2", "7");
		map.put("3", "6");
		map.put("4", "5");
		map.put("5", "4");
		map.put("6", "3");
		map.put("7", "2");
		map.put("8", "1");
		map.put("9", "0");
		map.put(" ", "@");
		map.put("@", " ");
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			str += map.get(""+s.charAt(i));
		}
		return str;
	}
	public static void main(String arg[]){
		
		System.out.println(encrypt("Zyyzh"));
	}
}
