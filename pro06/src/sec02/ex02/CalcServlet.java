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
			pw1.print("<html><font size=10>변환 결과</font><br>");
			pw1.print("<html><font size=10>"+ result + "</font><br>");
			pw1.print("<a href='/pro06/calc'>환율 계산기</a>");
			return;
		}
		
		pw1.print("<html><title>환율 계산기</title>");
		pw1.print("<font size=5>환율 계산기</font>");
		pw1.print("<form name='frmCalc\" method='get' action='/pro06/calc'>");
		pw1.print("원화: <input type='text' name='won' size=10 />");
		pw1.print("<select name='operator'>");
		pw1.print("<option value='dollar'>달러</option>");
		pw1.print("<option value='en'>엔화</option>");
		pw1.print("<option value='wian'>위안</option>");
		pw1.print("<option value='pound'>파운드</option>");
		pw1.print("<option value='euro'>유로</option>");
		pw1.print("</select>");
		pw1.print("<input type='hidden' name='command' value='calculate' />");
		pw1.print("<input type='submit' value='변환' />");
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
