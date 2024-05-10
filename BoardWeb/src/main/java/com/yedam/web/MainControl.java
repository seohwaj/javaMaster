package com.yedam.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.common.PageDTO;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class MainControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// main.do -> WEB-INF/board/boardList.jsp
		String path = "WEB-INF/board/boardList.jsp";
		
		String page = req.getParameter("page");
		page = page == null ? "1" : page; // page 파라미터가 없을 경우에는 1페이지 출력
		
		BoardService svc = new BoardServiceImpl();
		
		List<BoardVO> list = svc.boardList(Integer.parseInt(page)); // 목록
		PageDTO pageDTO = new PageDTO(Integer.parseInt(page), svc.getTotal()); // 건수
		
		// jsp 페이지에 정보 전달
		req.setAttribute("boardList", list);
		req.setAttribute("paging", pageDTO);
		
		req.getRequestDispatcher(path).forward(req, resp);
	}

}
