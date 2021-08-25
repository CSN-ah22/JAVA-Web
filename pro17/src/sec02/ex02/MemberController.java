package sec02.ex02;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/*") //���������� ��û�� �� �ܰ�� ��û�� �̷����
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberDAO memberDAO;
	
	public void init(ServletConfig config) throws ServletException {
		memberDAO = new MemberDAO();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String action = request.getPathInfo(); //URL���� ��û���� ������
		System.out.println("action:" + action);
		
		if(action == null || action.equals("/listMembers.do")) { //ȸ������ ��ȸ
		List<MemberVO> membersList = memberDAO.listMembers(); //��ȸ��ɽ���
		request.setAttribute("membersList", membersList); //��ȸ�� ���� request�� ���ε�
		nextPage = "/test03/listMembers.jsp";
		
		} else if(action.equals("/addMember.do")) {//ȸ������ �߰�
			//���� start
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVO memberVO = new MemberVO(id,pwd,name,email);
			//���� end
			memberDAO.addMember(memberVO);
			nextPage = "/member/listMembers.do";
		} else if(action.equals("/memberForm.do")) {
			nextPage = "/test03/MemberForm.jsp";
			
		} else if(action.equals("/modMemberForm.do")) {//ȸ������ ���������� �Ѿ�� ��
			String id=request.getParameter("id");
			MemberVO memInfo = memberDAO.findMember(id);//ID������ ���� �� ȸ������ ��ȸ
			request.setAttribute("memInfo", memInfo); //���� �� ȸ������ ���ε�
			nextPage="/test03/modMemberForm.jsp";
			
		} else if(action.equals("/modMember.do")) {//ȸ������ ����������
		     String id=request.getParameter("id");
		     String pwd=request.getParameter("pwd");
		     String name= request.getParameter("name");
	         String email= request.getParameter("email");
	         MemberVO memberVO = new MemberVO(id,pwd,name,email);
	         memberDAO.modMember(memberVO);
	         request.setAttribute("msg", "modified");
		     nextPage="/member/listMembers.do";
		}
		else if(action.equals("/delMember.do")) {
			String id = request.getParameter("id");
			memberDAO.delMember(id);
			request.setAttribute("msg", "deleted");
			nextPage="/member/listMembers.do";
		}
		else {
			List<MemberVO> membersList=memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/test03/listMembers.jsp";
		}
		
		//������
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}
