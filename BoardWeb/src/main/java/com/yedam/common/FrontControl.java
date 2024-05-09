package com.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.web.AddBoardControl;
import com.yedam.web.AddFormControl;
import com.yedam.web.BoardInfoControl;
import com.yedam.web.MainControl;
import com.yedam.web.ModifyControl;
import com.yedam.web.ModifyFormControl;
import com.yedam.web.RemoveControl;
import com.yedam.web.RemoveFormControl;

public class FrontControl extends HttpServlet {
	Map<String, Control> map;
	
	// 생성자
	public FrontControl() {
		map = new HashMap<>();
	}
	
	// init
	@Override
	public void init(ServletConfig config) throws ServletException {
		map.put("/main.do", new MainControl());
		map.put("/addForm.do", new AddFormControl());
		map.put("/addBoard.do", new AddBoardControl());
		map.put("/boardInfo.do", new BoardInfoControl());
		map.put("/modBoardForm.do", new ModifyFormControl());
		map.put("/updateBoard.do", new ModifyControl());
		map.put("/removeBoardForm.do", new RemoveFormControl());
		map.put("/deleteBoard.do", new RemoveControl());
	}
	
	// service
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uri = req.getRequestURI();
		String context = req.getContextPath();
		String path = uri.substring(context.length());
		
		Control control = map.get(path);
		control.exec(req, resp);
	}
	
	// destroy
	private void destory() {
		
	}
}
