package guestbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;


@WebServlet("/post.nhn")
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private PostDAO dao;
	private ServletContext ctx;
	
	// 웹 리소스 기본 경로 지정
	private final String START_PAGE = "guestbook/postList.jsp";
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new PostDAO();
		ctx = getServletContext();		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		
		dao = new PostDAO();
		
		// 자바 리플렉션을 사용해 if, switch 없이 요청에 따라 구현 메서드가 실행되도록 함.
		Method m;
		String view = null;
		
		// action 파라미터 없이 접근한 경우
		if (action == null) {
			action = "listPost";
		}
		
		try {
			// 현재 클래스에서 action 이름과 HttpServletRequest 를 파라미터로 하는 메서드 찾음
			m = this.getClass().getMethod(action, HttpServletRequest.class);
			
			// 메서드 실행후 리턴값 받아옴
			view = (String)m.invoke(this, request);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			ctx.log("요청 action 없음!!");
			view = START_PAGE;
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		// POST 요청 처리후에는 리디렉션 방법으로 이동 할 수 있어야 함.
		if(view.startsWith("redirect:/")) {
			// redirect/ 문자열 이후 경로만 가지고 옴
			String rview = view.substring("redirect:/".length());
			response.sendRedirect(rview);
		} else {
			// 지정된 뷰로 포워딩, 포워딩시 컨텍스트경로는 필요없음.
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);	
		}
	}
	
    public String addPost(HttpServletRequest request) {
		Post p = new Post();
		try {
			BeanUtils.populate(p, request.getParameterMap());
			dao.addPost(p);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("뉴스 추가 과정에서 문제 발생!!");
			request.setAttribute("error", "뉴스가 정상적으로 등록되지 않았습니다!!");
			return listPost(request);
		}
		
		return "redirect:/post.nhn?action=listPost";
		
	}

	public String deletePost(HttpServletRequest request) {
    	int aid = Integer.parseInt(request.getParameter("aid"));
		try {
			dao.delPost(aid);
		} catch (SQLException e) {
			e.printStackTrace();
			ctx.log("뉴스 삭제 과정에서 문제 발생!!");
			request.setAttribute("error", "뉴스가 정상적으로 삭제되지 않았습니다!!");
			return listPost(request);
		}
		return "redirect:/post.nhn?action=listPost";
	}
	
	public String listPost(HttpServletRequest request) {
		List<Post> list;
		try {
			list = dao.getAll();
			request.setAttribute("post", list);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("뉴스 목록 생성 과정에서 문제 발생!!");
			request.setAttribute("error", "뉴스 목록이 정상적으로 처리되지 않았습니다!!");
		}
    	return "guestbook/postList.jsp";
    }
    
    public String getPost(HttpServletRequest request) {
    	int aid = Integer.parseInt(request.getParameter("aid"));
    	try {
    		Post p = dao.getPost(aid);
    		request.setAttribute("post", p);
    	} catch (SQLException e) {
			e.printStackTrace();
			ctx.log("뉴스를 가져오는 과정에서 문제 발생!!");
			request.setAttribute("error", "뉴스를 정상적으로 가져오지 못했습니다!!");
		}
    	return "guestbook/viewPost.jsp";
    }
    
    public String updatePost(HttpServletRequest request) {
		Post p = new Post();
		int aid = Integer.parseInt(request.getParameter("aid"));
		try {
			BeanUtils.populate(p, request.getParameterMap());
			dao.updatePost(p, aid);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("뉴스 추가 과정에서 문제 발생!!");
			request.setAttribute("error", "뉴스가 정상적으로 등록되지 않았습니다!!");
			return listPost(request);
		}
		
		return "redirect:/post.nhn?action=listPost";
		
	}
    
    public static String getBody(HttpServletRequest request) throws IOException {
    	 
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
 
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
 
        body = stringBuilder.toString();
        return body;
    }

}