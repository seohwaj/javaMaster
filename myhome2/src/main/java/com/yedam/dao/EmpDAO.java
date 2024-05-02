package com.yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yedam.common.DAO;
import com.yedam.vo.EmpVO;

public class EmpDAO extends DAO {
	public List<Map<String, Object>> empList(){
		List<Map<String, Object>> list = new ArrayList<>();
		
		conn();
		
		try {
			psmt = conn.prepareStatement("select * from emp");
			rs = psmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("사원번호", rs.getString("emp_no"));
				map.put("사원명", rs.getString("emp_name"));
				map.put("연락처", rs.getString("emp_phone"));
				map.put("이메일", rs.getString("email"));
				
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disCon();
		}
		
		return list;
	}
	
	// 목록 List<EmpVO> selectList()
	public List<EmpVO> selectList() {
		List<EmpVO> list = new ArrayList<>();
		
		conn();
		String sql = "select * from emp";
		
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				EmpVO evo = new EmpVO();
				
				evo.setEmpNo(rs.getInt("emp_no"));
				evo.setEmpName(rs.getString("emp_name"));
				evo.setEmpPhone(rs.getString("emp_phone"));
				evo.setEmail(rs.getString("email"));
				evo.setHireDate(rs.getString("hire_date"));
				evo.setSalary(rs.getInt("salary"));
				
				list.add(evo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disCon();
		}
		
		return list;
	} // end of selectList
	
	public EmpVO selectEmp(int empNo) {
		conn();
		String sql = "select * from emp where emp_no = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, empNo);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				EmpVO evo = new EmpVO();
				
				evo.setEmpNo(rs.getInt("emp_no"));
				evo.setEmpName(rs.getString("emp_name"));
				evo.setEmpPhone(rs.getString("emp_phone"));
				evo.setEmail(rs.getString("email"));
				evo.setHireDate(rs.getString("hire_date"));
				evo.setSalary(rs.getInt("salary"));
				
				return evo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disCon();
		}
		return null;
	}
	
	// 등록 boolean insertEmp(EmpVO evo)
	public boolean insertEmp(EmpVO evo) {
		conn();
		String sql = "insert into emp(emp_no, emp_name, emp_phone, email, hire_date, salary) "
				+ "values(?, ?, ?, ?, ?, ?)";
		String seqSql = "select emp_seq.nextVal from dual";
		int seq = 0;
		
		try {
			psmt = conn.prepareStatement(seqSql);
			rs = psmt.executeQuery();
			if(rs.next()) {
				seq = rs.getInt(1);
				evo.setEmpNo(seq); // 매개변수의 evo에 empNo 저장
			}
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			psmt.setString(2, evo.getEmpName());
			psmt.setString(3, evo.getEmpPhone());
			psmt.setString(4, evo.getEmail());
			psmt.setString(5, evo.getHireDate());
			psmt.setInt(6, evo.getSalary());
			int r = psmt.executeUpdate();
			
			if(r > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disCon();
		}
		return false;
	}
	
	// 수정 boolean updateEmp(EmpVO evo) // 이메일, 급여 변경
	public boolean updateEmp(EmpVO evo) {
		conn();
		String sql = "update emp set email = ?, salary = ? "
				+ "where emp_no = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, evo.getEmail());
			psmt.setInt(2, evo.getSalary());
			psmt.setInt(3, evo.getEmpNo());
			int r = psmt.executeUpdate();
			
			if(r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disCon();
		}
		return false;
	}
	
	// 삭제 boolean deleteEmp(int empNo)
	public boolean deleteEmp(int empNo) {
		conn();
		String sql = "delete from emp where emp_no = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, empNo);
			int r = psmt.executeUpdate();
			
			if(r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disCon();
		}
		return false;
	}
}
