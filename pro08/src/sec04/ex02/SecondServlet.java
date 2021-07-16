package sec04.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/second")
public class SecondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String address = (String)request.getAttribute("address");
		out.print("<html>");
		out.print("<body>");
		out.print("address :"+address+"<br>");
		out.print("sendredirect�� �̿��� binding �ǽ��Դϴ�");
		out.print("</body>");
		out.print("</html>");
	}

}
