package Webclass05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CalPrj")
public class Calc extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
	throws  IOException, ServletException{
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		int n1 = Integer.parseInt(req.getParameter("n1"));
		int n2 = Integer.parseInt(req.getParameter("n2"));
		String op = req.getParameter("op");
		
		int result = 0;
		
		switch(req.getParameter("op")) {
		case "+": result = n1+n2; break;
		case "-": result = n1+n2; break;
		case "*": result = n1+n2; break;
		case "/": result = n1+n2; break;
		
		}
		
//		if(op.equals("+")) {
//			result = n1+n2;
//		}else if(op.equals("-")) {
//			result = n1-n2;
//		}else if(op.equals("/")) {
//			result = n1/n2;
//		}else {
//			result = n1*n2;
//		}
		
		out.append("<html><body>계산기 서블릿</h2><hr>")
		.append("계산 결과:"+result+"</body></html>");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		doGet(req,resp);
		
	}

}
