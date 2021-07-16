package sec04.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/guguTest3")
public class GuguTest3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init메서드 호출");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("destroy메서드 호출");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		int dan = Integer.parseInt(request.getParameter("dan"));
		out.println("<table border=1 width=800 align=center>");
		out.println("<tr align=center bgcolor='#FFFF66'>");
		out.println("<td colspan=4>"+dan+"단 출력 </td>");
		out.println("</tr>");
		
		for(int i=1; i<10; i++) {
			if(i%2==0) {
				out.print("<tr align=center bgcolor='#ACFA58'>");
			}else {
				out.print("<tr align=center bgcolor='#81BEF7'>");
			}

			out.println("<td width=200>");
			out.println("<input type='radio' />"+i);
			out.println("<input type='checkbox' />"+i);
			out.println("</td>");
			out.println("<td width=400>");
			out.println(dan+"*"+i);
			out.println("<td width=400>");
			out.println(i*dan);
			out.println("</td>");
			out.println("</tr>");
		}
		out.println("</table>");
	}

}
