package gateprotect;

import java.io.UnsupportedEncodingException;
import gateprotect.Funktionen;

public class Nummern {
	
	// erhalten 1-12 nummern
	public int erhalten_1_12(String txt) throws UnsupportedEncodingException {
		
		int num = 0;
		
		// numbers and names from zero to twelve (fixed names)
		String[] tNum = {"eins","zwei","drei","vier","fünf","sechs","sieben","acht","neun","zehn","elf","zwölf"};
		int[] nNum = {1,2,3,4,5,6,7,8,9,10,11,12};
		
		for(int i = 0; i < tNum.length; i++) {
			if(tNum[i].equals(Funktionen.makeUTF8(txt))) {
				num = nNum[i];
			}
		}
		
		// result
		return num;
		
	} // end erhalten 1-12
	
	// erhalten 13-19 nummern
	public int erhalten_13_19(String txt) throws UnsupportedEncodingException {
		
		int num = 0;
		String strNum;
		
		String[] tNum = {"dreizehn","vierzehn","fünfzehn","sechzehn","siebzehn","achtzehn","neunzehn"};
		int[] nNum = {13,14,15,16,17,18,19};
		
		for(int i = 0; i < tNum.length; i++) {
			strNum = Funktionen.makeUTF8(tNum[i]);
			if(strNum.equals(Funktionen.makeUTF8(txt))) {
				num = nNum[i];
			}
		}
		
		// result
		return num;		
		
	} // end erhalten 13-19
	
	// erhalten 20-29 nummern
	public int erhalten_20_29(String txt) throws UnsupportedEncodingException {
		
		String strNum;
		int num = 0;
						
		String[] tNum = {"zwanzig","einundzwanzig","zweiundzwanzig","dreiundzwanzig","vierundzwanzig","fünfundzwanzig","sechsundzwanzig","siebenundzwanzig","achtundzwanzig","neunundzwanzig"};
		
		int[] nNum = {20,21,22,23,24,25,26,27,28,29};
		
		for(int i = 0; i < tNum.length; i++) {
			strNum = Funktionen.makeUTF8(tNum[i]);
			if(strNum.equals(Funktionen.makeUTF8(txt))) {
				num = nNum[i];
			}
		}			
				
		// result
		return num;		
		
	} // end erhalten 20-29
	
	// erhalten 30-39 nummern
	public int erhalten_30_39(String txt) throws UnsupportedEncodingException {
		
		int num = 0;
		String strNum = Funktionen.makeUTF8("dreißig");
		
		String[] tNum = {"ein","zwei","drei","vier","fünf","sechs","sieben","acht","neun"};
		int[] nNum = {1,2,3,4,5,6,7,8,9};
		
		if(strNum.equals(txt)) {
			num = 30; // actually dreissig
		} else {
			// etwas und dreissig
			for(int i = 0; i < tNum.length; i++) {
				strNum = Funktionen.makeUTF8(tNum[i]) + Funktionen.makeUTF8("unddreißig");
				if(strNum.equals(Funktionen.makeUTF8(txt))) {
					num = nNum[i] + 30; // 20 + etwas
				}
			}			
		}
		
		// result
		return num;		
		
	} // end erhalten 30-39
	
	// erhalten 40-99 nummern
	public int erhalten_40_99(String txt) throws UnsupportedEncodingException {
		
		int num = 0;
		String strNum;
				
		// tens prefix
		String[] tTens = {"vier","fünf","sech","sieb","acht","neun"};
		int[] nTens = {40,50,60,70,80,90};
		
		String[] tNum = {"ein","zwei","drei","vier","fünf","sechs","sieben","acht","neun"};
		int[] nNum = {1,2,3,4,5,6,7,8,9};
		
		for(int i = 0; i < tTens.length; i++) {
			strNum = Funktionen.makeUTF8(tTens[i]) + "zig";
			if(strNum.equals(Funktionen.makeUTF8(txt))) {
				num = nTens[i];
			} else {
				// ten + etwas
				for(int j = 0; j < tNum.length; j++) {
					// nummer + und + ten + zig :: Example fünfundachtzig
					strNum = Funktionen.makeUTF8(tNum[j]) + "und" + Funktionen.makeUTF8(tTens[i]) + "zig";
					if(strNum.equals(Funktionen.makeUTF8(txt))) {
						num = nTens[i] + nNum[j];
					}
				}
			}
		}
				
		// result
		return num;		
		
	} // end erhalten 40-99
	
	// erhalten 100-999
	public int erhalten_100_999(String txt) throws UnsupportedEncodingException {
		
		int num = 0;
		int dec = 0;
		String strNum = null;
		String strDec = null;
						
		// hundreds prefix
		String[] tNum = {"einhundert","zweihundert","dreihundert","vierhundert","fünfhundert","sechshundert","siebenhundert","achthundert","neunhundert"};
		int[] nNum = {100,200,300,400,500,600,700,800,900};
		
		for(int i = 0; i < tNum.length; i++) {
			
			strNum = Funktionen.makeUTF8(tNum[i]);
			
			if(strNum.length() == txt.length()) {
		
				// exact hundred
				if(strNum.equals(Funktionen.makeUTF8(txt))) {				
					num = nNum[i];
				}
				
			} else if (txt.length() > strNum.length()) {
				
				// hundred + etwas
				
				// digit + hundert + 1-12
				if((txt.indexOf(strNum) == 0) && num == 0) {
					// etwas hundert + 1-12
					strDec = txt.substring(strNum.length());
					dec = this.erhalten_1_12(strDec);
					if(dec != 0) {
						num = dec + nNum[i];
					}				
				} // end hundert + 1-12
				
				// digit + hundert + 13-19
				if((txt.indexOf(strNum) == 0) && num == 0) {
					// etwas hundert + 13-19
					strDec = txt.substring(strNum.length());
					dec = this.erhalten_13_19(strDec);
					if(dec != 0) {
						num = dec + nNum[i];
					}				
				} // end hundert + 13-19
				
				// digit + hundert + 20-29
				if((txt.indexOf(strNum) == 0) && num == 0) {
					// etwas hundert + 20-29
					strDec = txt.substring(strNum.length());
					dec = this.erhalten_20_29(strDec);
					if(dec != 0) {
						num = dec + nNum[i];
					}
				} // end hundert + 20-29
				
				// digit + hundert + 30-39
				if((txt.indexOf(strNum) == 0) && num == 0) {
					// etwas hundert + 30-39
					strDec = txt.substring(strNum.length());
					dec = this.erhalten_30_39(strDec);
					if(dec != 0) {
						num = dec + nNum[i];
					}
				} // end hundert + 30-39
				
				// digit + hundert + 40-99
				if((txt.indexOf(strNum) == 0) && num == 0) {
					// etwas hundert + 40-99
					strDec = txt.substring(strNum.length());
					dec = this.erhalten_40_99(strDec);
					if(dec != 0) {
						num = dec + nNum[i];
					}
				} // end hundert + 40-99
				
			} // end if lengths comparison
			
		} // for tNum array
		
		return num;						
	} // end erhalten 100-999
	
	// erhalten 1000 - 999999
	public int erhalten_1000_999999(String txt) throws UnsupportedEncodingException {
		
		String str1000 = "tausend";
		int nPos1000; // thousand string beginning
		String strNum = "";
		int nThousandPrefix = 0; // thousand prefix (number)
		int num = 0; // result num
		String strSuffix = "";
		int nPosSuffix; // suffix position
		int nSuffix = 0; // suffix value
		
		//verify is a thousand number
		if(txt.indexOf("tausend") != -1) {
						
			// string length without "tausend"
			nPos1000 = txt.length() - str1000.length();
			
			// get thousand prefix
			strNum = txt.substring(0,nPos1000);
			
			// numbers 1-12
			nThousandPrefix = this.erhalten_1_12(strNum);
			
			// number 13-19
			if(nThousandPrefix == 0) {
				nThousandPrefix = this.erhalten_13_19(strNum);
			}
			
			// number 20-29
			if(nThousandPrefix == 0) {
				nThousandPrefix = this.erhalten_20_29(strNum);
			}
			
			// number 30-39
			if(nThousandPrefix == 0) {
				nThousandPrefix = this.erhalten_30_39(strNum);
			}
			
			// number 40-99
			if(nThousandPrefix == 0) {
				nThousandPrefix = this.erhalten_40_99(strNum);
			}
			
			// number 100-999
			if(nThousandPrefix == 0) {
				nThousandPrefix = this.erhalten_100_999(strNum);
			}
			
			// thousands value
			num = nThousandPrefix * 1000;
			
			if(txt.length() > (strNum.length() + str1000.length())) {
				
				// There is a suffix
				// suffix position
				nPosSuffix = strNum.length() + str1000.length();
				// suffix string
				strSuffix = txt.substring(nPosSuffix);
				
				// numbers 1-12
				nSuffix = this.erhalten_1_12(strSuffix);
				
				// number 13-19
				if(nSuffix == 0) {
					nSuffix = this.erhalten_13_19(strSuffix);
				}
				
				// number 20-29
				if(nSuffix == 0) {
					nSuffix = this.erhalten_20_29(strSuffix);
				}
				
				// number 30-39
				if(nSuffix == 0) {
					nSuffix = this.erhalten_30_39(strSuffix);
				}
				
				// number 40-99
				if(nSuffix == 0) {
					nSuffix = this.erhalten_40_99(strSuffix);
				}
				
				// number 100-999
				if(nSuffix == 0) {
					nSuffix = this.erhalten_100_999(strSuffix);
				}
				
				// add suffix value
				num = num + nSuffix;
				
			} // end there's suffix
					
		} // end thousand verification
				
		return num;
		
	} // end erhalten 1000-999999

} // CLASS END
