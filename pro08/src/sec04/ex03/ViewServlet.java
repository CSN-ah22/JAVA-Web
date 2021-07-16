package sec04.ex03;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ViewServlet
 */
@WebServlet("/viewMembers")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		List membersList = (List)request.getAttribute("membersList");
		
		out.print("<html>");
		out.print("<body>");
		out.print("<table border=1>");
		out.print("<tr align='center' bgcolor='lightgreen'>");
		out.print("<td>���̵�</td>");
		out.print("<td>��й�ȣ</td>");
		out.print("<td>�̸�</td>");
		out.print("<td>�̸���</td>");
		out.print("<td>������</td>");
		out.print("<td>����</td>");
		out.print("</tr>");
		
for(int i=0 ; i<membersList.size(); i++) {
			
			MemberVO memberVO = (MemberVO)membersList.get(i);
			
			String id = memberVO.getId();
			String pwd = memberVO.getPwd();
			String name = memberVO.getName();
			String email = memberVO.getEmail();
			Date joinDate = memberVO.getJoinDate();
			
			out.print("<tr>");
			out.print("<td>"+id+"</td>");
			out.print("<td>"+pwd+"</td>");
			out.print("<td>"+name+"</td>");
			out.print("<td>"+email+"</td>");
			out.print("<td>"+joinDate+"</td>");
			out.print("<td><a href='/pro08/viewservlet?command=delMember&id="+id+"'>����</a></td>");
			out.print("</tr>");
		}
		out.print("</table>");
		out.print("</body></html>");
		out.print("<a href='/pro08/memberForm.html'>�� ȸ�� ����ϱ�</a>");
	}

}
