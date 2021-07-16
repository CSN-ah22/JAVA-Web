package sec03.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/second5")
public class SecondServlet5 extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		out.print("<html>");
		out.print("<body>");
		out.print("name :"+name+"<br>");
		out.print("dispatch¸¦ ÀÌ¿ëÇÑ foward ½Ç½ÀÀÔ´Ï´Ù");
		out.print("</body>");
		out.print("</html>");
	}

}
