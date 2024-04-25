package co.yedam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManageDAO {
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	Date date;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
		
	private void getConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "jsp", "jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 대출도서목록 기능
	List<Manage> borrowList() {
		getConn();
		List<Manage> list = new ArrayList<>();
		String sql = "select m.book_no, b.book_name, l.mem_name, m.borrow_date "
				+ "from manage m join book b on m.book_no = b.book_no "
				+ "join lib_mem l on m.mem_no = l.mem_no "
				+ "order by m.mng_no";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				Manage mng = new Manage();
				mng.setBookNo(rs.getInt("book_no"));
				mng.setBookName(rs.getString("book_name"));
				mng.setMemName(rs.getString("mem_name"));
				date = rs.getDate("borrow_date");
				mng.setBorrowDate(sdf.format(date));

				list.add(mng);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;	
	}
	
	// 대출도서조회 기능
	List<Manage> searchBook(String bookName) {
		getConn();
		List<Manage> list = new ArrayList<>();
		String sql = "select m.book_no, b.book_name, l.mem_name, m.borrow_date "
				+ "from manage m join book b "
				+ "on m.book_no = b.book_no "
				+ "join lib_mem l "
				+ "on m.mem_no = l.mem_no "
				+ "where book_name like ?";
		try {
			psmt = conn.prepareStatement(sql);
			String param = "%" + bookName + "%";
			psmt.setString(1, param);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				Manage mng = new Manage();
				
				mng.setBookNo(rs.getInt("book_no"));
				mng.setBookName(rs.getString("book_name"));
				mng.setMemName(rs.getString("mem_name"));
				date = rs.getDate("borrow_date");
				mng.setBorrowDate(sdf.format(date));
				
				list.add(mng);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return list;
	}
	
	// 대출 기능
	boolean borrowBook(int bookNo, int memNo) {
		getConn();
		String sql1 = "select mem_no from lib_mem";
		String sql2 = "select book_no from book";
		String sql3 = "select book_no from manage";
		String sql4 = "insert into manage(mng_no, book_no, mem_no)"
				+ "values(mng_no_seq.nextval, ?, ?)";
		try {
			// 존재하는 회원인지 확인
			psmt = conn.prepareStatement(sql1);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				if(memNo == rs.getInt("mem_no")) {
					// 존재할 경우
					// 존재하는 책인지 확인
					psmt = conn.prepareStatement(sql2);
					rs = psmt.executeQuery();
					
					while(rs.next()) {
						if(bookNo == rs.getInt("book_no")) {
							// 존재할 경우 대출된 책인지 확인
							psmt = conn.prepareStatement(sql3);
							rs = psmt.executeQuery();
							
							while(rs.next()) {
								// 대출된 책일 경우
								if(bookNo == rs.getInt("book_no")) {
									System.out.println("[이미 대출된 책입니다]");
									return false;
								} else {
									// 대출 실행
									psmt = conn.prepareStatement(sql4);
									psmt.setInt(1, bookNo);
									psmt.setInt(2, memNo);
									
									int r = psmt.executeUpdate();
									
									if(r > 0) return true;
								}
							}
						}
					}
					System.out.println("[존재하지 않는 책입니다]");
					return false;
				}
			}
			System.out.println("[존재하지 않는 회원입니다]");
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 반납 기능
	boolean returnBook(int bookNo) {
		getConn();
		String sql = "delete from manage "
				+ "where book_no = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bookNo);
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
