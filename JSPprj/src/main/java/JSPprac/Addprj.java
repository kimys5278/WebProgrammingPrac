package JSPprac;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Addprj")
public class Addprj extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
	throws IOException, ServletException{
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html ;charset = UTF-8");
		
		String x_ = req.getParameter("x");
		String y_ = req.getParameter("y");
		
		int x = 0;
		int y = 0;
		if(!x_.equals("")) {x = Integer.parseInt(x_);}
		if(!y_.equals("")) {y = Integer.parseInt(y_);}

		int result = x+y;
		resp.getWriter().printf("result is %d\n", result);
	}

}
