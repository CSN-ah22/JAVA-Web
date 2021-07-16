package sec02.ex01;

import java.sql.*;
import java.util.*;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MemberDAO {
	
	/*
	 * private String driver = "oracle.jdbc.driver.OracleDriver"; private String url
	 * = "jdbc:oracle:thin:@localhost:1521:xe"; private String user = "scott";
	 * private String pwd = "tiger";
	 */
	
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public MemberDAO(){
		
		try
		{
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List listMembers(){
		List list = new ArrayList();
		try
		{
//			connDB();//DB����
			con = dataFactory.getConnection();
			String query = "select * from t_member";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			//������ ���ڵ庰 ó��
			while (rs.next())
			{	//�÷��� ������ ��������
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				
				MemberVO vo = new MemberVO(); //vo��ü ����
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				
				list.add(vo);
				
			}
			rs.close();
			pstmt.close();
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
//	private void connDB() {
//		try {
//			//����̹� �о����
//			Class.forName(driver);
//			System.out.println("����̹� �б� ����");
//			//������ ���̽� ����
//			con = DriverManager.getConnection(url, user, pwd);
//			DriverManager.getConnection(url, user, pwd);
//			System.out.println("������ ���̽� ���� ����");
//		
////		stmt = con.createStatement();
////			System.out.println("statement ���� ����");
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
}
