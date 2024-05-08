package com.yedam.common;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yedam.dao.EmpDAO;
import com.yedam.vo.EmpVO;

public class AppTest {
	public static void main(String[] args) {
		EmpDAO edao = new EmpDAO();
		
		// 등록
//		EmpVO evo = new EmpVO();
//		evo.setEmail("test@email.com");
//		evo.setEmpName("testName");
//		evo.setEmpPhone("02-1010-2020");
//		evo.setHireDate("2020-01-05");
//		evo.setSalary(4000);
//		
//		if(edao.insertEmp(evo)) {
//			System.out.println("등록성공");
//		} else {
//			System.out.println("등록실패");
//		}
		
		// 수정
//		EmpVO evo = new EmpVO();
//		evo.setEmail("testing@email.com");
//		evo.setSalary(4300);
//		evo.setEmpNo(21);
//		
//		if(edao.updateEmp(evo)) {
//			System.out.println("수정성공");
//		} else {
//			System.out.println("수정실패");
//		}
		
//		// 삭제
//		if(edao.deleteEmp(21)) {
//			System.out.println("삭제성공");
//		} else {
//			System.out.println("삭제실패");
//		}		
//		
//		// 목록
//		List<EmpVO> list = edao.selectList();
//		
//		for(EmpVO vo : list) {
//			System.out.println(vo.toString());
//		}
		
		Map<String, Integer> resultMap = edao.getCntPerDept();
		Set<String> keyset = resultMap.keySet();
		for(String key : keyset) {
			System.out.println("key: " + key + ", val: " + resultMap.get(key));
		}
	}
}
