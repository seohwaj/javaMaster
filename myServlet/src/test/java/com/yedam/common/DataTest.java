package com.yedam.common;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yedam.mapper.EmpMapper;
import com.yedam.vo.EmployeeVO;

public class DataTest {
	public static void main(String[] args) {
		SqlSessionFactory factory = DataSource.getInstance();
		SqlSession session = factory.openSession(true);
		
//		List<EmployeeVO> list = session.selectList("com.yedam.mapper.EmpMapper.selectEmp");
//		for(EmployeeVO evo : list) {
//			System.out.println(evo.toString());
//		}
		
		EmpMapper mapper = session.getMapper(EmpMapper.class);
		
//		EmployeeVO evo = new EmployeeVO();
//		evo.setEmployeeId(999);
//		evo.setFirstName("길동");
//		evo.setLastName("홍");
//		evo.setEmail("hong@email");
//		evo.setJobId("IT_PROG");
		
//		int r = mapper.deleteEmp(evo.getEmployeeId()); // mapper.insertEmp(evo); 
//		System.out.println(r + "건 처리");
		
//		session.commit(); // 커밋
		
//		List<EmployeeVO> list = mapper.selectEmp();
//		for(EmployeeVO e : list) {
//			System.out.println(e.toString());
//		}
		
	}
}
