package gateprotect;

import java.io.IOException;
import java.io.PrintWriter;
//import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Nummern Funktionen
import gateprotect.Nummern;

/**
 * Servlet implementation class TextProzess
 */
public class TextProzess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// textarea content
		String txtBerechnung = request.getParameter("berechnung");
		
		// each literal from the textarea
		String[] worte = txtBerechnung.split("\\s+");
		
		// number of members
		int mn = worte.length;
		
		// operations members
		String member[] = new String[worte.length];
		
		// numbers values
		int numval[] = new int[worte.length];
		
		// found switch
		boolean found;
		
		// Number string
		String strNum;
		
		// Result
		float nResult = 0;
		
		// page content
		PrintWriter out=response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append("<html><head><title>Folge</title></head>");
		sb.append("<body><h2>Folge</h2>");
		sb.append("<p>Betrieb: ");
		
		// search for operators and millions
		for (int i = 0; i < mn; i++) {
			
			member[i] = "nummer"; // by default
						
			// ask for operator
			if(worte[i].equals("plus") || worte[i].equals("+")) {
				member[i] = "+";
				continue;
			}
			
			if(worte[i].equals("minus") || worte[i].equals("-")) {
				member[i] = "-";
				continue;
			}
			
			if(worte[i].equals("mal") || worte[i].equals("*")) {
				member[i] = "*";
				continue;
			}
			
			if(worte[i].equals("geteilt")) {
				//member[i] = "/";
				member[i] = "";
				continue;
			}
			
			if(worte[i].equals("durch") || worte[i].equals("/")) {
				member[i] = "/";
				continue;
			}
			
			if(worte[i].indexOf("millionen") == 0) {
				member[i] = "millionen";
			}
			
		} // end for search operators
		
		// convert numbers from strings
		for (int i = 0; i < mn; i++) {
			
			// check if is a number
			if(member[i] == "nummer") {
				
				found = false;
				strNum = worte[i];
				Nummern num = new Nummern(); // instance number
				
				// check if actually is a number
				numval[i] = num.tryParse(strNum);
				
				// it's a literal.
				if(numval[i] == 0) {
				
				// check if a number of millions is (yoda be proud of me!) xD
				if( (i < (mn-1)) && (member[i+1] == "millionen") ) {
					
					// MILLIONS -> 1.000.000 - 9.999.999
					numval[i] = num.erhalten_1000000_9999999(strNum, worte[i+1]);
					if(numval[i] != 0) {
						found = true;
					} // end numbers 1000-999999
					
				}
								
				// number 0
				if(!found){
					if(strNum.equals("null")) {
						numval[i] = 0;
						found = true;
					}
				} // end number 0
				
				// numbers 1-12
				if(!found) {				
					numval[i] = num.erhalten_1_12(strNum);
					if(numval[i] != 0) {
						found = true;
					}
				} // end numbers 1-12
				
				// numbers 13-19
				if(!found) {			
					numval[i] = num.erhalten_13_19(strNum);
					if(numval[i] != 0) {
						found = true;
					}
				} // end numbers 13-19
				
				// numbers 20-29
				if(!found) {				
					numval[i] = num.erhalten_20_29(strNum);
					if(numval[i] != 0) {
						found = true;
					}
				} // end numbers 20-29
				
				// numbers 30-39
				if(!found) {				
					numval[i] = num.erhalten_30_39(strNum);
					if(numval[i] != 0) {
						found = true;
					}
				} // end numbers 30-39
				
				// numbers 40-99
				if(!found) {				
					numval[i] = num.erhalten_40_99(strNum);
					if(numval[i] != 0) {
						found = true;
					}
				} // end numbers 40-99
				
				// numbers 100-999
				if(!found) {				
					numval[i] = num.erhalten_100_999(strNum);
					if(numval[i] != 0) {
						found = true;
					}
				} // end numbers 100-999
				
				// numbers 1000-999999
				if(!found) {				
					numval[i] = num.erhalten_1000_999999(strNum);
					if(numval[i] != 0) {
						found = true;
					}
				} // end numbers 1000-999999
				
				} // if(numval[i] == 0)
				
			} // end check number
			
		} // end convert numbers from strings
		
		// show operation
		for( int i = 0; i < mn; i++ ) {
			
			// ask for data type
			if(member[i].equals("nummer")) {
				// nummern
				sb.append(numval[i]);
			} else {
				// operators
				if( member[i] != "million" && member[i] != "millionen" ) {
					sb.append(member[i]);
				}				
			}			
		} // end show operation
		
		// calculate
		for( int i = 0; i < mn; i++ ) {
			
			// ask if the first number is
			if( nResult == 0 ) {
				
				// it's the first number
				if( member[0] == "nummer") {
					nResult = numval[i];
				}
				// first number is negative
				if( (member[0] == "-") && (member[1] == "nummer") ) {
					nResult = numval[i] * -1;
				}
				
			} else {
				
				// nummer ?
				if( member[i] == "nummer" ) {
					
					// plus
					if( member[i-1] == "+" ) {
						nResult = nResult + numval[i];
					}
					// minus
					if( member[i-1] == "-" ) {
						nResult = nResult - numval[i];
					}
					// mal
					if( member[i-1] == "*" ) {
						nResult = nResult * numval[i];
					}
					// geteilt durch -> // (double) :: numval > 0 to avoid divided by zero error
					if( (member[i-1] == "/") && (member[i-2] == "") && (numval[i] > 0)) {
						nResult = nResult / numval[i];
					}
				}
				
			}					
			
		} // end calculate
		
		sb.append("<p>Result: ");
		sb.append(nResult);
		sb.append("<p><a href=rechner>Back</a>");
		sb.append("</body></html>");
		out.println(sb);
		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}