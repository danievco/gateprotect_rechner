package gateprotect;

import java.io.UnsupportedEncodingException;
import gateprotect.Funktionen;

public class Nummern {
	
	// try to get numeric value
	public Integer tryParse(String txt) {
		try {
			return new Integer(txt);
		} catch (NumberFormatException e) {
		    return 0;
		}
	
	} // end tryParse(string)
	
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
		
		int num = 0; // number to return
				
		String str1000 = "tausend";
		String strPrefix = "";
		String strSuffix = "";
		int nPos1000;
		int nPosSuffix;
		int nPrefix = 0;
		int nSuffix = 0;
		
		nPos1000 = txt.indexOf(str1000);
		
		// check if thousand
		if(nPos1000 != -1) {
			
			// check prefix
			if(nPos1000 > 0) {
				
				// prefix
				strPrefix = txt.substring(0,nPos1000);
				
				// numbers 1-12
				nPrefix = this.erhalten_1_12(strPrefix);
				
				// number 13-19
				if(nPrefix == 0) {
					nPrefix = this.erhalten_13_19(strPrefix);
				}
				
				// number 20-29
				if(nPrefix == 0) {
					nPrefix = this.erhalten_20_29(strPrefix);
				}
				
				// number 30-39
				if(nPrefix == 0) {
					nPrefix = this.erhalten_30_39(strPrefix);
				}
				
				// number 40-99
				if(nPrefix == 0) {
					nPrefix = this.erhalten_40_99(strPrefix);
				}
				
				// number 100-999
				if(nPrefix == 0) {
					nPrefix = this.erhalten_100_999(strPrefix);
				}
				
				// thousands value
				num = nPrefix * 1000;
				
				// check if there's a suffix
				if(txt.length() > nPos1000 + 7) {
					
					// there'es suffix
					nPosSuffix = nPos1000 + 7;
					strSuffix = txt.substring(nPosSuffix);
					
					// numbers 1-12
					nSuffix = this.erhalten_1_12(strSuffix);
					
					// numbers 13-19
					if(nSuffix == 0) {
						nSuffix = this.erhalten_13_19(strSuffix);
					}
					
					// numbers 20-29
					if(nSuffix == 0) {
						nSuffix = this.erhalten_20_29(strSuffix);
					}
					
					// numbers 30-39
					if(nSuffix == 0) {
						nSuffix = this.erhalten_30_39(strSuffix);
					}
					
					// numbers 40-99
					if(nSuffix == 0) {
						nSuffix = this.erhalten_40_99(strSuffix);
					}
					
					// numbers 100-999
					if(nSuffix == 0) {
						nSuffix = this.erhalten_100_999(strSuffix);
					}
					
					// cents and else value
					num = num + nSuffix;
					
				} // end suffix
				
			} // end prefix
			
		} // end check if thousand

		return num;
		
	} // end erhalten 1000-999999
	
	// erhalten 1.000.000 - 9.999.999
	public int erhalten_1000000_9999999(String txt, String txtM) throws UnsupportedEncodingException {
		
		// txt = number of millions prefix :: txtM = millions string
		
		int num = 0; // number to return
				
		// numbers and names for millions
		String[] tNum = {"zwei","drei","vier","fünf","sechs","sieben","acht","neun"};
		int[] nNum = {2,3,4,5,6,7,8,9};
		
		String strMillion = "millionen";
		String strSuffix = "";
		int nPosSuffix;
		int nSuffix = 0;
		
		// check millions string
		if( txtM.indexOf(strMillion) == 0) {
			
			if( txtM.length() > strMillion.length() ) {
				// millions plus else
				nPosSuffix = strMillion.length();
				strSuffix = txtM.substring(nPosSuffix);
				nSuffix = this.erhalten_1000_999999(strSuffix);
				if( nSuffix > 0) num = nSuffix;
			}
			
			// millions quantity
			for(int i = 0; i < tNum.length; i++) {
				if(tNum[i].equals(Funktionen.makeUTF8(txt))) {
					num = num + nNum[i] * 1000000;
				}
			}
		
		}
		
		// millions returned
		return num;
		
	} // end erhalten_1000000_9999999	

} // CLASS END