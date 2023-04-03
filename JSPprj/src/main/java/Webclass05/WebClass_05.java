package Webclass05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/hello")
@WebServlet(name = "HelloWorld", description = "My Frist Servlet", urlPatterns = { "/hello" })
public class WebClass_05 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public WebClass_05() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html ; charset= UTF-8");
		PrintWriter out = response.getWriter();
		out.append("<!doctype html><html><head><title> Hello World Servlet </title></head><body>")
		.append("<h2>Hello World !! </h2><hr>")
		.append("현재 날씨와 시간은"+java.time.LocalDateTime.now())
		.append("</body></html>");
		
	}
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
