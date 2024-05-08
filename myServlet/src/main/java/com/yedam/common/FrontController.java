package com.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.web.ABCControl;
import com.yedam.web.InfoControl;
import com.yedam.web.RegisterControl;
import com.yedam.web.addEmpControl;

public class FrontController extends HttpServlet {
	
	Map<String, Control> map;
	
	// 생성자
	public FrontController() {
		map = new HashMap<>();
	}
	
	// init
	@Override
	public void init(ServletConfig config) throws ServletException {
		// url 패턴과 실행할 Control 인터페이스의 구현클래스 정의
		map.put("/abc.do", new ABCControl());
		map.put("/info.do", new InfoControl());
		map.put("/addEmp.do", new addEmpControl());
		map.put("/registerEmp.do", new RegisterControl());
	}
	
	// service
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8"); // 요청 정보의 한글 처리
		// http://localhost:8080/myServlet/abc.do
		String uri = req.getRequestURI(); // /myServlet/abc.do (서버와 포트 정보가 빠진 부분)
		String context = req.getContextPath(); // /myServlet (프로젝트 이름)
		System.out.println("uri: " + uri + ", context: " + context);
		String path = uri.substring(context.length()); // /abc.do (요청한 페이지 정보)
		System.out.println("path: " + path);
		
		Control control = map.get(path);
		control.exec(req, resp);
	}
	
	// destroy
	@Override
	public void destroy() {
		
	}
}
