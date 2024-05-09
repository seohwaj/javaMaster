package com.yedam.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class BoardInfoControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bno = req.getParameter("bno");
		
		BoardService svc = new BoardServiceImpl();
		svc.addViewCnt(Integer.parseInt(bno)); // 조회수 증가
		BoardVO vo = svc.getBoard(Integer.parseInt(bno)); // 조회기능
		
		req.setAttribute("result", vo);
		
		String path = "WEB-INF/board/board.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}

}
