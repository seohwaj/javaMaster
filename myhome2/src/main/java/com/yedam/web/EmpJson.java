package com.yedam.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.dao.EmpDAO;
import com.yedam.vo.EmpVO;

@WebServlet("/empsave.json")
public class EmpJson extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 추가(add), 수정(edit), 삭제(delete)
		// 삭제기능(사원번호 emp.html에서 파라미터 수신)
		String job = req.getParameter("job");
		String eno = req.getParameter("empNo");
		String salary = req.getParameter("salary");
		String email = req.getParameter("email");
		
		EmpDAO edao = new EmpDAO();
		EmpVO evo = new EmpVO();
		
		Map<String, Object> map = new HashMap<>();
		Gson gson = new GsonBuilder().create();
		
		if(job.equals("add")) {
			String name = req.getParameter("name");
			String phone = req.getParameter("phone");
			String hire = req.getParameter("hire");
			
			evo.setEmail(email);
			evo.setEmpName(name);
			evo.setEmpPhone(phone);
			evo.setHireDate(hire);
			evo.setSalary(Integer.parseInt(salary));
						
			if(edao.insertEmp(evo)) {
				map.put("retCode", "OK");
				map.put("retVal", evo);
			} else {
				map.put("retCode", "NG");
				map.put("retVal", null);				
			}
			resp.getWriter().print(gson.toJson(map));
			
		} else if(job.equals("edit")) {
			evo.setEmpNo(Integer.parseInt(eno));
			evo.setEmail(email);
			evo.setSalary(Integer.parseInt(salary));
			
			if(edao.updateEmp(evo)) {
				evo = edao.selectEmp(evo.getEmpNo()); // empName 가져오기
				
				map.put("retCode", "OK");
				map.put("retVal", evo);				
			} else {
				map.put("retCode", "NG");
				map.put("retVal", null);
			}
			resp.getWriter().print(gson.toJson(map));
			
		} else if(job.equals("delete")) {
			if(edao.deleteEmp(Integer.parseInt(eno))) {
				// {"retCode": "OK"}
				resp.getWriter().print("{\"retCode\": \"OK\"}");
			} else {
				// {"retCode": "NG"}
				resp.getWriter().print("{\"retCode\": \"NG\"}");
			}			
		} // end of if
		
	}
}
