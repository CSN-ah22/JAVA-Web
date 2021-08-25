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

@WebServlet("/member/*") //브라우저에서 요청시 두 단계로 요청이 이루어짐
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
		
		String action = request.getPathInfo(); //URL에서 요청명을 가져옴
		System.out.println("action:" + action);
		
		if(action == null || action.equals("/listMembers.do")) { //회원정보 조회
		List<MemberVO> membersList = memberDAO.listMembers(); //조회기능실행
		request.setAttribute("membersList", membersList); //조회한 값을 request에 바인딩
		nextPage = "/test03/listMembers.jsp";
		
		} else if(action.equals("/addMember.do")) {//회원정보 추가
			//세팅 start
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVO memberVO = new MemberVO(id,pwd,name,email);
			//세팅 end
			memberDAO.addMember(memberVO);
			nextPage = "/member/listMembers.do";
		} else if(action.equals("/memberForm.do")) {
			nextPage = "/test03/MemberForm.jsp";
			
		} else if(action.equals("/modMemberForm.do")) {//회원정보 수정페이지 넘어가기 전
			String id=request.getParameter("id");
			MemberVO memInfo = memberDAO.findMember(id);//ID값으로 수정 전 회원정보 조회
			request.setAttribute("memInfo", memInfo); //수정 전 회원정보 바인딩
			nextPage="/test03/modMemberForm.jsp";
			
		} else if(action.equals("/modMember.do")) {//회원정보 수정페이지
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
		
		//포워딩
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}
