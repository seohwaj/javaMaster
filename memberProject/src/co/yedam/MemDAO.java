package co.yedam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemDAO {
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	
	private void getConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "jsp", "jsp");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	// 회원목록 기능
	List<Member> memList() {
		getConn();
		List<Member> list = new ArrayList<>();
		String sql = "select * from member order by mem_no";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				Member mem = new Member();
				mem.setMemNo(rs.getInt("mem_no"));
				mem.setName(rs.getString("name"));
				mem.setPhone(rs.getString("phone"));
				mem.setBirth(rs.getString("birth"));
				mem.setSex(rs.getString("sex"));
				
				list.add(mem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 회원등록 기능
	boolean insertMem(Member mem) {
		getConn();
		String sql = "insert into member(mem_no, name, phone, birth, sex) "
				+ "values(mem_seq.nextval, ?, ?, ?, ?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, mem.getName());
			psmt.setString(2, mem.getPhone());
			psmt.setString(3, mem.getBirth());
			psmt.setString(4, mem.getSex());
			
			int r = psmt.executeUpdate();
			
			if(r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 정보수정 기능
	boolean updateMem(Member mem) {
		getConn();
		String sql = "update member "
				+ "set phone = ? "
				+ "where mem_no = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, mem.getPhone());
			psmt.setInt(2, mem.getMemNo());
			
			int r = psmt.executeUpdate();
			
			if(r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 정보삭제 기능
	boolean deleteMem(Member mem) {
		getConn();
		String sql = "delete from member "
				+ "where mem_no = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, mem.getMemNo());
			
			int r = psmt.executeUpdate();
			
			if(r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return false;
	}
}
