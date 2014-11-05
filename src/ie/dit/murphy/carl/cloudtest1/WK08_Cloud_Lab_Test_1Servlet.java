package ie.dit.murphy.carl.cloudtest1;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class WK08_Cloud_Lab_Test_1Servlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");

		String sA, sB, sC, sD; // parameters as strings
		double dA=0d, dB=0d, dC=0d, dD=0d;    // parameters as doubles
		
		// get parameters
		sA = req.getParameter("A");
		sB = req.getParameter("B");
		sC = req.getParameter("C");
		sD = req.getParameter("D");
		
		// print to page
		resp.getWriter().println("Param A = "+sA+", Param B = "+sB+", Param C = "+sC+", Param D = "+sD);
		
		// call function to check/convert to double
		try {
			dA = parse(sA, "A");
			dB = parse(sB, "B");
			dC = parse(sC, "C");
			dD = parse(sD, "D");
		}
		catch (Exception e) {
			resp.getWriter().println(e.getMessage());
			return;
		}
		
		double dTot = 0;
		try {
			dTot = (dA - dB) * (dC-dD);
		} catch (Exception ex) {
			resp.getWriter().println("The total of (dA-dB) * (dC-dD) is too large to handle!");
			return;
		}
	
		resp.getWriter().println("The result is " + dTot);
		
	}
	
	public double parse(String s, String ParamName) throws Exception {
		double d;
		
		// handle nulls / spaces
		if (s == null) s = "";	
		s = s.trim();
		
		// Missing
		if (s == "" || s == null) {
			throw new Exception(String.format("Param %s is missing!", ParamName));
		}
		
		// out of range 
		 try {
			 d = Double.parseDouble(s);
		 } catch (NumberFormatException ex) {
			 throw new Exception(String.format("Param %s is not a valid number!", ParamName));
		 }
		 
		 return d;
	
	}
	
	
}
