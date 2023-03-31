import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Prac1")
public class Prac1 extends HttpServlet{
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
	throws IOException,ServletException{
		
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("UTF-8");
			
//			if(req.getMethod().equals("GET")) {
//				System.out.println("get요청입니다");
//			}else if(req.getMethod().equals("POST")){
//				System.out.println("post 요청입니다.");
//			}
	
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

				System.out.println("get 요청입니다.");
			
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
			System.out.println("post요청입니다.");
		
	}
}
