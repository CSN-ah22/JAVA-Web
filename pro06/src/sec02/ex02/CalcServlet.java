package sec02.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalcServlet
 */
@WebServlet("/calc")
public class CalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @param pw 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw1 = response.getWriter();
		
		String command = request.getParameter("command");
		String won = request.getParameter("won");
		String operator = request.getParameter("operator");
		
		if(command != null && command.equals("calculate")) {
			String result = calculate(Float.parseFloat(won), operator);
			pw1.print("<html><font size=10>��ȯ ���</font><br>");
			pw1.print("<html><font size=10>"+ result + "</font><br>");
			pw1.print("<a href='/pro06/calc'>ȯ�� ����</a>");
			return;
		}
		
		pw1.print("<html><title>ȯ�� ����</title>");
		pw1.print("<font size=5>ȯ�� ����</font>");
		pw1.print("<form name='frmCalc\" method='get' action='/pro06/calc'>");
		pw1.print("��ȭ: <input type='text' name='won' size=10 />");
		pw1.print("<select name='operator'>");
		pw1.print("<option value='dollar'>�޷�</option>");
		pw1.print("<option value='en'>��ȭ</option>");
		pw1.print("<option value='wian'>����</option>");
		pw1.print("<option value='pound'>�Ŀ��</option>");
		pw1.print("<option value='euro'>����</option>");
		pw1.print("</select>");
		pw1.print("<input type='hidden' name='command' value='calculate' />");
		pw1.print("<input type='submit' value='��ȯ' />");
		pw1.print("</form>");
		pw1.print("</html>");
		pw1.close();
		
	}
	private static String calculate(float won, String operator) {
		String result = null;
		if(operator.equals("dollar")) {
			result = String .format("%.6f", won / 1100);
		} else if(operator.equals("en")) {
			result = String .format("%.6f", won / 10);
		} else if(operator.equals("wian")) {
			result = String .format("%.6f", won / 1200);
		}else if(operator.equals("pound")) {
			result = String .format("%.6f", won / 1000);
		}else if(operator.equals("euro")) {
			result = String .format("%.6f", won / 1100);
		}
		return result;
	}

}
