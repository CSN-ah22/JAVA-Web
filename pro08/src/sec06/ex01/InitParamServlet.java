package sec06.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InitParamServlet
 */
@WebServlet(
		urlPatterns = { //urlPaterns를 이용해 매핑 이름을 여러개 설정할 수 있습니다	
				"/sInit", 
				"/sinit2"
		}, 	
		initParams = { 	//@WebInitParam을 이용해 여러 개의 매개변수를 설정할 수 있습니다
				@WebInitParam(name = "email", value = "csa2676@naver.com"), 
				@WebInitParam(name = "tel", value = "010-1111-2222")
		})
public class InitParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String email = getInitParameter("email");
		String tel = getInitParameter("tel"); //설정한 매개변수의 name으로 값을 가져옵니다
		out.print("email:" + email+"<br>");
		out.print("tel:" + tel+"<br>");
	}

}
