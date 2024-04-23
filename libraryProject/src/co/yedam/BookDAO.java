package co.yedam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
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
		}
	}
	
	// 도서목록 기능
	List<Book> bookList() {
		getConn();
		List<Book> list = new ArrayList<>();
		String sql = "select * from book order by book_no";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				Book book = new Book();
				book.setBookNo(rs.getInt("book_no"));
				book.setName(rs.getString("book_name"));
				book.setWriter(rs.getString("book_writer"));
				book.setPublisher(rs.getString("book_pub"));
				
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 도서조회 기능
	List<Book> searchBook(String name) {
		getConn();
		List<Book> list = new ArrayList<>();
		String sql = "select * from book "
				+ "where book_name like ?";
		try {
			psmt = conn.prepareStatement(sql);
			String param = "%" + name + "%";
			psmt.setString(1, param);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				Book book = new Book();
				
				book.setBookNo(rs.getInt("book_no"));
				book.setName(rs.getString("book_name"));
				book.setWriter(rs.getString("book_writer"));
				book.setPublisher(rs.getString("book_pub"));
				
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	// 도서등록 기능
	boolean insertBook(Book book) {
		getConn();
		String sql = "insert into book(book_no, book_name, book_writer, book_pub) "
				+ "values(book_no_seq.nextval, ?, ?, ?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, book.getName());
			psmt.setString(2, book.getWriter());
			psmt.setString(3, book.getPublisher());
			
			int r = psmt.executeUpdate();
			
			if(r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 도서수정 기능
	boolean updateBook(Book book) {
		getConn();
		String sql = "update book "
				+ "set book_name = nvl2(?, ?, book_name), "
				+ "book_writer = nvl2(?, ?, book_writer), "
				+ "book_pub = nvl2(?, ?, book_pub) "
				+ "where book_no = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, book.getName());
			psmt.setString(2, book.getName());
			psmt.setString(3, book.getWriter());
			psmt.setString(4, book.getWriter());
			psmt.setString(5, book.getPublisher());
			psmt.setString(6, book.getPublisher());
			psmt.setInt(7, book.getBookNo());
			
			int r = psmt.executeUpdate();
			
			if(r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 도서삭제 기능
	boolean deleteBook(int bookNo) {
		getConn();
		String sql = "delete from book "
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
