package gateprotect;

public class Betreiber {
	
	// erhalten betreiber
	public char erhalten_betreiber(String txt) {
		
		char oper = 'N'; // no operator
		String[] tOper = {"plus","minus","mal"};
		char[] nOper = {'+','-','*'};
		
		for(int i = 0; i < tOper.length; i++) {
			if(tOper[i].equals(txt)) {
				oper = nOper[i];
			}
		}
		
		return oper;		
	}
	
} // CLASS END
