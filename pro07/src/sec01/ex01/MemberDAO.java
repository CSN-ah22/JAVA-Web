package sec01.ex01;

import java.sql.*;
import java.util.*;
import java.util.Date;


public class MemberDAO {
	
	private Connection con;
	private Statement stmt;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "scott";
	private String pwd = "tiger";
	
	public List listMembers(){
		List list = new ArrayList();
		try
		{
			connDB();//DB����
			
			String query = "select * from t_member";
			System.out.println(query);
			
			ResultSet rs = stmt.executeQuery(query); //���ǹ� �����ϴ� �� => ȸ�� ������ ��ȸ
			
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
			stmt.close();
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	private void connDB() {
		try {
			//����̹� �о����
			Class.forName(driver);
			System.out.println("����̹� �б� ����");
			//������ ���̽� ����
			con = DriverManager.getConnection(url, user, pwd);
			DriverManager.getConnection(url, user, pwd);
			System.out.println("������ ���̽� ���� ����");
		
		stmt = con.createStatement();
			System.out.println("statement ���� ����");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
