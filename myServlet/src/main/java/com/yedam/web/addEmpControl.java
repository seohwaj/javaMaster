package com.yedam.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.Control;
import com.yedam.common.DataSource;
import com.yedam.mapper.EmpMapper;
import com.yedam.vo.EmployeeVO;

public class addEmpControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String a = req.getParameter("eid");
		String b = req.getParameter("first_name");
		String c = req.getParameter("last_name");
		String d = req.getParameter("email");
		String e = req.getParameter("job");
		
		EmployeeVO evo = new EmployeeVO();
		evo.setEmployeeId(Integer.parseInt(a));
		evo.setFirstName(b);
		evo.setLastName(c);
		evo.setEmail(d);
		evo.setJobId(e);
		
		SqlSession session = DataSource.getInstance().openSession(true);
		EmpMapper mapper = session.getMapper(EmpMapper.class);
		if(mapper.insertEmp(evo) == 1) {
			resp.getWriter().print("OK");
		} else {
			resp.getWriter().print("Fail");
		}
	}

}
