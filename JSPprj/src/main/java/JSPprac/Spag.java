package JSPprac;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
	    String num_ = request.getParameter("n");
	    
	    
	    if(num_ != null&&num_.equals("")) {
	    	
	    	num = Integer.parseInt(num_);
	    }
	    	String result ;
	    	
	    	if(num%2 ==0) 
	    		result = "짝수";
	    	else 
	    		result = "홀수";
	    
	    	request.setAttribute("result", result);
	    	
	    	String[] names = {"newlec","dragon"};
	    	request.setAttribute("names", names);
	    	
	    	Map<String, Object> notice = new HashMap<String, Object>();
	    	notice.put("id", 1);
	    	notice.put("title", "EL은 좋아요");
	    	request.setAttribute("notice", notice);
	    	
	    	
	    	
	    	//redirect - 새로운 요청.
	    	//forward - 이어간다.
	    	RequestDispatcher dispatcher
	    		= request.getRequestDispatcher("MVC2.jsp");  	
	  
	    	dispatcher.forward(request, response);
	    
	}
}
