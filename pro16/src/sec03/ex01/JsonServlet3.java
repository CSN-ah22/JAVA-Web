package sec03.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Servlet implementation class JsonServlet1
 */
@WebServlet("/json3")
public class JsonServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dohandle(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dohandle(request,response);
	}
	
	private void dohandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		
			JSONObject totalObject = new JSONObject();	//��
			JSONArray membersArray = new JSONArray();	//������
			JSONObject memberInfo = new JSONObject(); //����
			
			memberInfo.put("name","������");
			memberInfo.put("age","25");
			memberInfo.put("gender","����");
			memberInfo.put("nickname","��������");
			membersArray.add(memberInfo);
			
			memberInfo = new JSONObject();
			memberInfo.put("name","�迬��");
			memberInfo.put("age","21");
			memberInfo.put("gender","����");
			memberInfo.put("nickname","Įġ");
			membersArray.add(memberInfo);
			
			totalObject.put("members", membersArray);
		
			JSONArray bookArray = new JSONArray();
			JSONObject bookInfo = new JSONObject();
			bookInfo.put("title", "�ʺ��ڸ� ���� �ڹ� ���α׷���");
			bookInfo.put("writer", "�̺���");
			bookInfo.put("price", "30000");
			bookInfo.put("genre","IT");
			bookInfo.put("image", "http://localhost:8090/pro16/image/image1.jpg");
			bookArray.add(bookInfo);

			totalObject.put("books", bookArray);
			
			String jsonInfo = totalObject.toJSONString(); //totalObject�� ���ڿ��� ��ȯ
			System.out.print(jsonInfo);
			writer.print(jsonInfo);
		
	}

}