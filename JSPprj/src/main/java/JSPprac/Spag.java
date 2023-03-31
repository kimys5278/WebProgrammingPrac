package JSPprac;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MVC2")
public class Spag extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	    int num = 0;
	    String num_ = request.getParameter("numm");
	    
	    if(num_ != null&&num_.equals("")) {
	    	
	    	num = Integer.parseInt(num_);
	    }
	    	String result ;
	    	
	    	if(num%2 != 0) 
	    		result = "홀수";
	    	else 
	    		result = "짝수";
	    
	    	request.setAttribute("result", result);
	    	//redirect
	    	//forward - 이어간다.
	    	RequestDispatcher dispatcher
	    		= request.getRequestDispatcher("MVC2.jsp");  	
	  
	    	dispatcher.forward(request, response);
	    
	}
}
