package gateprotect;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Rechner
 */
public class Rechner extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append("<html><head><title>Rechner</title></head>");
		sb.append("<body><h2>Rechner</h2>");
		sb.append("<p>Operation:</p>" );
		sb.append("<form id=frm name=frm method=get action=txtproc>");
		sb.append("<p><textarea name=berechnung cols=50 rows=3></textarea>");
		sb.append("<p><input name=Submit type=submit value=berechnen />");
		sb.append("</form></body></html>");
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
