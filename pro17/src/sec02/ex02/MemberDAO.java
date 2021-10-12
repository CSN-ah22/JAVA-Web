package sec02.ex02;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.*;

import sec02.ex02.MemberVO;


public class MemberDAO {
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;
	
	public MemberDAO()
	{
		try
		{
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<MemberVO> listMembers(){
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		try
		{
			conn = dataFactory.getConnection();
			String query = "select * from t_member order by joinDate desc";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(); //������ ������ ����ޱ�
			while(rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				
				MemberVO memberVO = new MemberVO(id, pwd, name, email, joinDate);
				memberList.add(memberVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		}catch(Exception es) {
			
		}
		
		return memberList;
	}
	
	public void addMember(MemberVO memberVO) {
		try {
			conn = dataFactory.getConnection();
			
			String id = memberVO.getId();
			String pwd = memberVO.getPwd();
			String name = memberVO.getName();
			String email = memberVO.getEmail();
			
			String query = "insert into t_member(id, pwd, name, email)" + "values (?,?,?,?)";
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public MemberVO findMember(String id) {
		MemberVO memInfo =null;
		try {
			conn = dataFactory.getConnection();
			String query = "select * from t_member where id=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			System.out.println(query);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String id1 = rs.getString("id");
			String pwd = rs.getString("pwd");
			String name = rs.getString("name");
			String email = rs.getString("email");
			Date joinDate = rs.getDate("joinDate");
			memInfo = new MemberVO(id1, pwd, name, email, joinDate);
			pstmt.close();
			conn.close();
		}catch(Exception ee) {
			ee.printStackTrace();
		}
		return memInfo;
	}
	
	public void modMember(MemberVO memberVO) {
		String id = memberVO.getId();
		String pwd = memberVO.getPwd();
		String name = memberVO.getName();
		String email = memberVO.getEmail();
		try {
			conn = dataFactory.getConnection();
			String query = "update t_member set pwd=?,name=?,email=? where id=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pwd);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setString(4, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}catch(Exception es) {
			
		}
	}
	
	public void delMember(String id) {
		try {
			conn = dataFactory.getConnection();
			String query = "delete from t_member" + " where id=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}catch(Exception p) {
			p.printStackTrace();
		}
	}
}