package sec02.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/member3")
public class MemberServlet3 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		MemberDAO dao = new MemberDAO();
		
		String command = request.getParameter("command");
		if(command != null && command.equals("addMember")) {
			String _id = request.getParameter("id");
			String _pwd = request.getParameter("pwd");
			String _name = request.getParameter("name");
			String _email = request.getParameter("email");
			
			MemberVO vo = new MemberVO();
			
			vo.setId(_id);
			vo.setPwd(_pwd);
			vo.setName(_name);
			vo.setEmail(_email);
			
			dao.addMember(vo);
		}
		else if(command !=null && command.equals("delMember")) {
			String id = request.getParameter("id");
			dao.delMember(id);
		}else {
			
		}
			
		
		
		
		
		List list = dao.listMembers();//DB�� ����, ����, ��� �޾Ƽ� ��� ��ü ��ȯ�ޱ�
		
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
		
		for(int i=0 ; i<list.size(); i++) {
			
			MemberVO memberVO = (MemberVO)list.get(i);
			
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
			out.print("<td><a href='/pro07/member3?command=delMember&id="+id+"'>����</a></td>");
			out.print("</tr>");
		}
		out.print("</table>");
		out.print("</body></html>");
		out.print("<a href='/pro07/memberForm.html'>�� ȸ�� ����ϱ�</a>");
	}

}
