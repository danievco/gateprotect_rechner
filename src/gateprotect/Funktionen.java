package gateprotect;

import java.io.UnsupportedEncodingException;

public class Funktionen {
	
	// convert string to UTF-8
	public static String makeUTF8(final String toConvert) throws UnsupportedEncodingException {
		return new String(toConvert.getBytes("UTF-8"), "UTF-8");
	}

}
