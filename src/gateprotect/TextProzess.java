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
// Betreiber Funktionen
//import gateprotect.Betreiber;

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
		
		// found switch
		boolean found;
		int nNum; // nummer
		char sOper; // betreiber
		String strNum; // number string
		
		// math operators :: add divided by -> geteilt durch
		String[] txtbetreiber = {"plus","minus","mal"};
		char[] betrieber = {'+','-','*'};
		
		// equivalent numbers from literals
		int numop[] = new int[worte.length];
		
		// ocurrencies counter
		int nn = 0;
		// string operator
		String LastOperator = "NEIN";
		// here goes the result
		int nResult = 0;
		
		PrintWriter out=response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append("<html><head><title>Folge</title></head>");
		sb.append("<body><h2>Folge</h2>");
		sb.append("<p>Betrieb: ");
		sb.append("<p>");
		
		for (String wort : worte) {
			
			found = false;
			Nummern num = new Nummern(); // instance number
			//Betreiber oper = new Betreiber(); // instance operator
			
			// ask if is an operator
			if(wort.equals("plus") || wort.equals("minus") || wort.equals("mal")) {
				LastOperator = wort;
				if(wort.equals("plus")) sb.append("+");
				if(wort.equals("minus")) sb.append("-");
				if(wort.equals("mal")) sb.append("*");
				continue;
			}
			
			/*
			// search for an operator
			sOper = oper.erhalten_betreiber(wort);
			if((char)sOper != 'N') {
				// operator found
				numop[nn] = sOper;
				found = true;
				sb.append((char)numop[nn]);
				nn++;
				continue;
			}
			*/
						
			// number 0
			if(!found){
				if(wort.equals("null")) {
					numop[nn] = 0;
					found = true;
				}
			} // end number 0
			
			// numbers 1-12
			if(!found) {				
				nNum = num.erhalten_1_12(wort);
				if(nNum != 0) {
					numop[nn] = nNum;
					found = true;
				}
			} // end numbers 1-12
			
			// numbers from 13 to 19
			if(!found) {
				nNum = num.erhalten_13_19(wort);
				if(nNum != 0) {
					numop[nn] = nNum;
					found = true;					
				}
			} // end numbers 13-19
			
			// numbers 20-29
			if(!found) {
				nNum = num.erhalten_20_29(wort);
				if(nNum != 0) {
					numop[nn] = nNum;
					found = true;					
				}				
			} // end numbers 20-29
			
			// numbers 30-39
			if(!found) {
				nNum = num.erhalten_30_39(wort);
				if(nNum != 0) {
					numop[nn] = nNum;
					found = true;					
				}				
			} // end numbers 30-39
			
			// numbers from 40 to 99
			if(!found) {
				nNum = num.erhalten_40_99(wort);
				if(nNum != 0) {
					numop[nn] = nNum;
					found = true;					
				}				
			} // found 40-99
			
			// numbers 100-900
			if(!found) {
				nNum = num.erhalten_100_999(wort);
				if(nNum != 0) {
					numop[nn] = nNum;
					found = true;
				}				
			} // end numbers 100-900
			
			// numbers 1000-900000
			if(!found) {
				nNum = num.erhalten_1000_999999(wort);
				if(nNum != 0) {
					numop[nn] = nNum;
					found = true;
				}				
			} // end numbers 1000-900000
			
			// NUMBER WAS FOUND!
			if(found) {
				sb.append(numop[nn]);
				nn++;
											
				if(LastOperator.equals("plus")) {
					nResult = nResult + numop[nn];
				}
				if(LastOperator.equals("minus")) {
					nResult = nResult - numop[nn];
				}
				if(LastOperator.equals("mal")) {
					nResult = nResult * numop[nn];
				}
			}
			
		} // end for worte array
		
		// operation result
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