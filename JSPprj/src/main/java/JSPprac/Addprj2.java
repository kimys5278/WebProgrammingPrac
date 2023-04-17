package JSPprac;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Addprj2")
public class Addprj2 extends HttpServlet {
	protected void service(HttpServletRequest req,HttpServletResponse resp) 
	throws IOException,ServletException{
		
		//어플리케이션 저장소
		ServletContext app = req.getServletContext();
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String v_ = req.getParameter("v");
		String op = req.getParameter("oper");
		
		int v = 0;
		// 계산을 위한 조건 생성
		if(!v_.equals("")) {
			 v = Integer.parseInt(v_);
		 }
		
		if(op.equals("=")) {
			
			//x = 담은 값을 가져와 x에 저장(꺼내서 사용)
			int x = (Integer)app.getAttribute("value");
			int y =v;
			//oper = 담은 값을 가져와 operator에ㅔ 저장 (꺼내서 사용)
			String oper = (String)app.getAttribute("op");
			
			int result = 0;
			
			if(oper.equals("+")) {
				result = x+y;
			}else { 
				result = x-y;
			}
			
			resp.getWriter().printf("result is %d",result);
		}else {
			app.setAttribute("value",v);
			app.setAttribute("op",op);
		}
		//저장을 위한 조건 설정
		//오퍼레이터 입력이 덧셈 또는 뺄셈인 경우, 그냥 값 저장만 진행		
	}


}
