package com.yedam.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class RemoveControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// bno 파라미터	
		String bno = req.getParameter("bno");
		
		BoardService svc = new BoardServiceImpl();

		if(svc.removeBoard(Integer.parseInt(bno))) {
			resp.sendRedirect("main.do");
		} else {
			System.out.println("처리 중 에러");
		}

	}

}
