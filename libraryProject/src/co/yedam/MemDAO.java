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
			String url = "jdbc:oracle:thin:@192.168.0.7:1521:xe";
			conn = DriverManager.getConnection(url, "jsp", "jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 회원목록 기능
	List<Member> memList() {
		getConn();
		List<Member> list = new ArrayList<>();
		String sql = "select * from lib_mem order by mem_no";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				Member mem = new Member();
				mem.setMemNo(rs.getInt("mem_no"));
				mem.setName(rs.getString("mem_name"));
				mem.setPhone(rs.getString("mem_phone"));
				mem.setAddress(rs.getString("mem_addr"));
				
				list.add(mem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return list;
	}
	
	// 회원조회 기능
	List<Member> searchMem(String name){
		getConn();
		List<Member> list = new ArrayList<>();
		String sql = "select * from lib_mem "
				+ "where mem_name like ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, "%" + name + "%");
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				Member mem = new Member();
				mem.setMemNo(rs.getInt("mem_no"));
				mem.setName(rs.getString("mem_name"));
				mem.setPhone(rs.getString("mem_phone"));
				mem.setAddress(rs.getString("mem_addr"));
				
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
		String sql = "insert into lib_mem(mem_no, mem_name, mem_phone, mem_addr) "
				+ "values(lib_mem_seq.nextval, ?, ?, ?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, mem.getName());
			psmt.setString(2, mem.getPhone());
			psmt.setString(3, mem.getAddress());
			
			int r = psmt.executeUpdate();
			
			if(r > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 회원수정 기능
	boolean updateMem(Member mem) {
		getConn();
		String sql = "update lib_mem "
				+ "set mem_name = nvl2(?, ?, mem_name), "
				+ "mem_phone = nvl2(?, ?, mem_phone), "
				+ "mem_addr = nvl2(?, ?, mem_addr) "
				+ "where mem_no = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, mem.getName());
			psmt.setString(2, mem.getName());
			psmt.setString(3, mem.getPhone());
			psmt.setString(4, mem.getPhone());
			psmt.setString(5, mem.getAddress());
			psmt.setString(6, mem.getAddress());
			psmt.setInt(7, mem.getMemNo());
			int r = psmt.executeUpdate();
			
			if(r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return false;
	}
	
	// 회원삭제 기능
	boolean deleteMem(int memNo) {
		getConn();
		String sql = "delete from lib_mem "
				+ "where mem_no = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, memNo);
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
