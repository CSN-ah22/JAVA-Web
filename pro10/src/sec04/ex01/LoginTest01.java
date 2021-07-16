package sec04.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginTest
 */
@WebServlet("/login01")
public class LoginTest01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		LoginImpl01 loginUser=new LoginImpl01(user_id,user_pw);
		if(session.isNew()) {
			session.setAttribute("loginUser", loginUser);
		}
		out.println("<html>");
		
		out.println("<head>");
		out.println("<script type='text/javascript'>");
		out.println("setTimeout('history.go(0);',5000)");
	    out.println("</script>");
	    out.println("</head>");
		
		out.println("<body>");
		out.println("아이디는 "+loginUser.user_id+"<br>");
		out.println("총 접속자수는 "+LoginImpl01.total_user+"<br>");
		out.println("</body>");
		out.println("</html>");
	}

}
