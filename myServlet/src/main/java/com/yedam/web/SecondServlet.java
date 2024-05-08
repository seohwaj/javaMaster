package com.yedam.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/second.action")
public class SecondServlet extends HttpServlet {
	// 생명주기
	// init -> service -> destroy
	public SecondServlet() {
		System.out.println("생성자 호출됨 :: 인스턴스 생성");
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 호출됨 :: 서버 실행 후 최초 한번만 호출");
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service 호출됨 :: 서블릿이 실행될 때마다 호출");
	}
	@Override
	public void destroy() {
		System.out.println("destroy 호출됨 :: 인스턴스가 메모리에서 사라질 때 호출");
	}
}
